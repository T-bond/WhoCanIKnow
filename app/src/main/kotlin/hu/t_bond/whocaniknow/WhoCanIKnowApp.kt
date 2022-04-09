package hu.t_bond.whocaniknow

import android.app.Application
import com.google.android.material.color.DynamicColors
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class WhoCanIKnowApp : Application() {
    override fun onCreate() {
        super.onCreate()

        DynamicColors.applyToActivitiesIfAvailable(this);
    }
}