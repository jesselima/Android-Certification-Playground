package com.example.playground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_toast.*
import kotlinx.android.synthetic.main.custom_toast.view.*

class MainActivity : AppCompatActivity() {

    var someText = "Guess What? \n Hello world!"
    private val durationShortToast = Toast.LENGTH_SHORT
    private val durationLongToast = Toast.LENGTH_LONG


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewShowShortToastButton.setOnClickListener {
            showToastDefault("$someText\n Short Toast sample", durationShortToast)
        }

        viewShowLongToastButton.setOnClickListener {
           showToastDefault("$someText\n Long Toast sample", durationLongToast)
        }

        viewShowWithGravityTop.setOnClickListener {
            showToastTop("$someText\n Toast Top", durationShortToast)
        }

        viewShowWithGravityLeft.setOnClickListener {
            showToastLeft("$someText\n Toast Left", durationShortToast)
        }

        viewShowWithGravityRight.setOnClickListener {
            showToastRight("$someText\n Toast Right", durationShortToast)
        }

        viewShowCustomizedToastSuccess.setOnClickListener {
            showCustomizedToast(true)
        }

        viewShowCustomizedToastError.setOnClickListener {
            showCustomizedToast(false)
        }


    }


    private fun showToastDefault(text: String, duration: Int) {
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.show()
    }

    private fun showToastTop(text: String, duration: Int) {
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.setGravity(Gravity.TOP, 0, 100)
        toast.show()
    }

    private fun showToastLeft(text: String, duration: Int) {
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.setGravity(Gravity.START, 0, 0)
        toast.show()
    }

    private fun showToastRight(text: String, duration: Int) {
        val toast = Toast.makeText(applicationContext, text, duration)
        toast.setGravity(Gravity.END, 0, 0)
        toast.show()
    }


    private fun showCustomizedToast(isSuccess: Boolean) {

        val inflater = layoutInflater

        val layout: ViewGroup = inflater.inflate(
            R.layout.custom_toast,
            customToastContainer,
            false
        ) as ViewGroup


        val imageView: ImageView = layout.findViewById(R.id.toastImage)

        if(isSuccess) {
            layout.customText.text = "Success!"
            imageView.setImageResource(R.drawable.ic_check_circle)
        } else {
            layout.customText.text = "Oooops!"
            imageView.setImageResource(R.drawable.ic_error)
        }

        with (Toast(applicationContext)) {
            setGravity(Gravity.BOTTOM, 0, 100)
            duration = Toast.LENGTH_LONG
            view = layout
            show()
        }

    }

}
