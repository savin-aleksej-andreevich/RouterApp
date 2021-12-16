package ru.bmstu.actors;

import akka.actor.AbstractActor;
import akka.actor.ActorRef;
import akka.actor.Props;
import akka.routing.ActorRefRoutee;
import akka.routing.Routee;
import akka.routing.Router;

import java.util.ArrayList;
import java.util.List;

public class AppRouterActor extends AbstractActor {
    public static int EXECUTORS_COUNT = 5;
    private final Router router;
    private final ActorRef storeActor;

    public AppRouterActor() {
        storeActor = getContext().actorOf(Props.create(ResultStoreActor.class), "ResultStorage"); // создается ResultStorage
        List<Routee> routees = new ArrayList<>();
        for (int i = 0; i < EXECUTORS_COUNT; i++) { //Создается 5 executor и 
            ActorRef executor = getContext().actorOf(Props.create(ExecuteTestActor.class));
            getContext().watch(executor);
            routees.add(new ActorRefRoutee(executor));
        }
        router
    }

    @Override
    public Receive createRecive () {

    }

    private void executeJSON() {

    }

}
