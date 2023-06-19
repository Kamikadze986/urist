package com.example.urist1.ui

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.fragment.findNavController
import com.example.urist1.R
import com.example.urist1.authManager.AuthorizationManager
import com.example.urist1.bd.DataBase
import com.example.urist1.bd.users.UsersData
import com.example.urist1.databinding.FragmentAuthorizationBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class AuthorizationFragment : Fragment() {
    private var _binding: FragmentAuthorizationBinding? = null
    private val binding get() = _binding!!
    private lateinit var dataBase: DataBase
    override fun onAttach(context: Context) {
        dataBase = DataBase.getInstance(context)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAuthorizationBinding.inflate(inflater, container, false)
        binding.toolbar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
        binding.button.setOnClickListener {
            dataBase.playlistDao().apply {
                CoroutineScope(Dispatchers.Main).launch {
                    val job = withContext(Dispatchers.IO) {
                        getByEmail(binding.editTextEmail.text.toString())
                    }
                    when {
                        job == null -> {
                            Toast.makeText(
                                requireContext(),
                                "Такого аккаунта не существует",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                        (job.password == binding.editTextPassword.text.toString()) -> {
                            insert(
                                UsersData(
                                    binding.editTextEmail.text.toString(),
                                    binding.editTextPassword.text.toString()
                                )
                            )
                            AuthorizationManager(requireContext()).saveEmail(binding.editTextEmail.text.toString())
                            findNavController().popBackStack()
                        }

                        else -> {
                            Toast.makeText(
                                requireContext(),
                                "Неверный логин или пароль",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    }
                }


            }
        }

        binding.textViewRegister.setOnClickListener {
            findNavController().navigate(
                R.id.registerFragment, null, NavOptions.Builder()
                    .setPopUpTo(R.id.authorizationFragment, true)
                    .build()
            )
        }
        return binding.root
    }


}