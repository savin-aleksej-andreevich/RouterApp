package ru.bmstu.messages;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Test {
    @JsonProperty("testName");
    private final String testName;

    @JsonProperty("expectedResult")
    private final String expectedResult;

    @JsonProperty("params")
    private final Arraylist<Object> params;


}
