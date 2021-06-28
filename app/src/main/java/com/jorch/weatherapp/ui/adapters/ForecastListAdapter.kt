package com.jorch.weatherapp.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jorch.weatherapp.R
import com.jorch.weatherapp.domain.model.Forecast
import com.jorch.weatherapp.domain.model.ForecastList
import com.jorch.weatherapp.ui.utils.ctx
import com.squareup.picasso.Picasso
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.item_forecast.*
import kotlinx.android.synthetic.main.item_forecast.view.*

class ForecastListAdapter(private val weekForecast: ForecastList,
                          private val itemClick: (Forecast) -> Unit ) :
                        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>(){

    interface OnItemClickListener {
        operator fun invoke(forecast: Forecast)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.ctx)
            .inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount(): Int  = weekForecast.size

    class ViewHolder(override val containerView: View, val itemClick: (Forecast) -> Unit)
        : RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun bindForecast(forecast: Forecast){
            with(forecast){
                Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                dateText.text = date
                descriptionText.text = description
                maxTemperature.text = "${high}º"
                minTemperature.text = "${low}º"
                itemView.setOnClickListener { itemClick(this) }
            }
        }

    }
}
