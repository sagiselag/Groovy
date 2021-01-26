package sagi.sela.groovy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    companion object {
        // TODO replace ip and mockoonPort with your actual values
        val ip = "your ip address" //
        val mockoonPort = "your Mockoon port" // 3000 for example
        var mockoonUrl = "http://" + ip + ":" + mockoonPort + "/"
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