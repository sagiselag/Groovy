package sagi.sela.groovy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        var mockoonUrl = "http://192.168.100.1:3000/"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // the following code replaced by Jetpack Navigation
//        if(savedInstanceState == null)
//            supportFragmentManager.beginTransaction()
//                .add(R.id.container, PlaylistFragment.newInstance())
//                .commit()
    }
}