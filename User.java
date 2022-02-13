package Carpooling;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class User {
    Ride ride;
    Statement statement = null;
    ResultSet resultSet = null;



    public void displayselectRide(String source,String destination) throws SQLException {
        Statement statement = Database.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select id,source,destination,fare from rides where available = true and source = '"+source+"' and destination = '"+destination+"';");
        System.out.println("ID Source  Destination Fare");
        while(resultSet.next()){
            System.out.println(resultSet.getInt(1)+"  "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getInt(4));
        }
    }
    public void availableRides() throws SQLException{
        Statement statement = Database.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select id,source,destination,fare from rides where available = true;");
        System.out.println("ID Source  Destination Fare");
        while(resultSet.next()){
            System.out.println(resultSet.getInt(1)+"  "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getInt(4));
        }
    }
    public void bookRide(int id,int userid) throws SQLException {
        Statement statement = Database.connection.createStatement();
        String update = "update rides set available = false where id = "+id;
        int i = statement.executeUpdate(update);
        statement.executeUpdate("insert into bookings (rideid,userid) values('"+id+"', '"+userid+"');");
        if(i==1){
            System.out.println("Ride booked successfully");
        }
        else {
            System.out.println("Ride booking failed");
        }
    }
    public void yourRides(int id) throws SQLException{
        statement = Database.connection.createStatement();
        resultSet = statement.executeQuery("select id,source,destination,fare from rides where available = true and createrid ="+id+";");
        System.out.println("ID Source  Destination Fare");
        while(resultSet.next()){
            System.out.println(resultSet.getInt(1)+"  "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getInt(4));
        }
    }
    public void changePassword(String oldpassword,String newpassword,int id) throws SQLException {
        statement = Database.connection.createStatement();
        int i = statement.executeUpdate("update users set password = '"+newpassword+"' where id = "+id+" and password = '"+oldpassword+"';");
        if(i==1){
            System.out.println("Password changed");
        }
        else {
            System.out.println("Please check your entered password");
        }
    }
    public void cancelRide(int rideID,int id) throws SQLException {
        statement = Database.connection.createStatement();
        resultSet = statement.executeQuery("select userid from bookings where rideid ="+rideID+" order by bookingid desc;");
        if(resultSet.next() && resultSet.getInt(1) == id){
            int i = statement.executeUpdate("update rides set available = true where id ="+rideID+";");
            System.out.println("Your ride is cancelled");
        }
        System.out.println("Sorry you can't cancel ride...");

    }



}
