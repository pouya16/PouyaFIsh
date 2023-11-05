package com.example.pouyafish

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody

class SplashViewmodel:ViewModel() {

    private val _stringResponse = MutableLiveData<Int>()
    val stringResponse = _stringResponse


    suspend fun fetchDevice(mobile: String,number:String){
        _stringResponse.value = 0
        withContext(Dispatchers.IO){
            Log.i("Log1","fetch device name is  :")
            val client = OkHttpClient()
            val json = """
            {
              "originator": "+983000505",
              "recipients": [
                "98$number"
              ],
              "message":"code: $mobile"
            }
            """.trimIndent()


            val mediaType = "application/json; charset=utf-8".toMediaType()
            val requestBody = json.toRequestBody(mediaType)
            Log.i("Log1","request body is: $json")
            val url = "http://rest.ippanel.com/v1/messages"
            val request = Request.Builder()
                .url(url)
                .post(requestBody)
                .header("Authorization", "AccessKey 1VgegmYbZ5ZLrEbDou6fhkeE2M1yNubNfGJ3HAZE_PM=")
                .build()
            try {
                Log.i("Log1","getting response")
                val response = client.newCall(request).execute()
                Log.i("Log1","getting response:  ${response.isSuccessful}")
                Log.i("Log1","getting response code:  ${response.code}")
                Log.i("Log1","getting response bode:  ${response.body}")
                Log.i("Log1","getting response message:  ${response.message}")
                if (response.isSuccessful) {
                    Log.i("Log1","successful send sms")
                    withContext(Dispatchers.Main){
                        _stringResponse.value = 1
                    }
                    /*val responseBody = response.body()?.string()
                    if (responseBody != null) {
                        val gson = Gson()
                        val responseData = gson.fromJson(responseBody, ResponseDataSwitch::class.java)
                        Log.i("Log1","switch device response ${responseData}")
                        if(responseData.action){
                            _switchDeviceResponse.postValue(responseData.result)
                        }

                    }*/
                }
            } catch (e: Exception) {
                Log.i("Log1","exception fetching data is $e")
                withContext(Dispatchers.Main){
                    _stringResponse.value = 2
                }
                // Handle exceptions, e.g., network errors
                e.printStackTrace()
            }
        }
    }

}