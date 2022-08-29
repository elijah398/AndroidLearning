package com.elijah.androidlearning.util

import android.os.Build
import androidx.annotation.RequiresApi
import java.io.Closeable
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths

object FileUtil {

    const val FILE_TYPE_XML = ".xml"

    @RequiresApi(Build.VERSION_CODES.O)
    fun getFileNameList(fileDir: String, fileType: String) : ArrayList<Path> {
        val fileNameList = ArrayList<Path>()
        val resourcesPath = Paths.get(fileDir, "")

        try {
            Files.walk(resourcesPath)
                .filter { item -> Files.isRegularFile(item) }
                .filter { item -> item.toString().endsWith(fileType) }
                .forEach { item -> fileNameList.add(item) }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return fileNameList
    }
}