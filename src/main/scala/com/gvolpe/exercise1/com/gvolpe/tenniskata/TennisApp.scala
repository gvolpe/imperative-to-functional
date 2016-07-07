package com.gvolpe.exercise1.com.gvolpe.tenniskata

object TennisApp extends App {
  val p1 = Player("p1")
  val p2 = Player("p2")

  val tennisMatch = new TennisMatch(p1, p2)

  println(tennisMatch.gameState)

  tennisMatch.playerOnePointScored()

  println(tennisMatch.gameState)

  tennisMatch.playerOnePointScored()
  tennisMatch.playerTwoPointScored()

  println(tennisMatch.gameState)

  tennisMatch.playerOnePointScored()

  println(tennisMatch.gameState)

  tennisMatch.playerOnePointScored()

  println(tennisMatch.gameState)
}
