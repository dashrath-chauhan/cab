package org.mbition.cab;

import java.net.URI;
import java.io.IOException;
import javax.ws.rs.core.UriBuilder;
import org.eclipse.jetty.server.Server;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;


import javax.inject.Singleton;
import javax.servlet.http.HttpServlet;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;
import org.apache.catalina.startup.Tomcat;
import org.glassfish.jersey.jetty.JettyHttpContainerFactory;
import org.glassfish.jersey.server.ResourceConfig;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

@Path("myresource")
@Singleton
public class MyResource extends HttpServlet {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    private static StandardServiceRegistry standardServiceRegistry;
    private static SessionFactory sessionFactory;
    
    public static void main(String[] args) throws Exception {
    	startTomcat();
    	//startJetty();
    	startSession();
    }
    
    private static void startJetty() throws Exception {
    	org.eclipse.jetty.server.Server server = new Server(8080);

        ServletContextHandler servletContextHandler = new ServletContextHandler();

        servletContextHandler.setContextPath("/");
        server.setHandler(servletContextHandler);

        ServletHolder servletHolder = servletContextHandler.addServlet(ServletContainer.class, "/webapi/*");
        servletHolder.setInitOrder(0);
        servletHolder.setInitParameter("jersey.config.server.provider.packages","org.mbition.cab.controller");

        try {
            server.start();
            server.join();
        } catch (Exception ex) {
            System.exit(1);
        }

        finally {
            server.destroy();
        }
	}

	public static void startSession() {
    	if (sessionFactory == null) {
            try {
                // Create registry
            	standardServiceRegistry = new StandardServiceRegistryBuilder().configure().build();

                // Create MetadataSources
                MetadataSources sources = new MetadataSources(standardServiceRegistry);

                // Create Metadata
                Metadata metadata = sources.getMetadataBuilder().build();

                // Create SessionFactory
                sessionFactory = metadata.getSessionFactoryBuilder().build();

            } catch (Exception e) {
                e.printStackTrace();
                if (standardServiceRegistry != null) {
                    StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
                }
            }
        }
    }
    
    public static SessionFactory getSessionFactory() {
    	return sessionFactory;
    }
    
    static void startTomcat() throws Exception {

        String port = System.getenv("PORT");
        System.setProperty("java.security.egd", "file:///dev/urandom");
        if (port == null || port.isEmpty()) {
            port = "8080";
        }

        String contextPath = "";
        String appBase = ".";

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(Integer.valueOf(port));
        tomcat.getHost().setAppBase(appBase);
        tomcat.start();
        //tomcat.getServer().await();
    }
}
