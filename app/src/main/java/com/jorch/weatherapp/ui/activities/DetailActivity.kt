package com.jorch.weatherapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.widget.Toolbar
import com.jorch.weatherapp.R
import com.jorch.weatherapp.domain.commands.RequestDayForecastCommand
import org.jetbrains.anko.doAsync
import com.jorch.weatherapp.domain.model.Forecast
import com.jorch.weatherapp.extensions.color
import com.jorch.weatherapp.extensions.textColor
import com.jorch.weatherapp.extensions.toDateString
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.anko.find
import org.jetbrains.anko.uiThread
import java.text.DateFormat

class DetailActivity : CoroutineScopeActivity(), ToolbarManager {
    override val toolbar by lazy { find<Toolbar>(R.id.toolbar) }

    companion object{
        const val ID = "DetailActivity:id"
        const val CITY_NAME = "DetailActivity:cityName"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        initToolbar()

        title = intent.getStringExtra(CITY_NAME)

        enableHomeAsUp { onBackPressed() }

        launch {
            val id = intent.getLongExtra(ID,-1)
            val result = withContext(Dispatchers.IO) { RequestDayForecastCommand(id).execute() }
            bindForecast(result)
        }
    }

    private fun bindForecast(forecast: Forecast) = with(forecast) {
        Picasso.with(this@DetailActivity).load(iconUrl).into(icon)
        supportActionBar?.subtitle = date.toDateString(DateFormat.FULL)
        weatherDescription.text = description
        bindWeather(high to maxTemperature, low to minTemperature)
    }

    private fun bindWeather(vararg views: Pair<Int, TextView>) = views.forEach {
        it.second.text = "${it.first}"
        it.second.textColor = color(when(it.first){
            in -50..0 -> android.R.color.holo_red_dark
            in 0..15 -> android.R.color.holo_orange_dark
            else -> android.R.color.holo_green_dark
        })
    }
}