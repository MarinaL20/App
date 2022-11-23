package com.example.app.fragment1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.app.MainViewModel
import com.example.app.R
import com.example.app.adapter.AdapterListWeather
import com.example.app.adapter.ItemOneDay
import com.example.app.databinding.FragmentBlankDaysBinding

// цей фрагмен буде відповідати за список погоди на наступні дні
class BlankFragmentDays : Fragment() {

    private lateinit var binding: FragmentBlankDaysBinding
    private lateinit var adapter: AdapterListWeather
    private val model: MainViewModel by activityViewModels()//доступ до класу MainViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBlankDaysBinding.inflate(inflater, container, false)
        return binding.root
        //return inflater.inflate(R.layout.fragment_blank_days, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        model.lifeDataList.observe(viewLifecycleOwner){
            adapter.submitList(it.subList(1, it.size))
        }

    }
    private fun initRecyclerView() = with(binding){
        RecyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = AdapterListWeather()
        RecyclerView.adapter = adapter
        val list = listOf(
            ItemOneDay(nameCity = "", data_time = "", stane_weather = "", image = "", temp = "", tempMax = "", tempMin = "", clockeMap = "")
        )
        adapter.submitList(list)
    }

    companion object {

        @JvmStatic
        fun newInstance() = BlankFragmentDays()
    }
}