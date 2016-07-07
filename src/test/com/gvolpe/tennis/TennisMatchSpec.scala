package com.gvolpe.tennis

import org.scalatest.{FlatSpecLike, Matchers}

class TennisMatchSpec extends FlatSpecLike with Matchers {

  it should "get the correct score and give player 1 as a winner" in new TennisMatchFixture {
    val game = new TennisMatch(p1, p2)
    game.gameState.score should be (Score(Love, Love))

    game.playerOnePointScored()
    game.gameState.score should be (Score(Fifteen, Love))

    game.playerOnePointScored()
    game.gameState.score should be (Score(Thirty, Love))

    game.playerTwoPointScored()
    game.gameState.score should be (Score(Thirty, Fifteen))

    game.playerOnePointScored()
    game.gameState.score should be (Score(Forty, Fifteen))
    game.gameState.winner should be (None)

    game.playerOnePointScored()
    game.gameState.winner should be (Some(p1))

    println(game.winnerAndOrScore)
  }

  it should "get the correct score and give player 2 as a winner given a deuce game" in new TennisMatchFixture {
    val game = aDeuceGame
    game.gameState.score should be (Score(Forty, Forty))

    game.playerTwoPointScored()
    game.gameState.score should be (Score(Forty, Advantage))
    game.gameState.winner should be (None)

    game.playerTwoPointScored()
    game.gameState.winner should be (Some(p2))

    println(game.winnerAndOrScore)
  }

  it should "get the correct score, apply the correct transitions and give player 1 as a winner given a deuce game" in new TennisMatchFixture {
    val game = aDeuceGame
    game.gameState.score should be (Score(Forty, Forty))

    game.playerTwoPointScored()
    game.gameState.score should be (Score(Forty, Advantage))

    game.playerOnePointScored()
    game.gameState.score should be (Score(Forty, Forty))

    game.playerOnePointScored()
    game.gameState.score should be (Score(Advantage, Forty))

    game.playerTwoPointScored()
    game.gameState.score should be (Score(Forty, Forty))

    game.playerOnePointScored()
    game.gameState.score should be (Score(Advantage, Forty))

    game.playerOnePointScored()
    game.gameState.winner should be (Some(p1))

    println(game.winnerAndOrScore)
  }

}

trait TennisMatchFixture {
  val p1 = Player("p1")
  val p2 = Player("p2")

  def aDeuceGame: TennisMatch = {
    val game = new TennisMatch(p1, p2)
    game.playerOnePointScored()
    game.playerOnePointScored()
    game.playerOnePointScored()
    game.playerTwoPointScored()
    game.playerTwoPointScored()
    game.playerTwoPointScored()
    game
  }
}
