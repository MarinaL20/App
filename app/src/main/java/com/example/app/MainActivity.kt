package com.example.app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.app.fragment1.BlankFragmentMain
import com.example.app.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    //підключаю binding
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // міняю розмітку мейн актівіті на фрагмент
        supportFragmentManager.beginTransaction().replace(R.id.Main, BlankFragmentMain.newInstance()).commit()

        //обробка натискання кнопки
       /* binding.buttonGet.setOnClickListener{

            Result("Kiev")
        }*/
    }


    //ств функціяю, яка повина відправити запит та отримати відповідь
    /*private fun Result(name: String){ //передається назва міста
        // використовую бібліотеку воллі
        val url = "https://api.weatherapi.com/v1/current.json" + "?key=1ee92b924b3446d6956123251222111&q=$name&aqi=no"
               // "?key=${API_KEY}q=$name&aqi=no"
        //"?key=1ee92b924b3446d6956123251222111&q=Kiev&aqi=no"

        val queue = Volley.newRequestQueue(this)
        val strRequest = StringRequest(com.android.volley.Request.Method.GET,
            url,
            {resp ->
                val obj = JSONObject(resp) // за доп цього дістаємо все, що треба
                val Temperature = obj.getJSONObject("current")
                Log.d("Log", "VolleyGood: ${Temperature.getString("temp_c")}")}, //витягую температуру
            { Log.d("Log", "VolleyError: $it")}
        )
        queue.add(strRequest)

    }*/
}