package com.example.urist1.ui

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.urist1.R
import com.example.urist1.authManager.AuthorizationManager
import com.example.urist1.databinding.FragmentAboutMeBinding
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.shareIn

class AboutMeFragment : Fragment() {
    private var _binding: FragmentAboutMeBinding? = null
    private val binding get() = _binding!!
    private lateinit var progress: FrameLayout
    override fun onAttach(context: Context) {

        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutMeBinding.inflate(inflater, container, false)
        progress = binding.progress
        binding.linearPhone.setOnClickListener {
            if (ContextCompat.checkSelfPermission(
                    requireContext(),
                    android.Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    requireActivity(), arrayOf(android.Manifest.permission.CALL_PHONE),
                    1
                )

            } else {
                val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:835238-09-05"))
                startActivity(intent)
            }
        }

        AuthorizationManager(requireContext()).isAutorizeFlow()
            .onEach {
                if (it) {
                    binding.textViewEmail.text = AuthorizationManager(requireContext()).getEmail()
                    binding.authorization.visibility = View.VISIBLE
                    binding.dontAuthorization.visibility = View.GONE
                } else {
                    binding.dontAuthorization.visibility = View.VISIBLE
                    binding.authorization.visibility = View.GONE
                }
            }.launchIn(lifecycleScope)

        binding.imageViewRazlogin.setOnClickListener {
            setEmail("")
        }
        binding.buttonLogin.setOnClickListener {
            findNavController().navigate(R.id.authorizationFragment)
        }
        binding.linearDocks.setOnClickListener {
            findNavController().navigate(R.id.docksFragment2)
        }
        return binding.root
    }

    fun setEmail(string: String) {
        progress.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            AuthorizationManager(requireContext()).saveEmail(string)
            progress.visibility = View.GONE
        }, 2000)
    }
}