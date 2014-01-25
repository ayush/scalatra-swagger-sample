package com.oj.app.model

import java.util.Date

/**
 * @author ayush 
 * @since 1/25/14.
 */
case class Product(name: String, description: String, relatedProducts: List[Product])

case class ProductCatalog(name: String, publishedOn: Date, products: List[Product])

class Inventory {
  val orange = new Product("Orange", "Ripe Ones", Nil)
  val rawOrange = new Product("Raw Orange", "Plucked When Green", List(orange))

  val summerCatalog = new ProductCatalog("Summer 2013", new Date(), List(orange, rawOrange))
}

class WarrantyStatus(orderId: String, status: String)