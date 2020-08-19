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
public interface BorrowManager {
    void createBorrow(Client client, LibraryItem item);
    
    void createBorrow(Borrow borrow);
    
    void deleteBorrow(Client client, LibraryItem item);
    
    void deleteBorrow(Borrow borrow);
    
    void modifyBorrow(Borrow borrow);
    
    List<LibraryItem> findItemsForClient(Client c);
    
    Client findClientForItem(LibraryItem item);
    
    List<Borrow> findBorrowsForClient(Client client);
    
    List<Borrow> findAllBorrows();
    
    Borrow findBorrowById(long id);
    
}
