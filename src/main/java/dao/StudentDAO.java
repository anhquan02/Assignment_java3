/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import model.Student;
import pattern.DAO;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import util.JDBCUtils;

/**
 *
 * @author Mypc
 */
public class StudentDAO implements DAO<Student> {

    ArrayList<Student> lst;

    public StudentDAO() {
        lst = new ArrayList<>();
        read();

    }

    @Override
    public boolean create(Student t) {
        try (Connection conn =JDBCUtils.getConnection()) {
            PreparedStatement pst = conn.prepareStatement("insert into student values(?,?,?,?,?,?,?)");
            pst.setString(1, t.getMasv());
            pst.setString(2, t.getHoTen());
            pst.setString(3, t.getEmail());
            pst.setString(4, t.getSdt());
            int gt;
            if (t.getGioiTinh().equals("Nam")) {
                gt = 1;
            } else {
                gt = 0;
            }
            pst.setInt(5, gt);
            pst.setString(6, t.getDiaChi());
            pst.setString(7, t.getHinh());
            pst.executeUpdate();
            System.out.println("Thành công");
            conn.close();
            return true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void read() {
        try (Connection conn = JDBCUtils.getConnection()) {
            Statement st = conn.createStatement();
            String query = "Select * from student";
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                Student stt = new Student();
                stt.setMasv(rs.getString(1));
                stt.setHoTen(rs.getString(2));
                stt.setEmail(rs.getString(3));
                stt.setSdt(rs.getString(4));
                boolean a = rs.getBoolean(5);
                if (a) {
                    stt.setGioiTinh("Nam");
                } else {
                    stt.setGioiTinh("Nữ");
                }
                stt.setDiaChi(rs.getString(6));
                stt.setHinh(rs.getString(7));
                lst.add(stt);
            }
            System.out.println("Thành công");
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
            e.printStackTrace();
        }
    }

    @Override
    public void update(Student t, int index) {
        try (Connection conn =JDBCUtils.getConnection()) {
            PreparedStatement pst = conn.prepareStatement("update student set hoten=?,email=?,sdt=?,gioiTinh=?,diachi=?,hinh=? where masv=?");
            pst.setString(1, t.getHoTen());
            pst.setString(2, t.getEmail());
            pst.setString(3, t.getSdt());
            int gt;
            if (t.getGioiTinh().equals("Nam")) {
                gt = 1;
            } else {
                gt = 0;
            }
            pst.setInt(4, gt);
            pst.setString(5, t.getDiaChi());
            pst.setString(6, t.getHinh());
            pst.setString(7, lst.get(index).getMasv());
            pst.executeUpdate();
            System.out.println("Thành công");
            conn.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error");
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Student t) {
        try (Connection conn = JDBCUtils.getConnection()) {
            PreparedStatement pst = conn.prepareStatement("delete student where masv=?");
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
    public ArrayList<Student> getAll() {
        return this.lst;
    }

}
