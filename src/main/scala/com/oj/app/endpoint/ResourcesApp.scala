package com.oj.app.endpoint

import org.scalatra.swagger.{ApiInfo, NativeSwaggerBase, Swagger}

import org.scalatra.ScalatraServlet
import org.json4s.{DefaultFormats, Formats}

/**
 * @author ayush 
 * @since 1/25/14.
 */
class ResourcesApp(implicit val swagger: Swagger) extends ScalatraServlet with NativeSwaggerBase {
//  implicit override val jsonFormats: Formats = DefaultFormats

}

class OjSwagger extends Swagger("1.2", "1", new ApiInfo("oj.com api", "api to access oj.com's features", "http://oj.com/terms", "team@oj.com", "http://oj.com/license", "http://oj.com/license"))
