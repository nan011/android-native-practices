package id.ui.ac.cs.mobileprogramming.nandhikaprayoga

import id.ui.ac.cs.mobileprogramming.nandhikaprayoga.activities.Lab2Activity
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class Lab2Test {
    @Test
    fun ableToReverseTextWithEvenLength() {
        val original = "nandhika"
        val reversed = "akihdnan"
        assertEquals(Lab2Activity.reverseString(original), reversed)
    }

    @Test
    fun ableToReverseTextWithOddLength() {
        val original = "prayoga"
        val reversed = "agoyarp"
        assertEquals(Lab2Activity.reverseString(original), reversed)
    }

    @Test
    fun ableToReverseEmptyString() {
        val original = ""
        assertEquals(Lab2Activity.reverseString(original), original)
    }

    @Test
    fun twiceReverse() {
        val original = "nandhika prayoga"
        assertEquals(Lab2Activity.reverseString(Lab2Activity.reverseString(original)), original)
    }
}