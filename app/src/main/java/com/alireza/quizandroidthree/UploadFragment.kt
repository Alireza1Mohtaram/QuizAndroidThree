package com.alireza.quizandroidthree

import android.content.Context
import android.content.SharedPreferences
import android.graphics.Bitmap
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.launch
import androidx.fragment.app.Fragment
import com.alireza.quizandroidthree.databinding.UploadFragmentBinding
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.ByteArrayOutputStream

class UploadFragment : Fragment(R.layout.upload_fragment) {



    lateinit var binding:UploadFragmentBinding
    lateinit var imgBitmap:Bitmap
    private var luncher = registerForActivityResult(ActivityResultContracts.TakePicturePreview()) {
        getUi(it)
        imgBitmap = it
    }
    lateinit var handler: Handler
    lateinit var sp :SharedPreferences

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = UploadFragmentBinding.bind(view)

        binding.imgProfile.setOnClickListener {
            luncher.launch()
        }

       binding.btnUpload.setOnClickListener{
           sp = requireContext().getSharedPreferences("id",Context.MODE_PRIVATE)
           Log.d("id",sp.getString("id","null").toString())
           sp.getString("id","null")?.let { it1 -> uploadInServer(imgBitmap,binding.etUploadText.text.toString(), it1) }
       }


    }
    private fun uploadInServer(src: Bitmap , name:String , id:String) {

        val stream = ByteArrayOutputStream()
        src.compress(Bitmap.CompressFormat.JPEG,100,stream)

        val part = MultipartBody.Part.createFormData(
            "profile", "$name.jpg",
            RequestBody.create("image/*".toMediaTypeOrNull(), stream.toByteArray())
        )
        CreateUser.service.uploadProfile(id, part)
            .enqueue(object : Callback<String?> {
                override fun onResponse(call: Call<String?>, response: Response<String?>) {
                    response.body()?.let { Log.d("response", it) }
                }

                override fun onFailure(call: Call<String?>, t: Throwable) {
                    Log.d("response", "Fail")
                }
            })
    }
    private fun getUi(src: Bitmap) {
        handler = Handler(Looper.getMainLooper())
        handler.post {
            binding.imgProfile.setImageBitmap(src)
        }
    }
}