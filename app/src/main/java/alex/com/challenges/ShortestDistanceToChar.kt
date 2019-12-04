package alex.com.challenges

/**
 * Created by Alex Doub on 12/3/2019.
 * https://leetcode.com/problems/shortest-distance-to-a-character/
 */

class ShortestDistanceToChar {
    companion object {
        private fun debugPrint(string: String) {
            if (false) println(string)
        }

        fun shortestToChar(S: String, C: Char): IntArray {

            // Find indexes of C
            val indexes = mutableListOf<Int>()
            S.forEachIndexed { index, c ->
                if (c == C) {
                    indexes.add(index)
                }
            }

            val solution = IntArray(S.length)
            S.forEachIndexed { index, _ ->

                debugPrint("Checking char at : ${index}. index count: ${indexes.size}")

                //Smart loop: If next is smaller, remove prev. If next is higher, stop looping
                var keepGoing = true
                var min: Int? = null
                var ptr = 0
                while (keepGoing) {
                    val thatIndex = indexes[ptr]
                    val thatMin = kotlin.math.abs(thatIndex - index)

                    // Found a new min
                    if (min == null || thatMin <= min) {

                        //purge old min
                        if (min != null) {
                            indexes.removeAt(0)
                            debugPrint("Found new min, removing old index")
                        }

                        // Keep going or stop
                        if (ptr < indexes.size - 1) {
                            ptr += 1
                        } else {
                            keepGoing = false
                        }

                        min = thatMin
                    }
                    // New number is higher, stop
                    else {
                        keepGoing = false
                    }
                }
                solution[index] = min!!
            }
            return solution
        }

        fun shortestToChar_SLOW(S: String, C: Char): IntArray {

            // Find indexes of C
            val indexes = mutableListOf<Int>()
            S.forEachIndexed { index, c ->
                if (c == C) {
                    indexes.add(index)
                }
            }

            val solution = IntArray(S.length)
            S.forEachIndexed { index, _ ->
                solution[index] = indexes.map { i -> kotlin.math.abs(i - index) }.min()!!
            }

            return solution
        }
    }
}