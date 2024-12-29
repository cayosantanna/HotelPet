/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package dao;

import java.util.List;

/**
 *
 * @author thais
 */
public interface IDao<T> {
    void save(T obj);
    void update(T obj, T novo);  // Modificar para aceitar dois parâmetros
    boolean delete(T obj);
    T find(T obj);
    List<T> findAll();
}
