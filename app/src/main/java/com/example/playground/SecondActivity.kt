package com.example.playground

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.*
import android.text.Annotation
import android.widget.TextView
import androidx.annotation.FontRes
import androidx.core.content.res.ResourcesCompat
import androidx.core.text.HtmlCompat

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val textView: TextView = findViewById(R.id.textViewSample)

        val spannableString = intent.getCharSequenceExtra("TEXT_EXTRA") as SpannableString

        val annotations = spannableString.getSpans(3, 7, Annotation::class.java)

        // TODO -> Complete this implementation
        textView.text = spannableString

    }

}
