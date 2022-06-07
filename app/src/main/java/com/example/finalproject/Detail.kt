package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.finalproject.Model.Barang
import com.example.finalproject.Model.ListResponse
import com.example.finalproject.Model.SingleResponse
import com.example.finalproject.WebService.APIService
import com.example.finalproject.databinding.ActivityDetailBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class Detail : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
        deleteData()
        back()
    }

    private fun back(){
        binding.back.setOnClickListener{
            startActivity(Intent(this,MainActivity::class.java))
        }
    }

    private fun setData(data: MutableList<Barang>){
        binding.tvNamaBarang.text = data[0].nama
        binding.tvKodeBarang.text = data[0].kode.toString()
        binding.tvCreateBarang.text = data[0].createdAt
        binding.tvUpdateBarang.text = data[0].updatedAt
    }

    private fun getData(){
        APIService.ApiEndpoint().getDataById(intent.getIntExtra("id",0))
            .enqueue(object :Callback<ListResponse<Barang>>{
                override fun onResponse(
                    call: Call<ListResponse<Barang>>,
                    response: Response<ListResponse<Barang>>
                ) {
                    if (response.isSuccessful){
                        val body = response.body()
                        if (body != null){
                            setData(body.data)
                        }
                    }
                }

                override fun onFailure(call: Call<ListResponse<Barang>>, t: Throwable) {
                    Toast.makeText(this@Detail, "Gagal Terhubung", Toast.LENGTH_SHORT).show()
                }

            })
    }


    private fun deleteData(){
        binding.btnDelete.setOnClickListener {
            APIService.ApiEndpoint().DeleteBarang(intent.getIntExtra("id",0))
                .enqueue(object : Callback<SingleResponse<Barang>>{
                    override fun onResponse(
                        call: Call<SingleResponse<Barang>>,
                        response: Response<SingleResponse<Barang>>
                    ) {
                        if (response.isSuccessful){
                            val body = response.body()
                            if (body != null){
                                Toast.makeText(applicationContext, "Delete Data is Succes", Toast.LENGTH_SHORT).show()
                                startActivity(Intent(this@Detail,MainActivity::class.java).also {
                                    finish()
                                })
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