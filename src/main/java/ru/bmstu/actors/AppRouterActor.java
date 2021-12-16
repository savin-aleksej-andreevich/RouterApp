package ru.bmstu.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.japi.pf.ReceiveBuilder;
import akka.routing.ActorRefRoutee;
import akka.routing.RoundRobinRoutingLogic;
import akka.routing.Routee;
import akka.routing.Router;
import ru.bmstu.messages.GetMessage;
import ru.bmstu.messages.JsonMessage;
import ru.bmstu.messages.ResultMessage;

import java.util.ArrayList;
import java.util.List;

public class AppRouterActor extends AbstractActor {
    public static int EXECUTORS_COUNT = 5;
    private final Router router; //определяется router, который занимается рассылкой сообщений
    private final ActorRef storeActor; // определяется хранилище результатов

    public AppRouterActor() {
        storeActor = getContext().actorOf(Props.create(ResultStoreActor.class), "ResultStorage"); // создается ResultStorage
        List<Routee> routees = new ArrayList<>();
        for (int i = 0; i < EXECUTORS_COUNT; i++) { //Создается 5 executor и мониторятся
            ActorRef executor = getContext().actorOf(Props.create(ExecuteTestActor.class));
            getContext().watch(executor);
            routees.add(new ActorRefRoutee(executor));
        }
        router = new Router(new RoundRobinRoutingLogic(), routees); // создается router из routees
    }

    @Override
    public Receive createRecive () {
        return ReceiveBuilder.create()
                .match(GetMessage.class, message -> {storeActor.tell(message, sender());})
                .match(JsonMessage.class, this::executeJSON)
                .build();
    }

    private void executeJSON(JsonMessage jsonMessage) {
        for (Test test : jsonMessage.getTests()){

        }
    }

}
