/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern;

import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Mypc
 */
public interface Service<T> {
    public void fillToTable(DefaultTableModel model ,ArrayList<T> lst);
    public void addRow(DefaultTableModel model, T t);
    public void removeRow(DefaultTableModel model, int i);
    public void updateRow(DefaultTableModel model, T t,int i);
    public ArrayList<T> getAll();
    public void setList(ArrayList<T> lst);
}
