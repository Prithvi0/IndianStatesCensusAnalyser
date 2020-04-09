import Exception.CSVException;
import Exception.StateCensusAnalyserException;
import Factory.CSVBuilderFactory;
import Factory.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class StateCensusAnalyser {

    //  TAKE LIST FROM STATE CENSUS CSV FILE
    public int CensusCSVData(String getPath) throws StateCensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(getPath))
        ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<CSVStateCensus> csvStateCensusList = (List<CSVStateCensus>) csvBuilder.getCSVFileIterator(reader, CSVStateCensus.class);
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

    //  TAKE LIST FROM STATE CODE CSV FILE
    public int CensusCodeCSVData(String getPath) throws StateCensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(getPath))
        ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            List<CSVStates> csvStatesCodeList = (List<CSVStates>) csvBuilder.getCSVFileIterator(reader, CSVStates.class);
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
}