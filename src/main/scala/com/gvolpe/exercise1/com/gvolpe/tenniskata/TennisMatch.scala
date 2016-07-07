package com.gvolpe.exercise1.com.gvolpe.tenniskata

import com.gvolpe.exercise1.com.gvolpe.tenniskata.TennisMatch._

sealed trait PointDescription {
  def next: Option[PointDescription]
}
case object Love      extends PointDescription { val next = Some(Fifteen) }
case object Fifteen   extends PointDescription { val next = Some(Thirty) }
case object Thirty    extends PointDescription { val next = Some(Forty) }
case object Forty     extends PointDescription { val next = Some(Advantage) }
case object Advantage extends PointDescription { val next = None }

case class Player(name: String)

object TennisMatch {

  case class Score(p1: PointDescription, p2: PointDescription) {
    def p1Score: Option[Score] = this match {
      case Score(Advantage, Forty) |
           Score(Forty, _)           => None
      case Score(s1, s2)             => Some(Score(s1.next.get, s2))
    }
    def p2Score: Option[Score] = this match {
      case Score(Forty, Advantage) |
           Score(_, Forty)           => None
      case Score(s1, s2)             => Some(Score(s1, s2.next.get))
    }
  }

  case class GameState(score: Score, winner: Option[Player], history: Vector[Score])
}

class TennisMatch(p1: Player, p2: Player) {

  private var history: Vector[Score] = Vector(Score(Love, Love))
  private var winner: Option[Player] = None

  def playerOnePointScored(): Unit = history.last.p1Score match {
    case Some(score) => if (winner.isEmpty) history = history :+ score
    case None        => winner = Some(p1)
  }

  def playerTwoPointScored(): Unit = {
    history.last.p2Score match {
      case Some(score) => if (winner.isEmpty) history = history :+ score
      case None        => winner = Some(p2)
    }
  }

  def gameState: GameState = {
    val score = history.last
    GameState(score, winner, history)
  }

  def winnerAndOrScore: String = gameState.winner match {
    case Some(player) => s"The Winner is ${player.name}! - Final Score: ${gameState.score}"
    case None         => s"Current score: ${gameState.score}"
  }

}