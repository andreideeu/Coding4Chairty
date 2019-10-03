package com.mkyong.rest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class getData_cycle {

    //String name;
    String lat, lng, person;

    String lat1, lng1, person1;
    String lat2, lng2, person2;
    String lat3, lng3, person3;
    String lat4, lng4, person4;
    String lat5, lng5, person5;

    String lat6, lng6;
    String lat7, lng7;
    String lat8, lng8;
    String lat9, lng9;
    String lat10, lng10;

    String storedlat, storedlong;

    private Statement stmt = null;
    private ResultSet rs = null;




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

    public void setLong1(String lng1) {
        this.lng1 = lng1;
    }
    public void setLong2(String lng2) {
        this.lng2 = lng2;
    }
    public void setLong3(String lng3) {
        this.lng3 = lng3;
    }
    public void setLong4(String lng4) {
        this.lng4 = lng4;
    }
    public void setLong5(String lng5) {
        this.lng5 = lng5;
    }

    public void setLong6(String lng6) {
        this.lng6 = lng6;
    }
    public void setLong7(String lng7) {
        this.lng7 = lng7;
    }
    public void setLong8(String lng8) {
        this.lng8 = lng8;
    }
    public void setLong9(String lng9) {
        this.lng9 = lng9;
    }
    public void setLong10(String lng10) {
        this.lng10 = lng10;
    }

    public void setLat1(String lat1) {
        this.lat1 = lat1;
    }
    public void setLat2(String lat2) {
        this.lat2 = lat2;
    }
    public void setLat3(String lat3) {
        this.lat3 = lat3;
    }
    public void setLat4(String lat4) {
        this.lat4 = lat4;
    }
    public void setLat5(String lat5) {
        this.lat5 = lat5;
    }

    public void setLat6(String lat6) {
        this.lat6 = lat6;
    }
    public void setLat7(String lat7) {
        this.lat7 = lat7;
    }
    public void setLat8(String lat8) {
        this.lat8 = lat8;
    }
    public void setLat9(String lat9) {
        this.lat9 = lat9;
    }
    public void setLat10(String lat10) {
        this.lat10 = lat10;
    }

    public String getLat1() {
        return lat1;
    }
    public String getLat2() {
        return lat2;
    }
    public String getLat3() {
        return lat3;
    }
    public String getLat4() {
        return lat4;
    }
    public String getLat5() {
        return lat5;
    }

    public String getLat6() {
        return lat6;
    }
    public String getLat7() {
        return lat7;
    }
    public String getLat8() {
        return lat8;
    }
    public String getLat9() {
        return lat9;
    }
    public String getLat10() {
        return lat10;
    }

    public String getLong1() {
        return lng1;
    }
    public String getLong2() {
        return lng2;
    }
    public String getLong3() {
        return lng3;
    }
    public String getLong4() {
        return lng4;
    }
    public String getLong5() {
        return lng5;
    }
    public String getLong6() {
        return lng6;
    }
    public String getLong7() {
        return lng7;
    }
    public String getLong8() {
        return lng8;
    }
    public String getLong9() {
        return lng9;
    }
    public String getLong10() {
        return lng10;
    }

    public void setMidpoint(double lat1, double lon1, double lat2, double lon2) {

        double dLon = Math.toRadians(lon2 - lon1);

        //convert to radians
        lat1 = Math.toRadians(lat1);
        lat2 = Math.toRadians(lat2);
        lon1 = Math.toRadians(lon1);

        double Bx = Math.cos(lat2) * Math.cos(dLon);
        double By = Math.cos(lat2) * Math.sin(dLon);
        double lat3 = Math.atan2(Math.sin(lat1) + Math.sin(lat2), Math.sqrt((Math.cos(lat1) + Bx) * (Math.cos(lat1) + Bx) + By * By));
        double lon3 = lon1 + Math.atan2(By, Math.cos(lat1) + Bx);

        //print out in degrees
        System.out.println(Math.toDegrees(lat3) + " " + Math.toDegrees(lon3));

    }


    public String getcon() {

        try {
            String[] data = new String[6];
            String[] datamass = new String[1000];

            System.out.println("-----GETTING REQUEST CYCLE----");

            Class.forName("oracle.jdbc.driver.OracleDriver");

            Connection con = DriverManager.getConnection(
            "jdbc:oracle:thin:@IPv4:PORT:SID", "username", "password"
            );

            Statement stmt = con.createStatement();

            SimpleDateFormat formatter = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            Date date = new Date();
            System.out.println(formatter.format(date));


            stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery("select USERNAME,lat,lng,timestamp from CYCLETEST ORDER BY userid desc fetch first 10 rows only");

            int j = 1;

            while (rs.next())

                for (int i = 1; i < 5; i++) {
                    //System.out.println(rs.getInt(1)+"  	"+rs.getString(2)+" 	 "+rs.getString(3)+"		"+rs.getString(4)+"  	"+rs.getString(5)+"  	"+rs.getString(6)+" 	 ");
                    data[i] = rs.getString(i);

                    // System.out.println("data stored:  "+data[i]);
                    //System.out.println("--------------------------------");
                    //System.out.println(rs.getInt(1)+ "	"+rs.getString(2)+" 	 ");
                    datamass[j] = data[i];
                    i = i++;
                    //System.out.println(i);
                    j = j + 1;
                    //System.out.println(j);
                    //data[]
                }

            //System.out.println("data stored:  " + datamass[0]); // null
            //System.out.println("data stored:  " + datamass[1]); // user
            //System.out.println("data stored:  " + datamass[2]); // lat
            // System.out.println("data stored:  " + datamass[3]); // long
            // System.out.println("data stored:  " + datamass[4]); // time
            // System.out.println("data stored:  " + datamass[5]); // user again..
            //System.out.println("data stored:  " + datamass[6]); // user
            //System.out.println("data stored:  " + datamass[7]); // lat
            // System.out.println("data stored:  " + datamass[8]); // long
            // System.out.println("data stored:  " + datamass[10]); // time
            // System.out.println("data stored:  " + datamass[11]); // user again..

            setLat1(datamass[2]);
            setLat2(datamass[6]);
            setLat3(datamass[10]);
            setLat4(datamass[14]);
            setLat5(datamass[18]);

            setLat6(datamass[22]);
            setLat7(datamass[26]);
            setLat8(datamass[30]);
            setLat9(datamass[34]);
            setLat10(datamass[38]);

            setLong1(datamass[3]);
            setLong2(datamass[7]);
            setLong3(datamass[11]);
            setLong4(datamass[15]);
            setLong5(datamass[19]);

            setLong6(datamass[23]);
            setLong7(datamass[27]);
            setLong8(datamass[31]);
            setLong9(datamass[35]);
            setLong10(datamass[39]);

            System.out.println("Cycle pinpoint found "); // null
            System.out.println("");
            //setMidpoint(Double.parseDouble(datamass[2]),Double.parseDouble(datamass[3]),Double.parseDouble(datamass[6]),Double.parseDouble(datamass[7]));
            //setMidpoint(Double.parseDouble(datamass[2]),Double.parseDouble(datamass[3]),Double.parseDouble(datamass[10]),Double.parseDouble(datamass[11]));
            //setMidpoint(Double.parseDouble(datamass[2]),Double.parseDouble(datamass[3]),Double.parseDouble(datamass[14]),Double.parseDouble(datamass[15]));
            //setMidpoint(Double.parseDouble(datamass[2]),Double.parseDouble(datamass[3]),Double.parseDouble(datamass[18]),Double.parseDouble(datamass[19]));
            con.close();

        } catch (Exception e) {
            System.out.println(e);
        }

        //String alllof = getLat1()+","+getLong1()+getLat1()+","+getLong2()+getLat2()+","+getLong3()+getLat3()+","+getLong4()+getLat4()+","+getLong1()+getLat5()+","+getLong5();
        String alllof = "aa";
        return alllof;

    }


    @Override
    //public String toString() {return "Product [name=" + name + ", qty=" + qty + "]";}

    public String toString() {
        return "Latitude: " + lat + " Longitude: " + lng + " User: " + person;
    }

}
