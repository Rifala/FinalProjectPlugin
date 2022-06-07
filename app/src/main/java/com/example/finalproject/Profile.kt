package com.example.finalproject

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.finalproject.WebService.Constant
import com.example.finalproject.databinding.FragmentProfileBinding


class Profile : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(layoutInflater)
        GetData()
        back()
        Logout()

        return binding.root
    }

    private fun Logout(){
        binding.btnLogout.setOnClickListener {
            Constant.clearToken(this.requireContext())
            Constant.clearUsername(this.requireContext())
            Constant.clearEmail(this.requireContext())
        }
    }


    private fun back(){
        binding.back.setOnClickListener {
            startActivity(Intent())}

    }


    private fun GetData(){
        binding.disName.text = Constant.GetNama(this.requireContext())
        binding.disEmail.text = Constant.GetEmail(this.requireContext())
        binding.usernametextDisplay.text = Constant.GetUsername(this.requireContext())
    }

}