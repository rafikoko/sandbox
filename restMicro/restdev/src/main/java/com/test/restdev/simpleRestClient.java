/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.restdev;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
public class simpleRestClient {
 public static void main(String[] args) throws ClientProtocolException, IOException {
  HttpClient client = new DefaultHttpClient();
  HttpGet request = new HttpGet("http://localhost:8080/restdev/resources/developers/rafi-kokos");
  HttpGet request1 = new HttpGet("http://localhost:8081/restdev/resources/developers/rafi-kokos");
  HttpGet request2 = new HttpGet("http://localhost:8082/restdev/resources/developers/rafi-kokos");
  HttpResponse response;
  for(int i=0; i<10; i++){
      if(i%3==0){
        response = client.execute(request);
        System.out.println("If (i%3==0) -> call no. " + i + " to uri:" + request.getURI());
      }else
      if(i%3==1){
        response = client.execute(request1);
        System.out.println("If (i%3==1) -> call no. " + i + " to uri:" + request1.getURI());
      }else
      if(i%3==2){
        response = client.execute(request2);
        System.out.println("If (i%3==2) -> call no. " + i + " to uri:" + request2.getURI());
      }
      else{
        response = client.execute(request);
        System.out.println("Else -> call no. " + i + " to uri:" + request.getURI());
      }
    BufferedReader rd = new BufferedReader (new InputStreamReader(response.getEntity().getContent()));
    String line = "";
      System.out.println("call no. " + i);
    while ((line = rd.readLine()) != null) {
        System.out.println(line);
    }
  }
  
 }
}

