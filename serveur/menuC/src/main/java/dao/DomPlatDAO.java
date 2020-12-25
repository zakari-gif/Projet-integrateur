/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
import cinemaMenu.Ingredient;
import com.sun.org.apache.xerces.internal.parsers.DOMParser;
import cinemaMenu.Plat;
import cinemaMenu.TypeDePlat;
import com.sun.org.apache.xpath.internal.NodeSet;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
/**
 *
 * @author Acer
 */
public class DomPlatDAO extends DomDAO<Plat>
{

    @Override
    public boolean delete(Plat obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Plat obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

     @Override
    public Plat read(String id)
    {

        Plat unPlat = new Plat();
        
        try 
        {
            DOMParser parserPlat = new DOMParser();
            parserPlat.parse("src/main/java/xml/carte.xml");
            Document docPlat = parserPlat.getDocument();
            XPath xPath = XPathFactory.newInstance().newXPath();
            String expression = "//*[local-name()='plat'][@*[local-name() = 'nomPlat' and .=\""+id+"\"]]";
            NodeList plat = (NodeList)xPath.compile(expression).evaluate(docPlat, XPathConstants.NODESET);
            
            String nom = (((Element)plat.item(0)).getAttribute("nomPlat"));
                NodeList platChildNode = plat.item(0).getChildNodes();
                String typePlat = platChildNode.item(1).getTextContent();
                double prix = Double.parseDouble(platChildNode.item(3).getTextContent());
                String photo = platChildNode.item(5).getTextContent();
                NodeList ingredientsNodeList = plat.item(0).getChildNodes().item(7).getChildNodes();
                ArrayList<String> ingredients = new ArrayList<String>();
                for(int j = 1; j<ingredientsNodeList.getLength();j = j+2) {
                    ingredients.add(ingredientsNodeList.item(j).getTextContent());
                }
                unPlat = new Plat(nom, typePlat, prix, photo, ingredients);
        
        } 
        catch (org.xml.sax.SAXException ex) 
        {
            Logger.getLogger(DomPlatDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        catch (IOException ex)
        {
            Logger.getLogger(DomPlatDAO.class.getName()).log(Level.SEVERE, null, ex);
        } catch (XPathExpressionException ex) {
            Logger.getLogger(DomPlatDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return unPlat;


    }

    @Override
    public boolean create(Plat obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
