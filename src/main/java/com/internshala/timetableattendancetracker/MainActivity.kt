package com.internshala.timetableattendancetracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    //variables
    lateinit var topAnim: Animation
    lateinit var bottomAnim: Animation
    lateinit var image: ImageView
    lateinit var text: TextView
    lateinit var text1: TextView
    lateinit var text2: TextView
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        //fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        //Animations
        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation)

        //Hooks
        image = findViewById(R.id.logo)
        text = findViewById(R.id.text)
        text1 = findViewById(R.id.text1)
        text2 = findViewById(R.id.text2)

        image.animation = topAnim
        text.animation = bottomAnim
        text1.animation = bottomAnim
        text2.animation = bottomAnim

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@MainActivity, SignInActivity::class.java)
            startActivity(intent)
            finish()
        }, 4000)

    }
}