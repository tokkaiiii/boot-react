package com.backend.repository

import com.backend.domain.Reuser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource
import java.util.*

@RepositoryRestResource(exported = false)
interface ReuserRepository : JpaRepository<Reuser, Long> {
    fun findByUsername(username: String): Reuser?
    fun findByNAME(NAME: String): MutableList<Reuser>
}