/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package rest;

import com.google.gson.Gson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import wrk.Wrk;

/**
 * REST Web Service
 *
 * @author schwandern
 */
@Path("db")
public class DB {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DB
     */
    public DB() {
    }

    Wrk wrkDB = new Wrk("3306", "schwandern_133_memory");

    @GET
    @Path("getClassement")
    @Produces(MediaType.APPLICATION_JSON)
    public String getClassement() {
        Gson builder = new Gson();
        String toJson = builder.toJson(wrkDB.getClassement());
        return "{\"test\":" + toJson + "}";

    }

    @POST
    @Path("addEntree")
    @Produces(MediaType.TEXT_PLAIN)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
        public String addEntree(@FormParam("name") String name, @FormParam("score") int score, @FormParam("fk_user") int fkUser) {
        String result;
        if (wrkDB.insertClassementEntry(score, name, fkUser)) {
            result = "OK";
        } else {
            result = "NOK";
        }
        return result;
    }

}
