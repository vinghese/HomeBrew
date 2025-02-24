package edu.ddukk.homebrew.utils

import android.app.Activity
import android.view.Gravity
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import edu.ddukk.homebrew.R

fun Toast.showCustomToast(message: String, activity: Activity){
    val layout = activity.layoutInflater.inflate(R.layout.custom_toast_layout, activity.findViewById(R.id.layout_root_toast));

    val txtInput = layout.findViewById<TextView>(R.id.tvCustomToast);
    txtInput.text = message;
    val imgIcon = layout.findViewById<ImageView>(R.id.img_custom_toast);
    imgIcon.setImageResource(R.drawable.bluenoti)

    this.apply {
        setGravity(Gravity.BOTTOM, 0, 40);
        duration = Toast.LENGTH_LONG
        view = layout
        show()
    }


}