package iotchain.core.model;

import java.util.List;

public class TxLogEntry {
    private String loggerAddress;
    private List<String> logTopics;
    private String data;

    public String getLoggerAddress() {
        return loggerAddress;
    }

    public void setLoggerAddress(String loggerAddress) {
        this.loggerAddress = loggerAddress;
    }

    public List<String> getLogTopics() {
        return logTopics;
    }

    public void setLogTopics(List<String> logTopics) {
        this.logTopics = logTopics;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
