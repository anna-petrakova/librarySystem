/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petrakova.library.librarysystem.db;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 * File for creating session for database.
 * @author Anna Petráková
 */
public class DatabaseConnection {
    private static Session session;
    
    public static Session getSession() {
        if (session == null) {
            Configuration conf = new Configuration().configure("hibernate.cfg.xml").addAnnotatedClass(Client.class)
                                                            .addAnnotatedClass(Book.class)
                                                            .addAnnotatedClass(Magazine.class)
                                                            .addAnnotatedClass(CD.class)
                                                            .addAnnotatedClass(Borrow.class);
            ServiceRegistry reg = new ServiceRegistryBuilder().applySettings(conf.getProperties()).buildServiceRegistry();        
            SessionFactory sf = conf.buildSessionFactory(reg);        
            Session session = sf.openSession();
            
            DatabaseConnection.session = session;
        } 
        return session;
    }
    
    private DatabaseConnection(){};
    
}
