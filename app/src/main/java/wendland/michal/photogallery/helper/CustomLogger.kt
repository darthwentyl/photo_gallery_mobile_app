package wendland.michal.photogallery.helper

import android.util.Log
import wendland.michal.photogallery.BuildConfig

object CustomLogger {
    private lateinit var tag: String
    private lateinit var method: String

    init {
        if (BuildConfig.DEBUG) {
            var traceElem = Thread.currentThread().stackTrace[3]
            tag = traceElem.className
            method = traceElem.methodName + "(): "
        }
    }

    fun d(message: String) {
        Log.d(tag, method + message)
    }

    fun w(message: String) {
        Log.w(tag, method + message)
    }

    fun logMethod() {
        Log.d(tag, method)
    }

}