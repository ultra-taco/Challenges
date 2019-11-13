package alex.com.challenges

import org.junit.Assert.assertEquals
import org.junit.Test

class BinaryMatrixTest {
    @Test
    fun test1() {

        val actual = BinaryMatrix.reconstructMatrix(2, 1, intArrayOf(1, 1, 1))
        val expected1 = listOf(listOf(1, 1, 0), listOf(0, 0, 1))
        val expected2 = listOf(listOf(1, 0, 1), listOf(0, 1, 0))
        val expected3 = listOf(listOf(1, 0, 0), listOf(1, 0, 0))

        assert(actual == expected1 || actual == expected2 || actual == expected3)
    }

    @Test
    fun test2() {
        assertEquals(listOf<List<Int>>(), BinaryMatrix.reconstructMatrix(2, 3, intArrayOf(2,2,1,1)))
    }

    @Test
    fun test3() {
        val expected = listOf(listOf(1,1,1,0,1,0,0,1,0,0), listOf(1,0,1,0,0,0,1,1,0,1))
        assertEquals(expected, BinaryMatrix.reconstructMatrix(5, 5, intArrayOf(2,1,2,0,1,0,1,2,0,1)))
    }

    @Test
    fun test4_Obstacle1_unsolvable_input() {
        val topLimit = 53
        val botLimit = 82
        //input of 100 -- quickly gets to end state then works backwards...
        // my stress test is O(1 or 2 * n)
        // this stress test is insane in comparison

        // this sums to 92
        val input = intArrayOf(2,0,2,1,1,2,2,0,1,1,1,0,0,0,0,2,1,2,2,2,1,1,0,0,0,0,1,2,1,0,1,0,2,0,0,0,0,2,1,1,2,1,1,0,0,1,0,0,1,1,2,0,1,1,1,2,1,0,0,1,1,1,2,2,1,0,0,1,1,1,0,2,1,2,1,0,1,2,0,2,2,1,0,1,1,1,2,0,0,0,1,2,2,0,0,1,1,2,1,2)

        val solved = BinaryMatrix.reconstructMatrix(topLimit, botLimit, input)
        assert(solved.isEmpty())
    }

    @Test
    fun test5_Obstacle2() {
        val topLimit = 99
        val botLimit = 102
        // input size 200 = scales out of control
        val input = intArrayOf(2,1,1,1,1,2,0,2,2,2,0,1,0,0,2,1,1,1,2,2,1,2,1,1,1,1,2,0,1,2,1,1,2,2,1,0,2,0,1,0,0,1,0,1,0,1,2,1,0,1,1,0,2,1,1,0,1,0,1,0,1,0,1,1,2,1,1,2,2,1,1,2,2,2,0,1,1,0,0,1,1,1,1,0,0,2,1,1,1,2,1,1,2,1,0,1,2,0,1,1,2,2,2,1,1,2,2,2,0,2,1,2,2,1,0,1,1,1,1,0,1,1,1,2,0,1,0,2,1,2,1,1,2,1,1,2,1,1,1,1,0,2,0,1,0,0,1,1,1,0,1,1,0,0,2,0,0,1,1,1,0,0,2,2,1,1,1,1,1,1,0,2,1,1,0,1,2,1,2,0,1,0,1,1,1,0,1,1,0,0,0,0,1,2,2,2,1,1,0,2)

        var zeros = 0
        var ones = 0
        var twos = 0
        input.forEach {

            when (it) {
                0 -> zeros += 1
                1 -> ones += 1
                2 -> twos += 1
                else -> throw RuntimeException("AAAAAAAAAA")
            }
        }

        val solved = BinaryMatrix.reconstructMatrix(topLimit, botLimit, input)
        assert(solved.isEmpty())
    }

    @Test
    fun stressTest2() {
        //25 = 16.0s
        //23 = 4.33s
        //22 = 2.37s
        //20 = 1.4s
        val repeatCount = 23 // x6.
        val totalLimit = 6 * repeatCount
        val topLimit = totalLimit / 2
        val botLimit = totalLimit / 2

        //001122 repeating times 10
        val input = ArrayList<Int>()
        (1..repeatCount).forEach {
            input.addAll(listOf(0,0,1,1,2,2))
        }

        val solved = BinaryMatrix.reconstructMatrix(topLimit, botLimit, input.toTypedArray().toIntArray())
        assert(solved.isNotEmpty())
    }
}
//Thoughts: Need more efficient way