package com.auf.cea.navdrawerlesson

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.Menu
import android.widget.TextView
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.auf.cea.navdrawerlesson.databinding.ActivityHomeBinding
import com.auf.cea.navdrawerlesson.fragments.MainFragment
import com.auf.cea.navdrawerlesson.fragments.UserDetailsFragment
import com.auf.cea.navdrawerlesson.helpers.EMAIL
import com.auf.cea.navdrawerlesson.helpers.MY_PREFERENCE
import com.auf.cea.navdrawerlesson.helpers.USERNAME
import com.google.android.material.navigation.NavigationView

class HomeActivity : AppCompatActivity(), UserDetailsFragment.UserDetailsInterface, MainFragment.MainFragmentInterface {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityHomeBinding
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var navView: NavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        sharedPreferences = getSharedPreferences(MY_PREFERENCE,Context.MODE_PRIVATE)

        setSupportActionBar(binding.appBarContainer.toolbar)

        val drawerLayout : DrawerLayout = binding.drawerLayout
        navView = binding.navView
        val navController = findNavController(R.id.nav_host_fragment_content_main)

        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.nav_home, R.id.nav_user_details, R.id.nav_random_quotes
            ), drawerLayout
        )
        setupActionBarWithNavController(navController,appBarConfiguration)
        navView.setupWithNavController(navController)

        setHeaderNames(sharedPreferences.getString(USERNAME,""),sharedPreferences.getString(EMAIL,""))

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return super.onCreateOptionsMenu(menu)
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment_content_main)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onEdit(username: String, email: String) {
        Log.d(HomeActivity::class.java.simpleName, username)
        Log.d(HomeActivity::class.java.simpleName, email)

        val editor = sharedPreferences.edit()
        editor.putString(USERNAME,username)
        editor.putString(EMAIL,email)
        editor.apply()

        setHeaderNames(username,email)

        Toast.makeText(this,username,Toast.LENGTH_SHORT).show()
    }

    override fun gotoQuotes() {
        //Navigation.findNavController(this,R.id.nav_host_fragment_content_main).navigate(R.id.nav_random_quotes)
        binding.drawerLayout.openDrawer(GravityCompat.START)
    }

    private fun setHeaderNames(name: String?, email: String?){
        val headerView = navView.getHeaderView(0)
        val txtUsername = headerView.findViewById<TextView>(R.id.txt_name)
        val txtEmail = headerView.findViewById<TextView>(R.id.txt_email_address)

        txtEmail.text = name
        txtUsername.text = email
    }
}