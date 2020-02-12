package revolut.android.test.utils.bindingadapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter

class BindingViews {
    companion object {
        @BindingAdapter("src")
        @JvmStatic
        fun setImageViewResource(imageView: ImageView, resource: Int) {
            imageView.setImageResource(resource)
        }
    }

}