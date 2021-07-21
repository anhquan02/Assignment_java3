/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.Grade;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import pattern.DAO;
import pattern.Service;

/**
 *
 * @author Mypc
 */
public class ServiceGrade implements Service<Grade> {

    public ArrayList<Grade> lst;

    public ServiceGrade() {
        lst = new ArrayList<>();
    }


    @Override
    public ArrayList<Grade> getAll() {
        return this.lst;
    }

    @Override
    public void fillToTable(DefaultTableModel model, ArrayList<Grade> lst) {
        model.setRowCount(0);
        for (Grade x : lst) {
            model.addRow(new Object[]{x.getMasv(), x.getHoTen(), x.getTiengAnh(), x.getTinHoc(), x.getGDTC()});
        }
    }

    @Override
    public void addRow(DefaultTableModel model, Grade x) {
        model.addRow(new Object[]{x.getMasv(), x.getHoTen(), x.getTiengAnh(), x.getTinHoc(), x.getGDTC()});
    }

    @Override
    public void removeRow(DefaultTableModel model, int i) {
        model.removeRow(i);
    }

    @Override
    public void updateRow(DefaultTableModel model, Grade t, int i) {
        model.setValueAt(t.getMasv(), i, 0);
        model.setValueAt(t.getHoTen(), i, 1);
        model.setValueAt(t.getTiengAnh(), i, 2);
        model.setValueAt(t.getTinHoc(), i, 3);
        model.setValueAt(t.getGDTC(), i, 4);

    }

    @Override
    public void setList(ArrayList<Grade> lst) {
        this.lst = lst;
    }

}
