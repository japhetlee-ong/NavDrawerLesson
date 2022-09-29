package com.auf.cea.navdrawerlesson

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.auf.cea.navdrawerlesson.databinding.ActivityRegisterBinding
import com.auf.cea.navdrawerlesson.helpers.EMAIL
import com.auf.cea.navdrawerlesson.helpers.MY_PREFERENCE
import com.auf.cea.navdrawerlesson.helpers.USERNAME

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val sharedPreferences = getSharedPreferences(MY_PREFERENCE,Context.MODE_PRIVATE)

        binding.btnEnter.setOnClickListener{
            val editor = sharedPreferences.edit()
            editor.putString(USERNAME,binding.edtName.text.toString())
            editor.putString(EMAIL,binding.edtEmail.text.toString())
            editor.apply()

            val intent = Intent(this,HomeActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)

        }

    }

}