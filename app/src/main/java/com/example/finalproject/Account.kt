package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.finalproject.WebService.Constant
import com.example.finalproject.databinding.ActivityAccountBinding
import com.example.finalproject.databinding.ActivityMainBinding

class Account : AppCompatActivity() {

    private lateinit var binding : ActivityAccountBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAccountBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Logout()
        back()
        GetData()
    }

    private fun Logout(){
        binding.btnLogout.setOnClickListener {
            Constant.clearToken(this,)
            Constant.clearUsername(this)
            Constant.clearEmail(this)
            startActivity(Intent(this,Login::class.java))
        }
    }


    private fun back(){
        binding.back.setOnClickListener {
            startActivity(Intent())}

    }


    private fun GetData(){
        binding.disName.text = Constant.GetNama(this)
        binding.disEmail.text = Constant.GetEmail(this)
        binding.usernametextDisplay.text = Constant.GetUsername(this)
    }
}