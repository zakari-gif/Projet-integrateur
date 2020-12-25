/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l3m;

import cinemaMenu.Client;
import cinemaMenu.Commande;
import cinemaMenu.Plat;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mathis
 */
public class CommandeAddServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_OK);
        response.getWriter().println( this.processQueryTest(request) );
    }

        /**
         *
         * @param request
         * @param response
         * @throws ServletException
         * @throws IOException
         */
        @Override
        @SuppressWarnings("empty-statement")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/plain");
		// Extract userId from HTTP parameters

        String id = request.getParameter("id");
        String numero = request.getParameter("numero");
        String rue = request.getParameter("rue");
        String cp = request.getParameter("cp");
        String ville = request.getParameter("ville");
        ArrayList<String> films = new ArrayList<String>(Arrays.asList(request.getParameter("films").split(",")));
        ArrayList<String> quantites = new ArrayList<String>(Arrays.asList(request.getParameter("quantites").split(",")));
        ArrayList<String> nomsPlats = new ArrayList<String>(Arrays.asList(request.getParameter("nomsPlats").split(",")));
        ArrayList<String> prixPlats = new ArrayList<String>(Arrays.asList(request.getParameter("prixPlats").split(",")));

        // Call the database and return result
        try {
            
            String res = "";
                
           
                if(request.getParameterMap().containsKey("id") && 
               request.getParameterMap().containsKey("prixPlats") &&
               request.getParameterMap().containsKey("films") &&
               request.getParameterMap().containsKey("quantites") &&
               request.getParameterMap().containsKey("nomsPlats") &&
               request.getParameterMap().containsKey("numero") &&
               request.getParameterMap().containsKey("rue") &&
               request.getParameterMap().containsKey("cp") &&
               request.getParameterMap().containsKey("ville")){
                  
               ArrayList<Plat> lesPlats = new ArrayList<Plat>();
               double prix = films.size()*3.75;
               for(int i = 0; i<nomsPlats.size();i++) {
                   lesPlats.add(new Plat(nomsPlats.get(i),Integer.parseInt(quantites.get(i)),Double.parseDouble(prixPlats.get(i))));
                   prix += (Integer.parseInt(quantites.get(i))*Double.parseDouble(prixPlats.get(i)));
               }
               res = BdAccess.addCommande(new Commande(0,id,lesPlats,films, numero,rue,cp,ville,prix));
               response.setStatus(HttpServletResponse.SC_OK);
               //res = BdAccess.updateUser(new Client(id, nom, prenom, photo, email, tel, numero, rue, cp, ville));
            }
            else
            {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
	    response.getWriter().println(res);
            
        } catch (SQLException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println( e.toString() );
        }
    }

	private String processQueryTest(HttpServletRequest request) {
		String res = "{";
		Enumeration<String> P = request.getParameterNames();
		while (P.hasMoreElements()) {
			String p = P.nextElement();
			res += "\"" + p + "\": \"" + request.getParameter(p) + "\",";
		}
		return res + "}";
	}
}
