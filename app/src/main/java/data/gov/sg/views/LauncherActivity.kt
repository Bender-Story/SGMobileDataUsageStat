package data.gov.sg.views

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.android.rahul.samplepulltorefreshonmultiplescroll.controller.Navigator
import data.gov.sg.R
import data.gov.sg.components.ActivityUIExt
import data.gov.sg.utils.NetworkUtils
import kotlinx.android.synthetic.main.launcher_activity.*
import android.os.Handler
import data.gov.sg.Constants.GO_OFFLINE


class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.launcher_activity)
        supportActionBar?.hide()
    }

    override fun onResume() {
        super.onResume()
        animImageView.setAnimation(R.drawable.launcher_animation_list)
        callNextActivity()
    }


    private fun callNextActivity(){
        Handler().postDelayed({
           checkIfNetworkAvailable()
        }, 1000)
    }

    private fun checkIfNetworkAvailable(){
        if(NetworkUtils.isNetworkAvailable(this)){
            Navigator.goToActivity(this, MobileDataStatListActivity::class.java,true)

        }else{
            ActivityUIExt(this).buildDialog(getString(R.string.network_error),negativeText="Go offline",onReload={
                checkIfNetworkAvailable()
            },onNegitive={
                var bundle=Bundle()
                bundle.putBoolean(GO_OFFLINE,true)
                Navigator.goToActivity(this, MobileDataStatListActivity::class.java,true,bundle)
            })
        }
    }

    override fun onStop() {
        super.onStop()
        animImageView.stopAnimation()
    }
}
