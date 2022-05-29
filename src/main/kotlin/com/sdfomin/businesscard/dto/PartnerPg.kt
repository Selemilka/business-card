package com.sdfomin.businesscard.dto

import com.sdfomin.businesscard.entity.Partner
import org.springframework.data.jpa.repository.JpaRepository
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "partner")
class PartnerPg {
    @Id
    @Column(name = "partner_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0

    @Column(name = "login")
    var login: String = ""

    constructor()

    constructor(partner: Partner) {

    }
}

interface PartnerRepo : JpaRepository<PartnerPg, Int>