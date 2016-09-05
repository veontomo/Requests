package com.veontomo.requests

import org.json.simple.JSONObject
import java.io.IOException
import java.io.OutputStream
import java.net.HttpURLConnection
import java.net.URL
//import com.github.salomonbrys.kotson.*

/**
 * Manage the server.
 *
 * The class contains methods that change the server settings.
 *
 */
class ServerConfigurator(val host: String) {
    /**
     * Perform a PUT request with given data in the body of the request.
     * @param url resource name, relative to {@link #host}
     * @param data a map containing data to be passed to the server
     */
    fun putData(uri: String, data: JSONObject) {
        val url: URL = URL(host + uri)
        try {
            val connection: HttpURLConnection = url.openConnection() as HttpURLConnection;
            connection.doOutput = true
            connection.requestMethod = "PUT"
            connection.setRequestProperty("Accept-Charset", "ASCII")
            connection.addRequestProperty("User-Agent", "Mozilla/4.06 [en] (WinNT; I)")
            connection.setRequestProperty("Content-Type", "application/json")
            val out: OutputStream = connection.outputStream
            out.write(mapToByteArray(data))
            out.close()
            connection.inputStream
            val responseCode = connection.responseCode
            println("response code: $responseCode")

        } catch (ex: IOException) {
            println("Attention: exception when connecting to $uri ${ex.message}");
//            ex.printStackTrace();
        }
    }

    /**
     * Convert the hash map into a UTF-8 byte array.
     * @param data
     * @return a byte array with UTF-8 encoding
     */
    private fun mapToByteArray(data: JSONObject): ByteArray {
        return data.toString().toByteArray(Charsets.UTF_8)
    }
}
