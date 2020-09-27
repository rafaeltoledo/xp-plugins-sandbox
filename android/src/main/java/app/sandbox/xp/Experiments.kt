package app.sandbox.xp

import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.ktx.remoteConfig
import java.util.*

object Experiments {

    fun isTreated(xp: Xp): Boolean {
        return Firebase.remoteConfig.getString(xp.rawName()) == "treatment"
    }

    fun isControl(xp: Xp) = Firebase.remoteConfig.getString(xp.rawName()) == "control"

    fun getValue(xp: Xp) = Firebase.remoteConfig.getString(xp.rawName())
}

interface Xp {
    fun rawName(): String
}

enum class AppExperiments : Xp {
    NEWS_LAYOUT,
    NEWS_SUCCESSFUL_HANDLER,
    NEWS_EMPTY_HANDLER,
    NEWS_UNAUTHORIZED_ERROR_HANDLER,
    NEWS_GENERIC_ERROR_HANDLER,
    TEST_USER;

    override fun rawName() = name.toLowerCase(Locale.US)
}