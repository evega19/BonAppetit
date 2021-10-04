package org.bedu.bonappetit.inter

import android.view.View

interface ClickListener {
    abstract fun onClick(vista: View, position: Int)
}