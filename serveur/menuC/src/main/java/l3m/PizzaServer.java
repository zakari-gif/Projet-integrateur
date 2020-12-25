package l3m; 

import connection.TheConnection;
import dao.ClientDAO;
import java.sql.*;
import javax.servlet.http.HttpServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.servlet.ServletHandler;
import org.eclipse.jetty.util.thread.QueuedThreadPool;


public class PizzaServer extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private Server server;

    void start() throws Exception {
        int maxThreads = 100;
        int minThreads = 10;
        int idleTimeout = 120;

        QueuedThreadPool threadPool = new QueuedThreadPool(maxThreads, minThreads, idleTimeout);

        server = new Server(threadPool);
        ServerConnector connector = new ServerConnector(server);
        connector.setPort(8090);
        server.setConnectors(new Connector[] { connector });

        ServletHandler servletHandler = new ServletHandler();
        server.setHandler(servletHandler);

        servletHandler.addServletWithMapping(BlockingServlet.class, "/status");
        servletHandler.addServletWithMapping(ClientAuthentificationServlet.class, "/api/authentification");
        servletHandler.addServletWithMapping(ClientUpdateServlet.class, "/api/update");
        servletHandler.addServletWithMapping(CarteServlet.class, "/api/carte");
        servletHandler.addServletWithMapping(CommandeAddServlet.class,"/api/addCommande");
        servletHandler.addServletWithMapping(CommandesClientServlet.class,"/api/getComCli");
        
        server.start();
    }

    void stop() throws Exception {
        System.out.println("Server stop");
    	server.stop();
    }

	public static void main(String[] args) throws Exception {
		PizzaServer server = new PizzaServer();

		server.start();
     
	}

}

