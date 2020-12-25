/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

/**
 *
 * @author Acer
 * @param <T>
 */
public interface DAO<T>{
    
    public boolean create(T obj);
    public T read(String id);
    public boolean update(T obj);
    public boolean delete(T obj);
    
}
