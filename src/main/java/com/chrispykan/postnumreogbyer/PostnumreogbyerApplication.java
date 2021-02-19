package com.chrispykan.postnumreogbyer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.*;
import java.util.*;

@SpringBootApplication
public class PostnumreogbyerApplication {

    public static void main(String[] args) {

        SpringApplication.run(PostnumreogbyerApplication.class, args);

        //Connect to Database//

        //"reader" to be used to get db configuration info from application
        java.util.ResourceBundle reader = null;

        Connection con = null;

        //Scanner for user input
        Scanner sc = new Scanner (System.in);

        try {
        //Read config info from "application" as in application.properties
        reader = ResourceBundle.getBundle("application");

        //Connect to db using config info
        con = DriverManager.getConnection(reader.getString("spring.datasource.url"), reader.getString("spring.datasource.username"), reader.getString("spring.datasource.password"));

            if (con != null) {
                System.out.println("Connected to the database");
            }//if END

            //"stmt" to use to create a Statement object for sending SQL statements to the db
            Statement stmt = con.createStatement();

            //Obj ResultSet to use to point to * from the Danske_Postnumre table in my db
            ResultSet rs = stmt.executeQuery("select * from Danske_Postnumre");

            while (rs.next()) {

                //Assign variables to postnummer and by columns in table//
                //String Zipcode = rs.getString("postnummer"); If using Zipcode as a string
                int Zipcode = rs.getInt("postnummer"); //using Zipcode as int

                String City = rs.getString("by");

                // Print ALL Info from column labels
                System.out.println(" "+ Zipcode +" "+City);
            }//while END

        }//try END


        catch (SQLException ex) {
            System.out.println("An error occurred. Invalid user or password");
            ex.printStackTrace();
        }//catch END

        finally {
            if (con != null) {
                try {
                    con.close();
                }//try END
                catch (SQLException ex) {
                    ex.printStackTrace();
                }//catch END
            }//if END
        }//finally END

    }//main END
}//class END
