
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class DatabaseProcess {//This class for the methods when we add user to the system or update the user in the system 
    private Connection con = null;//connection variable
    private Statement statement = null;//statement variable
    private PreparedStatement preparedStatement =null;//preparedStatement variable
    
    public ArrayList<Workers> comesUser(){//This method for the bring all the user from database
        ArrayList<Workers> cikti = new ArrayList<Workers>(); // Arralist for the add all the user in the database after that, In welcom page we took arraylist to to convert Object variable
       
        
        try {
            statement = con.createStatement();  //statemnt variable 
            String command = "select * from workers";//DML code for the select all the users
           ResultSet rs = statement.executeQuery(command);
           while(rs.next()){//this while for the walk all the data that are in the database while doing that it add the user in the arraylist 
            String useName = rs.getString("username");
            String pass = rs.getString("pass");
            String email = rs.getString("email");
            int phone = rs.getInt("phone");
            String addr = rs.getString("address");
            
            cikti.add(new Workers(useName,pass,email,phone,addr));//Adding method 
            
            }
           return cikti;
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            statement = con.createStatement();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
    }
    public void update(String id, String name,String pName,String ma,String pho,String ad){//Update method for the user 
        String command ="update workers set username =? , pass=? , email=? , phone =? , address =? where username =?";//DML code for the update user in the database 
        try {   // try catch block AND Question markers represent the preparedStatement Using of this just make the proces more easy
            preparedStatement = con.prepareStatement(command);
            preparedStatement.setString(1, name);
             preparedStatement.setString(2, pName);          
              preparedStatement.setString(3, ma);          
               preparedStatement.setInt(4, Integer.valueOf(pho));       ///prepared statements for the update users
                preparedStatement.setString(5, ad);                         //also for the sql injection 
                 preparedStatement.setString(6, id);
            preparedStatement.executeUpdate(); //final execute command 
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
    }
    public void insertUser(String us, String ps, String mail, String pho, String ad){ // Insert method for while user sign-up to the system 
        
        String command = "Insert into workers(username,pass,email,phone,address) values(?,?,?,?,?)"; //DML codes
        try {
            preparedStatement = con.prepareStatement(command);
            preparedStatement.setString(1, us);
             preparedStatement.setString(2, ps);
              preparedStatement.setString(3, mail);      //Again preparedStatements for the adding process, this is for the make it more easy
              preparedStatement.setInt(4, Integer.valueOf(pho)); //also for the sql injection 
                preparedStatement.setString(5, ad);
                
                preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public boolean loginSystem(String uN, String pS){ // login system method check the user, if he/she is in the system or not, If he/she is in the system return true otherweise return false
        String command = "Select * from workers where username = ? and pass = ?"; //DML code for check username and password
        try {
            preparedStatement = con.prepareStatement(command);
            preparedStatement.setString(1,uN);              //also for the sql injection 
            preparedStatement.setString(2, pS);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            if(rs.next()==false){  // return true and false part, if the user in the database the method return true otherwise return false
                return false;
            }
            else{
                return true;   
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public DatabaseProcess(){//this part is constructer part, and when we create new object from DataBase Process authomatically connecet to the MySql database
        String url = "jdbc:mysql://" + Database.host +":"+Database.port+"/"+Database.db_name;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver"); //connect parts 
            
        }catch (ClassNotFoundException ex) {
               System.out.println("Driver can't find");
        }
        
            try {
                con = DriverManager.getConnection(url,Database.userName,Database.password); // connection part 
                System.out.println("Connected to the Database");
            } catch (SQLException ex) {
                System.out.println("Connection Faild");
            }
    }
    
    public static void main(String[]args){
        DatabaseProcess dp = new DatabaseProcess(); // in the beginning we are going to sure that, we connected to the database 
        
    }
}
