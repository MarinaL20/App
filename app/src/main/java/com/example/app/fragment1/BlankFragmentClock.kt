package com.example.app.fragment1

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.app.R
import com.example.app.databinding.FragmentBlankClockBinding
import com.example.app.databinding.FragmentBlankDaysBinding


class BlankFragmentClock : Fragment() {

    private lateinit var binding: FragmentBlankClockBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment_blank_clock, container, false)
        binding = FragmentBlankClockBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val image = binding.imageView3
        Glide.with(requireContext())
            .load("https://cdn.segodnya.ua/media/image/628/450/526/628450526123e.png")
            .into(image)
    }

    companion object {

        @JvmStatic
        fun newInstance() = BlankFragmentClock()

    }
}