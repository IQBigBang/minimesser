package util

import java.io.File
import java.io.InputStream
import java.io.OutputStream

class Resource internal constructor(val stream: InputStream) {
    fun transferTo(out: OutputStream): Long = stream.transferTo(out)

    fun copyToFile(path: String) = transferTo(File(path).outputStream())
    fun copyToFile(parent: String, child: String) = transferTo(File(parent, child).outputStream())

    fun readToString(): String {
        val build = StringBuilder()
        val buf = ByteArray(1024)
        var length: Int
        while (stream.read(buf).also { length = it } != -1) {
            build.append(String(buf, 0, length))
        }
        return build.toString()
    }
}

fun Any.loadResource(name: String): Resource? =
    javaClass.getResourceAsStream(name)?.let { Resource(it) }
