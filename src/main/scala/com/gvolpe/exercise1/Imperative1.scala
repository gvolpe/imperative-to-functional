package com.gvolpe.exercise1

object Imperative1 extends ServerDecider {
  override def apply(n: Int): String = {
    if (n < 100) "1"
    else if (n < 1000) "2"
    else if (n < 10000) "3"
    else "Server not available"
  }
}
