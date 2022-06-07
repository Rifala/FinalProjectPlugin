package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.finalproject.Model.Barang
import com.example.finalproject.Model.SingleResponse
import com.example.finalproject.WebService.APIService
import com.example.finalproject.databinding.ActivityUpdateBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Update : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        GetData()
        UpdateBarang()
        back()
    }

    private fun back(){
        binding.back.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
    }


    private fun GetData(){
        binding.etName.setText(intent.getStringExtra("nama"))
        binding.etCode.setText(intent.getStringExtra("kode"))
    }


    private fun UpdateBarang(){
        binding.btnUpdate.setOnClickListener {
            val id = intent.getIntExtra("id",0)
            val nama = binding.etName.text.toString()
            val kode = binding.etCode.text.toString().toInt()
            APIService.ApiEndpoint().UpdateBarang(id,nama,kode).enqueue(object :Callback<SingleResponse<Barang>>{
                override fun onResponse(
                    call: Call<SingleResponse<Barang>>,
                    response: Response<SingleResponse<Barang>>
                ) {
                    if (response.isSuccessful){
                        Toast.makeText(applicationContext, "Update Data Berhasil", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@Update,MainActivity::class.java).also {
                            finish()
                        })
                    }else{
                        Toast.makeText(this@Update, "error", Toast.LENGTH_SHORT).show()
                    }
                }

                override fun onFailure(call: Call<SingleResponse<Barang>>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_SHORT).show()
                }

            })
        }
    }
}