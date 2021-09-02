package com.example.wangsamplecode

import android.content.IntentFilter
import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import com.example.wangsamplecode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    val TAG = MainActivity::class.java.simpleName

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val connectivityManager = getSystemService(ConnectivityManager::class.java)

//        val linkProperties = connectivityManager.getLinkProperties(currentNetwork)

        binding.button.setOnClickListener{
            val currentNetwork = connectivityManager.getActiveNetwork()
            val caps = connectivityManager.getNetworkCapabilities(currentNetwork)

            if(caps==null) Log.d(TAG,"no network now")
            else Log.d(TAG,"network is connected now")
        }



        connectivityManager.registerDefaultNetworkCallback(object : ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network : Network) {
                Log.d(TAG, "The default network is now: " + network)
            }

            override fun onLost(network : Network) {
                Log.d(TAG, "The application no longer has a default network. The last default network was " + network)
            }

            override fun onCapabilitiesChanged(network : Network, networkCapabilities : NetworkCapabilities) {
                Log.d(TAG, "The default network changed capabilities: " + networkCapabilities)
            }

            override fun onLinkPropertiesChanged(network : Network, linkProperties : LinkProperties) {
                Log.d(TAG, "The default network changed link properties: " + linkProperties)
            }
        })

    }

}