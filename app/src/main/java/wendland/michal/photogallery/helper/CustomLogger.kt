package wendland.michal.photogallery.helper

import android.util.Log
import wendland.michal.photogallery.BuildConfig

object CustomLogger {
    private lateinit var tag: String
    private lateinit var method: String

    private fun setCurrent() {
        if (BuildConfig.DEBUG) {
            var traceElem = Thread.currentThread().stackTrace[4]
            tag = traceElem.className
            method = traceElem.methodName + "(): "
        }
    }

    fun d(message: String) {
        setCurrent()
        Log.d(tag, method + message)
    }

    fun w(message: String) {
        setCurrent()
        Log.w(tag, method + message)
    }

    fun logMethod() {
        setCurrent()
        Log.d(tag, method)
    }

}