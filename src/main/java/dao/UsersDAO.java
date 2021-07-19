/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Users;
import pattern.DAO;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Mypc
 */
public class UsersDAO implements DAO<Users>{
    ArrayList<Users>lst ;
    public UsersDAO() {
        lst= new ArrayList<>();
        read();
    }
    
    @Override
    public boolean create(Users t) {
        return true;
    }

    @Override
    public void read() {
        try (Connection conn = util.JDBCUtils.getConnection()) {
            Statement st = conn.createStatement();
            String query = "Select * from users";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                lst.add(new Users(rs.getString(1), rs.getString(2), rs.getString(3)));
            }
            System.out.println("Thành công");
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Users t, int index) {
    }

    @Override
    public void delete(Users t) {
    }

    @Override
    public ArrayList<Users> getAll() {
        return this.lst;
    }
    
}
