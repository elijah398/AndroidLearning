package com.elijah.androidlearning.xmlparser

import android.annotation.SuppressLint
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresApi
import com.elijah.androidlearning.R
import org.w3c.dom.Document
import java.io.File

class XMLParserActivity : AppCompatActivity() {

    private var mDoc: Document? = null

    private val listener = View.OnClickListener { v ->
        when (v.id) {
            R.id.parse_xml_to_doc -> parseXmlToDoc()
            R.id.build_doc_to_xml -> buildDocToXml()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_xmlparser)
        findViewById<View>(R.id.parse_xml_to_doc).setOnClickListener(listener)
        findViewById<View>(R.id.build_doc_to_xml).setOnClickListener(listener)
    }

    @SuppressLint("ResourceType")
    @RequiresApi(Build.VERSION_CODES.O)
    fun parseXmlToDoc() {
        try {
            val xmlIutDir = this.filesDir.absolutePath
            val xmlFileInputStream = File(xmlIutDir+"/activity_thread.xml").inputStream()
            mDoc = XmlUtil.xmlDomParser(xmlFileInputStream)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun buildDocToXml() {
        try {
            val xmlPutDir = this.filesDir.absolutePath.toString()
            var xmlOutFile = File("$xmlPutDir/ConvertedOut.xml")
            mDoc?.let { XmlUtil.xmlBuilder(it, xmlOutFile) }
            xmlOutFile.createNewFile()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}