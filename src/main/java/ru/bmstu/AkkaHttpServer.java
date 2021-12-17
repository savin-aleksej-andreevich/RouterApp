package ru.bmstu;

import akka.NotUsed;
import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.http.javadsl.Http;
import akka.http.javadsl.ServerBinding;
import akka.http.javadsl.model.HttpRequest;
import akka.http.javadsl.model.HttpResponse;
import akka.stream.ActorMaterializer;
import akka.stream.javadsl.Flow;
import ru.bmstu.actors.AppRouterActor;

import java.util.concurrent.CompletionStage;

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
        final Flow<HttpRequest, HttpResponse, NotUsed> routeFlow = instance.createRoute().flow(system, materializer);
        final CompletionStage<ServerBinding> buinding = http.bindAndHandle(
                routeFlow,
                connectHttp.toHost(HOST, PORT),
                materializer
        );
        System.out.println("Server online at http://" + HOST + ":" + PORT + "/\nPress return to stop...");
        System.in.read();
        buinding.thenCompose(ServerBinding::unbind).thenAccept(undound -> system.terminate());
    }
}
