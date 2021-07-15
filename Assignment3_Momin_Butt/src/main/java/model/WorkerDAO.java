/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import db.ConnectionPool;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author excus
 */
public class WorkerDAO {

    private static final String ADD_WORKER_SQL = "insert into workers values (?,?,?);";
    private static final String SELECT_ALL_SQL = "select * from workers;";
    private static final String SELECT_SEARCH_SALARY
            = "select * from workers where salary >= ? and salary <= ?;";

    public static boolean addWorker(Worker worker) {
        boolean done = false;
        //get connection from the pool, prepare statement, execute statement, process result
        //- to avoid using the try...catch...finally, try-with-resources
        try (Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(ADD_WORKER_SQL)) {
            ps.setString(1, worker.getId());
            ps.setString(2, worker.getFullname());
            ps.setDouble(3, worker.getSalary());
            done = ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("ex");
        }
        return done;
    }

    public static ArrayList<Worker> selectSearchSalaryWorkers(int minsal, int maxsal) {
        ArrayList<Worker> list = new ArrayList<>();
        try (
                Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(SELECT_SEARCH_SALARY);) {
            ps.setInt(1, minsal);
            ps.setInt(2, maxsal);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Worker(rs.getString("id"), rs.getString("fullname"),
                        rs.getDouble("salary")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public static ArrayList<Worker> selectAllWorkers() {
        ArrayList<Worker> list = new ArrayList<>();
        try (Connection connection = ConnectionPool.getInstance().getConnection();
                PreparedStatement ps = connection.prepareStatement(SELECT_ALL_SQL)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                list.add(new Worker(
                        rs.getString("id"), rs.getString("fullname"),
                        rs.getDouble("salary")));
            }
        } catch (SQLException ex) {
            System.out.println("ex");
        }
        return list;
    }

}
