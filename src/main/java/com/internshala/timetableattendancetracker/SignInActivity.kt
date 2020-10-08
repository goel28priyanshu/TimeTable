package com.internshala.timetableattendancetracker

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.IdpResponse
import com.google.android.gms.auth.api.Auth
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_sign_in.*
import java.util.*

class SignInActivity : AppCompatActivity() {

    private val MY_REQUEST_CODE: Int = 7177 //Any number
    lateinit var providers : List<AuthUI.IdpConfig>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        //Init
        providers = Arrays.asList<AuthUI.IdpConfig>(
            AuthUI.IdpConfig.EmailBuilder().build(),  //Email Login
            AuthUI.IdpConfig.GoogleBuilder().build(),  //Goggle Login
            AuthUI.IdpConfig.PhoneBuilder().build()    //Phone Login
        )

        showsSignInOptions()

        //Event
        btn_sign_out.setOnClickListener{
            AuthUI.getInstance().signOut(this@SignInActivity)
                .addOnCompleteListener {
                    btn_sign_out.isEnabled = false
                    showsSignInOptions()
                }
                .addOnFailureListener {
                    e-> Toast.makeText(this@SignInActivity,e.message,Toast.LENGTH_SHORT).show()
                }
        }

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode == MY_REQUEST_CODE)
        {
            val response = IdpResponse.fromResultIntent(data)
            if(resultCode == Activity.RESULT_OK)
            {
                val user = FirebaseAuth.getInstance().currentUser  //get current user
                Toast.makeText(this@SignInActivity, ""+user!!.email,Toast.LENGTH_SHORT).show()

                btn_sign_out.isEnabled = true
            }
            else
            {
                Toast.makeText(this@SignInActivity, ""+response!!.error!!.message,Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun showsSignInOptions() {
        startActivityForResult(
            AuthUI.getInstance().createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .setTheme(R.style.MyTheme)
                .build(),MY_REQUEST_CODE
        )
    }
}