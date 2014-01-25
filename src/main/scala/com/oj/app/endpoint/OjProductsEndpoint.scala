package com.oj.app.endpoint

import org.scalatra.swagger.Swagger
import com.oj.app.model.{Inventory, ProductCatalog}


class OjProductsEndpoint(implicit val swagger: Swagger) extends SwaggerApiEndpoint {
  override protected val applicationName = Some("api/products")
  protected val applicationDescription = "Products API"

  val getCatalog =
    (apiOperation[ProductCatalog]("getCatalog")
      summary "Get Catalog"
      notes "Gets complete catalog")

  get("/catalog", operation(getCatalog)) {
    new Inventory().summerCatalog
  }

  val findProducts =
    (apiOperation[List[Product]]("findProducts")
      summary "Get Matching Products"
      notes "Gets all products matching passed string"
      parameter pathParam[String]("keyword").required.description("String to search on"))

  get("/find-product/:keyword", operation(findProducts)) {
    new Inventory().summerCatalog.products.filter((p) => (p.name + ' ' + p.description).contains(params.getOrElse("keyword", "")))
  }

}
