package bank;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("org.h2.Driver");
        DbManager dbManager = new DbManager();
        List<Person> people = dbManager.getAllPeople();
        System.out.println(people.toString());
        List<Account> accounts = dbManager.getAllAccounts();
        System.out.println(accounts.toString());
        List<Bank> banks = dbManager.getAllBanks();
        System.out.println(banks.toString());
        List<Transaction> transactions = dbManager.getAllTransactions();
        System.out.println(transactions.toString());
        List<Withdraw> withdraws = dbManager.getAllWithdraws();
        System.out.println(withdraws.toString());
        List<Deposit> deposits = dbManager.getAllDeposits();
        System.out.println(deposits.toString());
        Menu.runMenu();
        dbManager.writeNewAccount();

    }
}
