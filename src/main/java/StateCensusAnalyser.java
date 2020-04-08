import Exception.StateCensusAnalyserException;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;

public class StateCensusAnalyser {

    int totalEntries;   // INITIALISING A VARIABLE TO STORE ALL CSV ENTRIES COUNT
    int totalStateCodeEntries;  // INITIALISING A VARIABLE TO STORE ALL CSV STATE CODE ENTRIES COUNT

    // METHOD TO READ CSV FILE OF STATE CENSUS
    public int CensusCSVData(String csvPath) throws IOException, StateCensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvPath))
        ) {

            Iterator<CSVStateCensus> csvStateCensusIterator;
            csvStateCensusIterator = CSVSingleResponsibilityAnalyser.getCSVStateCensusDataIterator(reader, CSVStateCensus.class);

            while (csvStateCensusIterator.hasNext()) {
                // READ ALL THE CSV CONTENTS INTO MEMORY
                CSVStateCensus csvStateCensus = csvStateCensusIterator.next();
                totalEntries++;
            }
        } catch (IOException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.getCause());
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.getCause());
        }
        return totalEntries;
    }

    //  METHOD TO READ CSV FILE OF STATE CENSUS CODE
    public int CensusCodeCSVData(String csvPath) throws IOException, StateCensusAnalyserException {
        try (Reader reader = Files.newBufferedReader(Paths.get(csvPath))
        ) {
            Iterator<CSVStates> csvStatesCodeIterator;
            csvStatesCodeIterator = CSVSingleResponsibilityAnalyser.getCSVStateCensusDataIterator(reader, CSVStates.class);
            while (csvStatesCodeIterator.hasNext()) {
                // READ ALL THE CSV CONTENTS INTO MEMORY
                CSVStates csvStates = csvStatesCodeIterator.next();
                totalStateCodeEntries++;
            }
        } catch (IOException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.getCause());
        } catch (RuntimeException e) {
            throw new StateCensusAnalyserException(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.getCause());
        }
        return totalStateCodeEntries;
    }
}