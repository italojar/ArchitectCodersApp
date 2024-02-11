package es.architectcoders.spaceexplorer.ui.common

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.NavController
import androidx.recyclerview.widget.DiffUtil
import com.bumptech.glide.Glide
import es.architectcoders.domain.Apod
import es.architectcoders.domain.Camera
import es.architectcoders.domain.CameraX
import es.architectcoders.domain.Photo
import es.architectcoders.domain.Rover
import es.architectcoders.spaceexplorer.R
import es.architectcoders.spaceexplorer.framework.server.roverServer.CameraResponse
import es.architectcoders.spaceexplorer.framework.server.roverServer.CameraXResponse
import es.architectcoders.spaceexplorer.framework.server.roverServer.RoverResponse
import es.architectcoders.spaceexplorer.ui.model.ApodObject
import es.architectcoders.spaceexplorer.ui.model.CameraObject
import es.architectcoders.spaceexplorer.ui.model.PhotoObject
import es.architectcoders.spaceexplorer.ui.model.RoverObject
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


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

private fun LinearLayout.animateVisibilityWithAnimation(
    show: Boolean,
    duration: Long,
    imageButton: ImageButton
) {
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

inline fun <T> basicDiffUtil(
    crossinline areItemsTheSame: (T, T) -> Boolean = { old, new -> old == new },
    crossinline areContentsTheSame: (T, T) -> Boolean = { old, new -> old == new }
) = object : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T & Any, newItem: T & Any): Boolean =
        areItemsTheSame(oldItem, newItem)

    override fun areContentsTheSame(oldItem: T & Any, newItem: T & Any): Boolean =
        areContentsTheSame(oldItem, newItem)
}

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

fun LifecycleOwner.launchAndCollect(
    state: Lifecycle.State = Lifecycle.State.STARTED,
    block: suspend CoroutineScope.() -> Unit
) = lifecycleScope.launch {
    lifecycle.repeatOnLifecycle(state, block)
}

fun <T> LifecycleOwner.launchAndCollectT(
    flow: Flow<T>,
    state: Lifecycle.State = Lifecycle.State.STARTED,
    body: (T) -> Unit
) {
    lifecycleScope.launch {
        this@launchAndCollectT.repeatOnLifecycle(state) {
            flow.collect(body)
        }
    }
}

fun ApodObject.toDomain() = Apod(
    id = id,
    copyright = copyright,
    date = date,
    explanation = explanation,
    hdurl = hdurl,
    mediaType = mediaType,
    serviceVersion = serviceVersion,
    title = title,
    url = url,
    favorite = favorite
)

fun Apod.toViewObject() = ApodObject(
    id = id,
    copyright = copyright,
    date = date,
    explanation = explanation,
    hdurl = hdurl,
    mediaType = mediaType,
    serviceVersion = serviceVersion,
    title = title,
    url = url,
    favorite = favorite
)

fun PhotoObject.toDomain() = Photo(
    camera = camera.toDomain(),
    earthDate = earthDate,
    id = id,
    imgSrc = imgSrc,
    rover = rover.toDomain(),
    sol = sol,
    favorite = favorite
)

fun CameraObject.toCameraResponse() = CameraResponse(
    fullName = fullName,
    id = id,
    name = name,
    roverId = roverId
)

fun RoverObject.toRoverResponse() = RoverResponse(
    cameras = cameras.map { it.toCameraXResponse() },
    id = id,
    landingDate = landingDate,
    launchDate = launchDate,
    maxDate = maxDate,
    maxSol = maxSol,
    name = name,
    status = status,
    totalPhotos = totalPhotos
)

fun Photo.toViewObject() = PhotoObject(
    camera = camera.toCameraResponse(),
    earthDate = earthDate,
    id = id,
    imgSrc = imgSrc,
    rover = rover.toRoverResponse(),
    sol = sol,
    favorite = favorite
)

fun Camera.toCameraResponse() = CameraResponse(
    fullName = fullName,
    id = id,
    name = name,
    roverId = roverId
)

fun Rover.toRoverResponse() = RoverResponse(
    cameras = cameras.map { it.toCameraXResponse() },
    id = id,
    landingDate = landingDate,
    launchDate = launchDate,
    maxDate = maxDate,
    maxSol = maxSol,
    name = name,
    status = status,
    totalPhotos = totalPhotos
)

fun CameraX.toCameraXResponse() = CameraXResponse(
    fullName = fullName,
    name = name
)

fun CameraResponse.toDomain() = Camera(
    fullName = fullName,
    id = id,
    name = name,
    roverId = roverId
)

fun RoverResponse.toDomain() = Rover(
    cameras = cameras.map { it.toDomain() },
    id = id,
    landingDate = landingDate,
    launchDate = launchDate,
    maxDate = maxDate,
    maxSol = maxSol,
    name = name,
    status = status,
    totalPhotos = totalPhotos
)

fun CameraXResponse.toDomain() = CameraX(
    fullName = fullName,
    name = name
)
