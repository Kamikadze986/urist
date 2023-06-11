package com.example.urist1.ui.price

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.urist1.MainActivity
import com.example.urist1.databinding.FragmentPriceBinding

class PriceFragment : Fragment() {

    private var _binding: FragmentPriceBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentPriceBinding.inflate(inflater, container, false)
        binding.card1.setOnClickListener(listener)
        binding.card2.setOnClickListener(listener)
        binding.card3.setOnClickListener(listener)
        binding.card4.setOnClickListener(listener)
        binding.card5.setOnClickListener(listener)
        binding.card6.setOnClickListener(listener)
        binding.card7.setOnClickListener(listener)
        binding.card8.setOnClickListener(listener)
        binding.card9.setOnClickListener(listener)

        val root: View = binding.root

        return root
    }

    private val listener = OnClickListener {

        (requireActivity() as MainActivity).moveToRequestForm()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}