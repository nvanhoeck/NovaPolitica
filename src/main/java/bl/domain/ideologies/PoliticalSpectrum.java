package bl.domain.ideologies;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class PoliticalSpectrum {
    private final int id;
    private Map<Integer, TopicResult> politicalResult;

    public PoliticalSpectrum(int id) {
        this.id = id;
        this.politicalResult = new HashMap<>();
    }

    public int getId() {
        return id;
    }

    public Map<Integer, TopicResult> getPoliticalResult() {
        return politicalResult;
    }

    public void setPoliticalResult(Map<Integer, TopicResult> politicalResult) {
        this.politicalResult = politicalResult;
    }
}
