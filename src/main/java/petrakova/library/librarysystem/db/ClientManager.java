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
public interface ClientManager {
    
    void createClient(Client client);
    
    void deleteClient(Client client);
    
    Client findClientById(Long id);
    
    List<Client> findAllClients();
    
}
