package edu.upc.dsa.Infraestructure;

import edu.upc.dsa.Domain.Entity.Exceptions.ObjetoNoExiste;
import edu.upc.dsa.Domain.Entity.Exceptions.ObjetoYaExiste;
import edu.upc.dsa.Domain.Entity.Exceptions.UserDoesNotExistException;
import edu.upc.dsa.Domain.Entity.Exceptions.UsuarioExiste;
import edu.upc.dsa.Domain.Entity.Objeto;
import edu.upc.dsa.Domain.Entity.Usuario;
import edu.upc.dsa.Domain.Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

public class ManagerImpl implements Manager {
    protected List<Objeto> objetos;

    protected Map<String, Usuario> usuarios;
    private static Manager instance;
    final static Logger logger = Logger.getLogger(ManagerImpl.class);
    public static Manager getInstance(){
        if(instance==null) instance = new ManagerImpl();
        return instance;
    }

    public int size(){
        int ret = this.usuarios.size();
        logger.info("size "+ret);
        return ret;
    }

    public ManagerImpl() {
        this.objetos = new ArrayList<>();
        this.usuarios = new HashMap<>();
    }

    @Override
    public List<Usuario> ordenUsuarios() {
        List<Usuario> users = new ArrayList<>(this.usuarios.values());
        users.sort((Usuario u1, Usuario u2)->{
            int value = u1.getApellidosUsuario().compareToIgnoreCase(u2.getApellidosUsuario());
            if (value==0){
                value = u1.getNombreUsuario().compareToIgnoreCase(u2.getNombreUsuario());
            }
            return value;
        });
        return users;
    }

    public Boolean userExistsByiD(String id) {
        for(Usuario user : this.usuarios.values()){
            if(user.getIdUsuario() == id){
                return true;
            }
        }
        return false;
    }

    @Override
    public void añadirUsuario(String id, String nombre, String apellidos) throws UsuarioExiste {
        if(userExistsByiD(id)){
            throw new UsuarioExiste();
        }
        Usuario usuario = new Usuario(id,nombre,apellidos);
        this.usuarios.put(usuario.getIdUsuario(),usuario);
    }

    public Usuario getUsuarioById(String id) {
        return this.usuarios.values().stream()
                .filter(x->x.getIdUsuario().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void modificarUsuario(String id, String nombre, String apellidos) throws UserDoesNotExistException {
        Usuario user = getUsuarioById(id);
        if(user==null){
            throw new UserDoesNotExistException();
        }
        user.modificarUsuario(nombre,apellidos);
    }

    @Override
    public int numUsuarios() {
        return this.usuarios.size();
    }

    @Override
    public Usuario infoUsuario(String id) throws UserDoesNotExistException {
        Usuario usuario = getUsuarioById(id);
        if(usuario == null){
            throw new UserDoesNotExistException();
        }
        return usuario;
    }

    public Objeto getObjetoById(String id) {
        return this.objetos.stream()
                .filter(x->id.equals(x.getIdObjeto()))
                .findFirst()
                .orElse(null);
    }

    @Override
    public void añadirObjetoUsuario(String idUsario, String idObjeto) throws UserDoesNotExistException, ObjetoNoExiste {
        Usuario usuario = getUsuarioById(idUsario);
        Objeto objeto = getObjetoById(idObjeto);
        if(usuario==null){
            throw new UserDoesNotExistException();
        }
        if(objeto==null){
            throw new ObjetoNoExiste();
        }
        List<Objeto> lista = usuario.getListaObjetos();
        lista.add(objeto);

    }

    @Override
    public List<Objeto> listaObjetos(String idUsuario) throws UserDoesNotExistException {
        Usuario usuario = getUsuarioById(idUsuario);
        if(usuario==null){
            throw new UserDoesNotExistException();
        }
        List<Objeto> lista = usuario.getListaObjetos();
        return lista;
    }

    @Override
    public int numObjetosUsuario(String idUsuario) {
        Usuario usuario = getUsuarioById(idUsuario);
        List<Objeto> lista = usuario.getListaObjetos();
        return lista.size();
    }


    public void añadirObjeto(String idObjeto, String nombre, String descr) throws ObjetoYaExiste {
        Objeto objeto = getObjetoById(idObjeto);
        if(objeto==null){
            Objeto objeto1 = new Objeto(idObjeto,nombre,descr);
            this.objetos.add(objeto1);
        }else {
            throw new ObjetoYaExiste();
        }

    }
}
