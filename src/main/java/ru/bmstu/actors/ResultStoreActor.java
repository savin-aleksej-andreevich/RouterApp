package ru.bmstu.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import ru.bmstu.messages.GetMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultStoreActor extends AbstractActor {

    private Map<String, ArrayList<String>> resultMap = new HashMap<>();

    @Override
    public createReceive() {
        return ReceiveBuilder.create()
                .match(GetMessage.class, this::getResult)
    }

    private void getResult (GetMessage msg) {
        String id = msg.getPackageID();
        ArrayList<String> result = resultMap.get(id);
        sender().tell(new ResultMessage (id, result), getContext().getParent());
    }

}
