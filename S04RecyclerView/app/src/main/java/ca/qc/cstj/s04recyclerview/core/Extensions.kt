package ca.qc.cstj.s04recyclerview.core

import android.widget.ImageView

fun ImageView.loadFromResource(imageName: String) {
    val imageId = resources.getIdentifier(imageName,"drawable", context.packageName)
    setImageResource(imageId)
}