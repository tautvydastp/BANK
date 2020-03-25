package bank;

import java.sql.ResultSet;
import java.util.List;

public interface DbManagerInterface {

     List<Person> getAllPeople();

     List<Account> getAllAccounts();

     List<Bank> getAllBanks();

     List<Deposit> getAllDeposits();

     List<Withdraw> getAllWithdraws();

     List<Transaction> getAllTransactions();

     List<PersonInfo> getInformation(String username, long persId);

     void insertNewAccountLogIn(String username, String password, long personId);

     void insertNewPerson(String name, String lastname, long personId);

     void insertPersonBank(String bankName);

     boolean logIn(String username, String password, long personId);

     List<Transaction> transactions();

     void makeTransaction(Transaction transaction);

     void addDeposit(String accNr, double sum);

     void withdrawMoney(String accNr, double sum);

}
