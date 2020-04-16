package dao;

import dto.CSVCensusUS;
import dto.CSVStateCensusIndia;
import dto.CSVStatesCodeCensusIndia;

public class CensusDAO {
    public String state;
    public Integer population;
    public Double areaInSqKm;
    public Double densityPerSqKm;
    public Integer srNo;
    public String stateName;
    public Integer tin;
    public String stateCode;

    public CensusDAO(CSVStateCensusIndia csvStateCensusIndia) {
        state = csvStateCensusIndia.getState();
        population = csvStateCensusIndia.getPopulation();
        areaInSqKm = csvStateCensusIndia.getAreaInSqKm();
        densityPerSqKm = csvStateCensusIndia.getDensityPerSqKm();
    }

    public CensusDAO(CSVStatesCodeCensusIndia csvStatesCodeCensusIndia) {
        srNo = csvStatesCodeCensusIndia.getSrNo();
        stateName = csvStatesCodeCensusIndia.getStateName();
        tin = csvStatesCodeCensusIndia.getTin();
        stateCode = csvStatesCodeCensusIndia.getStateCode();
    }

    public CensusDAO(CSVCensusUS csvCensusUS) {
        stateCode = csvCensusUS.getStateId();
        state = csvCensusUS.getState();
        population = csvCensusUS.getPopulation();
        areaInSqKm = csvCensusUS.getArea();
        densityPerSqKm = csvCensusUS.getPopulationDensity();
    }
}