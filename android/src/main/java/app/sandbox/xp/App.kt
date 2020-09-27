package app.sandbox.xp

import android.app.Application
import android.util.Log
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import com.google.firebase.remoteconfig.ktx.remoteConfig

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        Firebase.remoteConfig.apply {
            setDefaultsAsync(R.xml.remote_config_defaults)
            Firebase.remoteConfig.setConfigSettingsAsync(
                FirebaseRemoteConfigSettings
                    .Builder()
                    .setMinimumFetchIntervalInSeconds(360)
                    .build()
            )
            fetchAndActivate().addOnCompleteListener {
                Log.d(
                    "FETCHING_EXPERIMENTS", if (it.isSuccessful) {
                    "Completed with success"
                } else {
                    "Failed to fetch"
                }
                )
            }
        }
    }
}