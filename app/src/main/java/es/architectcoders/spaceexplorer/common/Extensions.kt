package es.architectcoders.spaceexplorer.common

import android.view.View
import android.view.animation.Animation
import android.view.animation.Transformation
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.google.android.material.card.MaterialCardView
import es.architectcoders.spaceexplorer.R

fun ImageView.loadUrl(url: String?) {
    Glide.with(context).load(url).error(R.mipmap.ic_launcher).into(this)
}

fun MaterialCardView.getExpandAnimation(targetView: View): Animation {
    val initialHeight = this.height
    val targetHeight = initialHeight + targetView.height

    val animation: Animation = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, transformation: Transformation?) {
            layoutParams.height =
                (initialHeight + (targetHeight - initialHeight) * interpolatedTime).toInt()
            requestLayout()
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }

    animation.duration = 200

    animation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {
            // No es necesario realizar acciones al inicio de la animación
        }

        override fun onAnimationEnd(animation: Animation?) {
            targetView.visibility = View.VISIBLE
        }

        override fun onAnimationRepeat(animation: Animation?) {
            // No es necesario realizar acciones en repeticiones de la animación
        }
    })

    return animation
}

fun MaterialCardView.getCollapseAnimation(targetView: View): Animation {
    val initialHeight = this.height
    val targetHeight = initialHeight - targetView.height

    val animation: Animation = object : Animation() {
        override fun applyTransformation(interpolatedTime: Float, transformation: Transformation?) {
            layoutParams.height =
                (initialHeight - (initialHeight - targetHeight) * interpolatedTime).toInt()
            requestLayout()
        }

        override fun willChangeBounds(): Boolean {
            return true
        }
    }

    animation.duration = 200

    animation.setAnimationListener(object : Animation.AnimationListener {
        override fun onAnimationStart(animation: Animation?) {
            // No es necesario realizar acciones al inicio de la animación
        }

        override fun onAnimationEnd(animation: Animation?) {
            targetView.visibility = View.GONE
        }

        override fun onAnimationRepeat(animation: Animation?) {
            // No es necesario realizar acciones en repeticiones de la animación
        }
    })

    return animation
}