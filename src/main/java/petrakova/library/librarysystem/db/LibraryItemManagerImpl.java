/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petrakova.library.librarysystem.db;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author Anna Petráková
 */
public class LibraryItemManagerImpl implements LibraryItemManager {
    private Session session = DatabaseConnection.getSession();
    private static LibraryItemManagerImpl instance;
    
    public static LibraryItemManagerImpl getInstance() {
        if (instance == null) {
            instance = new LibraryItemManagerImpl();
        }
        return instance;
    }
    
    private LibraryItemManagerImpl() {
    }

    @Override
    public void createLibraryItem(LibraryItem item) {
        if (item == null) {
            throw new IllegalArgumentException("Library item can not be null");
        }
        
        item.validateItem();
        
        Transaction tx = session.beginTransaction();   
        session.save(item);
        
        tx.commit();
    }

    @Override
    public void deleteLibraryItem(LibraryItem item) {
        if (item  == null) {
            throw new IllegalArgumentException("Client is null");
        }
        
        Transaction tx = session.beginTransaction();   
        
        Query q = session.createQuery("delete from LibraryItem where id=" + item.getId());
        q.executeUpdate();               
        
        tx.commit();
    }

    @Override
    public LibraryItem findLibraryItemById(Long id) {
        
        Transaction tx = session.beginTransaction();   
        
        Query q = session.createQuery("from LibraryItem where id=" + id);
        LibraryItem item = (LibraryItem) q.uniqueResult();
        
        tx.commit();
        
        return item;
    }

    @Override
    public List<LibraryItem> findAllItems() {
        
        Query q = session.createQuery("from LibraryItem");
        List<LibraryItem> items = q.list();
        
        return items;
    }
    
}
