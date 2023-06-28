package com.busanit.androidchallenge

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.busanit.androidchallenge.databinding.FragmentWeatherBinding
import com.busanit.androidchallenge.model.WeatherModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherFragment : Fragment() {

//    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentWeatherBinding.inflate(inflater, container, false)

//        val current = ZonedDateTime.now(ZoneId.of("Asia/Seoul"))
//        val formDate = DateTimeFormatter.ofPattern("yyyMMdd")
//        val formTime = DateTimeFormatter.ofPattern("HHmm")
//        val baseDate = current.format(formDate)
//        val baseTime = current.format(formTime)


//        textView.append("Daily Box Office : ${baseDate.format(cal.time)}\n\n")    // 위 설정한 날짜 출력

//        valley방식
//        val jsonRequest = object : JsonObjectRequest(
//            Request.Method.GET, url, null, Response.Listener {
//
//            }
//        )


        val call: Call<WeatherModel> = MyWeatherApplication.networkService.getWeatherList(
            MyWeatherApplication.serviceKey,1, 12, MyWeatherApplication.dataType, MyWeatherApplication.baseDate, MyWeatherApplication.baseTime, MyWeatherApplication.nx, MyWeatherApplication.ny
        )
        call.enqueue(object : Callback<WeatherModel> {
            override fun onResponse(
                call: Call<WeatherModel>,
                response: Response<WeatherModel>
            ) {
                if (response.isSuccessful) {
                    val weatherModel = response.body()
                    val weatherItem = weatherModel?.response?.body?.items?.item

                    Log.d("myLog", "통신 성공 weatherItems : $weatherItem ")  // 통신 후 되는데 왜 널임


                    for (i in 0 until weatherItem!!.size) {
                        val category = weatherItem[i].category

                        if (category.equals("TMP")) {   // TMP 1시간 기온
                            binding.temperature.text = weatherItem[i].fcstValue + "°C"
                        }
                        else if (category.equals("WSD")) {  // WSD 풍속
                            binding.windSpeed.text = weatherItem[i].fcstValue + "m/s"
                        }
                        else if (category.equals("PTY")) {  // PTY 강수형태
                            val PTY = weatherItem[i].fcstValue

                            if (PTY != null) {
                                binding.precipitationTypes.text = "없음"
                                if (PTY.equals("0")) {
                                    binding.precipitationTypes.text = "없음"
                                }
                                else if (PTY.equals("1")) {
                                    binding.precipitationTypes.text = "비"
                                }
                                else if (PTY.equals("2")) {
                                    binding.precipitationTypes.text = "비/눈"
                                }
                                else if (PTY.equals("3")) {
                                    binding.precipitationTypes.text = "눈"
                                }
                                else if (PTY.equals("4")) {
                                    binding.precipitationTypes.text = "소나기"
                                }
                            }
                        }
                        else if (category.equals("REH")) { // REH 습도
                            binding.humidity.text = weatherItem[i].fcstValue + "%"
                        }
                    }
                }
            }
            override fun onFailure(call: Call<WeatherModel>, t: Throwable) {
                Log.d("myLog", "통신 실패 : ${t.message}")
            }
        })
        return binding.root

    }
}