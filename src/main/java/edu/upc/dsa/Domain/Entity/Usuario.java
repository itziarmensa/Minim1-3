package edu.upc.dsa.Domain.Entity;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

public class Usuario {
    String idUsuario;
    String nombreUsuario;
    String apellidosUsuario;
    List<Objeto> listaObjetos;

    public Usuario(){
        this.listaObjetos = new LinkedList<>();
    }

    public Usuario(String idUsuario, String nombreUsuario, String apellidosUsuario) {
        this.idUsuario = idUsuario;
        this.nombreUsuario = nombreUsuario;
        this.apellidosUsuario = apellidosUsuario;
        this.listaObjetos = new LinkedList<>();
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

    public List<Objeto> getListaObjetos() {
        return listaObjetos;
    }

    public void setListaObjetos(List<Objeto> listaObjetos) {
        this.listaObjetos = listaObjetos;
    }

    public void modificarUsuario(String nombre, String apellidos){
        this.nombreUsuario = nombre;
        this.apellidosUsuario = apellidos;
    }

}
