package ru.bmstu.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

public class JsonMessage {
    @JsonProperty("packageId")
    private final String packageId;

    @JsonProperty("jsScript")
    private final String jsScript;

    @JsonProperty("functionName")
    private final String functionName;

    @JsonProperty("tests")
    private final ArrayList<Test> tests;

    @JsonProperty("packageId")
    public String getPackageId() {
        return packageId;
    }

    @JsonProperty("jsScript")
    public String getJsScript() {
        return jsScript;
    }

    @JsonProperty("functionName")
    public String getFunctionName() {
        return functionName;
    }
    
    @JsonProperty("tests")
    public ArrayList<Test> getTests() {
        return tests;
    }
}
