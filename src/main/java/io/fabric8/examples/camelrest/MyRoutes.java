package io.fabric8.examples.camelrest;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.cdi.ContextName;

@ContextName("myCdiRestCamelContext")
public class MyRoutes extends RouteBuilder {

    @Override
    public void configure() throws Exception {
        restConfiguration().component("netty4-http").host("0.0.0.0").port(8080);

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
