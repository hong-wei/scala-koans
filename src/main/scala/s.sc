val lst = List(1, 2, 3)


for {
  i <- lst
  j <- {println(1);List(i*2)}
  m <- {println(2);List(j+1)}
} yield
  {println(3);m}



lst // 1 2 3
  .map { x =>  x * 2 } // list(2,4,6), need run all these first ,and then run second 
  .map { x => x + 1 } // list(3,5,7)