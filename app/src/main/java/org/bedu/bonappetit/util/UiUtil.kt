package org.bedu.bonappetit.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

object UiUtil {

    fun showSnackbar(view: View, textId: Int, actionTextId: Int ){
        Snackbar.make(
            view,
            textId,
            Snackbar.LENGTH_LONG
        )
            .setAction(
                actionTextId
            ) { }
            .show()
    }

}