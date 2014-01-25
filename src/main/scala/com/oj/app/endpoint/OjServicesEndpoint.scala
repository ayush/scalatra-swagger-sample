package com.oj.app.endpoint

import org.scalatra.swagger.Swagger
import com.oj.app.model.WarrantyStatus
import org.scalatra.BadRequest

/**
 * @author ayush 
 * @since 1/25/14.
 */
class OjServicesEndpoint(implicit val swagger: Swagger)  extends SwaggerApiEndpoint {
  override protected val applicationName = Some("api/services")
  protected val applicationDescription = "Services API"


  val warrantyStatus =
    (apiOperation[WarrantyStatus]("getWarrantyStatus")
      summary "Get Warranty Status"
      notes "Gets status of warranty for passed order id"
      parameter queryParam[String]("orderId").required.description("Order Id"))

  get("/warranty-status", operation(warrantyStatus)) {
    params.get("orderId") match {
      case Some(orderId) => new WarrantyStatus(orderId, "active")
      case _ => BadRequest("orderId not passed")
    }

  }
}

// Exact same endpoint as above but will be pointed on a different path
class OjServicesEndpointAlt(implicit override val swagger: Swagger) extends OjServicesEndpoint {
  override protected val applicationName = Some("api_services")
  override protected val applicationDescription = "Alt Services API"

}