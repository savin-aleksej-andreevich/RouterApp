package ru.bmstu.messages;

public class StoreMessage {
    private final String id;
    private final String result;

    public StoreMessage (String id, String result) {
        this.id = id;
        this.result = result;
    }
    
    public String getId() {
        return id;
    }

    public String getResult() {
        return result;
    }
}
