/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.Student;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import pattern.DAO;
import pattern.Service;

/**
 *
 * @author Mypc
 */
public class ServiceStudent implements  Service<Student> {

    public ArrayList<Student> lst;

    public ServiceStudent() {
        lst = new ArrayList<>();
    }


    @Override
    public ArrayList<Student> getAll() {
        return this.lst;
    }

    @Override
    public void addRow(DefaultTableModel model, Student t) {
        model.addRow(new Object[]{t.getMasv(), t.getHoTen(), t.getEmail(), t.getSdt(), t.getGioiTinh(), t.getDiaChi(), t.getHinh()});
    }

    @Override
    public void removeRow(DefaultTableModel model, int i) {
        model.removeRow(i);
    }

    @Override
    public void updateRow(DefaultTableModel model, Student t, int i) {
        model.setValueAt(t.getMasv(), i, 0);
        model.setValueAt(t.getHoTen(), i, 1);
        model.setValueAt(t.getEmail(), i, 2);
        model.setValueAt(t.getSdt(), i, 3);
        model.setValueAt(t.getGioiTinh(), i, 4);
        model.setValueAt(t.getDiaChi(), i, 5);
        model.setValueAt(t.getHinh(), i, 6);
    }

    @Override
    public void fillToTable(DefaultTableModel model, ArrayList<Student> lst) {
        model.setRowCount(0);
        for (Student st : lst) {
            model.addRow(new Object[]{st.getMasv(), st.getHoTen(), st.getEmail(), st.getSdt(), st.getGioiTinh(), st.getDiaChi(), st.getHinh()});
        }
    }

    @Override
    public void setList(ArrayList<Student> lst) {
        this.lst=lst;
    }

}
