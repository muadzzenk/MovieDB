package com.example.assesmentmitra

import androidx.multidex.MultiDexApplication
import com.example.assesmentmitra.di.networkModule
import com.example.assesmentmitra.di.viewModelModule
import com.jakewharton.threetenabp.AndroidThreeTen
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import java.util.*

class MainApplication : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        AndroidThreeTen.init(this)
        startKoin {

            androidContext(this@MainApplication)
            //CoreComponent
            modules(
                    networkModule,
                    viewModelModule
            )
        }
    }
}
