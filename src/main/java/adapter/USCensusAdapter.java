package adapter;

import Exception.*;
import dao.CensusDAO;
import dto.CSVCensusUS;

import java.util.Map;

public class USCensusAdapter extends CensusAdapter {
    @Override
    public Map<String, CensusDAO> CensusCSVData(String... csvFilePath) throws CSVException, StateCensusAnalyserException {
        return super.CensusCSVData(CSVCensusUS.class, csvFilePath[0]);
    }
}
