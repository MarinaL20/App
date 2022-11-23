package com.example.app.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.example.app.R
import com.example.app.databinding.OneElementListItemBinding

class AdapterListWeather: ListAdapter<ItemOneDay, AdapterListWeather.MyViewHolder>(Comparator()) {
    class MyViewHolder(view: View):RecyclerView.ViewHolder(view){//даний клас зберігає посилання на елементи
        val binding = OneElementListItemBinding.bind(view)//доступ до елементів за ідентифікатором
        fun bind(item: ItemOneDay) = with(binding){
            textdata.text = item.data_time
            textSunny.text = item.stane_weather
            texttemp.text = item.temp.ifEmpty { "${item.tempMax}°C / ${item.tempMin}°C" }
            //textView2.text = item.temp
            val image = binding.imageView2
            Glide.with(imageView2).load("https:" + item.image).into(image)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.one_element_list_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
    class Comparator: DiffUtil.ItemCallback<ItemOneDay>(){
        override fun areItemsTheSame(oldItem: ItemOneDay, newItem: ItemOneDay): Boolean {
            return oldItem==newItem
        }

        override fun areContentsTheSame(oldItem: ItemOneDay, newItem: ItemOneDay): Boolean {
            return oldItem==newItem
        }

    }

}