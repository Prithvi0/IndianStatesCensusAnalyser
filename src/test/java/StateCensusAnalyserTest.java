import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class StateCensusAnalyserTest {
    private static final String INDIAN_CENSUS_CSV_FILE_PATH = "./src/test/resources/StateCensusData.csv";
    StateCensusAnalyser stateCensusAnalyser;
    // T.C 1.1: TEST CASE TO ENSURE THE NUMBER OF RECORD MATCHES BY STATES CENSUS CSV FILE
    @Test
    public void givenStateCensusCSVFile_EnsureRecordMatches() {
        try {
            Assert.assertEquals(29, stateCensusAnalyser.CensusCSVData(INDIAN_CENSUS_CSV_FILE_PATH));
        } catch (IOException | NullPointerException e) {
            e.printStackTrace();
        }
    }
}