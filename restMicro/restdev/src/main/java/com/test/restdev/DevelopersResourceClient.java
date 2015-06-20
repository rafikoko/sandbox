/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.restdev;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

/**
 *
 * @author rako
 */
public class DevelopersResourceClient {

        private WebTarget webTarget;
        private Client client;
        private static final String BASE_URI = "http://localhost:8080/restdev/resources";

        public DevelopersResourceClient() {
            client = javax.ws.rs.client.ClientBuilder.newClient();
            webTarget = client.target(BASE_URI).path("developers");
        }

        /*public <T> T developers(Class<T> responseType) throws ClientErrorException {
            WebTarget resource = webTarget;
            return resource.get(responseType);
        }

        public Response save() throws ClientErrorException {
            return webTarget.request().post(null, Response.class);
        }

        public <T> T developer(Class<T> responseType, String first, String last) throws ClientErrorException {
            WebTarget resource = webTarget;
            resource = resource.path(java.text.MessageFormat.format("{0}-{1}", new Object[]{first, last}));
            return resource.get(responseType);
        }*/

        public void close() {
            client.close();
        }
    }
    