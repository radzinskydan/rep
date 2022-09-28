/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clantask.Sqlite;

import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author stankevich
 */
public class Param {

    private int type;
    private long intVal;
    private String stringVal;

    public Param(String val) {
        stringVal = val;
        type = 2;
    }

    public Param(int val) {
        intVal = val;
        type = 1;
    }

    public Param(long val) {
        intVal = val;
        type = 1;
    }

    public Param(boolean val) {
        stringVal = String.valueOf(val);
        type = 2;
    }

    public void set(PreparedStatement q, int i) throws SQLException {
        if (type == 1) {
            q.setLong(i + 1, intVal);
        }
        if (type == 2) {
            q.setString(i + 1, stringVal);
        }
    }
}
