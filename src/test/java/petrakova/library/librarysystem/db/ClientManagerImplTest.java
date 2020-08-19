/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petrakova.library.librarysystem.db;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import org.junit.Before;

/**
 * Tests are working and passing, but they manipulate with the "real" database. Some embedded database should be set.
 * @author Anna Petráková
 */
public class ClientManagerImplTest {
    private ClientManagerImpl clientManager;
    
    
    /*@Before
    public void setUp() throws Exception {
        clientManager = ClientManagerImpl.getInstance();
    }
    
    
    public void tearDown() throws Exception {
    }
    
    private ClientBuilder sampleJohnClientBuilder() {
        return new ClientBuilder().name("John")
                            .surname("Green")
                            .email("johngreen@gmail.com");
    }

    private ClientBuilder sampleAliceClientBuilder() {
        return new ClientBuilder().name("Alice")
                .surname("Black")
                .email("Alice.Black@azet.sk");
    }

    private Client nullNameClient() {
        return sampleJohnClientBuilder().name(null).build();
    }

    private Client existingIdClient() {
        return sampleJohnClientBuilder().id(100L).build();
    }
    
    @Test
    public void createClient() {
        Client client = sampleJohnClientBuilder().build();
        clientManager.createClient(client);

        Long clientId = client.getId();
        assertThat(clientId).isNotNull();

        assertThat(clientManager.findClientById(clientId))
                .isEqualToComparingFieldByField(client);
    }

    @Test(expected = IllegalArgumentException.class)
    public void createOwnerClientWithNull() {
        clientManager.createClient(null);
    }

    @Test(expected =IllegalArgumentException.class)
    public void createInvalidNameClient() {
        clientManager.createClient(nullNameClient());
    }
    
    @Test
    public void createInvalidEmailClient() {
        Client client = sampleJohnClientBuilder().email("johngreen gmail").build();

        assertThatExceptionOfType(IllegalArgumentException.class).isThrownBy(() -> {
            clientManager.createClient(client);
        });
    }

    @Test
    public void findClientById() {
        Client client = sampleJohnClientBuilder().build();
        
        clientManager.createClient(client);

        Long ownerId = client.getId();

        assertThat(clientManager.findClientById(ownerId))
                .isEqualToComparingFieldByField(client);
    }

    @Test(expected = IllegalArgumentException.class)
    public void findClientByNullId() {
        clientManager.findClientById(null);
    }

    @Test
    public void findNonExistingClientById() {

        Client john = sampleJohnClientBuilder().build();
        Client alice = sampleAliceClientBuilder().build();

        clientManager.createClient(john);
        clientManager.createClient(alice);

        Long johnId = john.getId();
        Long aliceId = alice.getId();
        Long newId = johnId + aliceId;

        assertThat(clientManager.findClientById(newId)).isNull();
    }
    
   @Test
    public void deleteClient() {

        Client john = sampleJohnClientBuilder().build();
        Client alice = sampleAliceClientBuilder().build();
        clientManager.createClient(john);
        clientManager.createClient(alice);

        assertThat(clientManager.findClientById(john.getId())).isNotNull();
        assertThat(clientManager.findClientById(alice.getId())).isNotNull();

        clientManager.deleteClient(john);

        assertThat(clientManager.findClientById(john.getId())).isNull();
        assertThat(clientManager.findClientById(alice.getId())).isNotNull();

    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteNullClient() {
        clientManager.deleteClient(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void deleteNonExistingClient() {
        Client john = sampleJohnClientBuilder().build();
        
        john.setId(1000L);
        clientManager.deleteClient(john);
    }*/

}
