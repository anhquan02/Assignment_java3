/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlydiem;

import model.Grade;
import pattern.DAO;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import util.JDBCUtils;

/**
 *
 * @author Mypc
 */
public class GradeDAO implements DAO<Grade> {

    ArrayList<Grade> lst;
    String url = "jdbc:sqlserver://localhost:1433;databaseName=Assignment";

    public GradeDAO() {
        lst = new ArrayList<>();
        read();
    }

    @Override
    public boolean create(Grade t) {
        try (Connection conn = JDBCUtils.getConnection()) {
            PreparedStatement pst = conn.prepareStatement("insert into grade values(?,?,?,?)");
            pst.setString(1, t.getMasv());
            pst.setFloat(2, t.getTiengAnh());
            pst.setFloat(3, t.getTinHoc());
            pst.setFloat(4, t.getGDTC());
            pst.executeUpdate();
            System.out.println("Thành công");
            conn.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
//            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void read() {
        try (Connection conn = JDBCUtils.getConnection()) {
            Statement st = conn.createStatement();
            String query = "select grade.masv,hoten,tienganh,tinhoc,gdtc from student join grade on student.masv = grade.masv";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                lst.add(new Grade(rs.getString(1),rs.getString(2), rs.getFloat(3), rs.getFloat(4), rs.getFloat(5)));
                
            }
            System.out.println("Thành công");
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Grade t, int index) {
        try (Connection conn = JDBCUtils.getConnection()) {
            PreparedStatement pst = conn.prepareStatement("update grade set tienganh= ?,tinhoc=?,gdtc=? where masv=?");
            pst.setFloat(1, t.getTiengAnh());
            pst.setFloat(2, t.getTinHoc());
            pst.setFloat(3, t.getGDTC());
            pst.setString(4, lst.get(index).getMasv());
            pst.executeUpdate();
            System.out.println("Thành công");
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Grade t) {
        try (Connection conn = JDBCUtils.getConnection()) {
            PreparedStatement pst = conn.prepareStatement("delete grade where masv=?");
            pst.setString(1, t.getMasv());
            pst.executeUpdate();
            System.out.println("Thành công");
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
            e.printStackTrace();
        }
    }

    @Override
    public ArrayList<Grade> getAll() {
        return this.lst;
    }
}
