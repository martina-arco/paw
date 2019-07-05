package ar.edu.itba.webapp.controllers.api;


import ar.edu.itba.interfaces.service.UserService;
import ar.edu.itba.webapp.model.DTOs.UserDTO;
import ar.edu.itba.model.Team;
import ar.edu.itba.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.net.URI;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import javax.ws.rs.core.Response.*;

@Path("users")
@Component
public class UserController {
    @Autowired
    private UserService us;
    @Context
    private UriInfo uriInfo;

    @GET
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response listUsers() {
        final List<User> allUsers = Arrays.asList(new User("test", "test", "test@test.test", new Team(), new Date()));//us.getAll();
        return Response.ok(allUsers.parallelStream().map(UserDTO::new).collect(Collectors.toList())).build();
    }

    @POST
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response createUser(final UserDTO userDto) {
        final User user = us.create(userDto.getUsername(), userDto.getPassword(), userDto.getMail(), new Date());
        final URI uri = uriInfo.getAbsolutePathBuilder().path(String.valueOf(user.getId())).build();
        return Response.created(uri).build();
    }

    @GET
    @Path("/{id}")
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response getById(@PathParam("id") final int id) {
        final User user = us.findById(id);
        if (user != null) {
            return Response.ok(new UserDTO(user)).build();
        } else {
            return Response.status(Status.NOT_FOUND).build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Produces(value = { MediaType.APPLICATION_JSON, })
    public Response deleteById(@PathParam("id") final int id) {
        return Response.status(Status.METHOD_NOT_ALLOWED).build();
        //us.deleteById(id);
        //return Response.noContent().build();
    }
}