/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import connection.TheConnection;
import java.sql.Connection;

/**
 *
 * @author Acer
 * @param <T>
 */
public abstract class SqlDAO<T> implements DAO<T>{
    
    protected Connection connect;

    public SqlDAO(){
        connect = TheConnection.getInstance();
    }
    
    

    @Override
    public abstract boolean delete(T obj);

    @Override
    public abstract boolean update(T obj);

    @Override
    public abstract boolean create(T obj);
    
    @Override
    public abstract T read(String id);
    

    
        
    
    
}
