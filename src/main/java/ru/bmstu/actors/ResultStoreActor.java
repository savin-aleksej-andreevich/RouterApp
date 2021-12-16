package ru.bmstu.actors;

import akka.actor.AbstractActor;
import akka.japi.pf.ReceiveBuilder;
import ru.bmstu.messages.GetMessage;
import ru.bmstu.messages.ResultMessage;
import ru.bmstu.messages.StoreMessage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ResultStoreActor extends AbstractActor {

    private Map<String, ArrayList<String>> resultMap = new HashMap<>();

    @Override
    public Receive createReceive() {
        return ReceiveBuilder.create()
                .match(GetMessage.class, this::getResult) // обработка сообщения (получить результат)
                .match(StoreMessage.class, this::storeResult) // обработка сообщения (загрузить результат)
                .build();
    }

    private void getResult (GetMessage msg) {
        String id = msg.getPackageID();
        ArrayList<String> result = resultMap.get(id);
        sender().tell(new ResultMessage(id, result), getContext().getParent());
    } // возвращает результат по id

    private void storeResult (StoreMessage result) {
        ArrayList<String> resultList = resultMap.get(result.getId());
        if (resultList == null) {
            resultList = new ArrayList<>();
            resultList.add(result.getResult());
            resultMap.put(result.getId(), resultList);
        }
        else {
            resultList.add(result.getResult());
        }
    }

}
