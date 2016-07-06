imperative-to-functional
========================

### Exercise 1

Given a number of users the program should define which type of server should be used.

The following rules should be applied:
  *   N < 100               -> Server 1
  *   N >= 100 & < 1000     -> Server 2
  *   N >= 1000 & < 10000   -> Server 3
  *   N > 10000             -> Server not available

Refactor the Imperative code to achieve the same goal in a functional way.
