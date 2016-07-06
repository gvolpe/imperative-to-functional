package com.gvolpe.exercise1

object PartialFunctions1 extends ServerDecider {
  type ServerRule = PartialFunction[Int, Option[String]]

  private val rule1: ServerRule = {
    case v if v < 100 => Some("1")
  }

  private val rule2: ServerRule = {
    case v if v < 1000 => Some("2")
  }

  private val rule3: ServerRule = {
    case v if v < 10000 => Some("3")
  }

  private val defaultRule: ServerRule = {
    case _ => None
  }

  private val serverRules = rule1 orElse rule2 orElse rule3 orElse defaultRule

  override def apply(n: Int): String = {
    serverRules(n).getOrElse("Server not available")
  }
}
