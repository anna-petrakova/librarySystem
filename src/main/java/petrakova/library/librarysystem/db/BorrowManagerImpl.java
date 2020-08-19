/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petrakova.library.librarysystem.db;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.ApplicationContext;

/**
 *
 * @author Anna Petráková
 */
public class BorrowManagerImpl implements BorrowManager {
    private Session session = DatabaseConnection.getSession();
    private ApplicationContext factory = BeanFactory.getFactory();
    private static BorrowManagerImpl instance;
    private ClientManager clientManager = ClientManagerImpl.getInstance();
    private LibraryItemManager itemManager = LibraryItemManagerImpl.getInstance();
    
    public static BorrowManagerImpl getInstance() {
        if (instance == null) {
            instance = new BorrowManagerImpl();
        }
        return instance;
    }
    
    private BorrowManagerImpl() {
    }

    @Override
    public void createBorrow(Client client, LibraryItem item) {
        if (client == null) {
            throw new IllegalArgumentException("client can not be null");
        }
        if (item == null) {
            throw new IllegalArgumentException("item can not be null");
        }
        if (client.getId() == 0) {
            throw new IllegalArgumentException("client has null id");
        }
        if (item.getId() == 0) {
            throw new IllegalArgumentException("item has null id");
        }
        if (itemManager.findLibraryItemById(item.getId()) == null) {
            throw new IllegalArgumentException("item not in db");
        }

        if (clientManager.findClientById(client.getId()) == null) {
            throw new IllegalArgumentException("client does not exist in database");
        }
        if (itemManager.findLibraryItemById(item.getId()) == null) {
            throw new IllegalArgumentException("item does not exist in database");
        }
        if (clientManager.findClientById(client.getId()) == null) {
            throw new IllegalArgumentException("client not in db");
        }

        if (findClientForItem(item) != null) {
            throw new IllegalArgumentException("item already has assigned client");
        }
        
        Borrow borrow = factory.getBean(Borrow.class);
        borrow.setClient(client);
        borrow.setItem(item);
        LocalDate dt = LocalDate.now();
        dt = dt.plusDays(30);
        borrow.setDueTo(dt);
        
        Transaction tx = session.beginTransaction(); 
        session.save(borrow);        
        tx.commit();

    }
    
    public void createBorrow(Borrow borrow) {
        Client client = borrow.getClient();
        LibraryItem item = borrow.getItem();
        
        if (client == null) {
            throw new IllegalArgumentException("client can not be null");
        }
        if (item == null) {
            throw new IllegalArgumentException("item can not be null");
        }
        if (client.getId() == 0) {
            throw new IllegalArgumentException("client has null id");
        }
        if (item.getId() == 0) {
            throw new IllegalArgumentException("item has null id");
        }
        if (itemManager.findLibraryItemById(item.getId()) == null) {
            throw new IllegalArgumentException("item not in db");
        }

        if (clientManager.findClientById(client.getId()) == null) {
            throw new IllegalArgumentException("client does not exist in database");
        }
        if (itemManager.findLibraryItemById(item.getId()) == null) {
            throw new IllegalArgumentException("item does not exist in database");
        }
        if (clientManager.findClientById(client.getId()) == null) {
            throw new IllegalArgumentException("client not in db");
        }

        if (findClientForItem(item) != null) {
            throw new IllegalArgumentException("item already has assigned client");
        }
        
        validate(borrow);
        
        Transaction tx = session.beginTransaction(); 
        session.save(borrow);        
        tx.commit();
    }

    @Override
    public void deleteBorrow(Client client, LibraryItem item) {
        if (client == null) {
            throw new IllegalArgumentException("client can not be null");
        }
        if (item == null) {
            throw new IllegalArgumentException("item can not be null");
        }
        if (client.getId() == 0) {
            throw new IllegalArgumentException("client has null id");
        }
        if (item.getId() == 0) {
            throw new IllegalArgumentException("item has null id");
        }
        if (itemManager.findLibraryItemById(item.getId()) == null) {
            throw new IllegalArgumentException("item not in db");
        }

        if (clientManager.findClientById(client.getId()) == null) {
            throw new IllegalArgumentException("client does not exist in database");
        }
        if (itemManager.findLibraryItemById(item.getId()) == null) {
            throw new IllegalArgumentException("item does not exist in database");
        }
        if (clientManager.findClientById(client.getId()) == null) {
            throw new IllegalArgumentException("client not in db");
        }

        if (findClientForItem(item) != client) {
            throw new IllegalArgumentException("item has a different assigned client");
        }
        
        Transaction tx = session.beginTransaction();   
        
        Query q = session.createQuery("delete from Borrow where client_id=" + client.getId() + " and item_id=" + item.getId());
        q.executeUpdate();               
        
        tx.commit();
    }
    
    public void deleteBorrow(Borrow borrow) {
        if (borrow == null) {
            throw new IllegalArgumentException("Client is null");
        }
        Transaction tx = session.beginTransaction();   
        
        Query q = session.createQuery("delete from Borrow where id=" + borrow.getId());
        q.executeUpdate();               
        
        tx.commit();
    }

    @Override
    public void modifyBorrow(Borrow borrow) {
                
        validate(borrow);
        
        if (findBorrowById(borrow.getId()) == null) {
            throw new IllegalArgumentException("this borrow is not in the database");
        }
        
        Transaction tx = session.beginTransaction();  
        session.update(borrow);
        
        tx.commit();        
    }

    @Override
    public List<LibraryItem> findItemsForClient(Client c) {
        if (c == null) {
            throw new IllegalArgumentException("client must exist");
        }
        
        if (clientManager.findClientById(c.getId()) == null) {
            throw new IllegalArgumentException("client is not in the database");
        }
                
        Transaction tx = session.beginTransaction(); 
        Query q = session.createQuery("select item from Borrow where client_id=" + c.getId());
        List<LibraryItem> items = q.list();
        tx.commit();        
        
        return items;
    }

    @Override
    public Client findClientForItem(LibraryItem item) {
        if (item == null) {
            throw new IllegalArgumentException("item must exist");
        }
        
        if (itemManager.findLibraryItemById(item.getId()) == null) {
            throw new IllegalArgumentException("item is not in the database");
        }
        
        Transaction tx = session.beginTransaction();   
        
        Query q = session.createQuery("select client from Borrow where item_id=" + item.getId());
        Client client = (Client) q.uniqueResult();
        
        tx.commit();
        
        return client;
    }
    
    public Borrow findBorrowById(long id) {
        Transaction tx = session.beginTransaction();   
        
        Query q = session.createQuery("from Borrow where id=" + id);
        Borrow borrow = (Borrow) q.uniqueResult();
        
        tx.commit();
        
        return borrow;
    }
    
    public Borrow findBorrow(Client c, LibraryItem item) {
        if (item == null) {
            throw new IllegalArgumentException("item must exist");
        }
        
        if (itemManager.findLibraryItemById(item.getId()) == null) {
            throw new IllegalArgumentException("item is not in the database");
        }
        
        if (c == null) {
            throw new IllegalArgumentException("client must exist");
        }
        
        if (clientManager.findClientById(c.getId()) == null) {
            throw new IllegalArgumentException("client is not in the database");
        }
        
        Transaction tx = session.beginTransaction();   
        
        Query q = session.createQuery("from Borrow where client_id=" + c.getId() + " and item_id=" + item.getId());
        Borrow borrow = (Borrow) q.uniqueResult();
        
        tx.commit();
        
        return borrow;
        
    }
    
    public List<Borrow> findBorrowsForClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("client must exist");
        }
        
        if (clientManager.findClientById(client.getId()) == null) {
            throw new IllegalArgumentException("client is not in the database");
        }
                
        Transaction tx = session.beginTransaction();  
        
        Query q = session.createQuery("from Borrow where client_id=" + client.getId());
        
        List<Borrow> items = q.list();
        
        tx.commit();        
        
        return items;
    }
    
    public List<Borrow> findAllBorrows() {
        Query q = session.createQuery("from Borrow");
        List<Borrow> borrows = q.list();
        
        return borrows;
    }
    
    private void validate(Borrow borrow) {
        if (borrow == null) {
            throw new IllegalArgumentException("this borrow does not exist");
        }       
        LocalDateTime ldt = LocalDateTime.now();
        if (borrow.getDueTo() != null && borrow.getDueTo().isBefore(ldt.toLocalDate())) {
            throw new IllegalArgumentException("due to date can not be in the past");
        }
        
        if (borrow.getClient() == null) {
            throw new IllegalArgumentException("the item must belong to someone");
        }
        
        if (borrow.getItem() == null) {
            throw new IllegalArgumentException("client must have borrowed something");
        }

    }
    
}
