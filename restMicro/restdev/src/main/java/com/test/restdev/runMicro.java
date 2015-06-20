/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.restdev;

import fish.payara.micro.BootstrapException;
import fish.payara.micro.PayaraMicro;

/**
 * Basic Example showing how to programmatically create, edit, and
 * start an embedded Payara Server.
 * 
 * @author Andrew Pielage
 */
public class runMicro 
{
    /*
    public static void main(String [] args)
    {
        try 
        {
            BootstrapProperties bootstrap = new BootstrapProperties();
            GlassFishRuntime runtime = GlassFishRuntime.bootstrap();
            GlassFishProperties glassfishProperties = new GlassFishProperties();
            glassfishProperties.setPort("http-listener", 8083);
            glassfishProperties.setPort("https-listener", 8184);
            GlassFish glassfish = runtime.newGlassFish(glassfishProperties);
            glassfish.start();
        }
        
        catch (GlassFishException ex) 
        {
            Logger.getLogger(runMicro.class.getName()).log(Level.SEVERE, 
                    null, ex);
        }
    }
    */
    public static void main(String[] args) throws     
    BootstrapException  {

        //bootstrap first instance
        PayaraMicro micro = PayaraMicro.getInstance();
        micro.addDeployment("target/restdev.war").setHttpPort(8080)
        	.bootStrap();   
        
        StringBuffer info = new StringBuffer();
        info.append("Starting function -> Instance name:")
                .append(micro.getInstanceName())
                .append("; ")
                .append("Instance cluster start port:")
                .append(micro.getClusterStartPort())
                .append("; Instance http port: ")
                .append(micro.getHttpPort())
                .append("; Instance ssl port: ")
                .append(micro.getSslPort());
        System.out.println(info);
    }    
    
}
