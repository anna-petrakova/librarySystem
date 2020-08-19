/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petrakova.library.librarysystem.db;

import javax.persistence.Entity;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anna Petráková
 */
@Entity
@Component
public class Book extends LibraryItem {
    private String isbn;
    private String author;
    
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    
    public String getIsbn() {
        return isbn;
    }
    
    public void setAuthor(String author) {
        this.author = author;
    }
    
    public String getAuthor() {
        return author;
    }

    @Override
    public void validateItem() {
        if (!author.matches("[A-Z]+[a-zA-Z]*")) {
            throw new IllegalArgumentException("Authors name is not int the right format.");
        }
        if (!isbn.matches("[0-9\\-]+")) {
            throw new IllegalArgumentException("ISBN is not int the right format.");
        }        
    }
    
}
