package bank;

import java.sql.DriverManager;
import java.util.Scanner;

public class Menu {
    public static void runMenu() {
        Scanner scanner = new Scanner(System.in);
        String choice = "";
        while (!choice.equals("8")) {
            mainMenu();
            choice = scanner.next();
            if ("1".equals(choice)) {
                logIn(scanner);
            } else if ("2".equals(choice)) {
                registration(scanner);
            } else if ("3".equals(choice)) {
            } else if ("4".equals(choice)) {
            } else if ("5".equals(choice)) {
            } else if ("6".equals(choice)) {
            } else if ("7".equals(choice)) {
            } else if ("8".equals(choice)) {
            } else {
                System.out.println("Blogai ivestas pasirinkimas");
            }
        }
    }

    private static void logIn(Scanner scanner) {
        System.out.println("---------------Prisijungimas------------");
        System.out.println("Įveskite vartotojo vardą");
        String username = scanner.next();
        System.out.println("Įveskite slaptažodį");
        String password = scanner.next();
    }

    private static void registration(Scanner scanner) {
        NewAcc newAcc = new NewAcc();
        System.out.println("---------------Registracija------------");
        System.out.println("Įveskite vartotojo vardą");
        String newUserName = scanner.next();
        System.out.println("Įveskite slaptažodį");
        String newPassword = scanner.next();
//        System.out.println("Įveskite vardą");
//        String newName = scanner.next();
//        System.out.println("Įveskite pavardę");
//        String newLastname = scanner.next();
        System.out.println("Įveskite asmens kodą");
        long newPersonId = scanner.nextLong();
        newAcc.setPersonId(newPersonId);
        DbManager dbManager = new DbManager();
        dbManager.insertNewAccount(newUserName, newPassword, newPersonId);

    }


    public static void mainMenu() {
        System.out.println("[1] Prisijungti" +
                "\n[2] Užsiregistruoti" +
                "\n[3] Peržiūrėti savo balansą" +
                "\n[4] Tranzakcijų istorija" +
                "\n[5] Įnešti pinigų" +
                "\n[6] Išimti pinigų" +
                "\n[7] Atlikti pavedimą" +
                "\n[8] Atsijungti");
        System.out.println("\nIveskite pasirinkimą: ");
    }
}
