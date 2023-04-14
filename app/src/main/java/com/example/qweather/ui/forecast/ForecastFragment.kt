package com.example.qweather.ui.forecast

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.support.v4.app.Fragment
import android.arch.lifecycle.ViewModelProvider
import com.example.qweather.WeatherApi
import com.example.qweather.WeatherResponse
import com.example.qweather.databinding.FragmentForecastBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ForecastFragment : Fragment() {

    private var _binding: FragmentForecastBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val forecastViewModel =
            ViewModelProvider(
                this,
                ViewModelProvider.NewInstanceFactory()
            ).get(ForecastViewModel::class.java)

        _binding = FragmentForecastBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val api = WeatherApi.create()
        val call = api.getCurrentWeather("Moscow", "eeb7e283c831eeb1f11abbc6230edce2")

        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                response.body()
                // Обработка полученных данных
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                // Обработка ошибки
            }
        })

        return root
    }

}