package bank;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DbManager implements DbManagerInterface {

    private Connection createConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test", "sa", "");
    }

    public List<Person> getAllPeople() {
        List<Person> personList = new ArrayList<Person>();
        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ASMUO");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                long personId = resultSet.getLong("ASMENS_KODAS");
                String name = resultSet.getString("VARDAS");
                String lastname = resultSet.getString("PAVARDE");

                Person person = new Person(personId, name, lastname);
                personList.add(person);
            }
        } catch (SQLException e) {
            System.out.println("Unable to connect to data base");
            e.printStackTrace();
        }
        return personList;
    }

    public List<Account> getAllAccounts() {
        List<Account> accountList = new ArrayList<Account>();
        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM SASKAITA");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String accountNr = resultSet.getString("SASKAITOS_NR");
                String personId = resultSet.getString("ASMENS_KODAS");
                int bankId = resultSet.getInt("BANK_ID");
                String accountType = resultSet.getString("SASKAITOS_TIPAS");
                double balance = resultSet.getDouble("BALANSAS");

                Account account = new Account(accountNr, personId, bankId, accountType, balance);
                accountList.add(account);
            }
        } catch (SQLException e) {
            System.out.println("Unable to connect to data base");
            e.printStackTrace();
        }
        return accountList;
    }

    public List<Bank> getAllBanks() {
        List<Bank> bankList = new ArrayList<Bank>();
        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM BANKAS");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int bankId = resultSet.getInt("BANK_ID");
                String bankName = resultSet.getString("PAVADINIMAS");

                Bank bank = new Bank(bankId, bankName);
                bankList.add(bank);
            }
        } catch (SQLException e) {
            System.out.println("Unable to connect to data base");
            e.printStackTrace();
        }
        return bankList;
    }

    public List<Deposit> getAllDeposits() {
        List<Deposit> depositList = new ArrayList<Deposit>();
        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM INESIMAS");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String accountNr = resultSet.getString("SASKAITOS_NR");
                Date date = resultSet.getDate("DATA");
                double sum = resultSet.getDouble("SUMA");

                Deposit deposit = new Deposit(accountNr, date, sum);
                depositList.add(deposit);
            }
        } catch (SQLException e) {
            System.out.println("Unable to connect to data base");
            e.printStackTrace();
        }
        return depositList;
    }

    public List<Withdraw> getAllWithdraws() {
        List<Withdraw> withdrawList = new ArrayList<Withdraw>();
        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM ISEMIMAS");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String accountNr = resultSet.getString("SASKAITOS_NR");
                Date date = resultSet.getDate("DATA");
                double sum = resultSet.getDouble("SUMA");

                Withdraw withdraw = new Withdraw(accountNr, date, sum);
                withdrawList.add(withdraw);
            }
        } catch (SQLException e) {
            System.out.println("Unable to connect to data base");
            e.printStackTrace();
        }
        return withdrawList;
    }

    public List<Transaction> getAllTransactions() {
        List<Transaction> transactionList = new ArrayList<Transaction>();
        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM PAVEDIMAI");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String accountNrfrom = resultSet.getString("SASKAITOS_NR_FROM");
                String accountNrto = resultSet.getString("SASKAITOS_NR_TO");
                Date date = resultSet.getDate("DATA");
                double sum = resultSet.getDouble("SUMA");

                Transaction transaction = new Transaction(accountNrfrom, accountNrto, date, sum);
                transactionList.add(transaction);
            }
        } catch (SQLException e) {
            System.out.println("Unable to connect to data base");
            e.printStackTrace();
        }
        return transactionList;
    }


    public List<PersonInfo> getInformation(String username, long persId) {
        List<PersonInfo> information = new ArrayList<PersonInfo>();
        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement("SELECT ASMUO.ASMENS_KODAS, " +
                    "VARDAS, PAVARDE, SASKAITA.SASKAITOS_NR, SASKAITOS_TIPAS, BALANSAS " +
                    "FROM ASMUO JOIN SASKAITA ON ASMUO.ASMENS_KODAS = (SELECT PRISIJUNGIMAI.ASMENS_KODAS " +
                    "FROM PRISIJUNGIMAI " +
                    "WHERE PRISIJUNGIMO_VARDAS = ?) WHERE SASKAITA.SASKAITOS_NR = " +
                    "(SELECT SASKAITA.SASKAITOS_NR FROM SASKAITA " +
                    "WHERE SASKAITA.ASMENS_KODAS =?) ");

            statement.setString(1, username);
            statement.setLong(2, persId);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                long personId = resultSet.getLong("ASMENS_KODAS");
                String name = resultSet.getString("VARDAS");
                String lastname = resultSet.getString("PAVARDE");
                String accNr = resultSet.getString("SASKAITOS_NR");
                String accType = resultSet.getString("SASKAITOS_TIPAS");
                double balance = resultSet.getDouble("BALANSAS");
                PersonInfo info = new PersonInfo(personId, name, lastname, accNr, accType, balance);
                information.add(info);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return information;
    }

    public void insertNewAccountLogIn(String username, String password, long personId) {
        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO PRISIJUNGIMAI(PRISIJUNGIMO_VARDAS, SLAPTAZODIS, ASMENS_KODAS) " +
                            "VALUES (?,?,?)");
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setLong(3, personId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertNewPerson(String name, String lastname, long personId) {
        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO ASMUO(VARDAS,PAVARDE, ASMENS_KODAS) " +
                            "VALUES (?,?,?)");
            statement.setString(1, name);
            statement.setString(2, lastname);
            statement.setLong(3, personId);
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertPersonBank(String bankName) {
        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO BANKAS(PAVADINIMAS) " +
                            "VALUES (?)");
            statement.setString(1, bankName);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean logIn(String username, String password, long personId) {
        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT PRISIJUNGIMO_VARDAS, SLAPTAZODIS, PRISIJUNGIMAI.ASMENS_KODAS" +
                            " FROM PRISIJUNGIMAI" +
                            " WHERE PRISIJUNGIMO_VARDAS = ? AND SLAPTAZODIS = ?" +
                            "AND PRISIJUNGIMAI.ASMENS_KODAS = ?"
            );
            statement.setString(1, username);
            statement.setString(2, password);
            statement.setLong(3, personId);

            ResultSet resultSet = statement.executeQuery();
            return resultSet.next();

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


    }

    public List<Transaction> transactions() {

        List<Transaction> transactions = new ArrayList<Transaction>();

        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "SELECT PAVEDIMO_NR, SASKAITOS_NR_FROM, SASKAITOS_NR_TO, DATA, SUMA " +
                            "FROM PAVEDIMAI JOIN SASKAITA " +
                            "ON SASKAITOS_NR = PAVEDIMAI.SASKAITOS_NR_FROM AND SASKAITOS_NR = PAVEDIMAI.SASKAITOS_NR_TO");
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String accFrom = resultSet.getString("SASKAITOS_NR_FROM");
                String accTo = resultSet.getString("SASKAITOS_NR_TO");
                Date date = resultSet.getDate("DATA");
                double sum = resultSet.getDouble("SUMA");
                Transaction transaction = new Transaction(accFrom, accTo, date, sum);
                transactions.add(transaction);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return transactions;
    }

    public void addDeposit() {
        Deposit deposit = new Deposit();
        try {
            Connection connection = createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT BALANSAS FROM SASKAITA" +
                    " WHERE SASKAITOS_NR = ?");
            ResultSet rs = preparedStatement.executeQuery();
            double rsSum = rs.getDouble("BALANSAS");
            PreparedStatement statement = connection.prepareStatement("UPDATE SASKAITA" +
                    " SET BALANSAS = ? WHERE SASKAITOS_NR = ?");

            statement.setDouble(1, deposit.getSum() + rsSum);
            statement.setString(2, deposit.getAccountNr());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void withdrawMoney() {
        Withdraw withdraw = new Withdraw();
        try {
            Connection connection = createConnection();
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT BALANSAS FROM SASKAITA " +
                    "WHERE SASKAITOS_NR = ?");
            ResultSet rs = preparedStatement.executeQuery();
            double rsSum = rs.getDouble("BALANSAS");
            PreparedStatement statement = connection.prepareStatement("UPDATE SASKAITA SET BALANSAS = ?" +
                    " WHERE SASKAITOS_NR = ?");
            statement.setDouble(1, rsSum - withdraw.getSum());
            statement.setString(2, withdraw.getAccountNr());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void makeTransaction() {
        Transaction transaction = new Transaction();
        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO PAVEDIMAI(SASKAITOS_NR_FROM, SASKAITOS_NR_TO, DATA, SUMA) VALUES (?,?,?,?)");
            statement.setString(1, transaction.getAccountNrFrom());
            statement.setString(2, transaction.getAccountNrTo());
            statement.setDate(3, new java.sql.Date(transaction.getDate().getTime()));
            statement.setDouble(4, transaction.getSum());
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}




