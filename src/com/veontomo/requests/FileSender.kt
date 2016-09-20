package com.veontomo.requests

import java.io.File
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import java.nio.file.Files
import java.nio.file.Paths

/**
 * Send a file to a given server
 */
class FileSender() {
    val filelist = mutableListOf<String>()

    fun enqueue(vararg filenames: String) {
        for (filename in filenames){
            if (File(filename).exists()){
                filelist.add(filename)
                println("File $filename is enqueued.")
            } else {
                println("File $filename is not found.")
            }
        }
    }

    fun send(): Int {
        val url = "http://192.168.5.95/news/save/image/abc/efg"
        val charset = "UTF-8"
        val param = "value"
        val textFile = File(".\\data\\foo.txt")
        val binaryFile = File(".\\data\\foo.txt")
        val boundary = "aslskfsdki9jjjdjdj99ddk"
        val CRLF = "\r\n" // Line separator required by multipart/form-data.

        val connection: URLConnection = URL(url).openConnection()
        connection.doOutput = true
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary)

        val output: OutputStream = connection.outputStream
        val writer: PrintWriter = PrintWriter(OutputStreamWriter(output, charset), true)
        // Send normal param.
        writer.append("--" + boundary).append(CRLF)
        writer.append("Content-Disposition: form-data; name=\"param\"").append(CRLF)
        writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF)
        writer.append(CRLF).append(param).append(CRLF).flush()

        // Send text file.
        writer.append("--" + boundary).append(CRLF)
        writer.append("Content-Disposition: form-data; name=\"textFile\"; filename=\"" + textFile.name + "\"").append(CRLF)
        writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF) // Text file itself must be saved in this charset!
        writer.append(CRLF).flush()
        Files.copy(textFile.toPath(), output)
        output.flush() // Important before continuing with writer!
        writer.append(CRLF).flush() // CRLF is important! It indicates end of boundary.

        // Send binary file.
        writer.append("--" + boundary).append(CRLF)
        writer.append("Content-Disposition: form-data; name=\"binaryFile\"; filename=\"" + binaryFile.getName() + "\"").append(CRLF)
        writer.append("Content-Type: " + URLConnection.guessContentTypeFromName(binaryFile.name)).append(CRLF)
        writer.append("Content-Transfer-Encoding: binary").append(CRLF)
        writer.append(CRLF).flush()
        Files.copy(binaryFile.toPath(), output)
        output.flush() // Important before continuing with writer!
        writer.append(CRLF).flush() // CRLF is important! It indicates end of boundary.

        // End of multipart/form-data.
        writer.append("--" + boundary + "--").append(CRLF).flush()

        // Request is lazily fired whenever you need to obtain information about response.
        val responseCode = (connection as HttpURLConnection).responseCode
        return responseCode // Should be 200
    }
}
