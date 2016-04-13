package com.example.server;

import com.github.kristofa.brave.Brave;
import ratpack.server.RatpackServer;
import ratpack.zipkin.ServerTracingModule;

import static ratpack.guice.Guice.registry;

/**
 * RatPack Server.
 */
public class App {
  public static void main(String[] args) throws Exception {
    Integer serverPort = Integer.parseInt(System.getProperty("port", "8080"));
    RatpackServer.start(server ->
        server
            .serverConfig(config -> config.port(serverPort))
            .registry(registry(binding ->
                binding
                    .module(ServerTracingModule.class)
                    .bindInstance(ServerTracingModule.config()
                                                     .withBrave(new Brave.Builder("ratpack-demo")
                                                         .build()))
            ))
            .handlers(handler ->
                handler.all(ctx -> ctx.render("Hello world"))
            )
    ).start();
  }

}
