package data.gov.sg.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import data.gov.sg.R
import data.gov.sg.utils.NetworkUtils
import kotlinx.android.synthetic.main.launcher_activity.*

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launcher_activity)
        supportActionBar?.hide()
    }

    override fun onResume() {
        super.onResume()
        animImageView.setAnimation(R.drawable.launcher_animation_list)
        if(NetworkUtils.isNetworkAvailable(this)){

        }else{

        }
    }

    override fun onStop() {
        super.onStop()
        animImageView.stopAnimation()
    }
}
