package edu.upc.dsa.Domain;

import edu.upc.dsa.Domain.Entity.Exceptions.ObjetoNoExiste;
import edu.upc.dsa.Domain.Entity.Exceptions.ObjetoYaExiste;
import edu.upc.dsa.Domain.Entity.Exceptions.UserDoesNotExistException;
import edu.upc.dsa.Domain.Entity.Exceptions.UsuarioExiste;
import edu.upc.dsa.Domain.Entity.Objeto;
import edu.upc.dsa.Domain.Entity.Usuario;

import java.util.List;

public interface Manager {
    public List<Usuario> ordenUsuarios();
    public void añadirUsuario(String id, String nombre, String apellidos) throws UsuarioExiste;
    public void modificarUsuario(String id, String nombre, String apellidos) throws UserDoesNotExistException;
    public int numUsuarios();
    public Usuario infoUsuario(String id) throws UserDoesNotExistException;
    public void añadirObjetoUsuario(String idUsario, String idObjeto) throws UserDoesNotExistException, ObjetoNoExiste;
    public List<Objeto> listaObjetos(String idUsuario) throws UserDoesNotExistException;
    public int numObjetosUsuario(String idUsuario);
    public void añadirObjeto(String idObjeto, String nombre, String descr) throws ObjetoYaExiste;
    public int size();
}
