/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.test.restdev;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author rako
 */

@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
public class Developer {
    
    @XmlElement(name = "firstname")
    private String name;
    private String last;

    public Developer(String name, String last) {
        this.name = name;
        this.last = last;
    }

    public Developer() {
    }
    
    
}
