package bank;

import com.sun.org.apache.xerces.internal.impl.dv.DVFactoryException;
import com.sun.xml.internal.ws.policy.EffectiveAlternativeSelector;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void runMenu() {
        DbManager dbManager = new DbManager();
        Scanner scanner = new Scanner(System.in);
        AccLogIn accLogIn = new AccLogIn();
        String choice = "";
        while (!choice.equals("3")) {
            mainMenu();
            choice = scanner.next();
            if ("1".equals(choice)) {
                logIn(scanner, accLogIn);
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
                            deposit(scanner);
                            dbManager.addDeposit();
                        } else if ("4".equals(userChoice)) {
                            withdraw(scanner);
                            dbManager.withdrawMoney();
                        } else if ("5".equals(userChoice)) {
                            transaction(scanner);
                            dbManager.makeTransaction();
                        } else System.out.println("bloga ivestis");
                }
            } else if ("2".equals(choice)) {
                registration(scanner);
            } else {
                System.out.println("Bloga ivestis");
            }
        }
    }

    private static void deposit(Scanner scanner) {
        Deposit deposit = new Deposit();
        System.out.println("Įveskite sąskaitos numerį, į kurią norite įdėti pinigus: ");
        String accNr = scanner.next();
        deposit.setAccountNr(accNr);
        System.out.print("Iveskite įnešamą sumą: ");
        double sum = scanner.nextDouble();
    }

    private static void withdraw(Scanner scanner) {
        Withdraw withdraw = new Withdraw();
        System.out.println("Iveskite sąskaitos numerį, iš kurios norite išimti pinigus: ");
        String accNr = scanner.next();
        withdraw.setAccountNr(accNr);
        System.out.print("Iveskite sumą, kurią norite išimti: ");
        double sum = scanner.nextDouble();
        withdraw.setSum(sum);
    }

    private static void transaction(Scanner scanner) {
        Transaction transaction = new Transaction();
        System.out.println("Įvekite sąskaitos numerį, iš kurios norite pervesti pinigus:");
        String accNrFrom = scanner.next();
        transaction.setAccountNrFrom(accNrFrom);
        System.out.print("Iveskite sumą, kurią norite pervesti: ");
        double sum = scanner.nextDouble();
        transaction.setSum(sum);
        System.out.println("Iveskite sąskaitos numerį, į kurią norite pervesti pinigus");
        String accNrTo = scanner.next();
        transaction.setAccountNrTo(accNrTo);
    }

    private static void logIn(Scanner scanner, AccLogIn accLogIn) {
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
        } else {
            System.out.println("Nepavyko prisijungti");
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
