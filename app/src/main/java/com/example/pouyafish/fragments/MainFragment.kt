package com.example.pouyafish.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pouyafish.R
import com.example.pouyafish.TimeModel
import com.example.pouyafish.adapters.TimeAdapter
import com.example.pouyafish.data.UserPreferences
import com.example.pouyafish.databinding.FragmentMainBinding

class MainFragment : Fragment() {

    val gotData = 0
    val viewModel : SharedViewModel by activityViewModels()
    var arrayMonth = ArrayList<TimeModel>()
    var arrayYear = ArrayList<TimeModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val binding = FragmentMainBinding.inflate(inflater)
        val userPreferences = UserPreferences(requireContext())
        var phone = userPreferences.getMobileNumber()
        if(phone.length == 11){
            phone = phone.substring(1)
        }

        phone = "09138722413"
        viewModel.fetchData(phone)

        viewModel.yourDataList.observe(viewLifecycleOwner) { data ->
            Log.i("Log1","data has received ... ")
            binding.layoutLoading.visibility = View.GONE

            val adapterMonth = TimeAdapter{
                Toast.makeText(requireContext(),"Month is : ${it.index} and is selected is : ${it.isSelected}",Toast.LENGTH_SHORT).show()
            }
            val adapterYear = TimeAdapter{
                Toast.makeText(requireContext(),"Year is : ${it.text}",Toast.LENGTH_SHORT).show()
            }

            binding.recyclerMonth.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,true)
            binding.recyclerMonth.adapter = adapterMonth

            binding.recyclerYear.layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL,true)
            binding.recyclerYear.adapter = adapterYear


            try{
                binding.txtName.text = "${data[gotData].Name1}   ${data[gotData].Family1}"
                binding.txtPersonalCode.text = data[gotData].PNo
                binding.txtPelak.text = data[gotData].Bno
                binding.txtCarName.text = "${data[gotData].Systemx}"
                binding.txtMobile.text = data[gotData].TelePhone
            }catch (e: Exception){

            }
            try {
                var month = ArrayList<String>()
                var year = ArrayList<String>()
                for (i in data) {
                    year.add(i.Yearx.toString())
                    month.add(i.Mounthx)
                }

                month.add("خرداد")
                month.add("مرداد")
                month.add("مهر")
                month.add("فروردین")
                year.add("1401")

                createMonthArray(month)
                createYearArray(year)

                adapterMonth.submitList(arrayMonth)
                adapterYear.submitList(arrayYear)

            }catch (e: Exception){

            }

        }

        return binding.root
        //return inflater.inflate(R.layout.fragment_main, container, false)
    }

    private fun createYearArray(y:ArrayList<String>){
        for(i in y){
            val t = TimeModel(i.toInt(),i)
            arrayYear.add(t)
        }
        arrayYear.sortBy { timeModel: TimeModel -> timeModel.index }
    }

    private fun createMonthArray(m:ArrayList<String>){
        val m2 = m.distinct()
        for(i in m2){
            Log.i("Log1","month is: $i")
            val t = when(i){
                "فروردين" -> TimeModel(1,i)
                "ارديبهشت" -> TimeModel(2,i)
                "خرداد" -> TimeModel(3,i)
                "تير" -> TimeModel(4,i)
                "مرداد" -> TimeModel(5,i)
                "شهريور" -> TimeModel(6,i)
                "مهر" -> TimeModel(7,i)
                "آبان" -> TimeModel(8,i)
                "ابان" -> TimeModel(8,i)
                "آذر" -> TimeModel(9,i)
                "اذر" -> TimeModel(9,i)
                "دي" -> TimeModel(10,i)
                "بهمن" -> TimeModel(11,i)
                "اسفند" -> TimeModel(12,i)
                else -> TimeModel(0,i)
            }
            arrayMonth.add(t)
        }

        arrayMonth.sortBy { timeModel: TimeModel -> timeModel.index }
    }

}