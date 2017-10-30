import scala.annotation.tailrec //importing annotation!
@tailrec
def fact(i: BigInt, accumulator: BigInt): BigInt = { // This is an accumulator to ensure tail recursion!
  if (i <= 1) // 4,1 
    accumulator
  else
    fact(i - 1, i * accumulator)//3,1 //2,1//1,1
}

def factorial(i: BigInt): BigInt = {
  fact(i, 1)//2,1
}


factorial(2)