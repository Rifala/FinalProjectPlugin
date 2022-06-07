package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.finalproject.Model.Barang
import com.example.finalproject.Model.SingleResponse
import com.example.finalproject.WebService.APIService
import com.example.finalproject.databinding.ActivityCreateBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Create : AppCompatActivity() {

    private lateinit var binding: ActivityCreateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCreateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createData()
        back()
    }

    private fun back(){
        binding.back.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    private fun createData(){
        binding.btnAdd.setOnClickListener {
            val name = binding.etName.text.toString()
            val code = binding.etCode.text.toString().toInt()
            APIService.ApiEndpoint().PostData(name,code).enqueue(object :
                Callback<SingleResponse<Barang>> {
                override fun onResponse(
                    call: Call<SingleResponse<Barang>>,
                    response: Response<SingleResponse<Barang>>
                ) {
                    if (response.isSuccessful){
                        val body = response.body()
                        if (body != null){
                            Toast.makeText(applicationContext, "Create Data Succesfully", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@Create,MainActivity::class.java))
                        }
                    }
                }

                override fun onFailure(call: Call<SingleResponse<Barang>>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}