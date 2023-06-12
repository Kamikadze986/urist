package com.example.urist1.ui.requesst_form

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.urist1.databinding.FragmentRequestFormBinding

class RequestFormFragment : Fragment() {

    private var _binding: FragmentRequestFormBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    var name = false
    var phone = false
    var text = false
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRequestFormBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.editText.addTextChangedListener {
            text = it?.length != null && it.isNotEmpty()
            changeButton()
        }

        binding.editTextPhone.addTextChangedListener {
            phone = it?.length != null && it.isNotEmpty()
            changeButton()
        }

        binding.editTextName.addTextChangedListener {
            name = it?.length != null && it.isNotEmpty()
            changeButton()
        }

        binding.button.setOnClickListener {
            Toast.makeText(requireContext(), "Заявка отправлена", Toast.LENGTH_LONG).show()
            binding.editTextName.setText("")
            binding.editTextPhone.setText("")
            binding.editText.setText("")
        }
        return root
    }

    fun changeButton() {
        binding.button.isEnabled = text && phone && name
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}