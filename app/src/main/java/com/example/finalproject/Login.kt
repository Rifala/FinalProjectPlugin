package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.finalproject.Model.SingleResponse
import com.example.finalproject.Model.User
import com.example.finalproject.WebService.APIService
import com.example.finalproject.WebService.Constant
import com.example.finalproject.databinding.ActivityLoginBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        moveRegister()
        isLogin()
        btnLogin()
    }


    private fun moveRegister(){
        binding.btnMoveRegister.setOnClickListener {
            startActivity(Intent(this, Register::class.java))
        }
    }

    private fun isLogin(){
        val token = Constant.getToken(this)
        if (!token.equals("Undef")){
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }

    private fun btnLogin(){
        binding.btnLogin.setOnClickListener {
            val username = binding.etUsername.text.toString()
            val password = binding.etPassword.text.toString()
            APIService.ApiEndpoint().login(username, password).enqueue(object :Callback<SingleResponse<User>>{
                override fun onResponse(call: Call<SingleResponse<User>>, response: Response<SingleResponse<User>>){
                    if (response.isSuccessful){
                        val body = response.body()
                        if (body != null){
                            Constant.setToken(this@Login, body.data.token)
                            Constant.setNama(this@Login, body.data.name)
                            Toast.makeText(applicationContext, "Login Succes ${body.data.name}", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@Login, MainActivity::class.java))
                            finish()
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