/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petrakova.library.librarysystem.db;

import java.time.LocalDate;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import org.springframework.stereotype.Component;

/**
 *
 * @author Anna Petráková
 */
@Entity
@Component
public class Borrow {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private LibraryItem item;
    @ManyToOne
    private Client client;
    private LocalDate dueTo;
           
    public void setId(long id) {
        this.id = id;
    }
    
    public long getId() {
        return id;
    }
    
    public void setDueTo(LocalDate date) {
        if (date == null) {
            throw new IllegalArgumentException("Date can not be null");
        }
        this.dueTo = date;
    }
    
    public LocalDate getDueTo() {
        return dueTo;
    }
    
    public Client getClient() {
        return client;
    }
    
    public void setClient(Client c) {
        this.client = c;
    }
    
    public LibraryItem getItem() {
        return item;
    }
    
    public void setItem(LibraryItem item) {
        this.item = item;
    }
}
