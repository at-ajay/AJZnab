package com.aj.znab.auth.data

import com.aj.znab.core.App

class AuthApi {

    fun loadInitialData(userId: String) {
        App.znabDB.child(userId).setValue("Ajay")
    }

}