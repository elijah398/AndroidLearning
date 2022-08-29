package com.elijah.androidlearning.xmlparser

import org.w3c.dom.Document
import org.xml.sax.SAXException
import java.io.File
import java.io.IOException
import java.io.InputStream
import javax.xml.parsers.DocumentBuilder
import javax.xml.parsers.DocumentBuilderFactory
import javax.xml.parsers.ParserConfigurationException
import javax.xml.transform.OutputKeys
import javax.xml.transform.TransformerException
import javax.xml.transform.TransformerFactory
import javax.xml.transform.dom.DOMSource
import javax.xml.transform.stream.StreamResult

object XmlUtil {

    private const val DOCTYPE_DISALLOW  = "http://apache.org/xml/features/disallow-doctype-decl"
    private const val PRETTY_PRINT = "{http://xml.apache.org/xslt}indent-amount"

    fun xmlDomParser(xmlFileInputStream: InputStream): Document? {
        var doc: Document? = null

        try {
            val builderFactory = DocumentBuilderFactory.newInstance().apply {
                //setFeature(DOCTYPE_DISALLOW, true)
                isNamespaceAware = true
            }
            val docBuilder: DocumentBuilder = builderFactory.newDocumentBuilder()
            doc = docBuilder.parse(xmlFileInputStream)
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: ParserConfigurationException) {
            e.printStackTrace()
        } catch (e: SAXException) {
            e.printStackTrace()
        }

        return doc
    }

    fun xmlBuilder(doc: Document, xmlOutFile: File) {
        try {
            val transformer = TransformerFactory.newInstance().newTransformer()

            // ==== Start: Pretty print
            // https://stackoverflow.com/questions/139076/how-to-pretty-print-xml-from-java
            transformer.setOutputProperty(OutputKeys.INDENT, "yes")
            transformer.setOutputProperty(PRETTY_PRINT, "2")
            // ==== End: Pretty print

            transformer.transform(DOMSource(doc), StreamResult(xmlOutFile))
        }  catch (e: TransformerException) {
            e.printStackTrace()
        }
    }
}