package com.example.urist1.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.urist1.R
import com.example.urist1.authManager.AuthorizationManager
import com.example.urist1.authManager.ValidData
import com.example.urist1.bd.DataBase
import com.example.urist1.bd.users.UsersData
import com.example.urist1.databinding.FragmentAboutMeBinding
import com.example.urist1.databinding.FragmentRegisterBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
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
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        return binding.root
    }

    val buttonClickListener = OnClickListener {
        dataBase.playlistDao().apply {
            CoroutineScope(Dispatchers.Main).launch {
                val job = withContext(Dispatchers.IO) {
                    getByEmail(binding.editTextEmail.text.toString())
                }
                when {
                    job != null -> {
                        Toast.makeText(
                            requireContext(),
                            "Аккаунт с такой почтой уже существует",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    ValidData(requireContext()).isValidEmail(binding.editTextEmail.text.toString()) -> {
                        Toast.makeText(
                            requireContext(),
                            "Неверный формат email",
                            Toast.LENGTH_LONG
                        ).show()
                    }

                    binding.editTextPassword.text != binding.editTextTwoPassword.text -> {
                        Toast.makeText(
                            requireContext(),
                            "Ваши пароли не совпадают",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                    else->{
                        insert(
                            UsersData(
                                binding.editTextEmail.text.toString(),
                                binding.editTextPassword.text.toString()
                            )
                        )
                        AuthorizationManager(requireContext()).saveEmail(binding.editTextEmail.text.toString())
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }
}