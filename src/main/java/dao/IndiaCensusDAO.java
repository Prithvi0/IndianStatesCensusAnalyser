package dao;

import dto.CSVStateCensusIndia;
import dto.CSVStatesCodeCensusIndia;

public class IndiaCensusDAO {
    public String state;
    public String population;
    public String areaInSqKm;
    public String densityPerSqKm;
    public String srNo;
    public String stateName;
    public String tin;
    public String stateCode;

    public IndiaCensusDAO(CSVStateCensusIndia csvStateCensusIndia) {
        state = csvStateCensusIndia.getState();
        population = csvStateCensusIndia.getPopulation();
        areaInSqKm = csvStateCensusIndia.getAreaInSqKm();
        densityPerSqKm = csvStateCensusIndia.getDensityPerSqKm();
    }

    public IndiaCensusDAO(CSVStatesCodeCensusIndia csvStatesCodeCensusIndia) {
        srNo = csvStatesCodeCensusIndia.getSrNo();
        stateName = csvStatesCodeCensusIndia.getStateName();
        tin = csvStatesCodeCensusIndia.getTin();
        stateCode = csvStatesCodeCensusIndia.getStateCode();
    }
}