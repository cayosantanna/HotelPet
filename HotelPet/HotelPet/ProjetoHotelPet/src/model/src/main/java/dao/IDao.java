/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;
import model.Pet;

/**
 *
 * @author thais
 */
public interface IDao<T> {
<<<<<<< HEAD
    public void save(T obj);
    
    public void update(T obj, Pet novo);
    
    public boolean delete(T obj);
            
    public T find(T obj);
        
    public List<T> findAll();
}
=======
    void save(T obj);
    void update(T obj, T novo); 
    boolean delete(T obj);
    T find(T obj);
    List<T> findAll();
}
>>>>>>> Main
