import com.oj.app.endpoint._
import org.scalatra._
import javax.servlet.ServletContext

class ScalatraBootstrap extends LifeCycle {
  override def init(context: ServletContext) {
    implicit val swagger = new OjSwagger

    context.mount(new OjProductsEndpoint, "/api/products/*")
    context.mount(new OjServicesEndpoint, "/api/services/*")

    // Mount the same OjServicesEndpoint at an alternate path without slashes
    context.mount(new OjServicesEndpointAlt, "/api_services/*")

    context.mount(new ResourcesApp, "/api-docs/*")

  }
}
