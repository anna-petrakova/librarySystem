/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petrakova.library.librarysystem.app;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import petrakova.library.librarysystem.db.Client;
import petrakova.library.librarysystem.db.LibraryItem;

/**
 *
 * @author Anna Petráková
 */
public class ItemTableModel extends AbstractTableModel {
    private List<LibraryItem> items;
    
    public ItemTableModel(List<LibraryItem> items) {
        this.items = items;
    }

    public void setItems(List<LibraryItem> items) {
        this.items = items;
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        LibraryItem item = items.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return Long.valueOf(item.getId());
            case 1:
                String[] nameParts = item.getClass().getCanonicalName().split("\\.");
                return nameParts[nameParts.length - 1];
            case 2:
                return item.getName();
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
                return "Type";
            case 2:
                return "Name";
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
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }
    
}
