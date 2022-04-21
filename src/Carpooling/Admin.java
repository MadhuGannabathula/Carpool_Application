package Carpooling;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Admin extends User {
    static Connection connection = Database.connection;
    Statement statement = Database.connection.createStatement();

    public Admin() throws SQLException {
    }

    public void displayRide() throws SQLException{
        ResultSet resultSet = statement.executeQuery("select * from rides;");
        while(resultSet.next()){
            System.out.println(resultSet.getInt(1)+"  "+resultSet.getString(2)+" "+resultSet.getString(3)+" "+resultSet.getInt(4));
        }
    }
    public void displayCities() throws SQLException {
        String s = "select distinct cities from (select distinct source as cities from rides union select distinct destination as cities from rides) as towns;";
        ResultSet resultSet = statement.executeQuery(s);
        while(resultSet.next()){
            System.out.println(resultSet.getString(1));
        }
    }


    public void displayDestination(String source) throws SQLException {
        ResultSet resultSet = statement.executeQuery("select distinct destination from rides where source ='"+source+"';");
        while(resultSet.next()){
            System.out.println(resultSet.getString(1));
        }
    }
    public void displaySource(String destination) throws SQLException {
        ResultSet resultSet = statement.executeQuery("select distinct source from rides where destination ='"+destination+"';");
        while(resultSet.next()){
            System.out.println(resultSet.getString(1));
        }
    }
    public int totalFare() throws SQLException {
        int total = 0;
        Statement statement = Database.connection.createStatement();
        ResultSet resultSet = statement.executeQuery("select sum(fare) from rides;");
        while(resultSet.next()){
            total = resultSet.getInt(1);
        }
        return  total;
    }
    public void displayUsers() throws SQLException {
        ResultSet resultSet = statement.executeQuery("select * from users;");
        while(resultSet.next())
        {
            System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+"  "+resultSet.getString(3));
        }
    }


}
