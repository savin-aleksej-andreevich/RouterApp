package ru.bmstu;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.stream.ActorMaterializer;
import ru.bmstu.actors.AppRouterActor;

public class AkkaHttpServer {
    public static String SYSTEM_NAME = "appRouter";
    public static String ROUTER_NAME = "router";
    public static String HOST = "localhost";
    public static int PORT = 8080;

    public static void main(String[] args) {
        ActorSystem system = ActorSystem.create(SYSTEM_NAME);
        ActorRef router = system.actorOf(Props.create(AppRouterActor.class), ROUTER_NAME);
        final Http http = Http.get(system);
        final ActorMaterializer materializer = ActorMaterializer.create(system);
        MainHttp instance = new MainHttp(router);
    }
}
