package Carpooling;
import java.util.Scanner;
import java.sql.*;

public class Database {
     public static Connection connection = null;
     private static Statement statement;
     static Scanner sc = new Scanner(System.in);
     private static ResultSet resultSet =null;
     static PreparedStatement update;

     public Database() throws SQLException {
     }


     public static  void getconnect(){
          try{
               Database.connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/cp","postgres","Madhu@1310");
               statement = connection.createStatement();
          }
          catch (Exception e){
               System.err.println(e);
          }


     }
     public static void close() throws SQLException {
          connection.close();
     }
     public static void createRide(String source, String destination,int fare,int id) throws SQLException {
          PreparedStatement stmt=connection.prepareStatement("insert into rides (source, destination,fare,createrid, available) values(?,?,?,?,true)");
          stmt.setString(1,source);
          stmt.setString(2,destination);
          stmt.setInt(3,fare);
          stmt.setInt(4,id);
          int i = stmt.executeUpdate();
          if(i == 1){
               System.out.println("Ride created successfully");
          }
          else{
               System.out.println("Ride creation failed");
          }
     }
     public static int deleteRide(int id, int createrid) throws SQLException {
          if(createrid == 1){
               update = connection.prepareStatement("delete from rides where id = ?");
               update.setInt(1,id);
          }
          else {
               update = connection.prepareStatement("delete from rides where id = ? and createrid = ?");
               update.setInt(1,id);
               update.setInt(2,createrid);
          }
          int i = update.executeUpdate();
          if(i==1){
               System.out.println("Successfully deleted");
          }
          else {
               System.out.println("Deletion failed");
          }
          return i;
     }
     public static void updateRide(int id,String source,String destination,int fare,int createrid) throws SQLException {
          update = connection.prepareStatement("update rides set source = ?,destination = ?,fare =? where available = true and id =? and createrid = ?;");
          String update1 = "update rides set source = '"+source+"', destination = '"+destination+"', fare = "+fare+" where available = true and id = "+id+" and createrid = "+createrid;
          update.setString(1,source);
          update.setString(2,destination);
          update.setInt(3,fare);
          update.setInt(4,id);
          update.setInt(5,createrid);
          int i = update.executeUpdate();
          if(i == 1){
               System.out.println("Ride updated successfully");
          }
          else {
               System.out.println("Can't update the ride please check the ID");
          }
     }
     static void createuser(String username,String email,String password) throws SQLException {
          update = connection.prepareStatement("insert into users (username,password,email) values(?,?,?);");
          update.setString(1,username);
          update.setString(2,password);
          update.setString(3,email);
          update.executeUpdate();
     }
     static Integer checkuser(String username) throws SQLException{
          statement = connection.createStatement();
          resultSet = statement.executeQuery("select id from users where username = '"+username+"';");
          if(resultSet.next()){
               return resultSet.getInt(1);
          }
          return 0;
     }
     static Integer checkemail(String email) throws SQLException{
          resultSet = statement.executeQuery("select id from users where email = '"+email+"';");
          if(resultSet.next()){
               return resultSet.getInt(1);
          }
          return 0;
     }
     static Integer checkpassword(String username,String password) throws SQLException {
          resultSet = statement.executeQuery("select id from users where username = '"+username+"' and password ='"+password+"' ;");
          if(resultSet.next()){
               return resultSet.getInt(1);
          }
          return 0;
     }

}

