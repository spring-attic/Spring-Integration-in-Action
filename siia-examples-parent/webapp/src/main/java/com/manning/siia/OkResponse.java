package com.manning.siia;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Ok")
public class OkResponse {


    public String toString(){
        return "Ok";
    }


}
