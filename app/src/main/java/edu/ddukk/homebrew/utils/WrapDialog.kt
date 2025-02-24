package edu.ddukk.homebrew.utils

import android.content.Context
import androidx.appcompat.app.AlertDialog


class CustomDialog(context: Context) : AlertDialog.Builder(context){

    enum class  ResponseType { YES, NO, CANCEL}
    lateinit var onResponse: (response: ResponseType) -> Unit

    fun show(title: String, message: String, listener: (response: ResponseType)-> Unit){

        val builder= AlertDialog.Builder(context)
        builder.apply {
            setTitle(title)
            setMessage(message)
            setIcon(android.R.drawable.ic_dialog_info)
            onResponse = listener
        }

        builder.setPositiveButton("Yes") {dialog,id ->
            onResponse(ResponseType.YES)
        }

       builder.setNegativeButton("NO",) {_,_ ->
           onResponse(ResponseType.NO)
       }

        val alertDialog: AlertDialog = builder.create();

        alertDialog.setCancelable(false)
        alertDialog.show()
        

    }

}
