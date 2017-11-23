package sentence;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class FrmSentence extends javax.swing.JDialog {

    public Sentence sentence;
    private boolean isNew;
    private Integer catId;

    public FrmSentence(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    public FrmSentence(java.awt.Frame parent, boolean modal, Integer idCat, Integer idSente) throws Exception {
        super(parent, modal);
        initComponents();
        isNew = idSente == null;
        if (!isNew) {
            sentence = new Sentence().select(idSente);
            txtSpanish.setText(sentence.spanish);
            txtEnglish.setText(sentence.english);
        } else {
            this.catId = idCat;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnOk = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtSpanish = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtEnglish = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnOk.setText("Aceptar");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        jLabel3.setText("Español:");

        txtSpanish.setColumns(20);
        txtSpanish.setRows(5);
        jScrollPane2.setViewportView(txtSpanish);

        jLabel2.setText("Inglés:");

        txtEnglish.setColumns(20);
        txtEnglish.setRows(5);
        jScrollPane1.setViewportView(txtEnglish);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btnOk))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3))
                        .addGap(236, 236, 236))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnOk)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed
        try {
            if (txtSpanish.getText().equals("")) {
                throw new Exception("Escriba oración en español");
            }
            if (txtEnglish.getText().equals("")) {
                throw new Exception("Escriba oración en inglés");
            }
            if (isNew) {
                sentence = new Sentence();
                sentence.categoryId =catId;
            }
            sentence.spanish = txtSpanish.getText();
            sentence.english = txtEnglish.getText();
            if (isNew) {
                sentence.insert(sentence);
            } else {
                sentence.update(sentence);
            }
            this.dispose();
        } catch (Exception ex) {
            Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).log(Level.SEVERE, "", ex);
            JOptionPane.showMessageDialog(this, !ex.getMessage().equals("") ? ex.getMessage() : ex);
        }
    }//GEN-LAST:event_btnOkActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnOk;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea txtEnglish;
    private javax.swing.JTextArea txtSpanish;
    // End of variables declaration//GEN-END:variables
}
