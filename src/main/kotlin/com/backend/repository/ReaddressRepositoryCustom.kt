package com.backend.repository

import com.backend.domain.Readdress
import com.linecorp.kotlinjdsl.querydsl.expression.column
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.listQuery
import jakarta.persistence.EntityManager
import org.springframework.stereotype.Repository

@Repository
class ReaddressRepositoryCustom(
    private val em: EntityManager,
    private val queryFactory: SpringDataQueryFactory
) {
    fun findByZip(zip: String): List<Readdress> {
        return queryFactory.listQuery<Readdress> {
            select(entity(Readdress::class))
            from(entity(Readdress::class))
            where(
                column(Readdress::zip).equal(zip)
            )
        }
    }
}
