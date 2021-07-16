/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pattern;

import java.util.ArrayList;

/**
 *
 * @author Mypc
 */
public interface DAO<T> {

    public boolean create(T t);

    public void read();

    public void update(T t,int index);

    public void delete(T t);

    public ArrayList<T> getAll();

}
