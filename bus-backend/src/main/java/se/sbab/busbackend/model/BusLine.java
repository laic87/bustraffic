package se.sbab.busbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class BusLine {
    public BusLine(String lineNumber, String journeyPatternPointNumber) {
        this.lineNumber = lineNumber;
        this.journeyPatternPointNumber = journeyPatternPointNumber;
    }

    @JsonProperty("LineNumber")
    private String lineNumber;

    @JsonProperty("JourneyPatternPointNumber")
    private String journeyPatternPointNumber;

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getJourneyPatternPointNumber() {
        return journeyPatternPointNumber;
    }

    public void setJourneyPatternPointNumber(String journeyPatternPointNumber) {
        this.journeyPatternPointNumber = journeyPatternPointNumber;
    }
}
