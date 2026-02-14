package com.finance.financetracker.entity

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "transactions")
data class Transaction(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val amount: BigDecimal,
    val description: String,
    val transactionDate: LocalDateTime,
    val category: String,

    @Enumerated(EnumType.STRING)
    val type: TransactionType,
    val notes: String? = null

)

enum class TransactionType {
    INCOME, EXPENSE
}