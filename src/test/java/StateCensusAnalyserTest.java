import com.google.gson.Gson;
import dao.IndiaCensusDAO;
import Exception.CSVException;
import Exception.StateCensusAnalyserException;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

public class StateCensusAnalyserTest {
    // FILES ACCESS FOR TC 1.1 - 1.5
    private static final String INDIAN_CENSUS_CSV_FILE_PATH = "./src/test/resources/StateCensusData.csv";
    private static final String INDIAN_CENSUS_CSV_WRONG_FILE = "./src/test/resources/StateCensusDate.csv";
    private static final String INDIAN_CENSUS_CSV_WRONG_FILE_TYPE = "./src/test/resources/StateCensusDate.aac";
    private static final String INDIAN_CENSUS_CSV_WRONG_FILE_DELIMITER = "./src/test/resources/StateCensusDataDelimiter.csv";
    private static final String INDIAN_CENSUS_CSV_WRONG_FILE_HEADER = "./src/test/resources/StateCensusDataHeader.csv";

    // FILES ACCESS FOR TC 2.1 - 2.5
    private static final String INDIAN_CENSUS_CSV_CODE_FILE_PATH = "./src/test/resources/StateCode.csv";
    private static final String INDIAN_CENSUS_CSV_CODE_WRONG_FILE = "./src/test/resources/StateCodes.csv";
    private static final String INDIAN_CENSUS_CSV_CODE_WRONG_FILE_TYPE = "./src/test/resources/StateCode.aac";
    private static final String INDIAN_CENSUS_CSV_CODE_WRONG_FILE_DELIMITER = "./src/test/resources/StateCodeDelimiter.csv";
    private static final String INDIAN_CENSUS_CSV_CODE_WRONG_FILE_HEADER = "./src/test/resources/StateCodeHeader.csv";

    // OBJECT TO ACCESS THROUGHOUT THE PROGRAM
    StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();

    // T.C 1.1: TEST CASE TO ENSURE THE NUMBER OF RECORD MATCHES BY STATES CENSUS CSV FILE
    @Test
    public void givenStateCensusCSVFile_ShouldEnsureRecordMatches() throws IOException, RuntimeException {
        try {
            int totalCensusEntries = stateCensusAnalyser.CensusCSVData(INDIAN_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, totalCensusEntries);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    // T.C 1.2: TEST CASE TO RETURN A CUSTOM EXCEPTION IF CSV FILE IS INCORRECT
    @Test
    public void givenStateCensusCSVFile_IfIncorrect_ShouldReturnCustomException() throws IOException, CSVException {
        try {
            stateCensusAnalyser.CensusCSVData(INDIAN_CENSUS_CSV_WRONG_FILE);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    // T.C 1.3: TEST CASE TO RETURN A CUSTOM EXCEPTION IF THE FILE TYPE IS INCORRECT
    @Test
    public void givenStateCensusFile_IfIncorrectFileType_ShouldReturnCustomException() throws IOException, CSVException {
        try {
            stateCensusAnalyser.CensusCSVData(INDIAN_CENSUS_CSV_WRONG_FILE_TYPE);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    // T.C 1.4: TEST CASE TO RETURN A CUSTOM EXCEPTION IF THE FILE DELIMITER IS INCORRECT
    @Test
    public void givenStateCensusFile_WhenIncorrectDelimiter_ShouldReturnCustomException() throws IOException, CSVException {
        try {
            stateCensusAnalyser.CensusCSVData(INDIAN_CENSUS_CSV_WRONG_FILE_DELIMITER);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
        }
    }

    // T.C 1.5: TEST CASE TO RETURN A CUSTOM EXCEPTION IF THE FILE HEADER IS INCORRECT
    @Test
    public void givenStateCensusFile_WhenIncorrectHeader_ShouldReturnCustomException() throws IOException, CSVException {
        try {
            stateCensusAnalyser.CensusCSVData(INDIAN_CENSUS_CSV_WRONG_FILE_HEADER);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
        }
    }

    // T.C 2.1: TEST CASE TO ENSURE THE NUMBER OF RECORD MATCHES BY STATES CENSUS CODE CSV FILE
    @Test
    public void givenStateCensusCSVCodeFile_ShouldEnsureRecordMatches() throws IOException, CSVException {
        try {
            int totalCensusCodeEntries = stateCensusAnalyser.CensusCodeCSVData(INDIAN_CENSUS_CSV_CODE_FILE_PATH);
            Assert.assertEquals(37, totalCensusCodeEntries);
        } catch (StateCensusAnalyserException e) {
            e.printStackTrace();
        }
    }

    // T.C 2.2: TEST CASE TO RETURN A CUSTOM EXCEPTION IF CSV CODE FILE IS INCORRECT
    @Test
    public void givenStateCensusCSVCodeFile_WhenIncorrect_ShouldReturnCustomException() throws IOException, NoSuchFileException, CSVException {
        try {
            stateCensusAnalyser.CensusCodeCSVData(INDIAN_CENSUS_CSV_CODE_WRONG_FILE);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    // T.C 2.3: TEST CASE TO RETURN A CUSTOM EXCEPTION IF THE CODE FILE TYPE IS INCORRECT
    @Test
    public void givenStateCensusCodeFile_WhenIncorrectFileType_ShouldReturnCustomException() throws IOException, CSVException {
        try {
            stateCensusAnalyser.CensusCodeCSVData(INDIAN_CENSUS_CSV_CODE_WRONG_FILE_TYPE);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_SUCH_FILE, e.type);
        }
    }

    // T.C 2.4: TEST CASE TO RETURN A CUSTOM EXCEPTION IF THE CODE FILE DELIMITER IS INCORRECT
    @Test
    public void givenStateCensusCodeFile_WhenIncorrectDelimiter_ShouldReturnCustomException() throws IOException, CSVException {
        try {
            stateCensusAnalyser.CensusCodeCSVData(INDIAN_CENSUS_CSV_CODE_WRONG_FILE_DELIMITER);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
        }
    }

    // T.C 2.5: TEST CASE TO RETURN A CUSTOM EXCEPTION IF THE CODE FILE HEADER IS INCORRECT
    @Test
    public void givenStateCensusCodeFile_WhenIncorrectHeader_ShouldReturnCustomException() throws IOException, CSVException {
        try {
            stateCensusAnalyser.CensusCodeCSVData(INDIAN_CENSUS_CSV_CODE_WRONG_FILE_HEADER);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
        }
    }

    // T.C 3: TEST CASES TO CHECK FOR THE FIRST AND LAST STATES
    @Test
    public void givenIndiaStateCensusData_WhenSortedAlphabetically_ShouldReturnFirstState() {
        try {
            stateCensusAnalyser.CensusCSVData(INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusDAO[] StateCensusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusDAO[].class);
            Assert.assertEquals("Andhra Pradesh", StateCensusCSV[0].state);
        } catch (StateCensusAnalyserException e) { }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedAlphabeticallyAndLastIndexProvided_ShouldReturnLastState() {
        try {
            stateCensusAnalyser.CensusCSVData(INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusDAO[].class);
            Assert.assertEquals("West Bengal", censusCSV[censusCSV.length - 1].state);
        } catch (StateCensusAnalyserException e) { }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedAlphabeticallyAndIndexWrongProvided_ShouldReturnCustomException() {
        try {
            stateCensusAnalyser.CensusCSVData(INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getStateWiseSortedCensusData();
            IndiaCensusDAO[] censusCSV = new Gson().fromJson(sortedCensusData, IndiaCensusDAO[].class);
            Assert.assertNotEquals("Telangana", censusCSV[0].state);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }

    // T.C 4: TEST CASES TO CHECK FOR THE FIRST AND LAST STATES CODE
    @Test
    public void givenIndianCensusStateCodeData_WhenSortedAlphabetically_ShouldReturnFirstStateCode() throws CSVException {
        try {
            stateCensusAnalyser.CensusCodeCSVData(INDIAN_CENSUS_CSV_CODE_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getStateWiseSortedStateCode();
            IndiaCensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, IndiaCensusDAO[].class);
            Assert.assertEquals("AD", stateCensuses[0].stateCode);
        } catch (StateCensusAnalyserException e) { }
    }

    @Test
    public void givenIndianCensusStateCodeData_WhenSortedAlphabeticallyAndLastIndexProvided_ShouldReturnLastStateCode() throws CSVException {
        try {
            stateCensusAnalyser.CensusCodeCSVData(INDIAN_CENSUS_CSV_CODE_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getStateWiseSortedStateCode();
            IndiaCensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, IndiaCensusDAO[].class);
            Assert.assertEquals("WB", stateCensuses[stateCensuses.length - 1].stateCode);
        } catch (StateCensusAnalyserException e) { }
    }

    @Test
    public void givenIndianStateCodeData_WhenSortedAlphabeticallyAndWrongIndexProvided_ShouldReturnCustomException() throws CSVException {
        try {
            stateCensusAnalyser.CensusCodeCSVData(INDIAN_CENSUS_CSV_CODE_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getStateWiseSortedStateCode();
            IndiaCensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, IndiaCensusDAO[].class);
            Assert.assertNotEquals("MH", stateCensuses[0].stateCode);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_CENSUS_STATE_CODE_DATA, e.type);
        }
    }

    // T.C 5: TEST CASES TO CHECK FOR THE FIRST AND LAST STATES BASED ON POPULATION
    @Test
    public void givenIndianCensusStatePopulationData_WhenSortedAndLastIndexProvided_ShouldReturnMostPopulatedState() {
        try {
            stateCensusAnalyser.CensusCSVData(INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getStateWiseSortedPopulation();
            IndiaCensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, IndiaCensusDAO[].class);
            Assert.assertEquals("Uttar Pradesh", stateCensuses[stateCensuses.length - 1].state);
        } catch (StateCensusAnalyserException e) { }
    }

    @Test
    public void givenIndianCensusStatePopulationData_WhenSortedAndFirstIndexProvided_ShouldReturnLeastPopulatedState() {
        try {
            stateCensusAnalyser.CensusCSVData(INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getStateWiseSortedPopulation();
            IndiaCensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, IndiaCensusDAO[].class);
            Assert.assertEquals("Sikkim", stateCensuses[0].state);
        } catch (StateCensusAnalyserException e) { }
    }

    @Test
    public void givenIndianCensusStatePopulationData_WhenSortedAndNothingProvided_ShouldReturnCustomException() {
        try {
            stateCensusAnalyser.CensusCSVData(INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getStateWiseSortedPopulation();
            IndiaCensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, IndiaCensusDAO[].class);
            Assert.assertNotEquals("", stateCensuses[0].stateCode);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }

    // T.C 6: TEST CASES TO CHECK FOR THE FIRST AND LAST STATES BASED ON DENSITY PER SQ. KM.
    @Test
    public void givenIndianCensusStateDensityPerKmSqData_WhenSortedAndFirstIndexProvided_ShouldReturnMostDensestState() {
        try {
            stateCensusAnalyser.CensusCSVData(INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getDensityInSqKmWiseSortedCensusData();
            IndiaCensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, IndiaCensusDAO[].class);
            Assert.assertEquals("Bihar", stateCensuses[stateCensuses.length - 1].state);
        } catch (StateCensusAnalyserException e) { }
    }

    @Test
    public void givenIndianCensusStateDensityPerKmSqData_WhenSortedAndLastIndexProvided_ShouldReturnLeastDensestState() {
        try {
            stateCensusAnalyser.CensusCSVData(INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getDensityInSqKmWiseSortedCensusData();
            IndiaCensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, IndiaCensusDAO[].class);
            Assert.assertEquals("Arunachal Pradesh", stateCensuses[0].state);
        } catch (StateCensusAnalyserException e) { }
    }

    @Test
    public void givenIndianCensusStateDensityPerKmSqData_WhenSortedAndNothingProvided_ShouldReturnCustomException() {
        try {
            stateCensusAnalyser.CensusCSVData(INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getDensityInSqKmWiseSortedCensusData();
            IndiaCensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, IndiaCensusDAO[].class);
            Assert.assertNotEquals("", stateCensuses[0].state);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }

    // T.C 7: TEST CASES TO CHECK FOR THE FIRST AND LAST STATES BASED ON SQ. KM. AREA
    @Test
    public void givenIndianCensusStateAreaInKmSqData_WhenSortedAndFirstIndexProvided_ShouldReturnHighestAreaState() {
        try {
            stateCensusAnalyser.CensusCSVData(INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getAreaInSqKmWiseSortedCensusData();
            IndiaCensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, IndiaCensusDAO[].class);
            Assert.assertEquals("Rajasthan", stateCensuses[stateCensuses.length - 1].state);
        } catch (StateCensusAnalyserException e) { }
    }

    @Test
    public void givenIndianCensusStateAreaInKmSqData_WhenSortedAndLastIndexProvided_ShouldReturnLowestAreaState() {
        try {
            stateCensusAnalyser.CensusCSVData(INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getAreaInSqKmWiseSortedCensusData();
            IndiaCensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, IndiaCensusDAO[].class);
            Assert.assertEquals("Arunachal Pradesh", stateCensuses[0].state);
        } catch (StateCensusAnalyserException e) { }
    }

    @Test
    public void givenIndianCensusStateAreaInKmSqData_WhenSortedAndWrongProvided_ShouldReturnCustomException() {
        try {
            stateCensusAnalyser.CensusCSVData(INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getAreaInSqKmWiseSortedCensusData();
            IndiaCensusDAO[] stateCensuses = new Gson().fromJson(sortedCensusData, IndiaCensusDAO[].class);
            Assert.assertNotEquals("Goa", stateCensuses[0].state);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.NO_CENSUS_DATA, e.type);
        }
    }
}