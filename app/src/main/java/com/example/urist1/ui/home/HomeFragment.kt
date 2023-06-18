package com.example.urist1.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.urist1.MainActivity
import com.example.urist1.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        binding.card1.setOnClickListener(list)
        binding.card2.setOnClickListener(list)
        binding.card3.setOnClickListener(list)

        return root
    }

    private val list = OnClickListener {
        (requireActivity() as MainActivity).moveToRequestForm()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}