/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logica;

/**
 *
 * @author griselda
 */
public class ClienteLogica {
    private int IdCliente;
    private String Nombres;
    private String Apellidos;
    private String Direccion;
    private int IdSexo;
    private String Telefono;
    private int IdNacionalidad;

    public int getIdCliente() {
        return IdCliente;
    }

    public void setIdCliente(int IdCliente) {
        this.IdCliente = IdCliente;
    }

    public String getNombres() {
        return Nombres;
    }

    public void setNombres(String Nombres) {
        this.Nombres = Nombres;
    }

    public String getApellidos() {
        return Apellidos;
    }

    public void setApellidos(String Apellidos) {
        this.Apellidos = Apellidos;
    }

    public String getDireccion() {
        return Direccion;
    }

    public void setDireccion(String Direccion) {
        this.Direccion = Direccion;
    }

    public int getIdSexo() {
        return IdSexo;
    }

    public void setIdSexo(int IdSexo) {
        this.IdSexo = IdSexo;
    }

    public String getTelefono() {
        return Telefono;
    }

    public void setTelefono(String Telefono) {
        this.Telefono = Telefono;
    }

    public int getIdNacionalidad() {
        return IdNacionalidad;
    }

    public void setIdNacionalidad(int IdNacionalidad) {
        this.IdNacionalidad = IdNacionalidad;
    }    
}
