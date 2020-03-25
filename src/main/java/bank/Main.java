package bank;

import java.sql.*;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {

        Class.forName("org.h2.Driver");
        Menu.runMenu();

    }
}
