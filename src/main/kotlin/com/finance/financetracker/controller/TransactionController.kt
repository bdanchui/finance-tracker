package com.finance.financetracker.controller

import com.finance.financetracker.entity.Transaction
import com.finance.financetracker.service.TransactionService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/transactions")
class TransactionController(
    private val transactionService: TransactionService,
) {

    @GetMapping
    fun getTransactions(): ResponseEntity<List<Transaction>> {
        return ResponseEntity.ok(
            transactionService.getAllTransactions()
        )
    }

    @PostMapping
    fun createTransaction(
        @RequestBody transaction: Transaction
    ): ResponseEntity<Transaction> {

        return ResponseEntity.status(HttpStatus.CREATED).body(
            transactionService.createTransaction(transaction)
        )
    }

    @GetMapping("/{id}")
    fun getTransactionById(
        @PathVariable("id") id: Long
    ): ResponseEntity<Transaction?> {
        val transaction = transactionService.getTransactionById(id)
        return if (transaction != null) ResponseEntity.ok(transaction) else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{id}")
    fun deleteTransaction(
        @PathVariable("id") id: Long
    ): ResponseEntity<Void> {
        transactionService.deleteTransaction(id = id)
        return ResponseEntity.noContent().build()
    }

    @PutMapping("/{id}")
    fun updateTransaction(
        @PathVariable("id") id: Long,
        @RequestBody transaction: Transaction
    ): ResponseEntity<Transaction> {
        val existingTransaction = transactionService.getTransactionById(id)
            ?: return ResponseEntity.notFound().build()

        val updatedTransaction = existingTransaction .copy(
            amount = transaction.amount,
            description = transaction.description,
            transactionDate = transaction.transactionDate,
            category = transaction.category,
            type = transaction.type,
            notes = transaction.notes,
        )
        return ResponseEntity.ok(transactionService.updateTransaction(updatedTransaction))
    }
}