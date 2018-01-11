package com.polyglott.JavaSpringService;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.tomcat.jni.Local;

import javax.xml.stream.Location;

public class Raid {
    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;



    Raid(){
    }

    Raid(int Id, String Name){
        this.name = Name;
        this.id = Id;

    }



    public String getName() {
        return name;
    }
    public void setName(String Name){
        this.name= Name;
    }

    public void setId(int Id){this.id =Id;}
}
