package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.finalproject.Model.SingleResponse
import com.example.finalproject.Model.User
import com.example.finalproject.WebService.APIService
import com.example.finalproject.databinding.ActivityRegisterBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        btnRegister()
    }


    private fun btnRegister(){
        binding.btnRegister.setOnClickListener {
            val name = binding.etName.text.toString()
            val username = binding.etUsername.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            APIService.ApiEndpoint().register(name,username, email, password)
                .enqueue(object :Callback<SingleResponse<User>>{
                    override fun onResponse(
                        call: Call<SingleResponse<User>>,
                        response: Response<SingleResponse<User>>
                    ) {
                        if (response.isSuccessful){
                            val body = response.body()
                            if (body != null){
                                Toast.makeText(applicationContext, "Register is Succes", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@Register,Login::class.java))
                            }
                        }
                    }

                    override fun onFailure(call: Call<SingleResponse<User>>, t: Throwable) {
                        Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                    }
                })
        }
    }
}