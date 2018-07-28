package com.example.stefano.currencyconverter

import android.app.Activity
import android.os.StrictMode
import android.support.multidex.MultiDexApplication
import android.support.v7.app.AppCompatDelegate
import com.example.stefano.currencyconverter.di.AppComponent
import com.example.stefano.currencyconverter.di.DaggerAppComponent
import com.jakewharton.threetenabp.AndroidThreeTen
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import timber.log.Timber
import javax.inject.Inject

class CurrencyConverterApp : MultiDexApplication(), HasActivityInjector {

    @Inject lateinit var activityInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> = activityInjector

    companion object {
        lateinit var appComponent: AppComponent
        lateinit var appInstance: CurrencyConverterApp
    }

    override fun onCreate() {
        super.onCreate()
        appInstance = this

        initVectorGraphics()
        initThreeTen()
        initDagger()
        initPolicies()
        initTimber()
    }

    private fun initVectorGraphics() = AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
    private fun initThreeTen() = AndroidThreeTen.init(this)

    private fun initDagger() {
        appComponent = DaggerAppComponent
                .builder()
                .context(this)
                .build()
        appComponent.inject(this)
    }

    private fun initPolicies() {
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                    StrictMode.ThreadPolicy
                            .Builder()
                            .detectDiskReads()
                            .detectDiskWrites()
                            .detectNetwork()
                            .penaltyLog()
                            .build())

            StrictMode.setVmPolicy(
                    StrictMode.VmPolicy
                            .Builder()
                            .detectLeakedSqlLiteObjects()
                            .detectLeakedClosableObjects()
                            .detectActivityLeaks()
                            .detectLeakedRegistrationObjects()
                            .penaltyLog()
                            .build())
        }
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            //Timber.plant(CrashReportingTree())
        }
    }
}