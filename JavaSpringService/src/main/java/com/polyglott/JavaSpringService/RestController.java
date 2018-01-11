package com.polyglott.JavaSpringService;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;


@org.springframework.web.bind.annotation.RestController
public class RestController {

@Autowired
djangoClient rabbit;




    // request calling raidService request to save raid with the given namen
    @PostMapping("/addRaid/{name}")
    public void addRaid(@PathVariable("name") String name){
        RestTemplate restTemplate = new RestTemplate();

        // post request to raidService saving raid with requested name
        ResponseEntity<String> resp = restTemplate.postForEntity("http://raidService:8080/saveRaid/",name,String.class);
        System.out.println("[LOG] Response: "+ resp);
    }



    // request to retrive raid by name from raidService
    @GetMapping("getRaid/{name}")
    public List<Raid> getRaidByName(@PathVariable("name")String name){

        // get request to raidService
        RestTemplate restTemplate = new RestTemplate();
        String jsonList = restTemplate.getForObject("http://raidService:8080/getRaidByName/"+name+"/",String.class);

        //system LOG Output
        System.out.println("[LOG] retrieved raid: "+jsonList);

        //Map json response to Raid object
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Raid>> mapType = new TypeReference<List<Raid>>() {};
        List<Raid> raidList = null;
        try {
            raidList = mapper.readValue(jsonList,mapType);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return raidList;
    }

    // request to retrive all raids from raidService
    @GetMapping("getAllRaids")
    public List<Raid> getAllRaids() throws IOException {

        // get request to raid service for all raids
        RestTemplate restTemplate = new RestTemplate();
        String jsonList = restTemplate.getForObject("http://raidService:8080/getAllRaids/", String.class);

        //system LOG output
        System.out.println("[LOG] retrived raids: " + jsonList);

        // map json response to raid list
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<List<Raid>> mapType = new TypeReference<List<Raid>>(){};
        List <Raid> raidList = null;
        raidList = mapper.readValue(jsonList,mapType);
        return raidList;
    }


    // request to get all Items from itemService via rabbitmq
    @GetMapping("getAllItems")
    public List<Item> testcall(){
        return rabbit.getItems();
    }

/*
    @DeleteMapping("deletRaid/{name}")
    public int deleteRaid(){

        return 0;
    }


    ////////////////////////

    @PostMapping("/testpost/{name}")
    public String testPost(@PathVariable("name") String name){
        System.out.println("received "+ name);
        return name + " was send";
    }

    @GetMapping("/food")
    public void foodcall(){
        rabbit.getfoodlist();
    }

    @GetMapping("/testPath/{name}")
    public String testPath(@PathVariable("name")String name){
        return name +" was send";
    }

    */
}
