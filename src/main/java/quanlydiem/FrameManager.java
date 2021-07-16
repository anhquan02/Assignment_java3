/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quanlydiem;

import java.util.ArrayList;
import pattern.DAO;

/**
 *
 * @author Mypc
 */
public class FrameManager implements DAO<Grade>{
    ArrayList<Grade>lst ;

    public FrameManager() {
        lst = new ArrayList<>();
    }
    
    @Override
    public boolean create(Grade t) {
        lst.add(t);
        return true;
    }

    @Override
    public void read() {
    }

    @Override
    public void update(Grade t, int index) {
        Grade grade = lst.get(index);
        grade.setTiengAnh(t.getTiengAnh());
        grade.setTinHoc(t.getTinHoc());
        grade.setGDTC(t.getGDTC());
    }

    @Override
    public void delete(Grade t) {
        lst.remove(lst.indexOf(t));
    }

    @Override
    public ArrayList<Grade> getAll() {
        return this.lst;
    }
    
}
