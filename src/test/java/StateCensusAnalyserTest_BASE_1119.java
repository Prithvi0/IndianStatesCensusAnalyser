import Exception.StateCensusAnalyserException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyserTest {
    private static final String INDIAN_CENSUS_CSV_FILE_PATH = "./src/test/resources/StateCensusData.csv";
    private static final String INDIAN_CENSUS_CSV_WRONG_FILE = "./src/test/resources/StateCensusDate.csv";

    StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();

    // T.C 1.1: TEST CASE TO ENSURE THE NUMBER OF RECORD MATCHES BY STATES CENSUS CSV FILE
    @Test
    public void givenStateCensusCSVFile_EnsureRecordMatches() throws StateCensusAnalyserException {
        try {
            int totalEntries = stateCensusAnalyser.CensusCSVData(INDIAN_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, totalEntries);
        } catch (NullPointerException | IOException e) {
            e.printStackTrace();
        }
    }

    // T.C 1.2: TEST CASE TO RETURN A CUSTOM EXCEPTION IF CSV FILE IS INCORRECT
    @Test
    public void givenStateCensusCSVFile_IfIncorrect_ReturnCustomException() throws IOException, NullPointerException {
        try {
            stateCensusAnalyser.CensusCSVData(INDIAN_CENSUS_CSV_WRONG_FILE);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
            e.printStackTrace();
        }
    }
}