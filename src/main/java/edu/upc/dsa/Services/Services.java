package edu.upc.dsa.Services;

import edu.upc.dsa.Domain.Entity.Exceptions.ObjetoNoExiste;
import edu.upc.dsa.Domain.Entity.Exceptions.ObjetoYaExiste;
import edu.upc.dsa.Domain.Entity.Exceptions.UserDoesNotExistException;
import edu.upc.dsa.Domain.Entity.Exceptions.UsuarioExiste;
import edu.upc.dsa.Domain.Entity.Objeto;
import edu.upc.dsa.Domain.Entity.TO.UsuarioInfo;
import edu.upc.dsa.Domain.Entity.Usuario;
import edu.upc.dsa.Domain.Manager;
import edu.upc.dsa.Infraestructure.ManagerImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.awt.*;
import java.util.List;

@Api(value = "/juego", description = "Endpoint to juego Service")
@Path("/juego")
public class Services {
    private Manager manager;

    public Services() throws UsuarioExiste, ObjetoYaExiste {
        this.manager = ManagerImpl.getInstance();
        if(manager.size() == 0){
            this.manager.añadirUsuario("5609","Itziar","Mensa");
            this.manager.añadirUsuario("3243","Óscar","Boullosa");
            this.manager.añadirUsuario("5467","Monica","Mensa");

            this.manager.añadirObjeto("3256","Escudo","Defender");
            this.manager.añadirObjeto("5678","Espada","Matar");
            this.manager.añadirObjeto("7859","Cofre","Magia");

        }
    }

    @POST
    @ApiOperation(value = "añadir un nuevo usuario", notes = "Añadir usuarios")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Exitoso", response= UsuarioInfo.class),
            @ApiResponse(code = 409, message = "El usuario ya existe")
    })
    @Path("/usuario")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response añadirUsuario(UsuarioInfo usuario){
        try{
            this.manager.añadirUsuario(usuario.getIdUsuario(),usuario.getNombreUsuario(),usuario.getApellidosUsuario());
        }catch(UsuarioExiste e){
            return Response.status(409).entity(usuario).build();
        }
        return Response.status(201).entity(usuario).build();
    }

    @GET
    @ApiOperation(value = "usuarios ordenados alfabéticamente", notes = "Obtener Usuarios")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exitoso", response = Usuario.class, responseContainer="List"),
    })
    @Path("/usuario")
    @Produces(MediaType.APPLICATION_JSON)
    public Response usuariosOrdenados(){
        List<Usuario> lista = this.manager.ordenUsuarios();
        GenericEntity<List<Usuario>> entity = new GenericEntity<List<Usuario>>(lista) {};
        return Response.status(200).entity(entity).build();
    }

    @PUT
    @ApiOperation(value = "modificar nombres y apellidos de un usuario", notes = "Modificar Usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Exitoso"),
            @ApiResponse(code = 400, message = "El usuario no existe")
    })
    @Path("/usuario/{usuarioId}/{nombreUsuario}/{apellidosUsuario}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response modificarUsuario(@PathParam("usuarioId") String userId, @PathParam("nombreUsuario") String nombre, @PathParam("apellidosUsuario") String apellidos) {
        try {
            this.manager.modificarUsuario(userId, nombre, apellidos);
        } catch (UserDoesNotExistException e) {
            return Response.status(400).build();
        }
        return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "numero de usuarios", notes = "Numero Usuarios")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exitoso"),
    })
    @Path("/usuario/num")
    @Produces(MediaType.APPLICATION_JSON)
    public Response numUsuarios(){
        int num = this.manager.numUsuarios();
        String numq = String.valueOf(num);
        return Response.status(200).entity(numq).build();
    }

    @PUT
    @ApiOperation(value = "añadir un objeto a la lista de objetos de un usuario", notes = "Añadir Objeto a Usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Exitoso"),
            @ApiResponse(code = 400, message = "El usuario o el objeto no existen")
    })
    @Path("/usuario/{userId}/{objectId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response añadirObjetoUsuario(@PathParam("userId") String userId, @PathParam("objectId") String objectId) {
        try {
            this.manager.añadirObjetoUsuario(userId, objectId);
        } catch (UserDoesNotExistException | ObjetoNoExiste e) {
            return Response.status(400).build();
        }
        return Response.status(201).build();
    }

    @GET
    @ApiOperation(value = "Lista de objetos de un usuario", notes = "Lista Objetos Usuario")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Exitoso", response = Usuario.class, responseContainer="List"),
            @ApiResponse(code = 400, message = "El usuario no existe")
    })
    @Path("/usuario/{usuarioId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listaObjetosUsuario(@PathParam("usuarioId") String usuarioId){
        try {
            List<Objeto> lista = this.manager.listaObjetos(usuarioId);
            GenericEntity<List<Objeto>> entity = new GenericEntity<List<Objeto>>(lista) {};
            return Response.status(200).entity(entity).build();
        }catch (UserDoesNotExistException e){
            return Response.status(400).build();
        }

    }

    @POST
    @ApiOperation(value = "añadir un nuevo objeto", notes = "Añadir objetos")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Exitoso", response= Usuario.class),
            @ApiResponse(code = 409, message = "El objeto ya existe")
    })
    @Path("/objeto")
    @Consumes({MediaType.APPLICATION_JSON})
    public Response añadirObjeto(Objeto objeto){
        try{
            this.manager.añadirObjeto(objeto.getIdObjeto(),objeto.getNombreObjeto(),objeto.getDescripcionObjeto());
        }catch(ObjetoYaExiste e){
            return Response.status(409).entity(objeto).build();
        }
        return Response.status(201).entity(objeto).build();
    }

}
