package org.example;


import java.util.Scanner;

public class UserInterface extends GetConnection{
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
        request();
    }
public static void request(){
    Scanner sc=new Scanner(System.in);
    System.out.println("welcome to book shop..");
    System.out.println("1.New User");
    System.out.println("2.Exisisting User");
    System.out.print("Enter Your Choices:");
    int c=sc.nextInt();
    switch (c){
        case 1:{
            newUser(getConnection1());
        }
        case 2:{
            check(getConnection1());
        }
    }
    }
}
