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

class SplashActivity2 : AppCompatActivity() {

    lateinit var topAnim: Animation
    lateinit var bottomAnim: Animation
    lateinit var logo: ImageView
    lateinit var hello: TextView
    lateinit var handle: TextView
    lateinit var and: TextView
    lateinit var attend: TextView
    lateinit var handler: Handler

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //fullscreen
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        setContentView(R.layout.activity_main)

        topAnim = AnimationUtils.loadAnimation(this,R.anim.top_animation)
        bottomAnim = AnimationUtils.loadAnimation(this,R.anim.bottom_animation)

        logo = findViewById(R.id.logo)
        hello = findViewById(R.id.hello)
        handle = findViewById(R.id.handle)
        and = findViewById(R.id.and)
        attend = findViewById(R.id.attend)

        logo.animation = topAnim
        hello.animation = bottomAnim
        handle.animation = bottomAnim
        and.animation = bottomAnim
        attend.animation = bottomAnim

        handler = Handler()
        handler.postDelayed({
            val intent = Intent(this@SplashActivity2, Dashboard::class.java)
            startActivity(intent)
            finish()
        }, 8000)
    }
}