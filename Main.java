package Carpooling;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static  Scanner sc = new Scanner(System.in);
    static Signuplogin login = new Signuplogin();
    public static void ui(int id) throws SQLException {
        int op = -1;
        int rideID;
        String source;
        String destination;
        int fare;
        int result;
        User user = new User();
        Admin admin = new Admin();

        while(op !=0) {
            System.out.printf("%-35s", "Enter 1 to Create Ride");
            System.out.println("Enter 6 to see available rides");
            System.out.printf("%-35s", "Enter 2 to update Ride");
            System.out.println("Enter 7 to find a Ride for you");
            System.out.printf("%-35s", "Enter 3 to see your Rides");
            System.out.println("Enter 8 to see cancel your booking");
            System.out.printf("%-35s", "Enter 4 to delete Ride");
            System.out.println("Enter 9 to Change password");
            System.out.printf("%-35s", "Enter 5 to book Ride");
            System.out.println("Enter 0 to Logout");
            if(id == 1){
                System.out.printf("%-35s","Enter 11 to display all Rides");
                System.out.println("Enter 14 to see display all cities");
                System.out.printf("%-35s", "Enter 12 to total fare");
                System.out.println("Enter 15 to diaplay Rides from...");
                System.out.printf("%-35s", "EEnter 13 to see all users");
                System.out.println("Enter 16 to display Rides to... ");
            }
            System.out.print("Please provide your input here:");
            op = sc.nextInt();
            switch (op) {
                case 0:
                    System.out.println("Logged out successfully!!");
                    break;
                case 1:
                    System.out.print("Enter source:");
                    source = sc.next();
                    System.out.print("Enter destination:");
                    destination = sc.next();
                    System.out.print("Enter fare:");
                    fare = sc.nextInt();
                    Database.createRide(source,destination,fare,id);
                    break;
                case 2:
                    System.out.print("Enter ride ID:");
                    rideID = sc.nextInt();
                    System.out.print("Enter source:");
                    source = sc.next();
                    System.out.print("Enter destination:");
                    destination = sc.next();
                    System.out.print("Enter fare:");
                    fare = sc.nextInt();
                    Database.updateRide(rideID,source,destination,fare,id);
                    break;
                case 3:
                    user.yourRides(id);
                    break;
                case 4:
                    System.out.print("Enter ride ID:");
                    rideID = sc.nextInt();
                    result = Database.deleteRide(rideID,id);
                    if(result == 0){
                        System.out.println("Ride deleted successfully");
                    }
                    else{
                        System.out.println("Can't delete the ride please check the ID");
                    }
                    break;
                case 5:
                    System.out.print("Enter ride ID:");
                    rideID = sc.nextInt();
                    user.bookRide(rideID,id);
                    break;
                case 6:
                    user.availableRides();
                    break;
                case 7:
                    System.out.print("Enter source:");
                    source = sc.next();
                    System.out.print("Enter destination:");
                    destination = sc.next();
                    user.displayselectRide(source,destination);
                    break;
                case 8:
                    System.out.print("Enter ride ID:");
                    rideID = sc.nextInt();
                    user.cancelRide(rideID,id);
                    break;
                case 9:
                    System.out.print("Please enter password:");
                    String password = sc.next();
                    System.out.print("Please enter new password:");
                    String newpassword = sc.next();
                    user.changePassword(password,newpassword,id);

            }
            if(op > 9 && op< 0 && id !=0){
                System.out.println("Invalid input..");
            }
            if(id == 1) {

                switch (op) {
                    case 11:
                        admin.displayRide();
                        break;
                    case 12:
                        admin.totalFare();
                        break;
                    case 13:
                        admin.displayUsers();
                        break;
                    case 14:
                        admin.displayCities();
                        break;
                    case 15:
                        System.out.print("Enter source:");
                        source = sc.next();
                        admin.displayDestination(source);
                        break;
                    case 16:
                        System.out.print("Enter destination:");
                        destination = sc.next();
                        admin.displaySource(destination);
                        break;
                }
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        Database.getconnect();
        int id = login.log();
        System.out.println(id);
        ui(id);
        Database.close();


    }
}

