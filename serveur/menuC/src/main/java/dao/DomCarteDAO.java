/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import cinemaMenu.Carte;
import cinemaMenu.Plat;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.w3c.dom.Element;
/**
 *
 * @author Acer
 */
public class DomCarteDAO extends DomDAO<Carte> 
{

    @Override
    public boolean delete(Carte obj) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Carte obj) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

        @Override
    public Carte read(String id) 
    {
        ArrayList<Plat> lesPlats = new ArrayList<Plat>();
        
        try {
            DOMParser parser = new DOMParser();
            parser.parse("src/main/java/xml/carte.xml");
            Document doc = parser.getDocument();
            
            NodeList plats = doc.getElementsByTagName("plat");
            for(int i = 0; i<plats.getLength(); i++) {
                String nom = (((Element)plats.item(i)).getAttribute("nomPlat"));
                NodeList platChildNode = plats.item(i).getChildNodes();
                String typePlat = platChildNode.item(1).getTextContent();
                double prix = Double.parseDouble(platChildNode.item(3).getTextContent());
                String photo = platChildNode.item(5).getTextContent();
                NodeList ingredientsNodeList = plats.item(i).getChildNodes().item(7).getChildNodes();
                ArrayList<String> ingredients = new ArrayList<String>();
                for(int j = 1; j<ingredientsNodeList.getLength();j = j+2) {
                    ingredients.add(ingredientsNodeList.item(j).getTextContent());
                }
                Plat plat = new Plat(nom, typePlat, prix, photo, ingredients);
                lesPlats.add(plat);
            }
        }
        catch(IOException ex) {
            Logger.getLogger(DomPlatDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SAXException ex) {
            Logger.getLogger(DomCarteDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        Carte uneCarte = new Carte(lesPlats);
        return uneCarte;
    }

    @Override
    public boolean create(Carte obj) 
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
