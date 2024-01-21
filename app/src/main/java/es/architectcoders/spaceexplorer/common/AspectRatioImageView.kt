package es.architectcoders.spaceexplorer.common

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView

class AspectRatioImageView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : AppCompatImageView(context, attrs) {

    var ratio: Float = 1f

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val width = measuredWidth
        val height = measuredHeight

        if (width == 0 && height == 0) {
            return
        }

        if (width > height * ratio) {
            setMeasuredDimension(height * ratio.toInt(), height)
        } else {
            setMeasuredDimension(width, width / ratio.toInt())
        }

    }
}