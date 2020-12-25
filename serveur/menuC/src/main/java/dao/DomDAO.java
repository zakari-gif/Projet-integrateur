/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import javax.swing.text.Document;

/**
 *
 * @author Acer
 */
public abstract class DomDAO<T> implements DAO<T> {
    
    protected String nomFichier;
    protected Document Doc;

    @Override
    public abstract boolean delete(T obj);

    @Override
    public abstract boolean update(T obj);

    @Override
    public abstract T read(String id);

    @Override
    public abstract boolean create(T obj);
    
    
}
