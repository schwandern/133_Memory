/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import wrk.Wrk;

/**
 * REST Web Service
 *
 * @author reyx
 */
@Path("db")
public class DB {

    @Context
    private UriInfo context;
    Wrk wrkDB = new Wrk("3306", "reyx_133_G4_Admin");

    /**
     * Creates a new instance of DB
     */

    public DB() {

    }

    @POST
    @Path("Getusers")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsers(@FormParam("user") String user) {
        Gson builder = new Gson();

        String toJson = builder.toJson(wrkDB.getUsers(user));
        return "{\"user\":" + toJson + "}";

    }

    @POST
    @Path("Addusers")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public String addUsers(@FormParam("user") String user, @FormParam("password") String password) {
        String s;
        if (wrkDB.addUsers(user, password)) {
            s = "OK";
        } else {
            s = "KO";
        }
        return s;

    }

}
