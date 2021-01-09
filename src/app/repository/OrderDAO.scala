package app.repository

import app.entity.Produto
import app.entity.Produto
import java.time.LocalDateTime
import java.time.Month
import scala.math.BigDecimal
import app.entity.Item
import app.entity.Item
import app.entity.Item
import scala.collection.mutable.MutableList
import app.entity.Order
import app.entity.Item

class OrderDAO {

	val NOKIA_1100: String = "Nokia 1100"
	val SAMSUNG_A1: String = "Samsung A1"
	val I_PHONE_12: String = "Iphone 12"
	
	def createProducts() : List[Produto] = {
	  val recentDate = LocalDateTime.of(2018, Month.JANUARY, 29, 10, 30, 40)
	  val middleDate = LocalDateTime.of(2017, Month.NOVEMBER, 15, 9, 30, 40)
	  val farawayDate = LocalDateTime.of(2013, Month.MAY, 1, 13, 30, 40)
	  
	  val iphone = new Produto(I_PHONE_12, "Smartphone", recentDate, BigDecimal("1500"))
	  val samsungA1 = new Produto(SAMSUNG_A1, "Smartphone", middleDate, BigDecimal("1805.22"))
	  val nokia1100 = new Produto(NOKIA_1100, "Smartphone", farawayDate, BigDecimal("100.50"))
	  
	  List(iphone, samsungA1, nokia1100)
	}
	
	private def createItens() : MutableList[Item] = {
	  val produtos = createProducts();
	  var itens = MutableList[Item]()
	  produtos.foreach(prod => {
      val item: Item = new Item(prod.price, BigDecimal("50.0"), BigDecimal("75"), 1,prod)
      itens += item
	  })
	  itens
	}
	
	private def createOrdersIphone12(item: Item) : List[Order] = {
	  val dateOrder1 = LocalDateTime.of(2018, Month.FEBRUARY, 15, 15, 30, 40)
	  val order1 = new Order("Juccelino", "", "", BigDecimal("2500"), dateOrder1, Set(item))
	  
	  val dateOrder2 = LocalDateTime.of(2018, Month.MARCH, 16, 14, 30, 40)
	  val order2 = new Order("Rodrigues", "", "", BigDecimal("2700"), dateOrder2, Set(item))
	  
	  val dateOrder3 = LocalDateTime.of(2018, Month.MAY, 17, 13, 30, 40)
	  val order3 = new Order("Alves", "", "", BigDecimal("2233"), dateOrder3, Set(item))
	  
	  val dateOrder4 = LocalDateTime.of(2018, Month.JULY, 18, 19, 30, 40)
	  val order4 = new Order("de", "", "", BigDecimal("1800.56"), dateOrder4, Set(item))
	  
	  val dateOrder5 = LocalDateTime.of(2018, Month.SEPTEMBER, 19, 11, 30, 40)
	  val order5 = new Order("Barros", "", "", BigDecimal("1900.12"), dateOrder5, Set(item))
	  
	  val dateOrder6 = LocalDateTime.of(2018, Month.DECEMBER, 20, 5, 30, 40)
	  val order6 = new Order("Juka", "", "", BigDecimal("2600"), dateOrder6, Set(item))
	  
	  List(order1, order2, order3, order4, order5, order6)
	}
	
	private def createOrdersSamsung(item: Item) : List[Order] = {
	  val dateOrder1 = LocalDateTime.of(2018, Month.FEBRUARY, 15, 15, 30, 40)
	  val order1 = new Order("Will", "", "", BigDecimal("1100"), dateOrder1, Set(item))
	  
	  val dateOrder2 = LocalDateTime.of(2018, Month.MARCH, 16, 14, 30, 40)
	  val order2 = new Order("Bob", "", "", BigDecimal("1300"), dateOrder2, Set(item))
	  
	  val dateOrder3 = LocalDateTime.of(2018, Month.MAY, 17, 13, 30, 40)
	  val order3 = new Order("Francisca", "", "", BigDecimal("1554"), dateOrder3, Set(item))
	  
	  val dateOrder4 = LocalDateTime.of(2018, Month.JULY, 18, 19, 30, 40)
	  val order4 = new Order("Juliana", "", "", BigDecimal("900"), dateOrder4, Set(item))
	  
	  List(order1, order2, order3, order4)
	}
	
	private def createOrdersNokia(item: Item) : List[Order] = {
	  val dateOrder1 = LocalDateTime.of(2018, Month.APRIL, 15, 15, 30, 40)
	  val order1 = new Order("Mickey", "", "", BigDecimal("200"), dateOrder1, Set(item))
	  
	  val dateOrder2 = LocalDateTime.of(2018, Month.OCTOBER, 16, 14, 30, 40)
	  val order2 = new Order("Donald", "", "", BigDecimal("250"), dateOrder2, Set(item))
	  
	  List(order1, order2)
	}
	
	def loadData() : List[Order] = {
	  val itens = createItens()
	  var orders = List[Order]()
	  itens.foreach(item => {
	    if (item.product.name.equals(I_PHONE_12)) {
	    	orders ++= this.createOrdersIphone12(item)
	    	
	    } else if (item.product.name.equals(SAMSUNG_A1)) {
	      orders ++= this.createOrdersSamsung(item)
	    } else {
	      orders ++= this.createOrdersNokia(item)
	    }
	  
	  })
	 orders
	}


}