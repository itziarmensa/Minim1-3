package edu.upc.dsa.Domain.Entity.TO;

import edu.upc.dsa.Domain.Entity.Objeto;

import java.util.LinkedList;
import java.util.List;

public class UsuarioInfo {
    String idUsuario;
    String nombreUsuario;
    String apellidosUsuario;

    public UsuarioInfo(){

    }

    public UsuarioInfo(String idUsuario, String nombreUsuario, String apellidosUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidosUsuario = apellidosUsuario;

    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(String idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getApellidosUsuario() {
        return apellidosUsuario;
    }

    public void setApellidosUsuario(String apellidosUsuario) {
        this.apellidosUsuario = apellidosUsuario;
    }


    public void modificarUsuario(String nombre, String apellidos){
        this.nombreUsuario = nombre;
        this.apellidosUsuario = apellidos;
    }

}
