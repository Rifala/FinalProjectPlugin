package com.example.finalproject

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.Adapter.MainAdapter
import com.example.finalproject.Model.Barang
import com.example.finalproject.Model.ListResponse
import com.example.finalproject.WebService.APIService
import com.example.finalproject.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mainAdapter: MainAdapter
    private lateinit var profile: Profile

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
        btnNav()
    }


    private fun btnNav(){
        binding.navigatinBottom.setOnItemSelectedListener {
            when(it.itemId){

                R.id.addNavigation -> startActivity(Intent(this,Create::class.java))
                R.id.profileNav -> startActivity(Intent(this,Account::class.java))
            }
            true

        }

//        BottomNavigationView.OnNavigationItemSelectedListener { item ->
//            when(item.itemId){
//                R.id.addNavigation -> {startActivity(Intent(this,MainActivity::class.java))
//                    true
//                }
//
//                R.id.profileNavigation -> {startActivity(Intent(this,Profile::class.java))
//                    true
//                }
//                else -> false
//
//            }
//        }
    }

    private  fun setfragment(fragment : Fragment){
        supportFragmentManager.beginTransaction().add(R.id.container,fragment).commit()
    }



    private fun showRecycler(data: MutableList<Barang>){
        mainAdapter = MainAdapter(data,object :MainAdapter.OnClick{
            override fun OnRead(barang: Barang) {
                startActivity(Intent(this@MainActivity,Detail::class.java).also {
                    it.putExtra("id",barang.id)
                })

            }

            override fun OnUpdate(barang: Barang) {
                startActivity(Intent(this@MainActivity,Update::class.java).also {
                    it.putExtra("id",barang.id)
                    it.putExtra("nama",barang.nama)
                    it.putExtra("kode",barang.kode.toString())
                })
            }

        })

        binding.RecyclerView.apply {
            adapter = mainAdapter
            layoutManager = LinearLayoutManager(this@MainActivity)
        }
    }


    private fun getData(){
        APIService.ApiEndpoint().getData().enqueue(object :Callback<ListResponse<Barang>>{
            override fun onResponse(
                call: Call<ListResponse<Barang>>,
                response: Response<ListResponse<Barang>>
            ) {
                if (response.isSuccessful){
                    val body = response.body()
                    if (body != null){
                        showRecycler(body.data)
                    }
                }
            }

            override fun onFailure(call: Call<ListResponse<Barang>>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Gagal Terhubung", Toast.LENGTH_SHORT).show()
            }

        })

    }
}