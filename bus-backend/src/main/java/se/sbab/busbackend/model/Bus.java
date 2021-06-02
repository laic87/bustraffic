package se.sbab.busbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Bus {

    @JsonProperty("LineNumber")
    private String lineNumber;

    @JsonProperty("DirectionNumber")
    private String directioNumber;

    @JsonProperty("JourneyPatternPointNumber")
    private String journeyPatternPointNumber;

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getDirectioNumber() {
        return directioNumber;
    }

    public void setDirectioNumber(String directioNumber) {
        this.directioNumber = directioNumber;
    }

    public String getJourneyPatternPointNumber() {
        return journeyPatternPointNumber;
    }

    public void setJourneyPatternPointNumber(String journeyPatternPointNumber) {
        this.journeyPatternPointNumber = journeyPatternPointNumber;
    }
}
