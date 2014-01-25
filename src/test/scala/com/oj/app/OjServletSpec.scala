package com.oj.app

import org.scalatra.test.specs2._
import com.oj.app.endpoint._
import org.json4s._
import org.json4s.native.JsonMethods._

class OjServletSpec extends ScalatraSpec {
  // Define implicit for Endpoint instantiation
  implicit val swagger = new OjSwagger
  // implicit to parse json
  implicit val f = org.json4s.DefaultFormats

  // Mount endpoints
  addServlet(new OjProductsEndpoint(), "/api/products/*")
  addServlet(new OjServicesEndpoint(), "/api/services/*")
  addServlet(new OjServicesEndpointAlt(), "/api_services/*")
  addServlet(new ResourcesApp(), "/api-docs/*")

  def is = s2"""
  Swagger Endpoints should
    work for root resources endpoint $checkResourcesEndpoint
    return status 200 for endpoint without slash $serviceWithoutSlash
    return valid operation method $checkOperationMethod
    return status 200 for endpoint with slash $serviceWithSlash
  """

  def checkResourcesEndpoint = get("/api-docs") {
    status must_== 200
  }

  def serviceWithoutSlash = get("/api-docs/api_services") {
    status must_== 200
  }

  def checkOperationMethod = get("/api-docs/api_services") {
    val json = parse(response.body)
    val opNick = ((json \ "apis" \ "operations")(0) \ "nickname").extract[String]
    (opNick must_== "getWarrantyStatus").and(((json \ "apis" \ "operations")(0) \ "method").extract[String].toLowerCase must_== "get")
  }

  def serviceWithSlash = get("/api-docs/api/services") {
    status must_== 200
  }
}
