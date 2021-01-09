package app.service

import java.time.LocalDateTime
import app.repository.OrderDAO
import app.repository.OrderDAO
import app.entity.Order
import scala.collection.mutable.MutableList
import scala.collection.Map
import scala.collection.mutable.HashMap
import java.time.temporal.ChronoUnit
import java.time.YearMonth

class OrderService {

	val _12_MONTHS: String = ">12 months"
			val _7_12_MONTHS: String = "7-12 months"
			val _4_6_MONTHS: String = "4-6 months"
			val _1_3_MONTHS: String = "1-3 months"

			var dao : OrderDAO = new OrderDAO()
			var allOrders : List[Order] = dao.loadData()

			def filterOrdersBySpecificInterval(monthInit: Integer, monthEnd: Integer) : Map[String, Integer] = {

					var numberOfOrders: Long = allOrders.flatMap(order => order.itens.filter(item => 
					ChronoUnit.MONTHS.between(
							YearMonth.from(item.product.creationDate), 
							YearMonth.from(order.dateOrder)) >= monthInit 
							&& 
							ChronoUnit.MONTHS.between(
									YearMonth.from(item.product.creationDate), 
									YearMonth.from(order.dateOrder)) <= monthEnd)).length

							Map(""+monthInit+"-" + monthEnd + " months" -> numberOfOrders.intValue())

			}

}