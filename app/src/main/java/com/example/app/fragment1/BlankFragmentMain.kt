package com.example.app.fragment1

import android.Manifest
import android.content.Context
import android.os.Bundle
import android.provider.Settings.Global
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.activityViewModels
import androidx.room.Room
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.example.app.MainViewModel
import com.example.app.adapter.Adapter
import com.example.app.adapter.ItemOneDay
import com.example.app.cache.DB
import com.example.app.cache.DataBase
import com.example.app.cache.WeatherDao
import com.example.app.databinding.FragmentBlankMainBinding
import com.google.android.material.tabs.TabLayoutMediator
import com.squareup.picasso.Picasso
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.json.JSONObject


class BlankFragmentMain : Fragment() {
    private lateinit var pLauncher: ActivityResultLauncher<String>
    private lateinit var binding: FragmentBlankMainBinding
    private val mod: MainViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBlankMainBinding.inflate(inflater, container, false)
        return binding.root

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        checkPerm() //викликаєм перевірку на початку вик додатку
        permissiomList()//реєструю
        init()
        updatecurentData()
        giveDataW("Kiev")
        //region Room
        val db = DB.getDB(requireContext())
        binding.imageButton.setOnClickListener{
            val item = DataBase(null,
                binding.nameCity.text.toString(),
                binding.temperat.text.toString(),
                binding.sunny.text.toString())
            Thread{
                db.weatherDao().insertAll(item)
            }.start()
            Toast.makeText(
                context?.applicationContext,
                "Weather saved",
                Toast.LENGTH_LONG
            ).show()

        }
    }
    //підключаю адаптер до вікна показу даних
    private val frList = listOf(
        BlankFragmentClock.newInstance(),
        BlankFragmentDays.newInstance()
    )
    private val listTab = listOf(//витягни з ресурсів
        "Map",
        "Days"
    )
    private fun init() = with(binding){
        val adapter = Adapter(activity as FragmentActivity, frList) //as FragmentActivityAppCompatActivity
        tvPages.adapter = adapter
        //підписи
        TabLayoutMediator(tabLayout2, tvPages){
            tab, pos -> tab.text = listTab[pos]
        }.attach()
    }
    //дозвіл на використання певних доповнень: камера, локація, мікрофон і тд
    private fun permissiomList(){
        pLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()){
            Toast.makeText(activity, "Location $it", Toast.LENGTH_LONG).show() //повідомлення
        }
    }
    private fun checkPerm(){
        if(!isPermissionGranted(Manifest.permission.ACCESS_FINE_LOCATION)){ //при відсутності дозволу даємо запит
            permissiomList()
            pLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)

        }
    }
    private fun giveDataW(city: String){
        val url = "https://api.weatherapi.com/v1/forecast.json?key=" +
                "1ee92b924b3446d6956123251222111" +
                "&q=" +
                city +
                "&days=" +
                "7" +
                "&aqi=no&alerts=no"
        val queue = Volley.newRequestQueue(context)
        val strRequest = StringRequest(
            Request.Method.GET,
            url,
            {resp -> resultF(resp) },
            { error -> Log.d("Log", "VolleyError: $error")
            }
        )
        queue.add(strRequest)
    }
    private fun resultF(result: String ){
        val obj = JSONObject(result)//all data
        val list = allDays(obj)
        currentData(obj, list[0])
    }
    private fun allDays(obj: JSONObject): List<ItemOneDay>{
        val list = ArrayList<ItemOneDay>()
        val arrayDays = obj.getJSONObject("forecast").getJSONArray("forecastday")
        val name = obj.getJSONObject("location").getString("name")
        for(i in 0 until arrayDays.length()) {
            val day = arrayDays[i] as JSONObject
            val item1 = ItemOneDay(
                name,
                day.getString("date"),
                day.getJSONObject("day").getJSONObject("condition").getString("text"),
                "",
                day.getJSONObject("day").getString("maxtemp_c"),
                day.getJSONObject("day").getString("mintemp_c"),
                day.getJSONObject("day").getJSONObject("condition").getString("icon"),
                ""

            )
            list.add(item1)
        }

        mod.lifeDataList.value = list
        return list
    }
    private fun currentData(obj: JSONObject, weather: ItemOneDay){

        val baseData = ItemOneDay(
            obj.getJSONObject("location").getString("name"),
            obj.getJSONObject("location").getString("localtime"),
            obj.getJSONObject("current").getJSONObject("condition").getString("text"),
            obj.getJSONObject("current").getString("temp_c"),weather.tempMax,weather.tempMin,
            obj.getJSONObject("current").getJSONObject("condition").getString("icon"),
            ""

        )
        mod.lifeData.value = baseData
    }
    private fun updatecurentData() = with(binding){
        mod.lifeData.observe(viewLifecycleOwner){
            dataandtime.text = it.data_time
            nameCity.text = it.nameCity
            temperat.text = it.temp + "°C"
            sunny.text = it.stane_weather
            val minmaxtemp = "${it.tempMax}°C/ ${it.tempMin}°C"
            maxandmin.text = minmaxtemp
            val image = binding.imageIconWeather
            Glide.with(requireContext()).load("https:" + it.image).into(image)
           // Picasso.get().load("https:" + it.image).into(imageIconWeather)


        }
    }
    companion object {

        @JvmStatic
        fun newInstance() = BlankFragmentMain()

    }
}