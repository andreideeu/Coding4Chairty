package com.mkyong.rest;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Product_runner {
    //RUNNER API
    String name;
    String lat, lng, person, storedlat, storedlong;
    int num = 0;

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

            System.out.println("-----------STARTED CONNECTING----" + num + "----------");
            Connection con = DriverManager.getConnection(
                "jdbc:oracle:thin:@IPv4:PORT:SID", "username", "password"
            );

            if (con != null) {
                System.out.println("Connected to the database!");
            } else {
                System.out.println("Failed to make connection!");
            }

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            Date date = new Date();
            System.out.println("  Date: " + formatter.format(date));

            System.out.println("---POSTING---");
            System.out.print("  Type: Runner ");

            if (lat.equals(storedlat) == false || lng.equals(storedlong) == false) {
                System.out.println("....is on the move");
            }

            if (lat.equals(storedlat) == true || lng.equals(storedlong) == true) {
                System.out.println("....is standing still");
            }

            System.out.println("  User: " + name);
            System.out.println("  LAT: " + lat);
            System.out.println("  LONG: " + lng);
            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("INSERT INTO RUNTEST (USERNAME,LAT,LNG,TIMESTAMP) VALUES ('" + name + "','" + lat + "','" + lng + "',TO_DATE('" + formatter.format(date) + "','YYYY-MM-DD HH24:MI'))");

            System.out.println("Successfully Posted");
            System.out.println("-------------------------------------- ");
            System.out.println(" ");
            con.close();
            storedlat = lat;
            num++;

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
