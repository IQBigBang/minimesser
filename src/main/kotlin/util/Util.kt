package util

import java.io.InputStream

fun Any.loadResource(name: String): InputStream? =
    this.javaClass.getResourceAsStream(name)

fun InputStream.readToString(): String {
    val build = StringBuilder()
    val buf = ByteArray(1024)
    var length: Int
    while (this.read(buf).also { length = it } != -1) {
        build.append(String(buf, 0, length))
    }
    return build.toString()
}