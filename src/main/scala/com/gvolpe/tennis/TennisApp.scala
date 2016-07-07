package com.gvolpe.tennis

object TennisApp extends App {
  val p1 = Player("p1")
  val p2 = Player("p2")

  val tennisMatch = new TennisMatch(p1, p2)

  println(tennisMatch.winnerAndOrScore)

  tennisMatch.playerOnePointScored()

  println(tennisMatch.winnerAndOrScore)

  tennisMatch.playerOnePointScored()
  tennisMatch.playerTwoPointScored()

  println(tennisMatch.winnerAndOrScore)

  tennisMatch.playerTwoPointScored()
  tennisMatch.playerTwoPointScored()
  tennisMatch.playerTwoPointScored()

  println(tennisMatch.winnerAndOrScore)
}
