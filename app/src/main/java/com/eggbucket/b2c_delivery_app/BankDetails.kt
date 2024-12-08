package com.eggbucket.b2c_delivery_app

data class BankDetails(
    val accountHolderName: String,
    val bankName: String,
    val accountNumber: String,
    val branchName: String,
    val ifscCode: String
)
