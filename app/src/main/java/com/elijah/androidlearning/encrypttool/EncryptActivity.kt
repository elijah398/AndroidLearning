package com.elijah.androidlearning.encrypttool

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.elijah.androidlearning.R
import kotlinx.android.synthetic.main.activity_encrypt.*
import java.io.IOException

class EncryptActivity : AppCompatActivity() {

    private val RELEASE_SERVER_KEY = "puzXeT3yJGDAJ2TubrLIUt6yGX7KwaEG"
    private val KEY_ACCOUNT_DUID = "KEY_ACCOUNT_DUID"
    private var data: String = ""
    private val listener = View.OnClickListener { v ->
        when (v.id) {
            R.id.encrypt -> encryptData()
            R.id.decrypt -> decryptData()
            R.id.keyStoreEncrypt -> keyStoreEncryptData()
            R.id.keyStoreDecrypt -> keyStoreDecryptData()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_encrypt)
        findViewById<View>(R.id.encrypt).setOnClickListener(listener)
        findViewById<View>(R.id.decrypt).setOnClickListener(listener)
        findViewById<View>(R.id.keyStoreEncrypt).setOnClickListener(listener)
        findViewById<View>(R.id.keyStoreDecrypt).setOnClickListener(listener)
    }

    private fun encryptData(): ByteArray {
        val inputText = editData.text.toString()
        var encryptedData: ByteArray = AESCryptUtil.encryptDataWithKey(inputText.toByteArray(), RELEASE_SERVER_KEY)
        val displayText = "encryptedData is $encryptedData"
        decryptDataText.text = displayText
        return encryptedData
    }

    private fun decryptData() {
        try {
            val encryptedData = encryptData()
            val decryptedData = AESCryptUtil.decryptDataWithKey(encryptedData, RELEASE_SERVER_KEY)
            decryptDataText.text = "decryptedData is $decryptedData"
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    private fun keyStoreEncryptData() {
        val inputText = editData.text.toString()
        var encryptedData: String = KeyStoreEncryptUtils.getInstance().encryptString(inputText, KEY_ACCOUNT_DUID)
        data = encryptedData
        val displayText = "encryptedData is $encryptedData"
        decryptDataText.text = displayText
    }

    private fun keyStoreDecryptData() {
        try {
            val decryptedData = KeyStoreEncryptUtils.getInstance().decryptString(data, KEY_ACCOUNT_DUID)
            decryptDataText.text = "decryptedData is $decryptedData"
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}