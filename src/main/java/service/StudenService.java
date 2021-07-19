/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import model.Student;
import java.util.ArrayList;
import pattern.DAO;

/**
 *
 * @author Mypc
 */
public class StudenService implements DAO<Student>{
    public ArrayList<Student> lst ;

    public StudenService() {
        lst = new ArrayList<>();
    }
    
    @Override
    public boolean create(Student t) {
        lst.add(t);
        return true;
    }

    @Override
    public void read() {
    }

    @Override
    public void update(Student t, int index) {
        Student st = lst.get(index);
        st.setDiaChi(t.getDiaChi());
        st.setEmail(t.getEmail());
        st.setGioiTinh(t.getGioiTinh());
        st.setHinh(t.getHinh());
        st.setHoTen(t.getHoTen());
        st.setSdt(t.getSdt());
        st.setMasv(t.getMasv());
    }

    @Override
    public void delete(Student t) {
        this.lst.remove(lst.indexOf(t));
    }

    @Override
    public ArrayList<Student> getAll() {
        return this.lst;
    }
    
}
