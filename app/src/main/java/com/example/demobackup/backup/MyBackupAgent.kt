package com.example.demobackup.backup

import android.app.backup.BackupAgent
import android.app.backup.BackupDataInput
import android.app.backup.BackupDataOutput
import android.os.ParcelFileDescriptor
import com.example.demobackup.utils.Constant.KEY_PREF_STRING
import com.example.demobackup.utils.PrefKtx.getPref
import com.example.demobackup.utils.PrefKtx.putAnyThing

class MyBackupAgent : BackupAgent() {

    override fun onBackup(
        oldState: ParcelFileDescriptor?,
        data: BackupDataOutput?,
        newState: ParcelFileDescriptor?
    ) {
        // Save the string value to the backup
        val myString = getPref()?.getString(KEY_PREF_STRING, null)
        if (myString != null) {
            val bytes = myString.toByteArray(Charsets.UTF_8)
            data?.writeEntityHeader(KEY_PREF_STRING, bytes.size)
            data?.writeEntityData(bytes, bytes.size)
        }
    }

    override fun onRestore(data: BackupDataInput?, appVersionCode: Int, newState: ParcelFileDescriptor?) {
        // Restore the string value from the backup
        while (data?.readNextHeader() == true) {
            val key = data.key
            val dataSize = data.dataSize
            if (key == KEY_PREF_STRING) {
                val dataBytes = ByteArray(dataSize)
                data.readEntityData(dataBytes, 0, dataSize)
                val myString = String(dataBytes, Charsets.UTF_8)
                getPref()?.putAnyThing(KEY_PREF_STRING,myString)
            }
            data.skipEntityData()
        }
    }
}