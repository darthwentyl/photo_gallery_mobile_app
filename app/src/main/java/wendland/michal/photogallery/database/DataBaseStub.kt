package wendland.michal.photogallery.database

import android.content.Context
import android.widget.Toast
import wendland.michal.photogallery.helper.CustomLogger

class DataBaseStub(private val context: Context): IDataBase {
    override fun sync(): Boolean {
        CustomLogger.logMethod()
        Toast.makeText(context,
            "Synchronization with database will be implemented",
            Toast.LENGTH_SHORT).show()
        return true
    }
}