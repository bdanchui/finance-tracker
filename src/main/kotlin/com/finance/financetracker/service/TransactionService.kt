package com.finance.financetracker.service

import com.finance.financetracker.entity.Transaction
import com.finance.financetracker.repository.TransactionRepository
import org.springframework.stereotype.Service
import kotlin.jvm.optionals.getOrNull

@Service
class TransactionService(
    private val transactionRepository: TransactionRepository
) {
    fun createTransaction(transaction: Transaction): Transaction {
        return transactionRepository.save(transaction)
    }

    fun getAllTransactions(): List<Transaction> {
        return transactionRepository.findAll()
    }

    fun getTransactionById(id: Long): Transaction? {
        return transactionRepository.findById(id).getOrNull()
    }

    fun deleteTransaction(id: Long) {
        return transactionRepository.deleteById(id)
    }

    fun updateTransaction(transaction: Transaction): Transaction {
        return transactionRepository.save(transaction)
    }
}