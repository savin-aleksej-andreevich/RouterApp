package ru.bmstu.messages;

import java.util.ArrayList;

public class ResultMessage {
    private final String id;
    private final ArrayList<String> result;

    public ResultMessage(String id, ArrayList<String> result) {
        this.id = id;
        this.result = result;
    }

    public String getId() {
        return id;
    }

    public ArrayList<String> getResult() {
        return result;
    }
}
