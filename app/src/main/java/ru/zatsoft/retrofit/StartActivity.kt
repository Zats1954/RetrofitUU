package ru.zatsoft.retrofit

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import ru.zatsoft.retrofit.databinding.ActivityStartBinding
import ru.zatsoft.retrofit.utils.RetrofitInstance
import java.io.IOException

class StartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStartBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val city = intent.getStringExtra("city") ?: "????"

        getCurrentWeather(city)
    }

    private fun getCurrentWeather(city: String) {
        GlobalScope.launch(Dispatchers.IO) {
            val response = try {
                RetrofitInstance.api.getCurrentWeather(
                    city,
                    "metric",
                    applicationContext.getString(R.string.api_key)
                )
            } catch (e: IOException) {
                Toast.makeText(applicationContext, "app error ${e.message}", Toast.LENGTH_LONG)
                    .show()
                return@launch
            } catch (e: HttpException) {
                Toast.makeText(applicationContext, "http error ${e.message}", Toast.LENGTH_LONG)
                    .show()
                return@launch
            }
            if (response.isSuccessful && response.body() != null) {
                withContext(Dispatchers.Main) {
                    val data = response.body()
                    binding.tvCity.text = data!!.name
                    binding.tvTemp.text = "Температура ${data.main.temp} °C"


                    val str = data.weather[0].icon
                    val iconString = getImageId(str)
                    Glide.with(binding.image).load(iconString)
                        .override(100, 100)
                        .circleCrop()
                        .placeholder(R.drawable.ic_o_24dp)
                        .error(R.drawable.ic_o_24dp)
                        .into(binding.image)

                    val windSector = data.wind.deg.div(22.5f).toInt()
                    val windDirection = when (windSector) {
                        0, 15 -> "N  "
                        1, 2 -> "NE  "
                        3, 4 -> "E  "
                        5, 6 -> "SE  "
                        7, 8 -> "S  "
                        9, 10 -> "SW  "
                        11, 12 -> "W  "
                        13, 14 -> "NW  "
                        else -> "??"
                    }
                    binding.tvWind.text =
                        "Ветер ${windDirection} ${data.wind?.speed.toString()} м/сек"
                    binding.tvPressure.text = "Давление ${data.main.pressure.toString()} КПа"
                    binding.tvHumidity.text = "Влажность ${data.main?.humidity.toString()} %"
                }
            }
        }
    }

    private fun getImageId(str: String?): Int {
        return when (str) {
            "01d" -> R.drawable.ic__01d
            "01n" -> R.drawable.ic__01n
            "02d" -> R.drawable.ic__02d
            "02n" -> R.drawable.ic__02n
            "03d" -> R.drawable.ic__03d
            "03n" -> R.drawable.ic__03n
            "04d" -> R.drawable.ic__04d
            "04n" -> R.drawable.ic__04n
            "09d" -> R.drawable.ic__09d
            "09n" -> R.drawable.ic__09n
            "10d" -> R.drawable.ic__10d
            "10n" -> R.drawable.ic__10n
            "11d" -> R.drawable.ic__11d
            "11n" -> R.drawable.ic__11n
            "13d" -> R.drawable.ic__13d
            "13n" -> R.drawable.ic__13n
            "50d" -> R.drawable.ic__50d
            "50n" -> R.drawable.ic__50n
            else -> R.drawable.ic_o_24dp
        }
    }
}

