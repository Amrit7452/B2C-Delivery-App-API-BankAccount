package com.eggbucket.b2c_delivery_app

import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class BankAccountDetails : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bank_account_details)

        val submitBtn: TextView = findViewById(R.id.submitButtonBankDoc)
        submitBtn.setOnClickListener {
            val accountHolderName = findViewById<EditText>(R.id.accountHolderNameInput).text.toString()
            val bankName = findViewById<EditText>(R.id.bankNameInput).text.toString()
            val accountNumber = findViewById<EditText>(R.id.accountNumberInput).text.toString()
            val branchName = findViewById<EditText>(R.id.branchNameInput).text.toString()
            val ifscCode = findViewById<EditText>(R.id.ifscCodeInput).text.toString()
            val phnnumber = findViewById<EditText>(R.id.primaryPhoneInput).text.toString()

            if (validateInputs(accountHolderName, bankName, accountNumber, branchName, ifscCode)) {
                val bankDetails = BankDetails(accountHolderName, bankName, accountNumber, branchName, ifscCode)
                submitBankDetailsApi(phnnumber, bankDetails)
            }
        }
    }

    private fun submitBankDetailsApi(phoneNumber: String, bankDetails: BankDetails) {
        val api = RetrofitClient.retrofit.create(ApiService::class.java)


        val call = api.submitBankDetails(phoneNumber, bankDetails)

        call.enqueue(object : Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Toast.makeText(this@BankAccountDetails, "Details submitted successfully!", Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this@BankAccountDetails, "Submission failed: ${response.message()}", Toast.LENGTH_LONG).show()
                }
            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Toast.makeText(this@BankAccountDetails, "Error: ${t.message}", Toast.LENGTH_LONG).show()
            }
        })
    }

    private fun validateInputs(
        accountHolderName: String,
        bankName: String,
        accountNumber: String,
        branchName: String,
        ifscCode: String
    ): Boolean {
        if (accountHolderName.isBlank() || bankName.isBlank() || accountNumber.isBlank() || branchName.isBlank() || ifscCode.isBlank()) {
            Toast.makeText(this, "Please fill all fields.", Toast.LENGTH_SHORT).show()
            return false
        }
        return true
    }
}
