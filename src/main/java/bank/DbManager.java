package bank;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DbManager {

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

                Account account = new Account(accountNr, personId, bankId, accountType);
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
                int transactionNr = resultSet.getInt("PAVEDIMO_NR");
                String accountNrfrom = resultSet.getString("SASKAITOS_NR_FROM");
                String accountNrto = resultSet.getString("SASKAITOS_NR_TO");
                Date date = resultSet.getDate("DATA");
                double sum = resultSet.getDouble("SUMA");

                Transaction transaction = new Transaction(transactionNr, accountNrfrom, accountNrto, date, sum);
                transactionList.add(transaction);
            }
        } catch (SQLException e) {
            System.out.println("Unable to connect to data base");
            e.printStackTrace();
        }
        return transactionList;
    }

    public void writeNewAccount(){
        try {
            Connection connection = createConnection();
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO PRISIJUNGIMAI(PRISIJUNGIMO_VARDAS, SLAPTAZODIS, ASMENS_KODAS) " +
                    "VALUES ('kestone', 'kestone123', 37009090909");
            ResultSet resultSet = statement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}



