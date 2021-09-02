package com.example.wangsamplecode

import android.net.ConnectivityManager
import android.net.LinkProperties
import android.net.Network
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.wangsamplecode.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    companion object {
        var isNetworkConnected = false
    }

    val TAG = MainActivity::class.java.simpleName

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val connectivityManager = getSystemService(ConnectivityManager::class.java)

        binding.button.setOnClickListener {
            Log.d(TAG, "is network connected:$isNetworkConnected ")
        }

        connectivityManager.registerDefaultNetworkCallback(object :
            ConnectivityManager.NetworkCallback() {
            override fun onAvailable(network: Network) {
                Log.d(TAG, "The default network is now: " + network)
                isNetworkConnected = true
            }

            override fun onLost(network: Network) {
                Log.d(
                    TAG,
                    "The application no longer has a default network. The last default network was " + network
                )
                isNetworkConnected = false
            }

            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities
            ) {
                Log.d(TAG, "The default network changed capabilities: " + networkCapabilities)
            }

            override fun onLinkPropertiesChanged(network: Network, linkProperties: LinkProperties) {
                Log.d(TAG, "The default network changed link properties: " + linkProperties)
            }
        })

    }

}