package app

import app.service.OrderService
import scala.collection.Map
import scala.collection.mutable.HashMap

object Application {
  
  def main(args: Array[String]): Unit = {
    
    var result: Map[String, Integer] = new HashMap[String, Integer]()
    val service : OrderService = new OrderService()
    if (args.length == 1) {
			var months: Array[String] = args(0).split("-")
			println("**** Group: " + args(0))
			result = filterByMonthsInterval(months(0), months(1))
		}
    
    if (!result.isEmpty) {
      result.foreach(res => println(res))
    } else {
      println("Not found")
    }
  }
  
  private def filterByMonthsInterval(init: String, end: String): Map[String, Integer] =
		  try {
			      val initMonth: Integer = int2Integer(Integer.parseInt(init))
					  val endMonth: Integer = int2Integer(Integer.parseInt(end))
					  val service: OrderService = new OrderService()
					  service.filterOrdersBySpecificInterval(initMonth, endMonth)
		  } catch {
		  case e: NumberFormatException => {
			  println("*** error: cannot parse string to integer. More info: "+ e.getMessage)
			  null
		  }

		  }

  
}