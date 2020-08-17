/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petrakova.library.librarysystem.db;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author Anna Petráková
 */
public class BeanFactory {
    private static ApplicationContext factory;
    
    public static ApplicationContext getFactory() {
        if (factory == null) {
            factory = new AnnotationConfigApplicationContext(AppConfig.class);
        }
        return factory;
    }
    
    private BeanFactory(){};
}
