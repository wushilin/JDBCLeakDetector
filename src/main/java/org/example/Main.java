package org.example;

import net.wushilin.jdbc.debug.DebuggingDataSource;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Random;

public class Main {
    static DataSource ds;
    static Random rand = new Random();
    public static void main(String[] args) throws SQLException, InterruptedException {
        System.out.println("Hello world!");
        ds = createDS();
        while(true) {
            randomLoop();
            Thread.sleep(2000);
        }
    }

    private static void randomLoop() throws SQLException {
        Connection conn = ds.getConnection();

        Statement stmt = conn.createStatement();

        PreparedStatement pstmt = conn.prepareStatement("insert into users(name, age) values(?, ?)");
        pstmt.setString(1, "Name-" + rand.nextInt(1000));
        pstmt.setInt(2, rand.nextInt(100));
        int affected = pstmt.executeUpdate();
        System.out.println("Inserted " + affected + " rows");

        ResultSet rs = stmt.executeQuery("select * from users");
        long count = 0;
        while(rs.next()) {
            int age = rs.getInt("age");
            count += age;
        }
        System.out.println("Total age: " + count);
        if(rand.nextInt(10) != 1) {
            rs.close();
        }
        if(rand.nextInt(10) != 1) {
            stmt.close();
        }
        if(rand.nextInt(10) != 1) {
            pstmt.close();
        }

        if(rand.nextInt(10) != 1) {
            conn.close();
        }
    }
    private static DataSource createDS() {
        DebuggingDataSource cpds = new DebuggingDataSource();
        cpds.setUrl("jdbc:mysql://mysql.jungle/testdb");
        cpds.setUsername("user");
        cpds.setDriverClass("com.mysql.jdbc.Driver");
        cpds.setPassword("pass1234");
        return cpds;
    }
}
