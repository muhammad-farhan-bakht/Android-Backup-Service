package com.example.demobackup.backup

import android.app.backup.BackupAgentHelper
import android.app.backup.SharedPreferencesBackupHelper
import com.example.demobackup.utils.Constant.PREFS_BACKUP_KEY
import com.example.demobackup.utils.Constant.PREFS_FILENAME

class MyBackupAgent : BackupAgentHelper() {

    override fun onCreate() {
        super.onCreate()
        val prefsHelper = SharedPreferencesBackupHelper(this, PREFS_FILENAME)
        addHelper(PREFS_BACKUP_KEY, prefsHelper)
    }
}