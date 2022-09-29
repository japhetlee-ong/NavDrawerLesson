package com.auf.cea.navdrawerlesson.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.auf.cea.navdrawerlesson.R
import com.auf.cea.navdrawerlesson.databinding.FragmentQuotesGeneratorBinding
import com.auf.cea.navdrawerlesson.helpers.MY_PREFERENCE
import com.auf.cea.navdrawerlesson.helpers.QuoteRandomizerHelper
import com.auf.cea.navdrawerlesson.helpers.SAVE_QUOTATION

class QuotesGeneratorFragment : Fragment() {

    private lateinit var binding: FragmentQuotesGeneratorBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentQuotesGeneratorBinding.inflate(inflater,container,false)

        sharedPreferences = requireActivity().getSharedPreferences(MY_PREFERENCE,Context.MODE_PRIVATE)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getRadomizeQuote()
        binding.btnRandomize.setOnClickListener{
            getRadomizeQuote()
        }

        binding.btnSaveQuote.setOnClickListener{
            val editor = sharedPreferences.edit()
            editor.putString(SAVE_QUOTATION,binding.txtQuote.text.toString())
            editor.apply()

            Toast.makeText(activity,"Quote has been saved",Toast.LENGTH_SHORT).show()
        }

    }

    private fun getRadomizeQuote(){
        val text = QuoteRandomizerHelper.getRandomQuote()
        binding.txtQuote.text = text
    }

}