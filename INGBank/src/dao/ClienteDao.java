/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import logica.ClienteLogica;
import logica.NacionalidadLogica;
import logica.SexoLogica;



/**
 *
 * @author griselda
 */
public class ClienteDao {

      private final Connection cnc;
    public ClienteDao() throws SQLException {
        this.cnc = conexion.conectarI();
        
    }
       //Insertar ciudad
public void insertarcliente(ClienteLogica c1) throws SQLException{
       //Preparar la consulta 
       String sql= "Insert into Cliente(Nombres,Apellidos,Direccion,Telefono) "
                +"Values(?,?,?,?) ";
       try (PreparedStatement st =(PreparedStatement) cnc.prepareStatement(sql)){
            st.setString(1, c1.getNombres());
            st.setString(2, c1.getApellidos());
            st.setString(3, c1.getDireccion());
            st.setString(4, c1.getTelefono());
            st.execute();
       } 
   }
   
public void modificarciudad(ClienteLogica c1) throws SQLException{
       //Preparar la consulta 
       String sql="Update  Cliente "
                +"Set Nombres = ? "
                +"Set Apellidos = ? "
                +"Set Direccion = ? "
                +"Set Telefono = ? "
                +"Where IdCliente = ? ";
       
       try (PreparedStatement st =(PreparedStatement) cnc.prepareStatement(sql)){
            st.setString(1, c1.getNombres());
             st.setString(1, c1.getApellidos());
              st.setString(1, c1.getDireccion());
               st.setString(1, c1.getTelefono());
            st.setInt(2, c1.getIdCliente());
            st.execute();
       } 
   }
 public void eliminarciudad(ClienteLogica c1) throws SQLException{
       //Preparar la consulta 
       String sql="Delete "
                +"From Cliete = ? "
                +"Where IdCliente = ? ";
       
       try (PreparedStatement st = cnc.prepareStatement(sql)){
            st.setInt(1, c1.getIdCliente());
            st.execute();
       } 
   } 
    public int autoIncrementar() throws SQLException{
        int clienteId = 0;
        
        String sql = "Select max(LAST_INSERT_ID(IdCliente)) + 1 as IdCliente "
                 +"From Cliente ";
        
        Statement st = cnc.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.first();
        
        clienteId = rs.getInt("IdCliente");
        if(clienteId == 0){
            clienteId = 1;
        }
        return clienteId;
    }
  public List<ClienteLogica>getLista() throws SQLException{
     
      String sql = "Select * from Cliente";
      
      List<ClienteLogica>miLista;
      
      try(PreparedStatement st =  cnc.prepareStatement(sql);
       ResultSet rs = st.executeQuery()){
       
          miLista = new ArrayList<>();
          while(rs.next()){
              ClienteLogica c1 = new  ClienteLogica();
              c1.setIdCliente(rs.getInt("IdCliente"));
              c1.setNombres(rs.getString("Nombres"));
              c1.setApellidos(rs.getString("Apellidos"));
              c1.setDireccion(rs.getString("Direccion"));
              c1.setIdSexo(rs.getInt("IdSexo"));
              c1.setTelefono(rs.getString("Telefono"));
              c1.setIdNacionalidad(rs.getInt("IdNacionalidad"));
                  
              miLista.add(c1);
              
          }
      }      
  return miLista;
  }
  public List<SexoLogica>getComboSexo() throws SQLException{
     
      String sql = "Select * from Sexo ";
      
      List<SexoLogica>miComboSexo;
      
      try(PreparedStatement st =  cnc.prepareStatement(sql);
       ResultSet rs = st.executeQuery()){
       
          miComboSexo = new ArrayList<>();
          while(rs.next()){
              SexoLogica c2 = new  SexoLogica();
              c2.setIdSexo(rs.getInt("idSexo"));
              c2.setSexo(rs.getString("Sexo"));
              miComboSexo.add(c2);
              
          }
      }      
  return miComboSexo;
  }
   public List<NacionalidadLogica>getComboNacionalidad() throws SQLException{
     
      String sql = "Select * from Nacionalidad ";
      
      List<NacionalidadLogica>miComboNacionalidad;
      
      try(PreparedStatement st =  cnc.prepareStatement(sql);
       ResultSet rs = st.executeQuery()){
       
          miComboNacionalidad = new ArrayList<>();
          while(rs.next()){
              NacionalidadLogica c3 = new  NacionalidadLogica();
              c3.setIdNacionalidad(rs.getInt("idNacionalidad"));
              c3.setNacionalidadcol(rs.getString("Nacionalidadcol"));
              miComboNacionalidad.add(c3);
              
          }
      }      
  return miComboNacionalidad;
  } 

}
  
