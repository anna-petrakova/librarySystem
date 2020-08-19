/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petrakova.library.librarysystem.db;

/**
 *
 * @author Anna Petráková
 */
public class ClientBuilder {
    private Long id;
    private String firstName;
    private String surname;
    private String email;
    
    public ClientBuilder id(Long id) {
        this.id = id;
        return this;
    }
    
    public ClientBuilder name(String name) {
        this.firstName = name;
        return this;
    }

    public ClientBuilder surname(String surname) {
        this.surname = surname;
        return this;
    }

    public ClientBuilder email(String email) {
        this.email = email;
        return this;
    }
    
    public Client build() {
        Client client = new Client();
        client.setFirstName(firstName);
        client.setSurname(surname);
        client.setEmail(email);
                
        return client;
    }

    
}
