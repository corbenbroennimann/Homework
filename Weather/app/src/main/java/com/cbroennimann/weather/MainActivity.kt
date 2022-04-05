package com.cbroennimann.weather

import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View

import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.cbroennimann.weather.MainViewModel

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var weatherViewModel: MainViewModel

    private lateinit var GET: SharedPreferences
    private lateinit var SET: SharedPreferences.Editor

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        GET = getSharedPreferences(packageName, MODE_PRIVATE)
        SET = GET.edit()


        weatherViewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        var cName = GET.getString("cityName", "Kailua")?.toLowerCase()
        findViewById<EditText>(R.id.city_name).setText(cName)
        weatherViewModel.refreshData(cName!!)

        getLiveData()

        findViewById<Button>(R.id.search_button).setOnClickListener {
            val cityName = findViewById<EditText>(R.id.city_name).text.toString()
            SET.putString("cityName", cityName)
            SET.apply()
            weatherViewModel.refreshData(cityName)
            getLiveData()
            Log.i(TAG, "onCreate: " + cityName)
        }

    }

    private fun getLiveData() {

        weatherViewModel.weather_data.observe(this, Observer { data ->
            data?.let {
                /*ll_data.visibility = View.VISIBLE

                tv_city_code.text = data.sys.country.toString()
                tv_city_name.text = data.name.toString()

                Glide.with(this)
                    .load("https://openweathermap.org/img/wn/" + data.weather.get(0).icon + "@2x.png")
                    .into(img_weather_pictures)*/

                findViewById<TextView>(R.id.temperature).text = data.main.temp.toString() + "°F"

                findViewById<TextView>(R.id.max_temp).text = "Max: " + data.main.tempMax.toString() + "°F"
                findViewById<TextView>(R.id.min_temp).text = "Min: " + data.main.tempMin.toString() + "°F"

                /*tv_humidity.text = data.main.humidity.toString() + "%"
                tv_wind_speed.text = data.wind.speed.toString()
                tv_lat.text = data.coord.lat.toString()
                tv_lon.text = data.coord.lon.toString()*/

            }
        })
/*
        weatherViewModel.weather_error.observe(this, Observer { error ->
            error?.let {
                if (error) {
                    tv_error.visibility = View.VISIBLE
                    pb_loading.visibility = View.GONE
                    ll_data.visibility = View.GONE
                } else {
                    tv_error.visibility = View.GONE
                }
            }
        })

        weatherViewModel.weather_loading.observe(this, Observer { loading ->
            loading?.let {
                if (loading) {
                    pb_loading.visibility = View.VISIBLE
                    tv_error.visibility = View.GONE
                    ll_data.visibility = View.GONE
                } else {
                    pb_loading.visibility = View.GONE
                }
            }
        })*/

    }

        /*  //findViewById<TextView>(R.id.temperature)
        //findViewById<EditText>(R.id.city_name)
        findViewById<Button>(R.id.search_button).setOnClickListener {
            val cityName = findViewById<EditText>(R.id.city_name).text.toString()
            val newTemp = weatherViewModel.getDataFromAPI(cityName)
            findViewById<TextView>(R.id.temperature).text = newTemp.toString()

        }*/

    }

