package com.finance.financetracker.entity

import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import jakarta.validation.constraints.DecimalMin
import jakarta.validation.constraints.Digits
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.PastOrPresent
import jakarta.validation.constraints.Size
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity
@Table(name = "transactions")
data class Transaction(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:DecimalMin(value = "0.01", message = "Amount must be greater than 0")
    @field:Digits(integer = 10, fraction = 2, message = "Amount can only have up to 2 decimal places")
    val amount: BigDecimal,

    @field:NotBlank(message = "Description cannot be blank")
    @field:Size(min = 1, max = 255, message = "Description must be less than 255 characters")
    val description: String,

    @field:PastOrPresent(message = "Transaction date cannot be in the future")
    val transactionDate: LocalDateTime,

    @field:NotBlank(message = "Category cannot be blank")
    @field:Size(min = 1, max = 50, message = "Category must be less than 50 characters")
    val category: String,

    @Enumerated(EnumType.STRING)
    val type: TransactionType,

    @field:Size(max = 500, message = "Notes cannot be more than 500 characters")
    val notes: String? = null

)

enum class TransactionType {
    INCOME, EXPENSE
}