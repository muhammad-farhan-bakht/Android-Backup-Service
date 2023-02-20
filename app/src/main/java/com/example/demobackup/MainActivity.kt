package com.example.demobackup

import android.app.backup.BackupManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.demobackup.databinding.ActivityMainBinding
import com.example.demobackup.utils.Constant.KEY_PREF_STRING
import com.example.demobackup.utils.PrefKtx.getPref
import com.example.demobackup.utils.PrefKtx.putAnyThing
import com.google.android.material.snackbar.Snackbar

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initListener()
        backupString()
    }

    private fun initListener() {
        binding.btnSave.setOnClickListener {
            binding.tvString.text = binding.etString.text
            updateBackupString(binding.tvString.text.toString())
            Snackbar.make(binding.root, "new value is saved!", Snackbar.LENGTH_SHORT).show()
        }
    }

    private fun backupString() {
        val savedString = getPref()?.getString(KEY_PREF_STRING,null)
        Log.d(TAG,"savedString $savedString")
        savedString?.let {
            binding.tvString.text = it
        }
    }

    private fun updateBackupString(newString: String) {
        getPref()?.putAnyThing(KEY_PREF_STRING,newString)
        BackupManager(this).dataChanged()
    }
}
