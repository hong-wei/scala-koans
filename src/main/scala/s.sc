import scala.collection.mutable

val mySet = Set("Michigan", "Ohio", "Wisconsin", "Iowa")
val aNewSet = mySet + "Illinois"+"123"

val mySetM =  mutable.Set("Michigan", "Ohio", "Wisconsin", "Iowa")
mySetM += "123" +"1234"

mySetM