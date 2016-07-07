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
    def p1Score: Option[Score] = (p1, p2) match {
      case (Advantage, Forty) |
           (Forty, _)           => None
      case (s1, s2)             => Some(Score(s1.next.get, s2))
    }
    def p2Score: Option[Score] = (p1, p2) match {
      case (Forty, Advantage) |
           (_, Forty)           => None
      case (s1, s2)             => Some(Score(s1, s2.next.get))
    }
  }

  case class GameState(score: Score, winner: Option[Player], history: Vector[Score])
}

class TennisMatch(p1: Player, p2: Player) {

  private var history: Vector[Score] = Vector(Score(Love, Love))
  private var winner: Option[Player] = None

  private def pointScored(isPlayerOne: Boolean) = isPlayerOne match {
    case true =>
      val lastScore = history.last
      lastScore.p1Score match {
        case Some(score) => history = history :+ score
        case None        => winner = Some(p1)
      }
    case false =>
      val lastScore = history.last
      lastScore.p2Score match {
        case Some(score) => history = history :+ score
        case None        => winner = Some(p2)
      }
      //require(winner.isEmpty, s"There is already a winner in the game: ${winner.get}")
  }

  def playerOnePointScored(): Unit = pointScored(isPlayerOne = true)

  def playerTwoPointScored(): Unit = pointScored(isPlayerOne = false)

  def gameState: GameState = {
    val score = history.last
    GameState(score, winner, history)
  }

}