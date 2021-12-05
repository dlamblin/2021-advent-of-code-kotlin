import java.io.File
import java.math.BigInteger
import java.security.MessageDigest

/**
 * Reads lines from the given input txt file.
 */
fun readInput(name: String) = File("src", "$name.txt").readLines()

/**
 * Converts string to md5 hash.
 */
fun String.md5(): String = BigInteger(1, MessageDigest.getInstance("MD5").digest(toByteArray())).toString(16)

val digits = Regex("""\d+""")

/**
 * Converts a list of strings to a list of lists of integers
 */
fun List<String>.toListOfInts(): List<List<Int>> {
    return map { s ->
        digits.findAll(s).map{ mr ->
            mr.value.toInt()
        }.toList() }

}