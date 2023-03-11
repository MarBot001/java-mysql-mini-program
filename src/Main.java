import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        while (true) {

            /*SIMPLE MYSQL DATABASE MANAGER JAVA PROGRAM: by, MarBot*/
            /*
            The format of the MYSQL database used for the example:
            id    username    password
             */


            /*Connecting to the database*/
            String url = "jdbc:mysql://localhost:3306/javadb";
            String username = "root";
            String password = "";

            /*Select option*/
            Scanner scan = new Scanner(System.in);
            System.out.println("-------------------------");
            System.out.println("DATABASE SOFTWARE");
            System.out.println("1. Full database\n2. New user registration\n3. Delete user\n4. Exit program");
            System.out.print("Option number: ");
            int a = scan.nextInt();

            if (a == 1) {
                try {
                    /*ConnectorJ addition*/
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    /*Explaining database access */
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    /*List all (*) elements of the table*/
                    System.out.println("-------------------------");
                    System.out.println("id username password");
                    ResultSet resultSet=statement.executeQuery("select * from tabla");

                    while (resultSet.next()){
                        /*Separating columns and then entering serial numbers according to the database*/
                        System.out.println(resultSet.getInt(1) + " " + resultSet.getString(2) + " " + resultSet.getString(3));
                    }
                    connection.close();
                }
                catch (Exception e){
                    System.out.println(e);
                }
            }
            else if (a == 2){
                /*User registration datas*/
                System.out.println("-------------------------");
                System.out.println("Registrate new user:");
                System.out.print("Username: ");
                String usernameInput = scan.next();
                System.out.print("Password: ");
                String passwordInput = scan.next();

                try {
                    /*ConnectorJ addition*/
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    /*Explaining database access */
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    /*Specifying *table* columns*/
                    String sql = "INSERT INTO tabla (username, password) VALUES ('" + usernameInput + "', '" + passwordInput + "')";
                    statement.executeUpdate(sql);
                    System.out.println("Registration success!");
                    connection.close();
                } catch (Exception e) {
                    /*Failed connection*/
                    System.out.println(e);
                    System.out.println("Registration failed!");
                }
            }

            else if (a == 3){
                System.out.println("Delete user:");
                /*User ID*/
                System.out.print("id: ");
                int id = scan.nextInt();

                try{
                    /*ConnectorJ addition*/
                    Class.forName("com.mysql.cj.jdbc.Driver");

                    /*Explaining database access */
                    Connection connection = DriverManager.getConnection(url, username, password);
                    Statement statement = connection.createStatement();
                    /*Specifying *table* column*/
                    String sql = "DELETE FROM tabla WHERE id = " + id;
                    statement.executeUpdate(sql);
                    connection.close();
                    System.out.println("Delete success!");

                }
                catch (Exception e){
                    /*Failed connection*/
                    System.out.println(e);
                    System.out.println("Delete failed!");

                }
            } else if (a == 4) {
                /*Exit method*/
                System.exit(0);

            } else {
                System.out.println("ERROR!");
            }

        }
    }
}