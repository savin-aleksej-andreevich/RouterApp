package ru.bmstu;

import akka.actor.ActorRef;
import akka.http.impl.engine.client.PoolConductor;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.util.Timeout;
import ru.bmstu.messages.GetMessage;

import java.time.Duration;
import java.util.concurrent.Future;

public class MainHttp {
    private final ActorRef router;

    public MainHttp (ActorRef router) {
        this.router = router;
    }
    public Route createRoute () {
        return route {
            get(() -> parameter("pachageId", (id) -> {
                Future<Object> result = Patterns.ask(router, new GetMessage(id), Timeout.create(Duration.ofSeconds(10)));
                return CompleteOK
            })
        }
    }
}
