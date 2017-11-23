package sentence;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FrmSentences extends javax.swing.JFrame {

    private DefaultTableModel model;
    private ArrayList<Sentence> sents;
    private int catId;

    public FrmSentences(int catId) {
        initComponents();
        this.catId = catId;
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
        model.addColumn("Inglés");
         model.addColumn("Español");
        sents = new Sentence().fillTable(catId);
        for (int i = 0; i < sents.size(); i++) {
            Object[] row = new Object[3];
            row[0] = (int) (sents.get(i).id);
            row[1] = sents.get(i).english;
            row[2] = sents.get(i).spanish;
            model.addRow(row);
        }
        tblSentences.setModel(model);
        hideFirstColumns();

        tblSentences.updateUI();//Actualiza la tabla

    }

    private Object getValueAt(int rowIndex) {
        return sents.get(rowIndex).id;
    }

    //esconde la primera columna de la tabla 
    private void hideFirstColumns() {
        tblSentences.getColumnModel().getColumn(0).setMaxWidth(0);
        tblSentences.getColumnModel().getColumn(0).setMinWidth(0);
        tblSentences.getTableHeader().getColumnModel().getColumn(0).setMaxWidth(0);
        tblSentences.getTableHeader().getColumnModel().getColumn(0).setMinWidth(0);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tblSentences = new javax.swing.JTable();
        btnNew = new javax.swing.JButton();
        btnEdit = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        tblSentences.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Id", "Inglés", "Español"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblSentences);
        if (tblSentences.getColumnModel().getColumnCount() > 0) {
            tblSentences.getColumnModel().getColumn(0).setResizable(false);
            tblSentences.getColumnModel().getColumn(0).setPreferredWidth(0);
            tblSentences.getColumnModel().getColumn(1).setResizable(false);
            tblSentences.getColumnModel().getColumn(1).setPreferredWidth(500);
            tblSentences.getColumnModel().getColumn(2).setResizable(false);
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
                    .addComponent(btnDelete))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewActionPerformed
        try {
            new FrmSentence(this, true, catId, null).setVisible(true);
            update();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, !ex.getMessage().equals("") ? ex.getMessage() : ex);
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, "", ex);
        }
    }//GEN-LAST:event_btnNewActionPerformed

    private void btnEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditActionPerformed
        try {
            if (tblSentences.getSelectedRow() == -1) {
                throw new Exception("Seleccione un elemento de la lista");
            }
            new FrmSentence(this, true, null, (Integer) getValueAt(tblSentences.getSelectedRow())).setVisible(true);
            update();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, !ex.getMessage().equals("") ? ex.getMessage() : ex);
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, "", ex);
        }
    }//GEN-LAST:event_btnEditActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        try {
            if (tblSentences.getSelectedRow() == -1) {
                throw new Exception("Seleccione un elemento de la lista");
            }
            int answ = JOptionPane.showConfirmDialog(this, "¿Desea eliminar éste elemento?", "Eliminación", JOptionPane.YES_NO_OPTION);
            if (answ == JOptionPane.YES_OPTION) {
                new Sentence().delete((Integer) getValueAt(tblSentences.getSelectedRow()));
                update();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, !ex.getMessage().equals("") ? ex.getMessage() : ex);
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, "", ex);
        }
    }//GEN-LAST:event_btnDeleteActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnEdit;
    private javax.swing.JButton btnNew;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblSentences;
    // End of variables declaration//GEN-END:variables
}
