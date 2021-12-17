package ru.bmstu;

import java.time.Duration;
import java.util.concurrent.Future;

import akka.actor.ActorRef;
import akka.http.impl.engine.client.PoolConductor;
import akka.http.javadsl.marshallers.jackson.Jackson;
import akka.http.javadsl.marshalling.Marshaller;
import akka.http.javadsl.model.RequestEntity;
import akka.http.javadsl.server.Route;
import akka.pattern.Patterns;
import akka.util.Timeout;
import static akka.http.javadsl.server.Directives.*;

import ru.bmstu.messages.GetMessage;
import bmstu.ru.messages.JsonMessage;
import ru.bmstu.messages.JsonMessage;

public class MainHttp {
    private final ActorRef router;

    public MainHttp (ActorRef router) {
        this.router = router;
    }
    public Route createRoute () {
        return route (
            get(() -> parameter("pachageId", (id) -> {
                Future<Object> result = Patterns.ask(router, new GetMessage(id), Timeout.create(Duration.ofSeconds(10)));
                return CompleteOKWithFuture(result, Jackson.marshaller());
            })),
            post(() -> entity((Jackson.unmarshaller(JsonMessage.class), (jsonMessage) -> {
                router.tell(jsonMessage, ActorRef.noSender());
                return complete("Test started");
            }))
        );
    }

    private Route CompleteOKWithFuture(Future<Object> result, Marshaller<Object, RequestEntity> marshaller) {
    }
}
