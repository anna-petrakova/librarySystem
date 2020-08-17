/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petrakova.library.librarysystem.app;

import java.time.LocalDate;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import petrakova.library.librarysystem.db.Borrow;
import petrakova.library.librarysystem.db.Client;

/**
 *
 * @author Anna Petráková
 */
public class BorrowTableModel extends AbstractTableModel {
    private List<Borrow> borrows;
    
    public BorrowTableModel(List<Borrow> borrows) {
        this.borrows = borrows;
    }

    public void setBorrows(List<Borrow> borrows) {
        this.borrows = borrows;
    }

    @Override
    public int getRowCount() {
        return borrows.size();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }


    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Borrow borrow = borrows.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return Long.valueOf(borrow.getId());
            case 1:
                return Long.valueOf(borrow.getClient().getId());
            case 2:
                return Long.valueOf(borrow.getItem().getId());
            case 3:
                return borrow.getDueTo();
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
                return "Client id";
            case 2:
                return "Item id";
            case 3:
                return "Due to";
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
                return Integer.class;
            case 2:
                return Integer.class;
            case 3:
                return LocalDate.class;
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }
    
}
