package app.entity

import java.time.LocalDateTime

class Order (var customerName: String, 
    var customerContact: String,
    var shippingAddress: String,
    var grandTotal: BigDecimal,
    var dateOrder: LocalDateTime,
    var itens: Set[Item]) {
  
}