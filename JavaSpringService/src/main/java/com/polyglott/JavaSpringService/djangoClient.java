package com.polyglott.JavaSpringService;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import java.io.IOException;
import java.lang.String;
import java.util.List;

public class djangoClient {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private DirectExchange itemExchange;


    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public List<Item> getItems()
    {

        String routingKey = "item_key";
        String msgStr ="ApiGateway requesting Items";

        MessageProperties props = new MessageProperties();
        //  props.setCorrelationIdString("yodeldido");


        System.out.println("[LOG] Requesting Item");
        Message msg = new Message(msgStr.getBytes(),props);
        System.out.println("[LOG] Sending the msg:" + msg.toString());
        System.out.println("[LOG] Message corelation Id "+ msg.getMessageProperties().getCorrelationIdString());
        Message resp = template.sendAndReceive(itemExchange.getName(),routingKey,msg);
         //Object response =template.sendAndReceive(exchange.getName(),"rpc",darn);
//        this.template.convertAndSend("my-queue",darn);
       // System.out.println("[LOG] Received response: " + resp.toString());
        String s = new String(resp.getBody());
        System.out.println("[LOG] Received response:"+ s);
        //return response.toString();
        ObjectMapper mapper = new ObjectMapper();
        Item item = null;


        //Map json response to itemList
        TypeReference<List<Item>> mapType = new TypeReference<List<Item>>() {};
        try {
            List<Item> itemList = mapper.readValue(resp.getBody(),mapType);
            System.out.println(itemList.get(1).getName());

            for(int i = 0; i < itemList.size();i++){
                System.out.println(itemList.get(i).getName());
            }
            return itemList;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

   /* public void getfoodlist(){
        System.out.println("Give me food ppplsss");
        //Object resp = template.convertSendAndReceive(foodExchange.getName(),"food_key","giv me food pls");
        //String jfood = new String(resp.toString());
        //System.out.println(jfood);

        MessageProperties props = new MessageProperties();
        Message msg = new Message("here is your food".getBytes(),props);
        Message respMsg = template.sendAndReceive(foodExchange.getName(),"food_key", msg);
        String respJson = new String(respMsg.getBody());
        System.out.println("The json resp was:" + respJson);

        TypeReference<List<Item>> mapType = new TypeReference<List<Item>>() {};
        ObjectMapper mapper = new ObjectMapper();
       // List<String> foodList = null;
        try {
            List<Item>  foodList = mapper.readValue(respMsg.getBody(), mapType);
            System.out.println("first element: "+ foodList.get(0).getName() + "second element:" + foodList.get(0));
            for(int i =0; i<foodList.size();i++) {
                System.out.println(foodList.get(i).getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        //List<String> foodlist2 = new List<String>();
      //foodlist2 = mapper.readValues(respMsg.getBody(),mmapTapType);


    } */


    /*
    Get all Items
     */
    public void getAllItems(){

    }

    /*e
    get Single Item by name
     */
    public void getItem(String itemName){

    }

}
