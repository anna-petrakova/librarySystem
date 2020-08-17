/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petrakova.library.librarysystem.db;

import java.util.List;

/**
 *
 * @author Anna Petráková
 */
public interface LibraryItemManager {
    
    void createLibraryItem(LibraryItem item);
    
    void deleteLibraryItem(LibraryItem item);
    
    LibraryItem findLibraryItemById(Long id);
    
    List<LibraryItem> findAllItems();
    
}
