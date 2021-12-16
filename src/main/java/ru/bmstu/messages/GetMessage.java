package ru.bmstu.messages;

public class GetMessage {
    private final String packageID;

    public GetMessage (String packageID) {
        this.packageID = packageID;
    }

    public String getPackageID() {
        return packageID;
    }
}
