package com.gvolpe.tennis

sealed trait PointDescription {
  def next: Option[PointDescription]
}
case object Love      extends PointDescription { val next = Some(Fifteen) }
case object Fifteen   extends PointDescription { val next = Some(Thirty) }
case object Thirty    extends PointDescription { val next = Some(Forty) }
case object Forty     extends PointDescription { val next = Some(Advantage) }
case object Advantage extends PointDescription { val next = None }

case class Player(name: String)
case class Score(p1: PointDescription, p2: PointDescription)
case class GameState(score: Score, winner: Option[Player], history: Vector[Score])

class TennisMatch(p1: Player, p2: Player) {

  private var history: Vector[Score] = Vector(Score(Love, Love))
  private var winner: Option[Player] = None

  def playerOnePointScored(): Unit = history.last match {
    case Score(Advantage, Forty) | Score(Forty, Love | Fifteen | Thirty) =>
      winner = Some(p1)
    case Score(Forty, Advantage) =>
      history = history :+ Score(Forty, Forty)
    case Score(s1, s2) =>
      val newScore = s1.next.map(Score(_, s2))
      if (winner.isEmpty) newScore.foreach(s => history = history :+ s)
  }

  def playerTwoPointScored(): Unit = history.last match {
    case Score(Forty, Advantage) | Score(Love | Fifteen | Thirty, Forty) =>
      winner = Some(p2)
    case Score(Advantage, Forty) =>
      history = history :+ Score(Forty, Forty)
    case Score(s1, s2) =>
      val newScore = s2.next.map(Score(s1, _))
      if (winner.isEmpty) newScore.foreach(s => history = history :+ s)
  }

  def gameState: GameState = GameState(history.last, winner, history)

  def winnerAndOrScore: String = gameState.winner match {
    case Some(player) => s"The Winner is ${player.name}! - Final Score: ${gameState.score}"
    case None         => s"Current score: ${gameState.score}"
  }

}