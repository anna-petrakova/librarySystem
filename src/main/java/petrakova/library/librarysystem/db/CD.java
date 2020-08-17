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
public class CD extends LibraryItem {
    private String bandName;
    
    public void setBandName(String name) {
        this.bandName = name;
    }
    
    public String getBandName() {
        return bandName;
    }
    
}
