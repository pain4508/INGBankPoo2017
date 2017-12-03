/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package presentacion;

import dao.ClienteDao;
import dao.conexion;
import static dao.conexion.conectarI;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.binding.Bindings;
import static javafx.beans.binding.Bindings.convert;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import logica.ClienteLogica;
import logica.NacionalidadLogica;
import logica.SexoLogica;

/**
 *
 * @author griselda
 */
public class JFraCliente
        extends javax.swing.JFrame {

    /**
     * Creates new form JFraCliente
     */

   Connection cnc = conectarI();
   
    public JFraCliente() throws SQLException {
        initComponents();
        setLocationRelativeTo(null);
          llenarCbS();
          llenarCbN();
          habilitarBotones(true,false,false,false,false);
          JOptionPane.showMessageDialog(null,"Bienvenido");
    }
 
 
public void llenarCbS() throws SQLException{
 ClienteDao dao = new ClienteDao();   
    jCboSexo.removeAllItems(); //Vaciamos el JComboBox
     
    List<SexoLogica> miComboSexo;
   
    miComboSexo = dao.getComboSexo();//La consulta tiene que retornar un ArrayList
 
    for(int i=0; i<miComboSexo.size();i++){
      jCboSexo.addItem(miComboSexo.get(i).getSexo());
    } 
} 
public void llenarCbN() throws SQLException{
 ClienteDao dao = new ClienteDao();   
    jCboNacionalidad.removeAllItems(); //Vaciamos el JComboBox
     
    List<NacionalidadLogica> miComboNacionalidad;
   
    miComboNacionalidad = dao.getComboNacionalidad();//La consulta tiene que retornar un ArrayList
 
    for(int i=0; i<miComboNacionalidad.size();i++){
      jCboNacionalidad.addItem(miComboNacionalidad.get(i).getNacionalidadcol());
    } 
}

      private void habilitarBotones(boolean nuevo, boolean guardar, boolean modificar, boolean eliminar, boolean textField){
        jBtnNuevo.setEnabled(nuevo);
        jBtnGuardar.setEnabled(guardar);
        jBtnModificar.setEnabled(modificar);
        jBtnEliminar.setEnabled(eliminar);
        jTFNombres.setEditable(textField);
        jTFApellidos.setEditable(textField);
        jCboSexo.setEditable(textField);
        jTFDireccion.setEditable(textField);
        jTFTelefono.setEditable(textField);
        jCboNacionalidad.setEditable(textField);
    }
      
     
      
  private void limpiar(){
       jTFIdCliente.setText("");
       jTFNombres.setText("");
       jTFApellidos.setText("");
       jTFDireccion.setText("");
       jTFTelefono.setText("");
       jCboSexo.removeAllItems();
       jCboNacionalidad.removeAllItems();
       
    }
  
    private void investigarCorrelativo() throws SQLException{
        ClienteDao dao = new ClienteDao();
        ClienteLogica c1 = new ClienteLogica();
        c1.setIdCliente(dao.autoIncrementar());
        jTFIdCliente.setText(String.valueOf(c1.getIdCliente()));
        
    }
     private boolean verificarTextField(){
        boolean estado;
        
        if(jTFNombres.getText().isEmpty()  || jTFDireccion.getText().isEmpty() || jTFTelefono.getText().isEmpty()){
            JOptionPane.showMessageDialog(null, "Ingrese los campos vacios");
            estado = false;    
        }else{
            estado = true;
        }
        return estado;
    }
    private void guardarCliente(){
        
        ClienteLogica c1 = new ClienteLogica();
        
        c1.setIdCliente(Integer.parseInt(this.jTFIdCliente.getText()));
        c1.setNombres(this.jTFNombres.getText());
        c1.setApellidos(this.jTFApellidos.getText());
        c1.setDireccion(this.jTFDireccion.getText());
        c1.setIdSexo(Integer.parseInt(this.jCboSexo.toString()));
        c1.setTelefono(this.jTFTelefono.getText());
        c1.setIdNacionalidad(Integer.parseInt(this.jCboNacionalidad.toString()));
             
        try {
            ClienteDao dao = new ClienteDao();
            dao.insertarcliente(c1);
            JOptionPane.showMessageDialog(null, "Registro almacenado satisfactoriamente.");
            limpiar();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al almacenar el Cliente." + e);
        }
    }
   private void limpiarTabla(){
      
        DefaultTableModel temp = (DefaultTableModel) this.jTblCliente.getModel(); //
        
        // Limpiar los datos de la tabla.
        while (temp.getRowCount() > 0) {
            temp.removeRow(0);
     }
    }
         
    private void llenarTabla() throws SQLException{
        limpiarTabla();
        
        ClienteDao dao = new ClienteDao();
        List<ClienteLogica> miLista = dao.getLista();
        
        DefaultTableModel temp = (DefaultTableModel) this.jTblCliente.getModel(); 
        
        for(ClienteLogica c1:miLista){
            //Se crea un array que sera una de las filas de la tabla.
            Object[] fila = new Object[3]; // Hay 2 columnas en la tabla
            // Se rellena cada posicion del array con una de las columnas de la tabla en base de datos.
            
                fila[0] = c1.getIdCliente();
                fila[1] = c1.getNombres();
                fila[2] = c1.getApellidos();
                fila[3] = c1.getDireccion();
                fila[4] = c1.getIdSexo();
                fila[5] = c1.getTelefono();
                fila[6] = c1.getIdNacionalidad();
                temp.addRow(fila);
        }   
    }

    
        
    private void lineaSeleccionada() {
        if (this.jTblCliente.getSelectedRow() != -1) {
            //Habilito los controles para que se pueda hacer una accion.
            if (this.jTblCliente.isEnabled() == true) {
                this.jTFIdCliente.setText(String.valueOf(this.jTblCliente.getValueAt(jTblCliente.getSelectedRow(), 0)));
                this.jTFNombres.setText(String.valueOf(this.jTblCliente.getValueAt(jTblCliente.getSelectedRow(), 1)));
                this.jTFApellidos.setText(String.valueOf(this.jTblCliente.getValueAt(jTblCliente.getSelectedRow(), 2)));
                this.jTFDireccion.setText(String.valueOf(this.jTblCliente.getValueAt(jTblCliente.getSelectedRow(), 3)));
                this.jTFTelefono.setText(String.valueOf(this.jTblCliente.getValueAt(jTblCliente.getSelectedRow(), 6)));
                
            }
        } else {
            limpiar();
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLblCerrar = new javax.swing.JLabel();
        jLblMinimizar = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        Direccion = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jTFIdCliente = new javax.swing.JTextField();
        jTFNombres = new javax.swing.JTextField();
        jTFApellidos = new javax.swing.JTextField();
        jTFDireccion = new javax.swing.JTextField();
        jTFTelefono = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jBtnNuevo = new javax.swing.JButton();
        jBtnGuardar = new javax.swing.JButton();
        jBtnModificar = new javax.swing.JButton();
        jBtnEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTblCliente = new javax.swing.JTable();
        jCboSexo = new javax.swing.JComboBox<>();
        jCboNacionalidad = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(68, 108, 179));

        jLblCerrar.setFont(new java.awt.Font("Phosphate", 1, 36)); // NOI18N
        jLblCerrar.setForeground(new java.awt.Color(255, 255, 255));
        jLblCerrar.setText("X");
        jLblCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLblCerrarMousePressed(evt);
            }
        });

        jLblMinimizar.setFont(new java.awt.Font("Phosphate", 2, 48)); // NOI18N
        jLblMinimizar.setForeground(new java.awt.Color(255, 255, 255));
        jLblMinimizar.setText("-");
        jLblMinimizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jLblMinimizarMousePressed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Perpetua Titling MT", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("GESTIONAR CLIENTE");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(177, 177, 177)
                .addComponent(jLblMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLblCerrar)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblCerrar)
                    .addComponent(jLblMinimizar, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(33, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(27, 27, 27))
        );

        jPanel2.setBackground(new java.awt.Color(1, 50, 67));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("IdCliente");

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Nombres");

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Apellidos");

        Direccion.setForeground(new java.awt.Color(255, 255, 255));
        Direccion.setText("Direccion");

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Sexo");

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Telefono");

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Pais Origen");

        jBtnNuevo.setText("Nuevo");
        jBtnNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnNuevoActionPerformed(evt);
            }
        });

        jBtnGuardar.setText("Guardar");
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnGuardarActionPerformed(evt);
            }
        });

        jBtnModificar.setText("Modificar");

        jBtnEliminar.setText("Eliminar");

        jTblCliente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "IdCliente", "Nombres", "Apellidos", "Direccion", "Sexo", "Telefono", "Nacionalidad"
            }
        ));
        jTblCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTblClienteMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTblCliente);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(37, 37, 37)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addGap(32, 32, 32))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel1)
                                            .addComponent(jLabel2)
                                            .addComponent(Direccion))
                                        .addGap(31, 31, 31)))
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jTFNombres, javax.swing.GroupLayout.DEFAULT_SIZE, 261, Short.MAX_VALUE)
                                            .addComponent(jCboSexo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jTFDireccion))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(16, 16, 16)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                                        .addComponent(jLabel6)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jTFTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addComponent(jCboNacionalidad, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                            .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(jLabel4)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 212, Short.MAX_VALUE))
                                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                                        .addComponent(jLabel3)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jTFApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                                    .addComponent(jTFIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(0, 130, Short.MAX_VALUE)
                                .addComponent(jBtnNuevo)
                                .addGap(18, 18, 18)
                                .addComponent(jBtnGuardar)
                                .addGap(18, 18, 18)
                                .addComponent(jBtnModificar)
                                .addGap(13, 13, 13)
                                .addComponent(jBtnEliminar)
                                .addGap(93, 93, 93))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jTFIdCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jTFNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jTFApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jTFTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Direccion)
                    .addComponent(jTFDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel4)
                    .addComponent(jCboSexo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jCboNacionalidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnNuevo)
                    .addComponent(jBtnGuardar)
                    .addComponent(jBtnModificar)
                    .addComponent(jBtnEliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(205, 205, 205))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLblCerrarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblCerrarMousePressed
        System.exit(0);
    }//GEN-LAST:event_jLblCerrarMousePressed

    private void jLblMinimizarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLblMinimizarMousePressed
       this.setState(JFraCliente.ICONIFIED);
    }//GEN-LAST:event_jLblMinimizarMousePressed

    private void jBtnNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnNuevoActionPerformed
        habilitarBotones(false, true, false, false, true);
        jTFNombres.requestFocus();
         try {
            investigarCorrelativo();
        } catch (SQLException ex) {
            Logger.getLogger(JFraCliente.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jBtnNuevoActionPerformed

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnGuardarActionPerformed
        if(verificarTextField()==true){
            guardarCliente();
            
            try {
                llenarTabla();
            } catch (SQLException ex) {
                Logger.getLogger(JFraCliente.class.getName()).log(Level.SEVERE, null, ex);
            }
            habilitarBotones(true, false, false, false, false);
        }
    }//GEN-LAST:event_jBtnGuardarActionPerformed

    private void jTblClienteMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTblClienteMousePressed
        lineaSeleccionada();
        habilitarBotones(false, false, true, true, true);
    }//GEN-LAST:event_jTblClienteMousePressed


    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(JFraCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFraCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFraCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFraCliente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
               
                try {
                    new JFraCliente().setVisible(true);
                } catch (SQLException ex) {
                    Logger.getLogger(JFraCliente.class.getName()).log(Level.SEVERE, null, ex);
                }
                
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Direccion;
    private javax.swing.JButton jBtnEliminar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JButton jBtnModificar;
    private javax.swing.JButton jBtnNuevo;
    private javax.swing.JComboBox<String> jCboNacionalidad;
    private javax.swing.JComboBox<String> jCboSexo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLblCerrar;
    private javax.swing.JLabel jLblMinimizar;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTFApellidos;
    private javax.swing.JTextField jTFDireccion;
    private javax.swing.JTextField jTFIdCliente;
    private javax.swing.JTextField jTFNombres;
    private javax.swing.JTextField jTFTelefono;
    private javax.swing.JTable jTblCliente;
    // End of variables declaration//GEN-END:variables
}
