package com.aj.znab.core.util

sealed class Task <T> (val data: T? = null, val errorMessage: String? = null){
    class Success <T> (data: T): Task <T> (data)
    class Failure <T> (errorMessage: String): Task <T> (errorMessage = errorMessage)
    class InProgress <T> : Task <T> ()
}