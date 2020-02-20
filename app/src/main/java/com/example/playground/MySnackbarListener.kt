package com.example.playground

import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Toast

/**
 * Created by jesse on 2/20/20.
 * This is a part of the project Android Certification Playground.
 */
class MySnackbarListener : View.OnClickListener {
    override fun onClick(view: View?) {
        Log.d("SnackBar action ", "clicked!")
        val toast = Toast.makeText(view?.context, "SnackBar Action Clicked!!!", Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.TOP, 0, 200)
        toast.show()
    }
}