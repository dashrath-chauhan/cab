package org.mbition.cab;

import java.net.URI;
import java.io.IOException;
import java.io.File;
import javax.inject.Singleton;
import java.io.IOException;
import java.io.PrintWriter;
import org.mbition.cab.controller.UserController;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.WebResourceRoot;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;
import org.apache.catalina.Context;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.init.JerseyServletContainerInitializer;

@Path("/myresource")
@Singleton
public class MyResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt() {
        return "Got it!";
    }
    
    private static StandardServiceRegistry standardServiceRegistry;
    private static SessionFactory sessionFactory;
    
    public static void main(String[] args) throws Exception {
    	startTomcat();
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
		Tomcat tomcat = new Tomcat();

        String port = System.getenv("PORT");
        
        if (port == null || port.isEmpty()) {
            port = "8080";
        }

		String webappDirLocation = "src/main/webapp/";
        String contextPath = "/webapi/";
        String appBase = ".";
		String docBase = new File(appBase).getAbsolutePath();

		Context context = tomcat.addContext(contextPath, docBase);
		tomcat.addWebapp("/cab", new File(webappDirLocation).getAbsolutePath());

		// Define and bind web.xml file location.
		File configFile = new File(webappDirLocation + "WEB-INF/web.xml");
		context.setConfigFile(configFile.toURI().toURL());	

        tomcat.setPort(Integer.valueOf(port));
        tomcat.getHost().setAppBase(appBase);
        tomcat.start();
		//create session factory and load initial database
		startSession();
        tomcat.getServer().await();
    }

}










