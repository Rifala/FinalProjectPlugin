package com.example.finalproject.WebService

import android.content.Context
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class APIService {

    companion object{
        private var retrofit: Retrofit? = null
        private var okHttpClient = OkHttpClient.Builder()
            .connectTimeout(30,TimeUnit.SECONDS)
            .readTimeout(30,TimeUnit.SECONDS)
            .writeTimeout(30,TimeUnit.SECONDS)
            .build()


        private fun getClient(): Retrofit{
            return if (retrofit == null){
                retrofit = Retrofit.Builder().baseUrl(Constant.BASE_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create()).build()
                retrofit!!
            }else{
                retrofit!!
            }
        }

        fun ApiEndpoint(): APIEndPoint = getClient().create(APIEndPoint::class.java)

    }
}


class Constant{

    companion object{
        const val BASE_URL = "https://apibarang.herokuapp.com/"

        fun setToken(context: Context, token: String){
            val settoken = context.getSharedPreferences("Token", Context.MODE_PRIVATE)
            settoken.edit().apply {
                putString("Token", token)
                apply()
            }
        }
        fun setNama(context: Context, nama: String){
            val settoken = context.getSharedPreferences("Token", Context.MODE_PRIVATE)
            settoken.edit().apply {
                putString("nama", nama)
                apply()
            }
        }

        fun setEmail(context: Context, email: String){
            val settoken = context.getSharedPreferences("Token", Context.MODE_PRIVATE)
            settoken.edit().apply {
                putString("Email", email)
                apply()
            }
        }

        fun getToken(context: Context): String{
            val sharePref = context.getSharedPreferences("Token", Context.MODE_PRIVATE)
            val token = sharePref.getString("Token","Undef")
            return token!!
        }

        fun GetNama(context: Context):String?{
            val name = context.getSharedPreferences("Token",Context.MODE_PRIVATE)
            val nameregist = name.getString("nama","nama")
            return nameregist
        }

        fun GetUsername(context: Context):String?{
            val name = context.getSharedPreferences("Token",Context.MODE_PRIVATE)
            val nameregist = name.getString("Username","User")
            return nameregist
        }

        fun GetEmail(context: Context):String?{
            val name_email = context.getSharedPreferences("Token",Context.MODE_PRIVATE)
            val registemail = name_email.getString("Email","email")
            return registemail
        }

        fun clearToken(context: Context){
            val sharePref = context.getSharedPreferences("Token", Context.MODE_PRIVATE)
            sharePref.edit().clear().apply()
        }

        fun clearUsername(context: Context){
            val delete = context.getSharedPreferences("Token",Context.MODE_PRIVATE)
            delete.edit().clear().apply()
        }

        fun clearEmail(context: Context){
            val delete = context.getSharedPreferences("Token",Context.MODE_PRIVATE)
            delete.edit().clear().apply()
        }


    }
}