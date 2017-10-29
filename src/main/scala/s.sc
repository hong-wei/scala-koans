val r = List(1, 2, 3) match {
  case x :: y :: z::Nil => y
  case _ => 0
}