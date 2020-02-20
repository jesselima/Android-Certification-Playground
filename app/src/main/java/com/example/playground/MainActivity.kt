package com.example.playground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val snackBarCallback = Snackbar.Callback()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        buttonShowSnackbar.setOnClickListener {
            showMySnackBar()
        }

        buttonShowSnackbarWithAction.setOnClickListener {
            showMySnackBarWithAction()
        }

        buttonShowSnackbarWithCallback.setOnClickListener {
            showMySnackBarWithCallback()
        }
    }

    private fun showMySnackBar() {
        Snackbar.make(main_layout, "Hello", Snackbar.LENGTH_LONG).show()
    }

    private fun showMySnackBarWithAction() {
        Snackbar.make(
            main_layout,
            "Hello",
            Snackbar.LENGTH_LONG
        )
        .setAction("Action", MySnackbarListener())
        .show()
    }

    private fun showMySnackBarWithCallback() {
        Snackbar.make(findViewById(R.id.main_layout), "Hi snackbar!", Snackbar.LENGTH_LONG)
            .addCallback(object : Snackbar.Callback() {
                override fun onDismissed(snackbar: Snackbar?, event: Int) {
                    Toast.makeText(applicationContext, "Snackbar Dismissed", Toast.LENGTH_LONG).show()
                }
                override fun onShown(snackbar: Snackbar?) {
                    Toast.makeText(applicationContext, "Showing Snackbar", Toast.LENGTH_LONG).show()
                }
            })
            .setAction("Action!") {
                Toast.makeText(applicationContext, "Action Triggered", Toast.LENGTH_LONG).show()
            }
            .show()
    }

}
