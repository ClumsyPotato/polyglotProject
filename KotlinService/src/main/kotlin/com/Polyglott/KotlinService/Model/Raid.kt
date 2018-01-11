package com.Polyglott.KotlinService.Model

import org.springframework.boot.autoconfigure.domain.EntityScan
import javax.annotation.Generated
import javax.persistence.*

@Entity
data class Raid constructor(
        val name: String="",


        @Id @GeneratedValue(strategy = GenerationType.AUTO) @Column(name="id")
        var id:Int=0



){

}