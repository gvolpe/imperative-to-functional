package com.gvolpe.exercise1

object PatternMatching1 extends ServerDecider {
  override def apply(n: Int): String =
    findServer(n).getOrElse("Server not available")

  private def findServer(n: Int): Option[String] = n match {
    case v if v < 100   => Some("1")
    case v if v < 1000  => Some("2")
    case v if v < 10000 => Some("3")
    case _              => None
  }
}
