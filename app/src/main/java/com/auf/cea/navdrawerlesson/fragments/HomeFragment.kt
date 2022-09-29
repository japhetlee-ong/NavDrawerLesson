package com.auf.cea.navdrawerlesson.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.auf.cea.navdrawerlesson.R
import com.auf.cea.navdrawerlesson.databinding.FragmentMainBinding
import com.auf.cea.navdrawerlesson.helpers.MY_PREFERENCE
import com.auf.cea.navdrawerlesson.helpers.SAVE_QUOTATION
import com.auf.cea.navdrawerlesson.helpers.USERNAME


class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var mainFragmentInterface: MainFragmentInterface

    interface MainFragmentInterface{
        fun gotoQuotes()
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainFragmentInterface = context as MainFragmentInterface
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val sharedPreferences = requireActivity().getSharedPreferences(MY_PREFERENCE,Context.MODE_PRIVATE)
        val faveQuote = sharedPreferences.getString(SAVE_QUOTATION,"")

        binding.txtUserName.text = String.format("Welcome %s", sharedPreferences.getString(USERNAME,""))

        if(!faveQuote.isNullOrEmpty()){
            binding.btnGotoQuotes.visibility = View.GONE
            binding.txtFaveQuote.text = faveQuote
        }else{
            binding.btnGotoQuotes.setOnClickListener{
                mainFragmentInterface.gotoQuotes()
            }
        }


    }

}