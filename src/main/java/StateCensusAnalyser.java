import Exception.StateCensusAnalyserException;
import Factory.CSVBuilderFactory;
import Factory.ICSVBuilder;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser {
    int totalEntries;   // INITIALISING A VARIABLE TO STORE ALL (CSV STATES CENSUS AND STATE CENSUS CODE) ENTRIES COUNT

    //  READING AND PRINTING DATA FROM STATE CENSUS CSV FILE
    public int CensusCSVData(String getPath) throws StateCensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(getPath))
        ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStateCensus> csvStateCensusIterator;
            csvStateCensusIterator = csvBuilder.getCSVFileIterator(reader, CSVStateCensus.class);
            totalEntries = getCount(csvStateCensusIterator, CSVStateCensus.class);
        } catch (IOException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.getCause());
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.getCause());
        }
        return totalEntries;
    }

    //  READING AND PRINTING DATA FROM STATE CODE CSV FILE
    public int CensusCodeCSVData(String getPath) throws StateCensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(getPath))
        ) {
            ICSVBuilder csvBuilder = CSVBuilderFactory.createCSVBuilder();
            Iterator<CSVStateCensus> csvStateCodeIterator;
            csvStateCodeIterator = csvBuilder.getCSVFileIterator(reader, CSVStates.class);
            totalEntries = getCount(csvStateCodeIterator, CSVStates.class);
        } catch (IOException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.getCause());
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.getCause());
        }
        return totalEntries;
    }

    //  GENERIC METHOD TO ITERATE THROUGH CSV FILE
    private <E> int getCount(Iterator iterator, E className) {
        while (iterator.hasNext()) {
            className = (E) iterator.next();
            totalEntries++;
        }
        return totalEntries;
    }
}