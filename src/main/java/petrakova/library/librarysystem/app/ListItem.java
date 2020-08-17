/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package petrakova.library.librarysystem.app;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.hibernate.Session;
import org.springframework.context.ApplicationContext;
import petrakova.library.librarysystem.db.BeanFactory;
import petrakova.library.librarysystem.db.BorrowManager;
import petrakova.library.librarysystem.db.BorrowManagerImpl;
import petrakova.library.librarysystem.db.Client;
import petrakova.library.librarysystem.db.ClientManager;
import petrakova.library.librarysystem.db.ClientManagerImpl;
import petrakova.library.librarysystem.db.DatabaseConnection;
import petrakova.library.librarysystem.db.LibraryItem;
import petrakova.library.librarysystem.db.LibraryItemManager;
import petrakova.library.librarysystem.db.LibraryItemManagerImpl;

/**
 *
 * @author Anna Petráková
 */
public class ListItem extends javax.swing.JFrame {
    private Session session = DatabaseConnection.getSession();
    private ApplicationContext factory = BeanFactory.getFactory();
    private ListSelectionModel model;
    private List<LibraryItem> items = new ArrayList<>();
    private ItemTableModel tableModel = new ItemTableModel(items);
    private LibraryItemManager itemManager = LibraryItemManagerImpl.getInstance();

    /**
     * Creates new form SearchItem
     */
    public ListItem() {
        initComponents();
        
        itemsTable.setModel(tableModel);
        itemsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        model = itemsTable.getSelectionModel();

        deleteButton.setEnabled(false);
        deleteButton.setVisible(false);
        
        ListSwingWorker listSwingWorker = new ListSwingWorker();
        listSwingWorker.execute();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        itemsTable = new javax.swing.JTable();
        deleteButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("List items");

        itemsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(itemsTable);

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(deleteButton)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(deleteButton)
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        if (getSelectedItemId() == null) {
            return;
        }
        //DeleteSwingWorker deleteSwingWorker = new DeleteSwingWorker();
        //deleteSwingWorker.execute();
        delete();
    }//GEN-LAST:event_deleteButtonActionPerformed
    
    public Long getSelectedItemId() {
        if (model.getMaxSelectionIndex() == -1) {
            return null;
        }
        return (Long) tableModel.getValueAt(model.getMaxSelectionIndex(), 0);
    }
    
    public void setDeleteButton() {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                deleteButton.setVisible(true);
                deleteButton.setEnabled(true);
            }
        });
    }
    
    public void delete() {
        BorrowManager borrowManager = factory.getBean(BorrowManager.class);
        LibraryItem item = itemManager.findLibraryItemById(getSelectedItemId());

        if (borrowManager.findClientForItem(item) != null) {
            JOptionPane.showMessageDialog(null, "This item is borrowed",
                    "Error Message", JOptionPane.ERROR_MESSAGE);
            return;
        }

        itemManager.deleteLibraryItem(item);
        
        items = itemManager.findAllItems();
            
        tableModel.setItems(items);
        tableModel.fireTableDataChanged();
    }
    
    /*private class DeleteSwingWorker extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {
            LibraryItemManager itemManager = new LibraryItemManagerImpl(session);
            BorrowManager borrowManager = new BorrowManagerImpl(session);
            LibraryItem item = itemManager.findLibraryItemById(getSelectedItemId());

            if (borrowManager.findClientForItem(item) != null) {
                JOptionPane.showMessageDialog(null, "This item is borrowed",
                        "Error Message", JOptionPane.ERROR_MESSAGE);
                return null;
            }

            itemManager.deleteLibraryItem(item);
            return null;
        }

        protected void done() {
            LibraryItemManager itemManager = new LibraryItemManagerImpl(session);
            items = itemManager.findAllItems();
            
            tableModel.setItems(items);
            tableModel.fireTableDataChanged();
        }
    }*/
    
    private class ListSwingWorker extends SwingWorker<Void, Void> {

        @Override
        protected Void doInBackground() throws Exception {
            List<LibraryItem> itemsFound;
            try {
                itemsFound = itemManager.findAllItems();
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(),
                        "Error Message", JOptionPane.ERROR_MESSAGE);
                 itemsFound = new ArrayList<>();
            }
            
            items = itemsFound;
            tableModel.setItems(items);
            tableModel.fireTableDataChanged();
            return null;
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteButton;
    private javax.swing.JTable itemsTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
