package com.Polyglott.KotlinService.Controller

import com.Polyglott.KotlinService.Model.Raid
import com.Polyglott.KotlinService.Repository.RaidRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class SampleController (@Autowired private val repository: RaidRepository){



    // saveing new raid
    @PostMapping("/saveRaid/")
    fun saveRaid2(@RequestBody name : String):String{
        println("[LOG]saveRaid was called")
        repository.save(Raid(name))
        return "Raid was saved"
    }

    // get Raid by ID
    @GetMapping("getRaidById/{Id}")
    fun getRaidById(@PathVariable("Id") Id:Int){
        val raid = repository.findById(Id)
    }

    // get Raid by name
    @GetMapping("/getRaidByName/{name}")
    fun getRaidByName(@PathVariable("name")name:String):String{

        //system logs
        println("[LOG] getRaidByName was called")

        //retrive raid from database
        val raidList: List<Raid> = repository.findByName(name)

        // transform object to json
        val objectMapper = ObjectMapper()
        val jsonList = objectMapper.writeValueAsString(raidList)
        return jsonList
    }



    @GetMapping("getAllRaids")
    fun getAllRaids():String{

        //system logs
        println("[LOG] gettAllraids was called")

        //retrive raids from database
        val raids = repository.findAll()

        // transforms object to json
        val objectMapper = ObjectMapper()
        return objectMapper.writeValueAsString(raids)


    }






    @DeleteMapping("/deleteRaid/{Id}")
    fun deleteRaid(@PathVariable("Id")Id:Long){
        //repository.delete(Id)
    }



///////////////////////////////////////////////////////////


    @GetMapping("/hello/{name}")
    fun sayHello(@PathVariable("name")name:String): String{
        return "hello "+name
    }


    @PostMapping("/saveRaid1/{name}")
    fun saveRaid(@PathVariable("name") name:String):String {
        println(name + " was printed")
        repository.save(Raid(name))
        return "Raid " + name +" was saved"
    }


    @GetMapping("/saveRaids")
    fun saveRaids(): String {
        repository.save(Raid("lel"))
        return "Message was saved...hopenfully"
    }

}