package com.example.urist1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.urist1.databinding.FragmentAboutMeBinding
import com.example.urist1.databinding.FragmentDocksBinding

class DocksFragment : Fragment() {
    private var _binding: FragmentDocksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDocksBinding.inflate(inflater, container, false)
        binding.card1.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://ocenkagarant.ru/documents.html")
                )
            )
        }
        binding.card2.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://ocenkagarant.ru/images/2/7.BMP")
                )
            )
        }
        binding.card3.setOnClickListener {
            startActivity(
                Intent(
                    Intent.ACTION_VIEW,
                    Uri.parse("http://ocenkagarant.ru/images/3/4.BMP")
                )
            )
        }
        return binding.root
    }
}