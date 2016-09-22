package com.veontomo.requests

import java.io.File
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.io.PrintWriter
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLConnection
import java.nio.file.Files

/**
 * Send a file to a given server
 */
class FileSender() {
    fun uploadIImage(url: String, imageFileName: String): String {
        val charset = "UTF-8"
        val param = "value"
//        val textFile = File(".\\data\\foo.txt")
        val binaryFile = File(imageFileName)
        if (!binaryFile.exists()) {
            return "File $imageFileName is not found."
        }
        val boundary = "some-random-string-123456"
        val CRLF = "\r\n" // Line separator required by multipart/form-data.

        val connection: URLConnection = URL(url).openConnection()
        connection.doOutput = true
        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + boundary)

        val outputStream: OutputStream = connection.outputStream
        val writer: PrintWriter = PrintWriter(OutputStreamWriter(outputStream, charset), true)
        // Send normal param.
        writer.append("--" + boundary).append(CRLF)
        writer.append("Content-Disposition: form-data; name=\"param\"").append(CRLF)
        writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF)
        writer.append(CRLF).append(param).append(CRLF).flush()

        // Send text file.
//        writer.append("--" + boundary).append(CRLF)
//        writer.append("Content-Disposition: form-data; name=\"textFile\"; filename=\"" + textFile.name + "\"").append(CRLF)
//        writer.append("Content-Type: text/plain; charset=" + charset).append(CRLF) // Text file itself must be saved in this charset!
//        writer.append(CRLF).flush()
//        Files.copy(textFile.toPath(), output)
//        output.flush() // Important before continuing with writer!
//        writer.append(CRLF).flush() // CRLF is important! It indicates end of boundary.

        // Send binary file.
        writer.append("--" + boundary).append(CRLF)
        writer.append("Content-Disposition: form-data; name=\"binaryFile\"; filename=\"${binaryFile.name}\"").append(CRLF)
        val type = URLConnection.guessContentTypeFromName(binaryFile.name)
        writer.append("Content-Type: $type").append(CRLF)
        writer.append("Content-Transfer-Encoding: binary").append(CRLF)
        writer.append(CRLF).flush()
        Files.copy(binaryFile.toPath(), outputStream)
        outputStream.flush() // Important before continuing with writer!
        writer.append(CRLF).flush() // CRLF is important! It indicates end of boundary.

        // End of multipart/form-data.
        writer.append("--$boundary--").append(CRLF).flush()

        // Request is lazily fired whenever you need to obtain information about response.
        val conn = connection as HttpURLConnection
        val responseCode = conn.responseCode
        val reader = conn.inputStream.reader()
        val outputString = reader.readLines().joinToString{ it }
        return "code: $responseCode, message: ${conn.responseMessage}, output: $outputString"
    }

}
