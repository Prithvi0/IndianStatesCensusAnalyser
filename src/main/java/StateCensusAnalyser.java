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

    List<CSVStateCensus> csvStateCensusList;

    //  METHOD TO TAKE LIST FROM STATE CENSUS CSV FILE
    public int CensusCSVData(String getPath) throws StateCensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(getPath))
        ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            csvStateCensusList = (List<CSVStateCensus>) csvBuilder.getCSVFileList(reader, CSVStateCensus.class);
            return csvStateCensusList.size();
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
    public int CensusCodeCSVData(String getPath) throws StateCensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(getPath))
        ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<CSVStatesCode> csvStatesCodeList = (List<CSVStatesCode>) csvBuilder.getCSVFileList(reader, CSVStatesCode.class);
            return csvStatesCodeList.size();
        } catch (IOException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.getCause());
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.getCause());
        } catch (CSVException e) {
            e.printStackTrace();
        }
        return 0;
    }
    //  METHOD TO CONVERT DATA IN JSON FORMAT BASED ON STATES ORDER ALPHABETICALLY
    public String getStateWiseSortedCensusData() throws StateCensusAnalyserException {
        if (csvStateCensusList == null || csvStateCensusList.size() == 0)
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, "No Census Data");
        Comparator<CSVStateCensus> censusCSVComparator = Comparator.comparing(census -> census.getState());
        this.stateSort(censusCSVComparator);
        String sortedStateCensusJson = new Gson().toJson(csvStateCensusList);
        return sortedStateCensusJson;
    }
    //  METHOD TO SORT STATE CENSUS LIST IN ASCENDING ORDER
    private void stateSort(Comparator<CSVStateCensus> censusComparator) {
        for (int statesIterate = 0; statesIterate < csvStateCensusList.size() - 1; statesIterate++) {
            for (int statesInsideIterate = 0; statesInsideIterate < csvStateCensusList.size() - statesIterate - 1; statesInsideIterate++) {
                CSVStateCensus census1 = csvStateCensusList.get(statesInsideIterate);
                CSVStateCensus census2 = csvStateCensusList.get(statesInsideIterate + 1);
                if (censusComparator.compare(census1, census2) > 0) {
                    csvStateCensusList.set(statesInsideIterate, census2);
                    csvStateCensusList.set(statesInsideIterate + 1, census1);
                }
            }
        }
    }
}