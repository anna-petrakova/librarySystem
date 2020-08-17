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
public class ClientManagerImpl implements ClientManager {
    private Session session = DatabaseConnection.getSession();
    public static ClientManagerImpl instance;
    
    public static ClientManagerImpl getInstance() {
        if (instance == null) {
            instance = new ClientManagerImpl();
        }
        return instance;
    }
    
    private ClientManagerImpl() {
    }

    @Override
    public void createClient(Client client) {
        if (client == null) {
            throw new IllegalArgumentException("Client can not be null");
        }
        
        if (client.getEmail() != null && !isValidAddress(client.getEmail())) {
            throw new IllegalArgumentException("email is not in the right format");
        }
        
        Transaction tx = session.beginTransaction(); 
        session.save(client);
        
        tx.commit();     
    }

    @Override
    public void deleteClient(Client client) {
        Transaction tx = session.beginTransaction();   
        
        Query q = session.createQuery("delete from Client where id=" + client.getId());
        q.executeUpdate();               
        
        tx.commit();
    }

    @Override
    public Client findClientById(Long id) {
        Transaction tx = session.beginTransaction();   
        
        Query q = session.createQuery("from Client where id=" + id);
        Client client = (Client) q.uniqueResult();
        
        tx.commit();
        
        return client;
    }

    @Override
    public List<Client> findAllClients() {
        Query q = session.createQuery("from Client");
        List<Client> clients = q.list();
        
        return clients;
    }
    
    private boolean isValidAddress(String address) {
        return address.matches("[a-zA-Z0-9\\.]+@[a-zA-Z0-9\\-\\_\\.]+\\.[a-zA-Z0-9]+");
    }
    
}
