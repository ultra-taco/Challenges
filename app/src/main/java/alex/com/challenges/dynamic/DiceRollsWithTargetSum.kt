package alex.com.challenges.dynamic

/**
 * Created by Alex Doub on 11/25/2019.
 * https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
 */

class DiceRollsWithTargetSum {
    companion object {
        fun numRollsToTarget(d: Int, f: Int, target: Int): Int {

            val mod = 1_000_000_007
            val solutionGraph = Array<IntArray>(d) { IntArray(target + 1) }

            // Base case
            (1..f).forEach {
                if (it < solutionGraph[0].size) {
                    solutionGraph[0][it] = 1
                }
            }

//            println("Finished base case. SolutionGraph = ${solutionGraph[0].joinToString()}")

            // For each dice, fill out a sum row
            (1 until solutionGraph.size).forEach { dice ->

                //For every face on the die, propagate new sums for this dice
                (1..f).forEach { face ->

                    // Enumerate calculated rows,
                    solutionGraph[dice - 1].forEachIndexed { index, value ->
                        if (index + face < solutionGraph[dice].size) {
                            solutionGraph[dice][index + face] += value
                            solutionGraph[dice][index + face] = solutionGraph[dice][index + face] % mod
                        } else {
//                            println("Skipped value that computed to: ${index + face}")
                        }
                    }
                }
//                println("Finished new level. SolutionGraph = ${solutionGraph[dice].joinToString()}")
            }

            return solutionGraph[d - 1][target]
        }
    }
}

//Build 2D array
//Rows = Solutions with this dice
//Columns = with this dice, heres how many options can add up to this