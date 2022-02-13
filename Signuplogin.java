package Carpooling;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Signuplogin {
    static Connection connection = Database.connection;
    static Scanner sc = new Scanner(System.in);
    int log() throws SQLException {
        System.out.println("Enter 1 for signup or 2 for login...");
        System.out.print("Please provide your input here:");
        int login = sc.nextInt();
        if(login == 1){
            System.out.print("Please enter a username:");
            String username = sc.next();
            if(Database.checkuser(username)>0) {
                System.out.println("Username taken!!");
            }
            else{
                System.out.print("Please enter a emailId:");
                String email = sc.next();
                if(Database.checkemail(email)>0){
                    System.out.println("Email was already used please login!!");
                }
                else{
                    System.out.print("Please enter a password:");
                    String password = sc.next();
                    Database.createuser(username,email,password);
                    System.out.println("User creation successful please login!!!");
                    return log();
                }
            }

        }
        else if(login == 2){
            System.out.print("Please enter a username:");
            String username = sc.next();
            System.out.print("Please enter password:");
            String password = sc.next();
            int id = Database.checkpassword(username,password);
            return id;
        }
        return 0;
    }
}
