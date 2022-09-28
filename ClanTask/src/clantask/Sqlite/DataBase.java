/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clantask.Sqlite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author stankevich
 */
public class DataBase {
    public static Connection conn = null;

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        conn = DriverManager.getConnection("jdbc:sqlite:Clans.db");
    }

    public static void execute(String sql, ArrayList<Param> params)
            throws ClassNotFoundException, SQLException {
        if (conn == null) {
            connect();
        }
        try (PreparedStatement statmt = getPreparedStatement(sql, params)) {
            statmt.execute();
        }
    }

    private static PreparedStatement getPreparedStatement(String sql, ArrayList<Param> params)
            throws SQLException {
        PreparedStatement statmt = conn.prepareStatement(sql);
        for (int i = 0; i < params.size(); i++) {
            params.get(i).set(statmt, i);
        }
        return statmt;
    }

    public static ArrayList<HashMap<String, String>> getAll(String table)
            throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM " + table + " ORDER BY id";
        return readRows(sql);
    }

    public static ArrayList<HashMap<String, String>> readRows(String sql)
            throws ClassNotFoundException, SQLException {
        if (conn == null) {
            connect();
        }
        ResultSet resSet;
        ArrayList<HashMap<String, String>> res;
        try (PreparedStatement statmt = conn.prepareStatement(sql)) {
            resSet = statmt.executeQuery();
            ResultSetMetaData metaData = resSet.getMetaData();
            int count = metaData.getColumnCount();
            res = new ArrayList();
            while (resSet.next()) {
                HashMap<String, String> row = new HashMap();
                for (int i = 1; i <= count; i++) {
                    String key = metaData.getColumnLabel(i);
                    String val = resSet.getString(key);
                    row.put(key, val);
                }
                res.add(row);
            }
        }
        resSet.close();
        return res;
    }

}
