package app.service

import java.time.LocalDateTime
import app.repository.OrderDAO
import app.repository.OrderDAO
import app.entity.Order
import scala.collection.mutable.MutableList
import scala.collection.mutable.HashMap
import java.time.temporal.ChronoUnit
import java.time.YearMonth
import scala.collection.mutable.Map
import scala.collection.mutable.LinkedHashMap

class OrderService {

	val _12_MONTHS: String = ">12 months"
	val _7_12_MONTHS: String = "7-12 months"
	val _4_6_MONTHS: String = "4-6 months"
	val _1_3_MONTHS: String = "1-3 months"

	var dao : OrderDAO = new OrderDAO()
	var allOrders : List[Order] = dao.loadData()

	def filterOrdersBySpecificInterval(monthInit: Integer, monthEnd: Integer) : scala.collection.Map[String, Integer] = {

			var numberOfOrders: Long = allOrders.flatMap(order => order.itens.filter(item => 
			ChronoUnit.MONTHS.between(
					YearMonth.from(item.product.creationDate), 
					YearMonth.from(order.dateOrder)) >= Integer2int(monthInit)
					&& 
					ChronoUnit.MONTHS.between(
							YearMonth.from(item.product.creationDate), 
							YearMonth.from(order.dateOrder)) <= Integer2int(monthEnd))).length

					Map(""+monthInit+"-" + monthEnd + " months" -> Integer2int(numberOfOrders.intValue()))

	}
	
	def filterAllOrders (dateInit: LocalDateTime, dateEnd: LocalDateTime) : Map[String, Integer] = {
	  var result: Map[String, Integer] = LinkedHashMap(_1_3_MONTHS -> 0, _4_6_MONTHS -> 0, _7_12_MONTHS -> 0, _12_MONTHS -> 0)

	  var filteredOrders: List[Order] = allOrders
	    .filter(order => order.dateOrder.isAfter(dateInit) && order.dateOrder.isBefore(dateEnd))
	  
    if (!filteredOrders.isEmpty) {
      filteredOrders.foreach(order => order.itens.foreach(item => {
    	  val monthsBTWCreationAndOrder: Long = ChronoUnit.MONTHS.between(
    			  YearMonth.from(item.product.creationDate), 
    			  YearMonth.from(order.dateOrder))

    	  if (monthsBTWCreationAndOrder < 4) {
    		  result.update(_1_3_MONTHS, result(_1_3_MONTHS) + 1)
    	  } else if (monthsBTWCreationAndOrder >= 4 && monthsBTWCreationAndOrder <= 6) {
    	    result.update(_4_6_MONTHS, Integer2int(result(_4_6_MONTHS)) + 1)
    	  } else if (monthsBTWCreationAndOrder > 6 && monthsBTWCreationAndOrder <= 12) {
    	    result.update(_7_12_MONTHS, Integer2int(result(_7_12_MONTHS)) + 1)
    	  } else {
    		  // > 12
    	    result.update(_12_MONTHS, Integer2int(result(_12_MONTHS)) + 1)
    	  }
      }));

    }
	  
		result
	}
	
}