package edu.upc.dsa.Domain.Entity;

public class Objeto {
    String idObjeto;
    String nombreObjeto;
    String descripcionObjeto;

    public Objeto(){

    }

    public Objeto(String idObjeto, String nombreObjeto, String descripcionObjeto) {
        this.idObjeto = idObjeto;
        this.nombreObjeto = nombreObjeto;
        this.descripcionObjeto = descripcionObjeto;
    }

    public String getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(String idObjeto) {
        this.idObjeto = idObjeto;
    }

    public String getNombreObjeto() {
        return nombreObjeto;
    }

    public void setNombreObjeto(String nombreObjeto) {
        this.nombreObjeto = nombreObjeto;
    }

    public String getDescripcionObjeto() {
        return descripcionObjeto;
    }

    public void setDescripcionObjeto(String descripcionObjeto) {
        this.descripcionObjeto = descripcionObjeto;
    }
}
