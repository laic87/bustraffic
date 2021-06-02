package se.sbab.busbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Result {

    @JsonProperty("LineNumber")
    private String lineNumber;

    @JsonProperty("JourneyPatternPointNumber")
    private String journeyPatternPointNumber;

    public Result() { }

    public Result(String lineNumber, String journeyPatternPointNumber) {
        this.lineNumber = lineNumber;
        this.journeyPatternPointNumber = journeyPatternPointNumber;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Result result = (Result) o;
        return Objects.equals(lineNumber, result.lineNumber) && Objects.equals(journeyPatternPointNumber, result.journeyPatternPointNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lineNumber, journeyPatternPointNumber);
    }

    @Override
    public String toString() {
        return "Result{" +
                "lineNumber='" + lineNumber + '\'' +
                ", journeyPatternPointNumber='" + journeyPatternPointNumber + '\'' +
                '}';
    }
}
