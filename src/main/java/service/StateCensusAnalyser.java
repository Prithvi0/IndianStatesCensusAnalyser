package service;

import Exception.CSVException;
import Exception.StateCensusAnalyserException;
import adapter.CensusAdapter;
import adapter.CensusAdapterFactory;
import com.google.gson.Gson;
import dao.CensusDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StateCensusAnalyser {
    //  MAP DECLARATION
    Map<String, CensusDAO> censusDAOMap = new HashMap<>();

    //  DEFAULT CONSTRUCTOR
    public StateCensusAnalyser() { }

    //  ENUM DECLARATION FOR COUNTRIES
    public enum COUNTRY {INDIA,US}

    // ENCAPSULATING COUNTRY NAMES
    private COUNTRY country;

    // PARAMETERISED CONSTRUCTOR
    public StateCensusAnalyser(COUNTRY country) {
        this.country = country;
    }

    // ENUM DECLARATION FOR BOTH COUNTRIES CSV DATA
    public enum SortingMode {AREA, STATE, STATECODE, DENSITY, POPULATION}

    //  GENERIC METHOD TO LOAD INDIA AND US CSV DATA
    public int CensusCSVData(COUNTRY country, String... csvFilePath) throws CSVException, StateCensusAnalyserException {
        CensusAdapter censusDataLoader = CensusAdapterFactory.getCensusData(country);
        censusDAOMap = censusDataLoader.CensusCSVData(csvFilePath);
        return censusDAOMap.size();
    }

    //  GENERIC METHOD TO SORT INDIA AND US CENSUS DATA
    public String getSortedCensusData(SortingMode mode) throws StateCensusAnalyserException {
        if (censusDAOMap == null || censusDAOMap.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        ArrayList censusDTO = censusDAOMap.values().stream()
                .sorted(CensusDAO.getCensusSortComparator(mode))
                .map(censusDAO -> censusDAO.getCensusDTO(country))
                .collect(Collectors.toCollection(ArrayList::new));
        return new Gson().toJson(censusDTO);
    }
}