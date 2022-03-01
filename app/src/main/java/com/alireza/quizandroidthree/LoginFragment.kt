package com.alireza.quizandroidthree

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.alireza.quizandroidthree.databinding.LoginFragmentBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginFragment : Fragment(R.layout.login_fragment) {
    
    lateinit var binding : LoginFragmentBinding
    lateinit var sp:SharedPreferences
    val nav : NavController by lazy { findNavController() }
    var result = true
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = LoginFragmentBinding.bind(view)
        binding.btnLogin.setOnClickListener{
            Log.d("response","clicked")
            //if (sp.contains("id")) nav.navigate(R.id.uploadFragment)
           if ( registerUser() ) it.findNavController().navigate(R.id.uploadFragment)
//            if (registerUser()) it.findNavController().navigate(R.id.uploadFragment)
//            else Toast.makeText(requireContext(),"User is not valid",Toast.LENGTH_SHORT).show()
        }

    }

    private fun registerUser():Boolean {

        val user = getUser()
        Log.d("response",user.toString())

        val sp = requireContext().getSharedPreferences("id",Context.MODE_PRIVATE)
        CreateUser.service.registerUser(
            user).enqueue(object : Callback<String?> {
            override fun onResponse(call: Call<String?>, response: Response<String?>) {
                if (response.isSuccessful) result = true
                val editor = sp.edit()
                editor.putString("id",response.body().toString())
                editor.commit()
                Log.d("response" , response.body().toString())
            }

            override fun onFailure(call: Call<String?>, t: Throwable) {
                Log.d("response" , t.toString())
                result = false
            }
        })
        return result

    }

    private fun getUser(): User {

        return User(
            binding.etName.text.toString(),
            binding.etLastname.text.toString(),
            binding.etCode.text.toString(),
            getListOfHobbies()
        )


            }

    private fun getListOfHobbies(): List<String> {
        val listOfHobbies  =  mutableListOf<String>()
        if (binding.cbSport.isChecked) listOfHobbies.add("Sport")
        if (binding.cbFootball.isChecked) listOfHobbies.add("football")

        return listOfHobbies
    }

}