package org.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        System.out.printf("Hello and welcome!");

        for (int i = 1; i <= 5; i++) {
            //TIP Press <shortcut actionId="Debug"/> to start debugging your code. We have set one <icon src="AllIcons.Debugger.Db_set_breakpoint"/> breakpoint
            // for you, but you can always add more by pressing <shortcut actionId="ToggleLineBreakpoint"/>.
            System.out.println("i = " + i);
        }
    }
}

//        insert into eshop.userinfo(ID,username,email,password,account) values(1,"shalvi","shalvi@gmail.com",1234,"b");
//        public static void newUser(Connection conn) {
//            try {
//                Scanner sc = new Scanner(System.in);
//                String query = "INSERT INTO userinfo (ID, username, email, password, account) VALUES (?, ?, ?, ?, ?)";
//                PreparedStatement ps = conn.prepareStatement(query);
//                System.out.print("Enter your id: ");
//                int id = sc.nextInt();
//                ps.setInt(1, id);
//                System.out.print("Enter your Name: ");
//                sc.nextLine();
//                String name = sc.nextLine();
//                ps.setString(2, name);
//                System.out.print("Enter your Email: ");
//                String email = sc.nextLine();
//                ps.setString(3, email);
//                System.out.print("Enter your password: ");
//                String password = sc.nextLine();
//                ps.setString(4, password);
//                System.out.print("Enter your account: ");
//                String account = sc.nextLine();
//                ps.setString(5,account);
//                ps.executeUpdate();
//                System.out.println("Successfully created Account");
//            } catch (SQLException e) {
//                e.printStackTrace();
//            } catch (IllegalArgumentException e) {
//                System.out.println("Invalid date format! Please use yyyy-mm-dd.");
//            }
//        }