/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package clantask.Sqlite;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author stankevich
 */
public class Clans implements DataBaseActions {

    private String id;     // id клана
    private String name; // имя клана
    private long gold = 0;    // текущее количество золота в казне клана
    private static final String TABLE = "Clan";

    public Clans(HashMap<String, String> obj) {
        id = obj.get("id");
        name = obj.get("name");
        gold = Integer.parseInt(obj.get("gold"));
    }

    public Clans() {

    }

    @Override
    public void insert() throws SQLException, ClassNotFoundException {
        String sql = "INSERT INTO " + TABLE + " (name, gold) VALUES(:1, :2)";
        ArrayList<Param> params = new ArrayList();
        params.add(new Param(name));
        params.add(new Param(gold));

        DataBase.execute(sql, params);
    }

    @Override
    public void update() throws SQLException, ClassNotFoundException {

        String sql = "UPDATE " + TABLE + " SET gold = :1 WHERE id = :2";
        ArrayList<Param> params = new ArrayList();
        params.add(new Param(gold));
        params.add(new Param(id));
        DataBase.execute(sql, params);
    }

    @Override
    public void delete() throws SQLException, ClassNotFoundException {

    }

    public static ArrayList<Clans> getAll()
            throws SQLException, ClassNotFoundException {
        ArrayList<HashMap<String, String>> items = DataBase.getAll(TABLE);
        ArrayList<Clans> res = new ArrayList();
        for (HashMap<String, String> obj : items) {
            res.add(new Clans(obj));
        }
        return res;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getGold() {
        return gold;
    }

    public void setGold(long gold) {
        this.gold = gold;
        if (this.gold < 0) {
            this.gold = 0;
        }
    }

    @Override
    public String toString() {
        return this.name;
    }

}
