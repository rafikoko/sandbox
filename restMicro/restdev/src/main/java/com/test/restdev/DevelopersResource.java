/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.restdev;

import fish.payara.micro.PayaraMicro;
import java.net.URI;
import java.time.Clock;
import java.util.concurrent.TimeUnit;
import javax.json.Json;
import javax.json.JsonObject;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.container.TimeoutHandler;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

/**
 *
 * @author rako
 */
@Path("developers")
public class DevelopersResource {
    
    @GET
    public void developers(@Suspended AsyncResponse response){
        JsonObject object = Json.createObjectBuilder().add("frstname", "second").build();
        //throw new RuntimeException("things suck");
        response.resume(object);
        response.setTimeout(1, TimeUnit.DAYS);
        response.setTimeoutHandler(new TimeoutHandler() {

            @Override
            public void handleTimeout(AsyncResponse asyncResponse) {
                //handles timeout event 
            }
        })
                ;
    }
    
    /*
    @GET
    public JsonArray developers(@Context HttpHeaders http){
        JsonObject object = Json.createObjectBuilder().add("frstname", "second").build();
        //throw new RuntimeException("things suck");
        return Json.createArrayBuilder().add(object).build();
    }
    
    
    
    
    @GET
    public JsonArray developers(@Context HttpHeaders http){
        JsonObject object = Json.createObjectBuilder().add("frstname", "second").build();
        throw new RuntimeException("things suck");
        //return Json.createArrayBuilder().add(object).build();
    }*/
    
    @GET
    @Path("{first}-{last}")
    public Response developer(@PathParam("first") String first, @PathParam("last") String last ){
        System.out.println(getPayaraInfo());
        return Response.status(Status.OK)
                .entity(new Developer(first, last)).build();
    }
    
    private String getPayaraInfo(){
        StringBuffer info = new StringBuffer();
        info.append("Instance name:")
                .append(PayaraMicro.getInstance().getInstanceName())
                .append("; ")
                .append("Instance cluster start port:")
                .append(PayaraMicro.getInstance().getClusterStartPort())
                .append("; Instance http port: ")
                .append(PayaraMicro.getInstance().getHttpPort())
                .append("; Instance ssl port: ")
                .append(PayaraMicro.getInstance().getSslPort());
        
        return info.toString();
    }
    
    @POST
    public Response save(Developer developer, @Context UriInfo info ){
        URI uri = info.getAbsolutePathBuilder().path("/id-" + System.currentTimeMillis()).build();
        return Response.created(uri).build();
    }
    
    /*
    @GET
    @Path("{first}-{last}")
    public Developer developer(@PathParam("first") String first, @PathParam("last") String last ){
        return new Developer(first, last);
    }
    
    */
    
}
