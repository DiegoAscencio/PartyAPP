package com.example.ppm


import android.app.Application
import com.parse.Parse

class PPMApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Parse.initialize(
            Parse.Configuration.Builder(this)
                .applicationId("s77IEEYnBkCeozJKyl751SiELVPidCWRqNeioWbN")
                .clientKey("RvRzeWE1juDEoRWn0Mets3RZJXyaS6eJjbsaRDn4")
                .server("https://parseapi.back4app.com/")
                .build()
        )
    }
}