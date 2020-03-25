package bank;

import com.sun.org.apache.xerces.internal.impl.dv.DVFactoryException;
import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    static DbManager dbManager = new DbManager();
    static Scanner scanner = new Scanner(System.in);
   static AccLogIn accLogIn = new AccLogIn();
    static Deposit deposit = new Deposit();
    static Withdraw withdraw = new Withdraw();
    static Transaction transaction = new Transaction();
    static Account account = new Account();

    public static void runMenu() {
        String choice = "";
        while (!choice.equals("3")) {
            mainMenu();
            choice = scanner.next();
            if ("1".equals(choice)) {
                if (logIn(scanner, accLogIn) == true) {
                    String userChoice = "";
                    while (!userChoice.equals("6")) {
                        userMenu();
                        userChoice = scanner.next();
                        if ("1".equals(userChoice)) {
                            List<PersonInfo> personInfo = dbManager.getInformation(accLogIn.getUsername(), accLogIn.getPersonId());
                            System.out.println(personInfo.toString());
                        } else if ("2".equals(userChoice)) {
                            dbManager.transactions();
                        } else if ("3".equals(userChoice)) {
                            deposit();
                        } else if ("4".equals(userChoice)) {
                            withdraw();
                        } else if ("5".equals(userChoice)) {
                            transaction();
                            dbManager.makeTransaction(transaction);
                        } else System.out.println("bloga ivestis");
                    }
                } else choice = "x";
            } else if ("2".equals(choice)) {
                registration(scanner);
            } else {
                System.out.println("Bloga ivestis");
            }
        }
    }

    private static void deposit() {
        System.out.println("Įveskite sąskaitos numerį, į kurią norite įdėti pinigus: ");
        String accNr = scanner.next();
        account.setAccountNr(accNr);
        System.out.print("Iveskite įnešamą sumą: ");
        double sum = scanner.nextDouble();
        account.setBalance(account.getBalance() + sum);
        dbManager.addDeposit(account.getAccountNr() , account.getBalance());
    }

    private static void withdraw() {
        System.out.println("Iveskite sąskaitos numerį, iš kurios norite išimti pinigus: ");
        String accNr = scanner.next();
        account.setAccountNr(accNr);
        System.out.print("Iveskite sumą, kurią norite išimti: ");
        double sum = scanner.nextDouble();
        account.setBalance(account.getBalance() - sum);
        dbManager.withdrawMoney(account.getAccountNr(), account.getBalance());
    }

    private static void transaction() {
        System.out.println("Įvekite sąskaitos numerį, iš kurios norite pervesti pinigus:");
        String accNrFrom = scanner.next();
        transaction.setAccountNrFrom(accNrFrom);
        System.out.print("Iveskite sumą, kurią norite pervesti: ");
        double sum = scanner.nextDouble();
        account.setBalance(account.getBalance() - sum);
        System.out.println("Iveskite sąskaitos numerį, į kurią norite pervesti pinigus");
        String accNrTo = scanner.next();
        transaction.setAccountNrTo(accNrTo);

    }

    private static boolean logIn(Scanner scanner, AccLogIn accLogIn) {
        DbManager dbManager = new DbManager();
        System.out.println("---------------Prisijungimas------------");
        System.out.println("Įveskite vartotojo vardą");
        String username = scanner.next();
        accLogIn.setUsername(username);
        System.out.println("Įveskite slaptažodį");
        String password = scanner.next();
        System.out.println("Įveskite asmens kodą");
        long personId = scanner.nextLong();
        accLogIn.setPersonId(personId);
        if (dbManager.logIn(username, password, personId)) {
            System.out.println("Prisijungti pavyko");
            return true;
        } else {
            System.out.println("Nepavyko prisijungti");
            return false;
        }

    }

    private static void registration(Scanner scanner) {
        System.out.println("---------------Registracija------------");
        System.out.println("Įveskite vartotojo vardą");
        String newUserName = scanner.next();
        System.out.println("Įveskite slaptažodį");
        String newPassword = scanner.next();
        System.out.println("Įveskite vardą");
        String newName = scanner.next();
        System.out.println("Įveskite pavardę");
        String newLastname = scanner.next();
        System.out.println("Įveskite asmens kodą");
        long newPersonId = scanner.nextLong();
        System.out.println("Iveskite savo banko pavadinimą");
        String newBankName = scanner.next();
        DbManager dbManager = new DbManager();
        dbManager.insertNewPerson(newName, newLastname, newPersonId);
        dbManager.insertNewAccountLogIn(newUserName, newPassword, newPersonId);
        dbManager.insertPersonBank(newBankName);
    }


    public static void mainMenu() {
        System.out.println("[1] Prisijungti" +
                "\n[2] Užsiregistruoti" +
                "\n[3] Atsijungti");
        System.out.println("\nIveskite pasirinkimą: ");
    }

    public static void userMenu() {
        System.out.println("\n[1] Peržiūrėti savo kortelės informaciją ir jos balansą" +
                "\n[2] Tranzakcijų istorija" +
                "\n[3] Įnešti pinigų" +
                "\n[4] Išimti pinigų" +
                "\n[5] Atlikti pavedimą" +
                "\n[6] Atsijungti");
    }

}
