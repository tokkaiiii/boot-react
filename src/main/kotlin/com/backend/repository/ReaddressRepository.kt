package com.backend.repository

import com.backend.domain.Readdress
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.query.Param
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "places")
interface ReaddressRepository : JpaRepository<Readdress, Long> {
    fun findByZip(@Param("zip") zip: String): List<Readdress>
    fun findByAddrContaining(@Param("addr") addr: String): List<Readdress>
}