/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package clantask.Sqlite;

import java.sql.SQLException;

/**
 *
 * @author stankevich
 */
public interface DataBaseActions {
    void insert() throws SQLException, ClassNotFoundException;
    void update() throws SQLException, ClassNotFoundException;
    void delete() throws SQLException, ClassNotFoundException;
}
