package Category;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import sentence.FrmSentences;

public class FrmCategories extends javax.swing.JFrame {

    private DefaultTableModel model;
    private ArrayList<Category> categs;

    public FrmCategories() {
        initComponents();
        try {
            update(); //Llamamos al método que rellena la tabla con los datos de la base de datos
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, !ex.getMessage().equals("") ? ex.getMessage() : ex);
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, "", ex);
        }
    }

    private void update() throws Exception {
        model = new DefaultTableModel();
        model.addColumn("Id"); //Añadimos las columnas a la tabla
        model.addColumn("Nombre");
        categs = new Category().fillTable();
        for (int i = 0; i < categs.size(); i++) {
            Object[] row = new Object[2];
            row[0] = (int) (categs.get(i).id);
            row[1] = categs.get(i).name;
            model.addRow(row);
        }
        tblCategories.setModel(model);
        hideFirstColumn();

        tblCategories.updateUI();//Actualiza la tabla

    }

    private Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return categs.get(rowIndex).getId();
            case 1:
                return categs.get(rowIndex).getName();
            default:
                throw new ArrayIndexOutOfBoundsException();
        }
    }

    //esconde la primera columna de la tabla 
    private void hideFirstColumn() {
        tblCategories.getColumnModel().getColumn(0).setMaxWidth(0);
        tblCategories.getColumnModel().getColumn(0).setMinWidth(0);
        tblCategories.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tblCategories.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblCategories = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnSentences = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblCategories.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Id", "Nombre"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblCategories);
        if (tblCategories.getColumnModel().getColumnCount() > 0) {
            tblCategories.getColumnModel().getColumn(0).setResizable(false);
            tblCategories.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblCategories.getColumnModel().getColumn(1).setResizable(false);
            tblCategories.getColumnModel().getColumn(1).setPreferredWidth(500);
        }

        btnNew.setText("Nueva");
        btnNew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewActionPerformed(evt);
            }
        });

        btnEdit.setText("Editar");
        btnEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditActionPerformed(evt);
            }
        });

        btnDelete.setText("Eliminar");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnSentences.setText("Listado");
        btnSentences.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSentencesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnNew)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnEdit)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnSentences)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNew)
                    .addComponent(btnEdit)
                    .addComponent(btnDelete)
                    .addComponent(btnSentences))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        try {
            new FrmCategory(this, true, null).setVisible(true);
            update();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, !ex.getMessage().equals("") ? ex.getMessage() : ex);
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, "", ex);
        }
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        try {
            if (tblCategories.getSelectedRow() == -1) {
                throw new Exception("Seleccione un elemento de la lista");
            }
            new FrmCategory(this, true, (Integer) getValueAt(tblCategories.getSelectedRow(), 0)).setVisible(true);
            update();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, !ex.getMessage().equals("") ? ex.getMessage() : ex);
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, "", ex);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
            if (tblCategories.getSelectedRow() == -1) {
                throw new Exception("Seleccione un elemento de la lista");
            }
            int answ = JOptionPane.showConfirmDialog(this, "¿Desea eliminar éste elemento?", "Eliminación", JOptionPane.YES_NO_OPTION);
            if (answ == JOptionPane.YES_OPTION) {
                new Category().delete((Integer) getValueAt(tblCategories.getSelectedRow(), 0));
                update();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, !ex.getMessage().equals("") ? ex.getMessage() : ex);
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, "", ex);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnSentencesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSentencesActionPerformed
        try {
            if (tblCategories.getSelectedRow() == -1) {
                throw new Exception("Seleccione un elemento de la lista");
            }
            new FrmSentences((Integer) getValueAt(tblCategories.getSelectedRow(), 0)).setVisible(true);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, !ex.getMessage().equals("") ? ex.getMessage() : ex);
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, "", ex);
        }
    }//GEN-LAST:event_btnSentencesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JButton btnSentences;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblCategories;
    // End of variables declaration//GEN-END:variables
}
