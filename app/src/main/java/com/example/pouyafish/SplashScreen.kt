package com.example.pouyafish

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.pouyafish.data.UserPreferences
import com.example.pouyafish.databinding.ActivitySplashScreenBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import kotlin.random.Random
var mode = 1
var number = ""
var mobile = ""
lateinit var binding : ActivitySplashScreenBinding
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_splash_screen)
        val viewModel = ViewModelProvider(this)[SplashViewmodel::class.java]

        viewModel.stringResponse.observe(this) { data ->
            when (data) {
                0 -> changeMode(0)
                1 -> {changeMode(2)
                mode = 2}
                2 -> {changeMode(1)
                    mode = 1 }
            }
        }

        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val userPreferences = UserPreferences(applicationContext)
        if(userPreferences.getMobileNumber().length>2){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
            this.finish()
        }
        changeMode(1)
        val coroutineScope = CoroutineScope(Dispatchers.Main)

        binding.btnContinue.setOnClickListener {
            if(mode == 1){
                mobile = binding.txtMobile.text.toString()
                number = generate()
                when (mobile.length) {
                    10 -> {
                        coroutineScope.launch {
                            viewModel.fetchDevice(number, mobile) // Replace with the name of your suspend function
                        }
                        //sendMessage(number,mobile)
                    }
                    11 -> {
                        mobile = mobile.substring(1)
                        coroutineScope.launch {
                            viewModel.fetchDevice(number, mobile) // Replace with the name of your suspend function
                        }
                        //sendMessage(number,mobile)
                    }
                    else -> {
                        Toast.makeText(applicationContext,"موبایل را بصورت صحیح وارد کنید",Toast.LENGTH_SHORT).show()
                    }
                }
            }else if(mode == 2){
                Log.i("Log1", "number is : $number and pinview is ${binding.pinview.text.toString()}")
                if(number.compareTo(binding.pinview.text.toString())==0){
                    userPreferences.saveMobileNumber(mobile)
                    val intent = Intent(this,MainActivity::class.java)
                    startActivity(intent)
                    this.finish()
                }else{
                    Toast.makeText(applicationContext,"کد وارد شده اشتباه است",Toast.LENGTH_SHORT).show()
                }
            }
        }

        binding.btnChangeMobile.setOnClickListener {
            changeMode(1)
        }
        binding.btnResendCode

    }
    private fun changeMode(modes:Int){
        mode = modes
        binding.progressBar.visibility = View.GONE
        binding.btnContinue.visibility = View.VISIBLE
        when(mode){
            0->{
                binding.progressBar.visibility = View.VISIBLE
                binding.btnContinue.visibility = View.GONE
                binding.boxMobile.visibility = View.VISIBLE
                binding.layoutPin.visibility = View.GONE
            }
            1->{
                binding.boxMobile.visibility = View.VISIBLE
                binding.layoutPin.visibility = View.GONE
            }
            2->{
                binding.boxMobile.visibility = View.GONE
                binding.layoutPin.visibility = View.VISIBLE
            }

        }
    }



    private fun generate():String{
        val random = Random.nextInt(1000,9999)
        return random.toString()
    }
}