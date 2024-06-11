package com.aj.znab.core

import android.app.Application
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.database

class App: Application() {
    companion object {
        lateinit var auth: FirebaseAuth
        lateinit var znabDB: DatabaseReference
    }

    override fun onCreate() {
        super.onCreate()

        auth = Firebase.auth
        znabDB = Firebase.database.reference
    }
}