import Exception.CSVException;
import Exception.StateCensusAnalyserException;
import Factory.CSVBuilderFactory;
import Factory.ICSVBuilder;
import com.google.gson.Gson;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

public class StateCensusAnalyser {
    List csvStateCensusList;
    List csvStateCodeCensusList;

    //  METHOD TO TAKE LIST FROM STATE CENSUS CSV FILE
    public int CensusCSVData(String getPath) throws StateCensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(getPath))
        ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            csvStateCensusList = csvBuilder.getCSVFileList(reader, CSVStateCensus.class);
            return csvStateCensusList.size();
        } catch (IOException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.getCause());
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.getCause());
        } catch (CSVException e) { }
        return 0;
    }

    //  METHOD TO TAKE LIST FROM STATE CODE CSV FILE
    public int CensusCodeCSVData(String getPath) throws StateCensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(getPath))
        ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            csvStateCodeCensusList = csvBuilder.getCSVFileList(reader, CSVStatesCode.class);
            return csvStateCodeCensusList.size();
        } catch (IOException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.getCause());
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.getCause());
        } catch (CSVException e) { }
        return 0;
    }

    //  METHOD TO CONVERT DATA IN JSON FORMAT BASED ON STATES, ALPHABETICALLY
    public String getStateWiseSortedCensusData() throws StateCensusAnalyserException {
        if (csvStateCensusList == null || csvStateCensusList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
            Comparator<CSVStateCensus> censusCSVComparator = Comparator.comparing(census -> census.getState());
            this.sort(censusCSVComparator);
            String sortedStateCensusJson = new Gson().toJson(csvStateCensusList);
            return sortedStateCensusJson;
    }

    //  METHOD TO CONVERT DATA IN JSON FORMAT BASED ON STATES CODE, ALPHABETICALLY
    public String getStateWiseSortedStateCode () throws StateCensusAnalyserException {
        if (csvStateCensusList == null || csvStateCensusList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_STATE_CODE_DATA, "No State Data");
            Comparator<CSVStatesCode> censusCSVCodeComparator = Comparator.comparing(census -> census.getStateCode());
            this.sort(censusCSVCodeComparator);
            String sortedStateCensusJson = new Gson().toJson(csvStateCensusList);
            return sortedStateCensusJson;
        }

    //  METHOD TO SORT STATE CENSUS LIST IN ASCENDING ORDER
     private <T> void sort (Comparator censusComparator){
        for (int iterate = 0; iterate < csvStateCensusList.size() - 1; iterate++) {
            for (int Inneriterate = 0; Inneriterate < csvStateCensusList.size() - iterate - 1; Inneriterate++) {
                for (int innerIterate = 0; innerIterate < csvStateCensusList.size() - iterate - 1; innerIterate++) {
                    T census1 = (T) csvStateCensusList.get(innerIterate);
                    T census2 = (T) csvStateCensusList.get(innerIterate + 1);
                    if (censusComparator.compare(census1, census2) > 0) {
                        csvStateCensusList.set(innerIterate, census2);
                        csvStateCensusList.set(innerIterate + 1, census1);
                    }
                }             }
            }
        }
    }