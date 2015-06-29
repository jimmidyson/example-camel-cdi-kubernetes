package io.fabric8.examples.camelrest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

import javax.enterprise.context.ApplicationScoped;

@ContextName("myCdiRestCamelContext")
@ApplicationScoped
public class MyRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().component("netty4-http").port(8080);

        rest("say")
                .get("/hello").to("direct:hello")
                .get("/bye").consumes("application/json").to("direct:bye")
                .post("/bye").to("mock:update");

        from("direct:hello")
                .transform().constant("Hello World");
        from("direct:bye")
                .transform().constant("Bye World");
    }

}
