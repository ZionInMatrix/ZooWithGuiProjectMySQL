package dao;

import core.Osetrovatele;

import java.io.FileInputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ZooDAO {

    private Connection myConn;

    public ZooDAO() throws Exception {

        Properties props = new Properties();
        props.load(new FileInputStream("demo.properties"));

        String user = props.getProperty("user");
        String password = props.getProperty("password");
        String dburl = props.getProperty("dburl");


        myConn = DriverManager.getConnection(dburl,user,password);

        System.out.println("DB connection successful to: " + dburl);
    }

    public List<Osetrovatele> getAllOsetrovatele() throws Exception {
        List<Osetrovatele> list = new ArrayList<>();

        Statement myStmt = null;
        ResultSet myRs = null;

        try {
            myStmt = myConn.createStatement();
            myRs = myStmt.executeQuery("SELECT * FROM Osetrovatele");

            while (myRs.next()) {
                Osetrovatele tempOsetrovatele = convertRowToOsetrovatel(myRs);
                list.add(tempOsetrovatele);
            }

            return list;
        } finally {
            close(myStmt, myRs);
        }
    }

    public List<Osetrovatele> searchOsetrovatele(String jmeno) throws Exception {
        List<Osetrovatele> list = new ArrayList<>();

        PreparedStatement myStmt = null;
        ResultSet myRs = null;

        try {
            jmeno += "%";
            myStmt = myConn.prepareStatement("select * from osetrovatele where jmeno like ?");

            myStmt.setString(1, jmeno);

            myRs = myStmt.executeQuery();

            while (myRs.next()) {
                Osetrovatele tempOsetrovatele = convertRowToOsetrovatel(myRs);
                list.add(tempOsetrovatele);
            }

            return list;
        } finally {
            close(myStmt, myRs);
        }
    }

    private Osetrovatele convertRowToOsetrovatel(ResultSet myRs) throws SQLException {

        int id = myRs.getInt("id");
        String jmeno = myRs.getString("jmeno");
        Date narozen = myRs.getDate("narozen");

        Osetrovatele tempOsetrovatele = new Osetrovatele(id, jmeno, narozen);

        return tempOsetrovatele;
    }


    private static void close(Connection myConn, Statement myStmt, ResultSet myRs)
            throws SQLException {

        if (myRs != null) {
            myRs.close();
        }

        if (myStmt != null) {
            myStmt.close();
        }

        if (myConn != null) {
            myConn.close();
        }
    }

    private void close(Statement myStmt, ResultSet myRs) throws SQLException {
        close(null, myStmt, myRs);
    }

    public static void main(String[] args) throws Exception {

        ZooDAO dao = new ZooDAO();
        System.out.println(dao.searchOsetrovatele("Jiri"));

        System.out.println(dao.getAllOsetrovatele());
    }
}
