package com.backend.domain

import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.*
import jakarta.persistence.FetchType.*
import jakarta.persistence.GenerationType.IDENTITY
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import java.time.LocalDateTime

@Entity
@Table(name = "readdress")
class Readdress(
    zip: String,
    addr: String,
    reuser: Reuser?
) {

    @Id @GeneratedValue(strategy = IDENTITY)
    var id: Long? = null

    var zip: String = zip
        private set
    var addr: String = addr
        private set

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "reuser_id")
    var reuser: Reuser = reuser?: throw IllegalArgumentException("유저를 찾을 수 없습니다.")
        private set

    @CreatedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
    var rdate: LocalDateTime = LocalDateTime.now()

    @LastModifiedDate
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd hh:mm:ss", timezone = "Asia/Seoul")
    var udate: LocalDateTime = LocalDateTime.now()


}