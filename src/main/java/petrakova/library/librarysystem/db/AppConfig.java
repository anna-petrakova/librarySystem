/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petrakova.library.librarysystem.db;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration file for Spring.
 * @author Anna Petráková
 */
@Configuration
public class AppConfig {
    
    @Bean
    public Book getBook() {
        return new Book();
    }
    
    @Bean
    public Client getClient() {
        return new Client();
    }
    
    @Bean
    public Magazine getMagazine() {
        return new Magazine();
    }
    
    @Bean
    public CD getCD() {
        return new CD();
    }
    
    @Bean
    public Borrow getBorrow() {
        return new Borrow();
    }
}
