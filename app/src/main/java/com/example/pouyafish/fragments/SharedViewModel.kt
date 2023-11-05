package com.example.pouyafish.fragments

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONArray
import java.io.IOException
import java.security.spec.ECField

class SharedViewModel: ViewModel() {


    private val _yourDataList = MutableLiveData<List<YourDataClass>>()
    val yourDataList: LiveData<List<YourDataClass>> get() = _yourDataList


    fun fetchData(mobile: String) {
        val url = "http://solicarpet.ir/fish_app/check.php"
        Log.i("Log1","user mobile is  : $mobile")
        val formBody = FormBody.Builder()
            .add("getData", "yes")
            .add("phone", mobile)
            .build()

        val request = Request.Builder()
            .url(url)
            .post(formBody)
            .header("Content-Type", "application/x-www-form-urlencoded")
            .build()

        val client = OkHttpClient()

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = client.newCall(request).execute()
                val responseBody = response.body?.string()
                if (response.isSuccessful && responseBody != null) {
                    val responseBodyString = responseBody// Read response body as a string

                    Log.i("Log1",responseBodyString)
                    val jsonArray = JSONArray(responseBody)

                    // Create a list to hold parsed data
                    val parsedData = mutableListOf<YourDataClass>()

                    for (i in 0 until jsonArray.length()) {
                        val jsonObject = jsonArray.getJSONObject(i)
                        val TotPay: Double = try{jsonObject.getDouble("TotPay")}catch (e:java.lang.Exception){0.0}
                        val MslCost08: Double = try{jsonObject.getDouble("MslCost08")}catch (e:Exception){0.0}
                        val MslHours08: Double = try{jsonObject.getDouble("MslHours08")}catch (e:java.lang.Exception){0.0}
                        val MslNo08: Int = try{jsonObject.getInt("MslHours08")}catch (e:Exception){0}
                        val MslDs08: String = try{jsonObject.getString("MslDs08")}catch (e:Exception){""}
                        val MslCost07: Double = try{jsonObject.getDouble("MslCost07")}catch (e:Exception){0.0}
                        val MslHours07: Double = try{jsonObject.getDouble("MslHours07")}catch (e:java.lang.Exception){0.0}
                        val MslNo07: Int = try{jsonObject.getInt("MslHours07")}catch (e:Exception){0}
                        val MslDs07: String = try{jsonObject.getString("MslDs07")}catch (e:Exception){""}
                        val MslCost06: Double = try{jsonObject.getDouble("MslCost06")}catch (e:Exception){0.0}
                        val MslHours06: Double = try{jsonObject.getDouble("MslHours06")}catch (e:java.lang.Exception){0.0}
                        val MslNo06: Int = try{jsonObject.getInt("MslHours06")}catch (e:Exception){0}
                        val MslDs06: String = try{jsonObject.getString("MslDs06")}catch (e:Exception){""}
                        val MslCost05: Double = try{jsonObject.getDouble("MslCost05")}catch (e:Exception){0.0}
                        val MslHours05: Double = try{jsonObject.getDouble("MslHours05")}catch (e:java.lang.Exception){0.0}
                        val MslNo05: Int = try{jsonObject.getInt("MslHours05")}catch (e:Exception){0}
                        val MslDs05: String = try{jsonObject.getString("MslDs05")}catch (e:Exception){""}
                        val MslCost04: Double = try{jsonObject.getDouble("MslCost04")}catch (e:Exception){0.0}
                        val MslHours04: Double = try{jsonObject.getDouble("MslHours04")}catch (e:java.lang.Exception){0.0}
                        val MslNo04: Int = try{jsonObject.getInt("MslHours04")}catch (e:Exception){0}
                        val MslDs04: String = try{jsonObject.getString("MslDs04")}catch (e:Exception){""}
                        val MslCost03: Double = try{jsonObject.getDouble("MslCost03")}catch (e:Exception){0.0}
                        val MslHours03: Double = try{jsonObject.getDouble("MslHours03")}catch (e:java.lang.Exception){0.0}
                        val MslNo03: Int = try{jsonObject.getInt("MslHours03")}catch (e:Exception){0}
                        val MslDs03: String = try{jsonObject.getString("MslDs03")}catch (e:Exception){""}
                        val MslCost02: Double = try{jsonObject.getDouble("MslCost02")}catch (e:Exception){0.0}
                        val MslHours02: Double = try{jsonObject.getDouble("MslHours02")}catch (e:java.lang.Exception){0.0}
                        val MslNo02: Int = try{jsonObject.getInt("MslHours02")}catch (e:Exception){0}
                        val MslDs02: String = try{jsonObject.getString("MslDs02")}catch (e:Exception){""}
                        val MslCost01: Double = try{jsonObject.getDouble("MslCost01")}catch (e:Exception){0.0}
                        val MslHours01: Double = try{jsonObject.getDouble("MslHours01")}catch (e:java.lang.Exception){0.0}
                        val MslNo01: Int = try{jsonObject.getInt("MslHours01")}catch (e:Exception){0}
                        val MslDs01: String = try{jsonObject.getString("MslDs01")}catch (e:Exception){""}
                        val MslName: String = try{jsonObject.getString("MslName")}catch (e:Exception){""}
                        val DAcctNo: String = try{jsonObject.getString("DAcctNo")}catch (e:Exception){""}
                        val Padash2: Double = try{jsonObject.getDouble("Padash2")}catch (e:Exception){0.0}
                        val AcctOwner: String = try{jsonObject.getString("AcctOwner")}catch (e:Exception){""}
                        val BnType: String = try{jsonObject.getString("BnType")}catch (e:Exception){""}
                        val AcctNo: String = try{jsonObject.getString("AcctNo")}catch (e:Exception){""}
                        val Padash1: Double = try{jsonObject.getDouble("Padash1")}catch (e:Exception){0.0}
                        val NetPay: Double = try{jsonObject.getDouble("NetPay")}catch (e:Exception){0.0}
                        val TotDed: Double = try{jsonObject.getDouble("TotDed")}catch (e:Exception){0.0}
                        val Insrt: Double = try{jsonObject.getDouble("Insrt")}catch (e:Exception){0.0}
                        val Ded: Double = try{jsonObject.getDouble("Ded")}catch (e:Exception){0.0}
                        val Insrx: Double = try{jsonObject.getDouble("Insrx")}catch (e:Exception){0.0}
                        val Bimeh: Double = try{jsonObject.getDouble("Bimeh")}catch (e:Exception){0.0}
                        val Tavoni: Double = try{jsonObject.getDouble("Tavoni")}catch (e:Exception){0.0}
                        val Alal: Double = try{jsonObject.getDouble("Alal")}catch (e:Exception){0.0}
                        val Jar2: Double = try{jsonObject.getDouble("Jar2")}catch (e:Exception){0.0}
                        val Jar1: Double = try{jsonObject.getDouble("Jar1")}catch (e:Exception){0.0}
                        val Dep: Double = try{jsonObject.getDouble("Dep")}catch (e:Exception){0.0}
                        val Bed: Double = try{jsonObject.getDouble("Bed")}catch (e:Exception){0.0}
                        val Taxx: Double = try{jsonObject.getDouble("Taxx")}catch (e:Exception){0.0}
                        val Vam1: Double = try{jsonObject.getDouble("Vam1")}catch (e:Exception){0.0}
                        val Vam2: Double = try{jsonObject.getDouble("Vam2")}catch (e:Exception){0.0}
                        val TotMaz: Double = try{jsonObject.getDouble("TotMaz")}catch (e:Exception){0.0}
                        val Sh80: Double = try{jsonObject.getDouble("Sh80")}catch (e:Exception){0.0}
                        val Padash: Double = try{jsonObject.getDouble("Padash")}catch (e:Exception){0.0}
                        val Moavx: Double = try{jsonObject.getDouble("Moavx")}catch (e:Exception){0.0}
                        val Disp: Double = try{jsonObject.getDouble("Disp")}catch (e:Exception){0.0}
                        val Sitex: Double = try{jsonObject.getDouble("Sitex")}catch (e:Exception){0.0}
                        val Mslns: Double = try{jsonObject.getDouble("Mslns")}catch (e:Exception){0.0}
                        val Mamrt: Double = try{jsonObject.getDouble("Mamrt")}catch (e:Exception){0.0}
                        val Extra: Double = try{jsonObject.getDouble("Extra")}catch (e:Exception){0.0}
                        val Maz: Double = try{jsonObject.getDouble("Maz")}catch (e:Exception){0.0}
                        val Moav: Double = try{jsonObject.getDouble("Moav")}catch (e:Exception){0.0}
                        val MsCost7: Double = try{jsonObject.getDouble("MsCost7")}catch (e:Exception){0.0}
                        val MsNoz7: Double = try{jsonObject.getDouble("MsNoz7")}catch (e:Exception){0.0}
                        val MsNo7: Double = try{jsonObject.getDouble("MsNo7")}catch (e:Exception){0.0}
                        val msDs7: String = try{jsonObject.getString("msDs7")}catch (e:Exception){""}
                        val mscd7: Double = try{jsonObject.getDouble("mscd7")}catch (e:Exception){0.0}
                        val rad7: Double = try{jsonObject.getDouble("rad7")}catch (e:Exception){0.0}

                        val MsCost6: Double = try{jsonObject.getDouble("MsCost6")}catch (e:Exception){0.0}
                        val MsNoz6: Double = try{jsonObject.getDouble("MsNoz6")}catch (e:Exception){0.0}
                        val MsNo6: Double = try{jsonObject.getDouble("MsNo6")}catch (e:Exception){0.0}
                        val msDs6: String = try{jsonObject.getString("msDs6")}catch (e:Exception){""}
                        val mscd6: Double = try{jsonObject.getDouble("mscd6")}catch (e:Exception){0.0}
                        val rad6: Double = try{jsonObject.getDouble("rad6")}catch (e:Exception){0.0}
                        val MsCost5: Double = try{jsonObject.getDouble("MsCost5")}catch (e:Exception){0.0}
                        val MsNoz5: Double = try{jsonObject.getDouble("MsNoz5")}catch (e:Exception){0.0}
                        val MsNo5: Double = try{jsonObject.getDouble("MsNo5")}catch (e:Exception){0.0}
                        val msDs5: String = try{jsonObject.getString("msDs5")}catch (e:Exception){""}
                        val mscd5: Double = try{jsonObject.getDouble("mscd5")}catch (e:Exception){0.0}
                        val rad5: Double = try{jsonObject.getDouble("rad5")}catch (e:Exception){0.0}
                        val MsCost4: Double = try{jsonObject.getDouble("MsCost4")}catch (e:Exception){0.0}
                        val MsNoz4: Double = try{jsonObject.getDouble("MsNoz4")}catch (e:Exception){0.0}
                        val MsNo4: Double = try{jsonObject.getDouble("MsNo4")}catch (e:Exception){0.0}
                        val msDs4: String = try{jsonObject.getString("msDs4")}catch (e:Exception){""}
                        val mscd4: Double = try{jsonObject.getDouble("mscd4")}catch (e:Exception){0.0}
                        val rad4: Double = try{jsonObject.getDouble("rad4")}catch (e:Exception){0.0}
                        val MsCost3: Double = try{jsonObject.getDouble("MsCost3")}catch (e:Exception){0.0}
                        val MsNoz3: Double = try{jsonObject.getDouble("MsNoz3")}catch (e:Exception){0.0}
                        val MsNo3: Double = try{jsonObject.getDouble("MsNo3")}catch (e:Exception){0.0}
                        val msDs3: String = try{jsonObject.getString("msDs3")}catch (e:Exception){""}
                        val mscd3: Double = try{jsonObject.getDouble("mscd3")}catch (e:Exception){0.0}
                        val rad3: Double = try{jsonObject.getDouble("rad3")}catch (e:Exception){0.0}
                        val MsCost2: Double = try{jsonObject.getDouble("MsCost2")}catch (e:Exception){0.0}
                        val MsNoz2: Double = try{jsonObject.getDouble("MsNoz2")}catch (e:Exception){0.0}
                        val MsNo2: Double = try{jsonObject.getDouble("MsNo2")}catch (e:Exception){0.0}
                        val msDs2: String = try{jsonObject.getString("msDs2")}catch (e:Exception){""}
                        val mscd2: Double = try{jsonObject.getDouble("mscd2")}catch (e:Exception){0.0}
                        val rad2: Double = try{jsonObject.getDouble("rad2")}catch (e:Exception){0.0}
                        val MsCost1: Double = try{jsonObject.getDouble("MsCost1")}catch (e:Exception){0.0}
                        val MsNoz1: Double = try{jsonObject.getDouble("MsNoz1")}catch (e:Exception){0.0}
                        val MsNo1: Double = try{jsonObject.getDouble("MsNo1")}catch (e:Exception){0.0}
                        val msDs1: String = try{jsonObject.getString("msDs1")}catch (e:Exception){""}
                        val mscd1: Double = try{jsonObject.getDouble("mscd1")}catch (e:Exception){0.0}
                        val rad1: Double = try{jsonObject.getDouble("rad1")}catch (e:Exception){0.0}
                        val MsCost0: Double = try{jsonObject.getDouble("MsCost0")}catch (e:Exception){0.0}
                        val MsNoz0: Double = try{jsonObject.getDouble("MsNoz0")}catch (e:Exception){0.0}
                        val MsNo0: Double = try{jsonObject.getDouble("MsNo0")}catch (e:Exception){0.0}
                        val msDs0: String = try{jsonObject.getString("msDs0")}catch (e:Exception){""}
                        val mscd0: Double = try{jsonObject.getDouble("mscd0")}catch (e:Exception){0.0}
                        val rad0: Double = try{jsonObject.getDouble("rad0")}catch (e:Exception){0.0}
                        val masirNam: String = try{jsonObject.getString("masirNam")}catch (e:Exception){""}
                        val radName: String = try{jsonObject.getString("radName")}catch (e:Exception){""}
                        val Family2: String = try{jsonObject.getString("Family2")}catch (e:Exception){""}
                        val Name2: String = try{jsonObject.getString("Name2")}catch (e:Exception){""}
                        val Family1: String = try{jsonObject.getString("Family1")}catch (e:Exception){""}
                        val Name1: String = try{jsonObject.getString("Name1")}catch (e:Exception){""}
                        val Modelx: Int = try{jsonObject.getInt("Name1")}catch (e:Exception){0}
                        val Systemx: String = try{jsonObject.getString("Systemx")}catch (e:Exception){""}
                        val Sr: Int = try{jsonObject.getInt("Sr")}catch (e:Exception){0}
                        val Bno: String = try{jsonObject.getString("Bno")}catch (e:Exception){""}
                        val Yearx: Int = try{jsonObject.getInt("Yearx")}catch (e:Exception){0}
                        val Mounthx: String = try{jsonObject.getString("Mounthx")}catch (e:Exception){""}
                        val TelePhone: String = try{jsonObject.getString("TelePhone")}catch (e:Exception){""}
                        val Family: String = try{jsonObject.getString("Family")}catch (e:Exception){""}
                        val Namex: String = try{jsonObject.getString("Namex")}catch (e:Exception){""}
                        val PNo: String = try{jsonObject.getString("PNo")}catch (e:Exception){""}
                        val Passwordx: Double = try{jsonObject.getDouble("Passwordx")}catch (e:Exception){0.0}
                        val Msg: String = try{jsonObject.getString("Msg")}catch (e:Exception){""}
                        val Seqx: Int = try{jsonObject.getInt("Seqx")}catch (e:Exception){0}


                        val dataObject = YourDataClass(
                            TotPay,
                            MslCost08,
                            MslHours08,
                            MslNo08,
                            MslDs08,
                            MslCost07,
                            MslHours07,
                            MslNo07,
                            MslDs07,
                            MslCost06,
                            MslHours06,
                            MslNo06,
                            MslDs06,
                            MslCost05,
                            MslHours05,
                            MslNo05,
                            MslDs05,
                            MslCost04,
                            MslHours04,
                            MslNo04,
                            MslDs04,
                            MslCost03,
                            MslHours03,
                            MslNo03,
                            MslDs03,
                            MslCost02,
                            MslHours02,
                            MslNo02,
                            MslDs02,
                            MslCost01,
                            MslHours01,
                            MslNo01,
                            MslDs01,
                            MslName,
                            DAcctNo,
                            Padash2,
                            AcctOwner,
                            BnType,
                            AcctNo,
                            Padash1,
                            NetPay,
                            TotDed,
                            Insrt,
                            Ded,
                            Insrx,
                            Bimeh,
                            Tavoni,
                            Alal,
                            Jar2,
                            Jar1,
                            Dep,
                            Bed,
                            Taxx,
                            Vam1,
                            Vam2,
                            TotMaz,
                            Sh80,
                            Padash,
                            Moavx,
                            Disp,
                            Sitex,
                            Mslns,
                            Mamrt,
                            Extra,
                            Maz,
                            Moav,MsCost7,MsNoz7,MsNo7,msDs7,mscd7,rad7,MsCost6,MsNoz6,MsNo6,msDs6,mscd6,rad6,MsCost5,MsNoz5,MsNo5,msDs5,mscd5,rad5,
                            MsCost4,MsNoz4,MsNo4,msDs4,mscd4,rad4,MsCost3,MsNoz3,MsNo3,msDs3,mscd3,rad3,MsCost2,MsNoz2,MsNo2,msDs2,mscd2,rad2,
                            MsCost1,MsNoz1,MsNo1,msDs1,mscd1,rad1,MsCost0,MsNoz0,MsNo0,msDs0,mscd0,rad0,masirNam,radName,Family2,Name2,Family1,
                            Name1,Modelx,Systemx,Sr,Bno,Yearx,Mounthx,TelePhone,Family,Namex,PNo,Passwordx,Msg,Seqx)

                            // Parse values from jsonObject

                        parsedData.add(dataObject)
                    }

                    /*val gson = Gson()
                    val type = object : TypeToken<List<YourDataClass>>() {}.type
                    val parsedData = gson.fromJson<List<YourDataClass>>(responseBodyString, type)*/
                    _yourDataList.postValue(parsedData)
                }
            } catch (e: IOException) {
                e.printStackTrace()
                // Handle exceptions here
            }
        }
    }
}