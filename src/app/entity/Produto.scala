package app.entity

import java.time.LocalDateTime

class Produto (var name: String, 
    var category: String,
    var creationDate: LocalDateTime,
    var price: BigDecimal){
}