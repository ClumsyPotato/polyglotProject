package com.Polyglott.KotlinService.Repository

import com.Polyglott.KotlinService.Model.Raid
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.CrudRepository
//import org.springframework.data.rest.core.annotation.RepositoryRestResource
import org.springframework.data.repository.query.QueryByExampleExecutor
import org.springframework.stereotype.Repository


//@RepositoryRestResource(collectionResourceRel = "raids", path = "raids")

@Repository
interface RaidRepository: JpaRepository<Raid,Long> {
    fun findByName(Name: String): List<Raid>
    fun findById(Id: Int): Raid
}