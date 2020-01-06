package alex.com.challenges

/**
 * Created by Alex Doub on 1/5/2020.
 * https://leetcode.com/problems/3sum/
 */

class ThreeSum {
    companion object {

        /**
        Approach 1: Make table of pairs, do final enumeration searching for last pair
        n^2 pair matching, n^2/nlogn final enumeration

        Correct but not fast enough
         */
        fun threeSum_0(nums: IntArray): List<List<Int>> {
            //do n^2 enumeration to build list of compliments
            val compliments = HashMap<Int, ArrayList<Pair<Int, Int>>>()
            nums.forEachIndexed { i1, x1 ->
                (i1 until nums.size).forEach { i2 ->
                    val x2 = nums[i2]
                    if (i2 > i1) {
                        val sum = x1 + x2
                        if (compliments[sum] == null) {
                            compliments[sum] = ArrayList<Pair<Int, Int>>()
                        }
                        compliments[sum]!!.add(Pair(i1, i2))
                        println("$x1 & $x2 sum to $sum")
                    }
                }
            }

            println("Done with phase 1")

            //do final n enumeration to check for matches
            val resultSet = emptySet<List<Int>>().toMutableSet()
            nums.forEachIndexed { i, x ->

                println(".. Enumerating nums. Compliment for $x has ${compliments[0 - x]?.size ?: "null"}")

                compliments[0 - x]?.forEach { pair ->
                    if (pair.first != i && pair.second != i) {
                        resultSet.add(listOf(x, nums[pair.first], nums[pair.second]).sorted())
                        println(".. .. did add match: ${listOf(x, nums[pair.first], nums[pair.second]).sorted().toString()}")
                    }
                }
            }

            return resultSet.toList()
        }


        /**
         *  Approach 2: Loop input once, do sliding window check (2 sum) for compliment
         *
         *  Must use result set or else will get duplicates
         *
         *  Runtime: NLogN sort + (loop x sliding window == N^2)
         *  RunSpace: 1 per pair, but limited by uniqueness...
         *          If all input was same = 1
         *          If all input was different = list / 3
         * */
        fun threeSum(nums: IntArray): List<List<Int>> {

            val results = mutableSetOf<List<Int>>()

            nums.sort()
            nums.forEachIndexed { xi, x ->
                var front = xi + 1
                var back = nums.size - 1

                while (front < back) {
                    val sum = x + nums[front] + nums[back]
                    when {
                        sum == 0 -> {
                            val entry = listOf(nums[front], nums[back], x).sorted()
                            results.add(entry)
                            front++
                        }
                        sum < 0 -> front++
                        else -> back--
                    }
                }
            }

            return results.toList()
        }


        private fun printDebug(string: String) {
            if (false) println(string)
        }
    }
}
