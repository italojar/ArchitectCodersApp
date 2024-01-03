package es.architectcoders.spaceexplorer.common

import android.view.MenuItem
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.navigation.NavController
import com.bumptech.glide.Glide
import es.architectcoders.spaceexplorer.R


fun MenuItem.onHomeSelected(navController: NavController) {
    this.setOnMenuItemClickListener { menuItem ->
        navController.navigate(menuItem.itemId)
        true
    }
}
fun ImageView.loadUrl(url: String?) {
    Glide.with(context).load(url).error(R.mipmap.ic_launcher).into(this)
}

fun LinearLayout.toggleVisibilityWithAnimation(imageButton: ImageButton, duration: Long = 200) {
    if (visibility == View.VISIBLE) {
        animateVisibilityWithAnimation(false, duration, imageButton)
    } else {
        animateVisibilityWithAnimation(true, duration, imageButton)
    }
}

private fun LinearLayout.animateVisibilityWithAnimation(show: Boolean, duration: Long, imageButton: ImageButton) {
    val targetAlpha = if (show) 1f else 0f

    animate()
        .alpha(targetAlpha)
        .setDuration(duration)
        .withEndAction {
            visibility = if (show) View.VISIBLE else View.GONE
            alpha = 1f
            val imageResource = if (show) R.drawable.ic_expand_less else R.drawable.ic_expand_more
            imageButton.setImageResource(imageResource)
        }
        .start()
}
