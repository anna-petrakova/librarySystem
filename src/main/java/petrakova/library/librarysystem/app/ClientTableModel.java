/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petrakova.library.librarysystem.app;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import petrakova.library.librarysystem.db.Client;

/**
 *
 * @author Anna Petráková
 */
public class ClientTableModel extends AbstractTableModel {
    private List<Client> clients;
    
    public ClientTableModel(List<Client> clients) {
        this.clients = clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public int getRowCount() {
        return clients.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Client client = clients.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return Long.valueOf(client.getId());
            case 1:
                return client.getFirstName();
            case 2:
                return client.getSurname();
            case 3:
                return client.getEmail();
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }
    
    @Override
    public String getColumnName(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return "Id";
            case 1:
                return "First name";
            case 2:
                return "Surname";
            case 3:
                return "Email";
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return Integer.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            case 3:
                return String.class;
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }
    
}
