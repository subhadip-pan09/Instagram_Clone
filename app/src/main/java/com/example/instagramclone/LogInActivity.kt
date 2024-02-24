package com.example.instagramclone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.widget.Toast
import com.example.instagramclone.Models.User
import com.example.instagramclone.databinding.ActivityLogInBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.google.firebase.ktx.app

class LogInActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityLogInBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val text = "<font color=#FF000000>Don't have an account?</font> <font color=#0009ff>Sign up</font>"
        binding.signup.setText(Html.fromHtml(text))

        binding.signup.setOnClickListener {
            startActivity(Intent(this@LogInActivity,SignUpActivity::class.java))
            finish()
        }

        binding.loginBtn.setOnClickListener {
            if (binding.email.editText?.text.toString().equals("") or
                binding.pass.editText?.text.toString().equals("")){
                Toast.makeText(this@LogInActivity, "Please fill all the details", Toast.LENGTH_SHORT).show()
            }else{
                var user= User(binding.email.editText?.text.toString(),
                               binding.pass.editText?.text.toString())

                Firebase.auth.signInWithEmailAndPassword(user.email!!, user.password!!)
                    .addOnCompleteListener {
                        if (it.isSuccessful){
                            startActivity(Intent(this@LogInActivity,HomeActivity::class.java))
                            finish()
                        }else{
                            Toast.makeText(this@LogInActivity, it.exception?.localizedMessage, Toast.LENGTH_SHORT).show()
                        }
                    }
            }
        }
    }
}