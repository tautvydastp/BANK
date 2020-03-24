package bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static void runMenu() {
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        while (!choice.equals("3")) {
            mainMenu();
            choice = scanner.next();
            if ("1".equals(choice)) {
                logIn(scanner);
                String userChoice = "";
                while (!userChoice.equals("6")) {
                    userMenu();
                    userChoice = scanner.next();
                    if ("1".equals(userChoice)) {
                        Account account = new Account();
                        System.out.println(account.toString());
                    } else if("2".equals(userChoice)){

                    }
                }
            } else if ("2".equals(choice)) {
                registration(scanner);
            } else {
                System.out.println("Bloga ivestis");
            }
        }
    }

    private static void logIn(Scanner scanner) {
        DbManager dbManager = new DbManager();
        System.out.println("---------------Prisijungimas------------");
        System.out.println("Įveskite vartotojo vardą");
        String username = scanner.next();
        System.out.println("Įveskite slaptažodį");
        String password = scanner.next();
        if (dbManager.logIn(username, password)) {
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
