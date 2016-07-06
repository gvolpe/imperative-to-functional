package com.gvolpe.exercise1

/**
  * Given a number of users the program should define which type of server
  * should be used.
  *
  * The following rules should be applied:
  *   N < 100               -> Server 1
  *   N >= 100 & < 1000     -> Server 2
  *   N >= 1000 & < 10000   -> Server 3
  *   N > 10000             -> Server not available
  *
  * Refactor the Imperative code to achieve the same goal in a functional way.
  * */
object Exercise1 {

  def main(args: Array[String]) {
    require(args.length == 1, "Number of users is mandatory!")
    require(args(0) forall Character.isDigit, "The input Must be a number!")

    val users = args(0).toInt

    val decider: ServerDecider = PartialFunctions1
    println(s"The server to use is: ${decider(users)}")
  }

}

trait ServerDecider {
  def apply(n: Int): String
}