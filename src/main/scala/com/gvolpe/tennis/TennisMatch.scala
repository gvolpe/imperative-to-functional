package com.gvolpe.tennis

import com.gvolpe.tennis.TennisMatch._

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
    def p1PointScored: Option[Score] = (p1, p2) match {
      case (Advantage, Forty) |
           (Forty, _)           => None
      case (s1, s2)             => s1.next.map(s => Score(s, s2))
    }
    def p2PointScored: Option[Score] = (p1, p2) match {
      case (Forty, Advantage) |
           (_, Forty)           => None
      case (s1, s2)             => s2.next.map(s => Score(s1, s))
    }
  }

  case class GameState(score: Score, winner: Option[Player], history: Vector[Score])
}

class TennisMatch(p1: Player, p2: Player) {

  private var history: Vector[Score] = Vector(Score(Love, Love))
  private var winner: Option[Player] = None

  def playerOnePointScored(): Unit = history.last.p1PointScored match {
    case Some(score) => if (winner.isEmpty) history = history :+ score
    case None        => winner = Some(p1)
  }

  def playerTwoPointScored(): Unit = {
    history.last.p2PointScored match {
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