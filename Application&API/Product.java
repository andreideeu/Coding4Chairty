package com.mkyong.rest;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Product {

    String name;
    String lat, lng, person;


    String storedlat, storedlong;

    private Statement stmt = null;
    private ResultSet rs = null;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }
    public String getLong() {
        return lng;
    }

    public void setLong(String lng) {
        this.lng = lng;
    }

    public String getcon() {


        try {


            System.out.println("-----------STARTED CONNECTING--------------");
            Connection con = DriverManager.getConnection(
            "jdbc:oracle:thin:@IPv4:PORT:SID", "username", "password"
            );

            if (con != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            java.util.Date date = new Date();
            System.out.println("  Date: " + formatter.format(date));

            System.out.println("---POSTING---");

            System.out.print("  Type: Cyclist ");

            if (lat.equals(storedlat) == false || lng.equals(storedlong) == false) {
                System.out.println("....is on the move");
            }

            if (lat.equals(storedlat) == true || lng.equals(storedlong) == true) {
                System.out.println("....is standing still");
            }

            System.out.println("  User: " + name);
            System.out.println("  LAT: " + lat);
            System.out.println("  LONG: " + lng);
            System.out.println("  COPY: " + lat + "," + lng);
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("INSERT INTO CYCLETEST (USERNAME,LAT,LNG,TIMESTAMP) VALUES ('" + name + "','" + lat + "','" + lng + "',TO_DATE('" + formatter.format(date) + "','YYYY-MM-DD HH24:MI'))");

            System.out.println("Successfully Posted");
            System.out.println("-------------------------------------- ");
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(" ");

            con.close();

            storedlat = lat;


        } catch (SQLException e) {
            e.printStackTrace();
        }

        String alllof = "aaa";

        return alllof;
    }


    @Override
    //public String toString() {return "Product [name=" + name + ", qty=" + qty + "]";}

    public String toString() {
        return "Latitude: " + lat + " Longitude: " + lng + " User: " + person;
    }

}
