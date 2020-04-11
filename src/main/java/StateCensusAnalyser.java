import Exception.CSVException;
import Exception.StateCensusAnalyserException;
import Factory.CSVBuilderFactory;
import Factory.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;

public class StateCensusAnalyser {

    Map<Object, Object> csvStateCensusMap;
    Map<Object, Object> csvStateCodeCensusMap;

    public StateCensusAnalyser() {
        this.csvStateCensusMap = new HashMap<>();
        this.csvStateCodeCensusMap = new HashMap<>();
    }

    //  METHOD TO TAKE LIST FROM STATE CENSUS CSV FILE
    public int CensusCSVData(String getPath) throws StateCensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(getPath))
        ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            csvStateCensusMap = csvBuilder.getCSVFileMap(reader, CSVStateCensus.class);
            return csvStateCensusMap.size();
        } catch (IOException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.getCause());
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.getCause());
        } catch (CSVException e) {
            e.printStackTrace();
        }
        return 0;
    }

    //  METHOD TO TAKE LIST FROM STATE CODE CSV FILE
    public int CensusCodeCSVData(String getPath) throws StateCensusAnalyserException, CSVException {
        try (Reader reader = Files.newBufferedReader(Paths.get(getPath))
        ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            csvStateCodeCensusMap = csvBuilder.getCSVFileMap(reader, CSVStatesCode.class);
            return csvStateCodeCensusMap.size();
        } catch (IOException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.getCause());
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.getCause());
        }
    }

    //  METHOD TO CONVERT DATA IN JSON FORMAT BASED ON STATES, ALPHABETICALLY
    public String getStateWiseSortedCensusData() throws StateCensusAnalyserException {
        if (csvStateCensusMap == null || csvStateCensusMap.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        Comparator<CSVStateCensus> censusCSVComparator = Comparator.comparing(census -> census.getState());
        this.censusSort(censusCSVComparator, csvStateCensusMap);
        Collection<Object> csvList1 = csvStateCensusMap.values();
        String sortedStateCensusJson = new Gson().toJson(csvList1);
        return sortedStateCensusJson;
    }

    //  METHOD TO CONVERT DATA IN JSON FORMAT BASED ON STATES CODE, ALPHABETICALLY
    public String getStateWiseSortedStateCode() throws StateCensusAnalyserException {
        if (csvStateCodeCensusMap == null || csvStateCodeCensusMap.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_STATE_CODE_DATA, "No State Data");
        Comparator<CSVStatesCode> censusCSVCodeComparator = Comparator.comparing(census -> census.getStateCode());
        this.censusSort(censusCSVCodeComparator, csvStateCodeCensusMap);
        Collection<Object> csvList2 = csvStateCensusMap.values();
        String sortedStateCensusJson = new Gson().toJson(csvList2);
        return sortedStateCensusJson;
    }

    // METHOD TO SORT CSV FILE USING MAP
    private <T> void censusSort(Comparator censusCSVComparator, Map<Object, Object> csvMap) {
                IntStream.range(0, csvMap.size() - 1)
                .flatMap(csvLengthIteration -> IntStream.range(0, csvMap.size() - 1))
                .forEachOrdered(csvDataIteration -> {
            T censusCSV1 = (T) csvMap.get(csvDataIteration);
            T censusCSV2 = (T) csvMap.get(csvDataIteration + 1);
            if (censusCSVComparator.compare(censusCSV1, censusCSV2) > 0) {
                csvMap.put(csvDataIteration, censusCSV2);
                csvMap.put(csvDataIteration + 1, censusCSV1);
            }
        });
    }
}
