package data.gov.sg.components

import android.content.Context
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.databinding.BindingAdapter
import data.gov.sg.R

class ActivityUIExt( val context : Context){
    // Build Error dialog.
    fun buildDialog(error:String, title:String=context.getString(R.string.dialog_title),
                    positiveText:String=context.getString(R.string.dialog_retry),
                    negativeText:String=context.getString(R.string.dialog_no),
                    onReload:() -> Unit){
        val builder = AlertDialog.Builder(context)
        builder.setTitle(title)
        builder.setMessage(error)
        builder.setCancelable(false)
        builder.setPositiveButton(positiveText){dialog, which ->
        onReload.invoke()
            dialog.dismiss()
        }

        builder.setNegativeButton(negativeText){dialog,which ->
            dialog.dismiss()
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()
    }
}

@BindingAdapter("visible")
fun View.visible(visible: Boolean) {
    this.visibility = if (visible) View.VISIBLE else View.GONE
}