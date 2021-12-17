package ru.bmstu.messages;

import java.util.ArrayList;

public class ExecutionMessage {
    private final String packageID;
    private final String functionName;
    private final String jsScript;
    private final String expectedResult;
    private final String testName;
    private final ArrayList<Object> params;

    public ExecutionMessage (String packageID, String functionName, String jsScript, String expectedResult, String testName, ArrayList<Object> params) {
        this.packageID = packageID;
        this.functionName = functionName;
        this.jsScript = jsScript;
        this.expectedResult = expectedResult;
        this.testName = testName;
        this.params = params;
    }

    public String getPackageID() {
        return packageID;
    }

    public String getFunctionName() {
        return functionName;
    }

    public String getJsScript() {
        return jsScript;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public String getTestName() {
        return testName;
    }

    public ArrayList<Object> getParams() {
        return params;
    }
}
