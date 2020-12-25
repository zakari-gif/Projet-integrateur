/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import cinemaMenu.Plat;

import javax.xml.parsers.SAXParser;

/**
 *
 * @author Acer
 */
public class SaxDAO implements DAO <Plat> {
    
    protected SAXParser saxParser;

    //Non implémentée
    @Override
    public boolean create(Plat obj) {
        return false;
    }

    //A implementer
    @Override
    public Plat read(String id) {
        return null;
    }

    //Non implémentée
    @Override
    public boolean update(Plat obj) {
       return false;
    }

    //Non implémentée
    @Override
    public boolean delete(Plat obj) {
       return false;
    }
    
    
}
