package org.example;

import java.sql.*;
import java.util.Scanner;

public class GetConnection {
    public static Connection getConnection() {
        Connection con=null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver Loaded successfully...");
            con=DriverManager.getConnection("jdbc:mysql://localhost:3306/Eshop", "root", "SMrenish@123");
            System.out.println("Database Connected Successfully...");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
        return con;
    }
        public static Connection getConnection1() {
            Connection conn=null;
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                System.out.println("Driver Loaded successfully...");
                conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/Transcation", "root", "SMrenish@123");
                System.out.println("Database Connected Successfully...");
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
            return conn;
        }


    public static void selectQuery(Connection con) {

        try {
            String query = "select * from payment";
            PreparedStatement ps = con.prepareStatement(query);
            ResultSet res = ps.executeQuery();
            System.out.println("query executed");
            System.out.println("list of Books...");
            while (res.next()) {
                System.out.println(res.getInt(1) + " " + res.getString(2) + " " + res.getInt(3));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println();

    }

    public static void insertQuery(Connection con) {

        try {
            String query = "insert into employee values(?,?)";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, 4);
            ps.setString(2, "arun");
            ps.execute();
            System.out.println("inserted new row");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("query wrong");
        }
    }

    public static void updateQuery(Connection con,int id) {

        try {
            String query = "update payment set quantity=? where id=?";
            PreparedStatement ps = con.prepareStatement(query);
            int b=selectQuantity(con,id);
            int c=0;
            if (b>=1){
                c=b-1;
            }
            else {
                deleteQuery(con,id);
                System.out.println("Out of Stock");
            }
            ps.setInt(1,c);
            ps.setInt(2,id);
            ps.execute();
            System.out.println("query executed");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("update query wrong");
        }
    }

    public static void deleteQuery(Connection con,int id) {

        try {
            String query = "delete from payment where id=?";
            PreparedStatement ps = con.prepareStatement(query);
            ps.setInt(1, id);
            ps.execute();
            System.out.println("deleted successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void columnDetails(Connection con) {
        try {
            String query = "select * from employee";
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery(query);
            ResultSetMetaData metadata = res.getMetaData();
            int c = metadata.getColumnCount();
            System.out.println("column count: " + metadata.getColumnCount());
            for (int i = 1; i <= c; i++) {
                System.out.println(metadata.getColumnName(i) + " " + metadata.getColumnTypeName(i));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static int selectQuantity(Connection con, int id) {
        int quantity = 0;
        try {
            String query = "SELECT quantity FROM payment WHERE id = ?"; // Correct SQL query
            PreparedStatement ps = con.prepareStatement(query); // Prepare the query
            ps.setInt(1, id); // Set the parameter value for the first placeholder
            ResultSet res = ps.executeQuery(); // Execute the query (no arguments here)

            // Process the ResultSet
            while (res.next()) {
                quantity = res.getInt("quantity");
                return quantity;
//                System.out.println("Quantity: " + res.getInt("quantity")); // Retrieve the value of the column
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return quantity;
    }
    public static void newUser(Connection conn) {
        try {
            Scanner sc = new Scanner(System.in);
            String query = "INSERT INTO account (id, name, phone_no, email, dob, password, account) VALUES (?, ?, ?, ?, ? ,? ,?)";
            PreparedStatement ps = conn.prepareStatement(query);
            System.out.print("Enter your id: ");
            int id = sc.nextInt();
            ps.setInt(1, id);
            System.out.print("Enter your Name: ");
            sc.nextLine();
            String name = sc.nextLine();
            ps.setString(2, name);
            System.out.print("Enter your Number: ");
            String number = sc.nextLine();
            ps.setString(3, number);
            System.out.print("Enter your Email: ");
            String email = sc.nextLine();
            ps.setString(4, email);
            System.out.print("Enter your DOB (yyyy-mm-dd): ");
            String dobInput = sc.nextLine();
            java.sql.Date dob = java.sql.Date.valueOf(dobInput);
            ps.setDate(5, dob);
            System.out.print("Enter password:");
            String password=sc.nextLine().toLowerCase();
            System.out.print("Re-enter password:");
            String password1=sc.nextLine().toLowerCase();
            if (password.equals(password1)){
                ps.setString(6,password);
            }
            else {
                System.out.println("not matched password");
            }
            System.out.println("Account Type");
            System.out.println("1.Buyer");
            System.out.println("2.Seller");
            System.out.print("Enter your choice: ");
            int type =sc.nextInt();
            switch (type) {
                case 1: {
                    ps.setString(7, "Buyer");
                    break; // Prevents fall-through
                }
                case 2: {
                    ps.setString(7, "Seller");
                    break; // Prevents fall-through
                }
                default: {
                    // Optionally handle unexpected type values
                    ps.setString(7, "Unknown");
                    break;
                }
            }
            ps.executeUpdate();
            System.out.println("Successfully created Account");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid date format! Please use yyyy-mm-dd.");
        }
    }
    public static boolean info(Connection conn) {
        try {
            Scanner sc = new Scanner(System.in);
            String query = "SELECT id FROM account WHERE id = ? AND name = ? AND password = ?"; // Modified SQL query
            PreparedStatement ps = conn.prepareStatement(query);

            // Prompt the user for their ID, username, and password
            System.out.print("Enter your ID: ");
            int id = sc.nextInt(); // Read the ID from the user
            sc.nextLine(); // Consume the newline character
            System.out.print("Enter your username: ");
            String name = sc.nextLine(); // Read the username from the user
            System.out.print("Enter your password: ");
            String password = sc.nextLine(); // Read the password from the user

            // Set the parameter values for the placeholders
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, password);

            // Execute the query
            ResultSet res = ps.executeQuery();

            // Check if the ResultSet has any results
            if (res.next()) {
                return true; // ID, username, and password match a record
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false; // No match found or an error occurred
    }

    public static boolean info1(Connection conn) {
        try {
            Scanner sc = new Scanner(System.in);
            String query = "SELECT id FROM account WHERE id = ? AND name = ? AND password = ?"; // Modified SQL query
            PreparedStatement ps = conn.prepareStatement(query);

            // Prompt the user for their ID, username, and password
            System.out.print("Enter your ID: ");
            int id = sc.nextInt(); // Read the ID from the user
            sc.nextLine(); // Consume the newline character
            System.out.print("Enter your username: ");
            String name = sc.nextLine(); // Read the username from the user
            System.out.print("Enter your password: ");
            String password = sc.nextLine(); // Read the password from the user

            // Set the parameter values for the placeholders
            ps.setInt(1, id);
            ps.setString(2, name);
            ps.setString(3, password);

            // Execute the query
            ResultSet res = ps.executeQuery();

            // Check if the ResultSet has any results
            if (res.next()) {
                return true; // ID, username, and password match a record
            }
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false; // No match found or an error occurred
    }



    public static void order(Connection conn,int id){
        String query="select * from account where id=?";
        try (PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet res = ps.executeQuery()) {
                if (!res.isBeforeFirst()) {
                    System.out.println("No account found for the given ID.");
                    return;
                }
                while (res.next()) {
                    System.out.println("Account Details:");
                    System.out.println("ID: " + res.getInt("id"));
                    System.out.println("Name: " + res.getString("name"));
                    System.out.println("Phone No: " + res.getString("phone_no"));
                    System.out.println("Email: " + res.getString("email"));
                    System.out.println("Account Type: " + res.getString("account"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void orderUpdate(Connection conn,int id) {
        try {
            Scanner sc = new Scanner(System.in);

            // Insert Order
            String query = "INSERT INTO orders (customer_id, customer_name, book_name, no_of_quantity, payment_type) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = conn.prepareStatement(query);

            //id
            ps.setInt(1, id);

            sc.nextLine(); // Consume leftover newline
            System.out.print("Your Name: ");
            String name = sc.nextLine();
            ps.setString(2, name);

            System.out.print("Book Name: ");
            String book_name = sc.nextLine();
            ps.setString(3, book_name);

            System.out.print("No_of_Quantity_Required: ");
            int quantity = sc.nextInt();
            ps.setInt(4, quantity);

            System.out.println("Payment Types ");
            System.out.println("1. Credit Card");
            System.out.println("2. Net Banking");
            System.out.println("3. Cash on Delivery");
            System.out.print("Choose Yours: ");
            int type = sc.nextInt();
            switch (type) {
                case 1 -> ps.setString(5, "Credit Card");
                case 2 -> ps.setString(5, "Net Banking");
                case 3 -> ps.setString(5, "Cash on Delivery");
                default -> {
                    System.out.println("Invalid payment type. Defaulting to 'Cash on Delivery'.");
                    ps.setString(5, "Cash on Delivery");
                }
            }

            ps.executeUpdate();
            System.out.println("ORDER PLACED SUCCESSFULLY...");

            // Retrieve Customer ID and Reference ID
            try {
                String query1 = "SELECT customer_id, reference_id FROM orders WHERE customer_id = ?";
                PreparedStatement ps1 = conn.prepareStatement(query1);
                ps1.setInt(1, id); // Set the parameter correctly

                ResultSet res = ps1.executeQuery();
                while (res.next()) {
                    System.out.println("Customer ID: " + res.getInt("customer_id") + " \nReference ID: " + res.getInt("reference_id"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void seller(Connection con,Connection conn){

    }


    public static void check(Connection conn) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your id: " );
        int id=sc.nextInt();

        // Use the updated `info` function
        if (info(conn)) {
            System.out.println("Yes, ID exists.");
            order(getConnection1(),id);
        } else {
            System.out.println("ID not found. Exiting...");
            return; // Exit the method if the ID is not found
        }

        boolean isChoosing = true;
        while (isChoosing) {
            // Display the available options
            selectQuery(getConnection()); // Use the passed connection instead of `getConnection`

            System.out.println("1. Buyer");
            System.out.println("2. Seller");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            switch (choice) {
                case 1 -> {
                    selectQuery(getConnection()); // Use the same connection
                    System.out.println("Which book would you like to buy?");
                    System.out.print("Enter the choice: ");
                    int input = sc.nextInt();
                    orderUpdate(getConnection1(),id);
                    updateQuery(getConnection(), input); // Update the record using the passed connection
                }
                case 2 -> {
                    System.out.println("Sorry, we don't provide this service...");
                }
                default -> {
                    System.out.println("Invalid choice. Please try again.");
                }
            }

            System.out.print("\nDo you want to continue with Buy/Sell? (yes/no): ");
            sc.nextLine(); // Clear the input buffer after reading an integer
            String continueChoice = sc.nextLine().trim().toLowerCase();
            if (continueChoice.equals("no")) {
                System.out.println("Thank you...");
                isChoosing = false; // Break the loop
            }
        }
    }
}