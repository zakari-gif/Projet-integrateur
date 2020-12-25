/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package l3m;

import cinemaMenu.Client;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Mathis
 */
public class ClientUpdateServlet extends HttpServlet{
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
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        String photo = request.getParameter("photo");
        String email = request.getParameter("mail");
        String tel = request.getParameter("tel");
        String numero = request.getParameter("numero");
        String rue = request.getParameter("rue");
        String cp = request.getParameter("cp");
        String ville = request.getParameter("ville");

        // Call the database and return result
        try {
            
            String res = "";
                
           
                if(request.getParameterMap().containsKey("id") && 
               request.getParameterMap().containsKey("nom") &&
               request.getParameterMap().containsKey("prenom") &&
               request.getParameterMap().containsKey("mail") &&
               request.getParameterMap().containsKey("photo") &&
               request.getParameterMap().containsKey("tel") &&
               request.getParameterMap().containsKey("numero") &&
               request.getParameterMap().containsKey("rue") &&
               request.getParameterMap().containsKey("cp") &&
               request.getParameterMap().containsKey("ville")){
                  
               response.setStatus(HttpServletResponse.SC_OK);
               res = BdAccess.updateUser(new Client(id, nom, prenom, photo, email, tel, numero, rue, cp, ville));
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
