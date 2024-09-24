package com.backend.controller

import com.backend.domain.Readdress
import com.backend.dto.ReaddressRequest
import com.backend.repository.ReaddressRepository
import com.backend.repository.ReuserRepository
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ReaddressController(
    val readdressRepository: ReaddressRepository,
    val reuserRepository: ReuserRepository) {

    @GetMapping("/addrs")
    fun getReaddresses(): MutableList<Readdress> = readdressRepository.findAll()

    @PostMapping("/addrs")
    fun addReaddress(@RequestBody readdressRequest: ReaddressRequest): Readdress {
        val findUser = reuserRepository.findByUsername(readdressRequest.username)?: throw IllegalStateException("유저가 없습니다")
        val readdress = Readdress(readdressRequest.zip,readdressRequest.addr,findUser)
       return readdressRepository.save(readdress)
    }
}