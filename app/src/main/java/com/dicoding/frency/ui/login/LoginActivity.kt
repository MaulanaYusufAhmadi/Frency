package com.dicoding.frency.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import com.dicoding.frency.ui.MainActivity
import com.dicoding.frency.R
import com.dicoding.frency.ViewModelFactory
import com.dicoding.frency.data.Result
import com.dicoding.frency.data.pref.UserModel
import com.dicoding.frency.databinding.ActivityLoginBinding
import com.dicoding.frency.ui.register.RegisterActivity
import com.dicoding.frency.utils.isInternetAvailable

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding



    private val viewModel by viewModels<LoginViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvNotHaveAcc.setOnClickListener {
            startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
        }

        viewModel.getSession().observe(this) { user ->
            if (user.isLogin) {
                Log.d("userId", "userId: $user")
                startActivity(Intent(this, MainActivity::class.java))
            }
            Log.d("userId", "userId: $user")
        }

        viewModel.loginResult.observe(this) { result ->
            when(result) {
                is Result.Loading -> {
                    binding.overlayLoading.visibility = View.VISIBLE
                    window.setFlags(
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                        WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                    )
                }
                is Result.Success -> {
                    binding.overlayLoading.visibility = View.GONE
                    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                    result.data.data.apply {
                        Log.d("userIdSave", "saveSession: $name, $token")
                        viewModel.saveSession(UserModel(id, name, email, token, role, "", "", ""))
                    }

                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent)
                }
                is Result.Error -> {
                    binding.overlayLoading.visibility = View.GONE
                    window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
                    if (!isInternetAvailable(this)) {
                        Toast.makeText(this, getString(R.string.network_error), Toast.LENGTH_SHORT).show()
                    } else {
                        Toast.makeText(this,
                            getString(R.string.username_password_is_incorrect), Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }

        binding.btnLogin.setOnClickListener {
            val email = binding.tlEmail.editText?.text.toString()
            val password = binding.tlPassword.editText?.text.toString()
            viewModel.login(email, password)
        }
    }
}