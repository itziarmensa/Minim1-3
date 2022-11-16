package edu.upc.dsa;

import edu.upc.dsa.Domain.Entity.Exceptions.ObjetoNoExiste;
import edu.upc.dsa.Domain.Entity.Exceptions.ObjetoYaExiste;
import edu.upc.dsa.Domain.Entity.Exceptions.UserDoesNotExistException;
import edu.upc.dsa.Domain.Entity.Exceptions.UsuarioExiste;
import edu.upc.dsa.Domain.Entity.Objeto;
import edu.upc.dsa.Domain.Entity.Usuario;
import edu.upc.dsa.Domain.Manager;
import edu.upc.dsa.Infraestructure.ManagerImpl;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ManagerImplTest {
    Manager manager;

    @Before
    public void setUp() throws UsuarioExiste, ObjetoYaExiste {
        this.manager = new ManagerImpl();

        this.manager.añadirUsuario("5609","Itziar","Mensa");
        this.manager.añadirUsuario("3243","Óscar","Boullosa");
        this.manager.añadirUsuario("5467","Monica","Mensa");

        this.manager.añadirObjeto("3256","Escudo","Defender");
        this.manager.añadirObjeto("5678","Espada","Matar");
        this.manager.añadirObjeto("7859","Cofre","Magia");

    }

    @After
    public void tearDown() {
        this.manager = null;
    }

    @Test
    public void testAñadirUsuario() throws UsuarioExiste{
        Assert.assertEquals(3,this.manager.numUsuarios());

        this.manager.añadirUsuario("7859","Paula","Mensa");
        Assert.assertEquals(4,this.manager.numUsuarios());

    }

    @Test
    public void testOrdenarUsuarios(){
        List<Usuario> users = this.manager.ordenUsuarios();
        Assert.assertEquals(3, users.size());

        Assert.assertEquals("Boullosa", users.get(0).getApellidosUsuario());
        Assert.assertEquals("Itziar", users.get(1).getNombreUsuario());
        Assert.assertEquals("Monica", users.get(2).getNombreUsuario());
    }

    @Test
    public void testModificarUsuario() throws UserDoesNotExistException {
        this.manager.modificarUsuario("5467","Monica","Minguito");
        Usuario usuario = this.manager.infoUsuario("5467");

        Assert.assertEquals("Minguito", usuario.getApellidosUsuario());
    }

    @Test
    public void testañadirObjetoUsuario() throws UserDoesNotExistException, ObjetoNoExiste {
        Usuario usuario = this.manager.infoUsuario("5467");
        List<Objeto> lista = usuario.getListaObjetos();
        Assert.assertEquals(0, lista.size());
        this.manager.añadirObjetoUsuario("5467","3256");
        Assert.assertEquals(1, lista.size());
        Assert.assertEquals("3256", lista.get(0).getIdObjeto());
        this.manager.añadirObjetoUsuario("5467","5678");
        Assert.assertEquals(2, lista.size());
        Assert.assertEquals("5678", lista.get(1).getIdObjeto());
    }

    @Test
    public void testListaObjetosUsuario() throws UserDoesNotExistException, ObjetoNoExiste {
        this.manager.añadirObjetoUsuario("5467","3256");
        this.manager.añadirObjetoUsuario("5467","5678");
        List<Objeto> lista = this.manager.listaObjetos("5467");
        Assert.assertEquals(2,lista.size());
        Assert.assertEquals("3256", lista.get(0).getIdObjeto());
        Assert.assertEquals("5678", lista.get(1).getIdObjeto());
    }

    @Test
    public void testNumObjetosUsusarios() throws ObjetoNoExiste, UserDoesNotExistException {
        this.manager.añadirObjetoUsuario("5467","3256");
        this.manager.añadirObjetoUsuario("5467","5678");
        int num = this.manager.numObjetosUsuario("5467");
        Assert.assertEquals(2,num);
    }
}
