package  revolut.android.test.views

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import kotlinx.android.synthetic.main.activity_splash.*
import revolut.android.test.R
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity() {
    lateinit var disposable: Disposable
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val anim: Animation = AnimationUtils.loadAnimation(this, R.anim.splash_animation)
        ac_splash_logo.startAnimation(anim)

        // Open [CurrencyActivity] after 3 second
        disposable =
            Completable.timer(2, TimeUnit.SECONDS, AndroidSchedulers.mainThread()).subscribe(
                {
                    val intent = Intent(
                        this,
                        CurrencyActivity::class.java
                    )
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    finish()
                })
    }

    override fun onPause() {
        super.onPause()
        if (!disposable.isDisposed) {
            disposable.dispose()
        }
    }
}
