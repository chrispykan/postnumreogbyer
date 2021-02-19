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

        try {
        //read config info from "application" as in application.properties
        reader = ResourceBundle.getBundle("application");

        //connect to db using config info
        con = DriverManager.getConnection(reader.getString("spring.datasource.url"), reader.getString("spring.datasource.username"), reader.getString("spring.datasource.password"));

            if (con != null) {
                System.out.println("Connected to the database");
            }//if END

        }//try END

        catch (SQLException ex) {
            System.out.println("An error occurred. Invalid user or password");
            ex.printStackTrace();
        }//catch END

    }//main END
}//class END
