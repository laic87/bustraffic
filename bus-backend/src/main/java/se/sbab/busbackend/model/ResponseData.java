package se.sbab.busbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseData {

    @JsonProperty("Result")
    private List<Result> result;

    public ResponseData() { }

    public ResponseData(List<Result> result) {
        this.result = result;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
}
