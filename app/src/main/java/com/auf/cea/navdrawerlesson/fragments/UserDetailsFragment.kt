package com.auf.cea.navdrawerlesson.fragments

import android.app.AlertDialog
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import com.auf.cea.navdrawerlesson.R
import com.auf.cea.navdrawerlesson.databinding.FragmentUserDetailsBinding
import com.auf.cea.navdrawerlesson.helpers.EMAIL
import com.auf.cea.navdrawerlesson.helpers.MY_PREFERENCE
import com.auf.cea.navdrawerlesson.helpers.USERNAME

class UserDetailsFragment : Fragment() {

    private lateinit var userDetailsInterface: UserDetailsInterface
    private lateinit var binding: FragmentUserDetailsBinding
    private lateinit var sharedPreferences: SharedPreferences

    interface UserDetailsInterface{
        fun onEdit(username: String, email: String)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        userDetailsInterface = context as UserDetailsInterface
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserDetailsBinding.inflate(inflater,container,false)
        sharedPreferences = requireActivity().getSharedPreferences(MY_PREFERENCE,Context.MODE_PRIVATE)

        binding.txtName.text = String.format("Name: %s", sharedPreferences.getString(USERNAME,""))
        binding.txtEmailAddress.text = String.format("Email address: %s", sharedPreferences.getString(
            EMAIL,""))

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEdtUserDetails.setOnClickListener{
            val editLayout = LinearLayout(activity)
            editLayout.orientation = LinearLayout.VERTICAL

            val edtUsername = EditText(activity)
            edtUsername.hint = "Username"
            edtUsername.setText(sharedPreferences.getString(USERNAME,""))

            val edtEmail = EditText(activity)
            edtEmail.hint = "Email"
            edtEmail.setText(sharedPreferences.getString(EMAIL,""))

            editLayout.addView(edtUsername)
            editLayout.addView(edtEmail)
            editLayout.setPadding(50,60,50,60)

            val alertDialog = AlertDialog.Builder(activity)
            with(alertDialog){
                setTitle("Edit user details")
                setView(editLayout)
            }

            alertDialog.setPositiveButton("Confirm"){dialog,_ ->
                val username = edtUsername.text.toString()
                val email = edtEmail.text.toString()

                binding.txtName.text = String.format("Name: %s", username)
                binding.txtEmailAddress.text = String.format("Email address: %s", email)

                userDetailsInterface.onEdit(username,email)
                dialog.dismiss()

            }

            alertDialog.setNegativeButton("Cancel"){dialog,_ ->
                dialog.dismiss()
            }

            alertDialog.show()
        }

    }

}