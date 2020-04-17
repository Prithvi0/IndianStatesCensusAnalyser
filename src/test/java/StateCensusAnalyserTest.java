import Exception.CSVException;
import Exception.StateCensusAnalyserException;
import com.google.gson.Gson;
import dto.CSVCensusUS;
import dto.CSVStateCensusIndia;
import org.junit.Assert;
import org.junit.Test;
import service.StateCensusAnalyser;

import java.io.IOException;
import java.nio.file.NoSuchFileException;

import static service.StateCensusAnalyser.COUNTRY.INDIA;
import static service.StateCensusAnalyser.COUNTRY.US;

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

    //  FILE ACCESS FOR US CENSUS DATA
    private static final String US_CENSUS_CSV_FILE_PATH = "./src/test/resources/USCensusData.csv";

    // OBJECT TO ACCESS THROUGHOUT THE PROGRAM
    StateCensusAnalyser stateCensusAnalyser = new StateCensusAnalyser();

    // T.C 1.1: TEST CASE TO ENSURE THE NUMBER OF RECORD MATCHES BY INDIAN STATES CENSUS CSV FILE
    @Test
    public void givenStateCensusCSVFile_ShouldEnsureRecordMatches() throws IOException, RuntimeException {
        try {
            int totalCensusEntries = stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(29, totalCensusEntries);
        } catch (CSVException | StateCensusAnalyserException e) { }
    }

    // T.C 1.2: TEST CASE TO RETURN A CUSTOM EXCEPTION IF CSV FILE IS INCORRECT
    @Test
    public void givenStateCensusCSVFile_IfIncorrect_ShouldReturnCustomException() throws IOException, CSVException {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_WRONG_FILE);
        } catch (CSVException e) {
            Assert.assertEquals(CSVException.ExceptionType.NO_SUCH_FILE, e.type);
        } catch (StateCensusAnalyserException e) { }
    }

    // T.C 1.3: TEST CASE TO RETURN A CUSTOM EXCEPTION IF THE FILE TYPE IS INCORRECT
    @Test
    public void givenStateCensusFile_IfIncorrectFileType_ShouldReturnCustomException() throws IOException, CSVException {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_WRONG_FILE_TYPE);
        } catch (CSVException e) {
            Assert.assertEquals(CSVException.ExceptionType.NO_SUCH_FILE, e.type);
        } catch (StateCensusAnalyserException e) { }
    }

    // T.C 1.4: TEST CASE TO RETURN A CUSTOM EXCEPTION IF THE FILE DELIMITER IS INCORRECT
    @Test
    public void givenStateCensusFile_WhenIncorrectDelimiter_ShouldReturnCustomException() throws IOException, CSVException {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_WRONG_FILE_DELIMITER);
        } catch (CSVException e) {
            Assert.assertEquals(CSVException.ExceptionType.INCORRECT_FILE, e.type);
        } catch (StateCensusAnalyserException e) { }
    }

    // T.C 1.5: TEST CASE TO RETURN A CUSTOM EXCEPTION IF THE FILE HEADER IS INCORRECT
    @Test
    public void givenStateCensusFile_WhenIncorrectHeader_ShouldReturnCustomException() throws IOException, CSVException {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_WRONG_FILE_HEADER);
        } catch (CSVException e) {
            Assert.assertEquals(CSVException.ExceptionType.INCORRECT_FILE, e.type);
        } catch (StateCensusAnalyserException e) {
        }
    }

    // T.C 2.1: TEST CASE TO ENSURE THE NUMBER OF RECORD MATCHES BY INDIAN STATES CENSUS CODE CSV FILE
    @Test
    public void givenStateCensusCSVCodeFile_ShouldEnsureRecordMatches() throws IOException, CSVException {
        try {
            int totalCensusCodeEntries = stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_CODE_FILE_PATH);
            Assert.assertEquals(37, totalCensusCodeEntries);
        } catch (CSVException | StateCensusAnalyserException e) { }
    }

    // T.C 2.2: TEST CASE TO RETURN A CUSTOM EXCEPTION IF CSV CODE FILE IS INCORRECT
    @Test
    public void givenStateCensusCSVCodeFile_WhenIncorrect_ShouldReturnCustomException() throws IOException, NoSuchFileException, CSVException {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_CODE_WRONG_FILE);
        } catch (CSVException e) {
            Assert.assertEquals(CSVException.ExceptionType.NO_SUCH_FILE, e.type);
        } catch (StateCensusAnalyserException e) { }
    }

    // T.C 2.3: TEST CASE TO RETURN A CUSTOM EXCEPTION IF THE CODE FILE TYPE IS INCORRECT
    @Test
    public void givenStateCensusCodeFile_WhenIncorrectFileType_ShouldReturnCustomException() throws IOException, CSVException {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_CODE_WRONG_FILE_TYPE);
        } catch (CSVException e) {
            Assert.assertEquals(CSVException.ExceptionType.NO_SUCH_FILE, e.type);
        } catch (StateCensusAnalyserException e) { }
    }

    // T.C 2.4: TEST CASE TO RETURN A CUSTOM EXCEPTION IF THE CODE FILE DELIMITER IS INCORRECT
    @Test
    public void givenStateCensusCodeFile_WhenIncorrectDelimiter_ShouldReturnCustomException() throws IOException, CSVException {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_CODE_WRONG_FILE_DELIMITER);
        } catch (CSVException e) {
            Assert.assertEquals(CSVException.ExceptionType.INCORRECT_FILE, e.type);
        } catch (StateCensusAnalyserException e) { }
    }

    // T.C 2.5: TEST CASE TO RETURN A CUSTOM EXCEPTION IF THE CODE FILE HEADER IS INCORRECT
    @Test
    public void givenStateCensusCodeFile_WhenIncorrectHeader_ShouldReturnCustomException() throws IOException, CSVException {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_CODE_WRONG_FILE_HEADER);
        } catch (CSVException e) {
            Assert.assertEquals(CSVException.ExceptionType.INCORRECT_FILE, e.type);
        } catch (StateCensusAnalyserException e) { }
    }

    // T.C 3: TEST CASES TO CHECK FOR THE FIRST AND LAST INDIAN STATES
    @Test
    public void givenIndiaStateCensusData_WhenSortedAlphabetically_ShouldReturnFirstState() {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATE);
            CSVStateCensusIndia[] StateCensusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensusIndia[].class);
            Assert.assertEquals("Andhra Pradesh", StateCensusCSV[0].state);
        } catch (CSVException | StateCensusAnalyserException e) { }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedAlphabeticallyAndLastIndexProvided_ShouldReturnLastState() {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATE);
            CSVStateCensusIndia[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensusIndia[].class);
            Assert.assertEquals("West Bengal", censusCSV[censusCSV.length - 1].state);
        } catch (CSVException | StateCensusAnalyserException e) { }
    }

    @Test
    public void givenIndianStateCensusData_WhenSortedAlphabeticallyAndIndexWrongProvided_ShouldReturnCustomException() throws StateCensusAnalyserException {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATE);
            CSVStateCensusIndia[] censusCSV = new Gson().fromJson(sortedCensusData, CSVStateCensusIndia[].class);
            Assert.assertNotEquals("Telangana", censusCSV[0].state);
        } catch (CSVException e) {
            Assert.assertEquals(CSVException.ExceptionType.INCORRECT_FILE, e.type);
        } catch (StateCensusAnalyserException e) { }
    }

    // T.C 4: TEST CASES TO CHECK FOR THE FIRST AND LAST INDIAN STATES CODE
    @Test
    public void givenIndianCensusStateCodeData_WhenSortedAlphabetically_ShouldReturnFirstStateCode() throws CSVException {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_CODE_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATECODE);
            CSVStateCensusIndia[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVStateCensusIndia[].class);
            Assert.assertEquals("AD", stateCensuses[0].stateCode);
        } catch (StateCensusAnalyserException | CSVException e) { }
    }

    @Test
    public void givenIndianCensusStateCodeData_WhenSortedAlphabeticallyAndLastIndexProvided_ShouldReturnLastStateCode() throws CSVException, StateCensusAnalyserException {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_CODE_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATECODE);
            CSVStateCensusIndia[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVStateCensusIndia[].class);
            Assert.assertEquals("WB", stateCensuses[stateCensuses.length - 1].stateCode);
        } catch (StateCensusAnalyserException | CSVException e) { }
    }

    @Test
    public void givenIndianStateCodeData_WhenSortedAlphabeticallyAndWrongIndexProvided_ShouldReturnCustomException() throws CSVException, StateCensusAnalyserException {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_CODE_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATECODE);
            CSVStateCensusIndia[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVStateCensusIndia[].class);
            Assert.assertNotEquals("MH", stateCensuses[0].stateCode);
        } catch (StateCensusAnalyserException | CSVException e) {
        }
    }

    // T.C 5: TEST CASES TO CHECK FOR THE FIRST AND LAST INDIAN STATES BASED ON POPULATION
    @Test
    public void givenIndianCensusStatePopulationData_WhenSortedAndLastIndexProvided_ShouldReturnMostPopulatedState() {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.POPULATION);
            CSVStateCensusIndia[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVStateCensusIndia[].class);
            Assert.assertEquals("Uttar Pradesh", stateCensuses[stateCensuses.length - 1].state);
        } catch (CSVException | StateCensusAnalyserException e) { }
    }

    @Test
    public void givenIndianCensusStatePopulationData_WhenSortedAndFirstIndexProvided_ShouldReturnLeastPopulatedState() {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.POPULATION);
            CSVStateCensusIndia[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVStateCensusIndia[].class);
            Assert.assertEquals("Sikkim", stateCensuses[0].state);
        } catch (CSVException | StateCensusAnalyserException e) { }
    }

    @Test
    public void givenIndianCensusStatePopulationData_WhenSortedAndNothingProvided_ShouldReturnCustomException() throws StateCensusAnalyserException {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.POPULATION);
            CSVStateCensusIndia[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVStateCensusIndia[].class);
            Assert.assertNotEquals("", stateCensuses[0].stateCode);
        } catch (CSVException e) {
            Assert.assertEquals(CSVException.ExceptionType.INCORRECT_FILE, e.type);
        } catch (StateCensusAnalyserException e) { }
    }

    // T.C 6: TEST CASES TO CHECK FOR THE FIRST AND LAST INDIAN STATES BASED ON DENSITY PER SQ. KM.
    @Test
    public void givenIndianCensusStateDensityPerKmSqData_WhenSortedAndFirstIndexProvided_ShouldReturnMostDensestState() {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.DENSITY);
            CSVStateCensusIndia[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVStateCensusIndia[].class);
            Assert.assertEquals("Bihar", stateCensuses[stateCensuses.length - 1].state);
        } catch (CSVException | StateCensusAnalyserException e) { }
    }

    @Test
    public void givenIndianCensusStateDensityPerKmSqData_WhenSortedAndLastIndexProvided_ShouldReturnLeastDensestState() {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.DENSITY);
            CSVStateCensusIndia[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVStateCensusIndia[].class);
            Assert.assertEquals("Arunachal Pradesh", stateCensuses[0].state);
        } catch (CSVException | StateCensusAnalyserException e) { }
    }

    @Test
    public void givenIndianCensusStateDensityPerKmSqData_WhenSortedAndNothingProvided_ShouldReturnCustomException() throws StateCensusAnalyserException {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.DENSITY);
            CSVStateCensusIndia[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVStateCensusIndia[].class);
            Assert.assertNotEquals("", stateCensuses[0].state);
        } catch (CSVException e) {
            Assert.assertEquals(CSVException.ExceptionType.INCORRECT_FILE, e.type);
        } catch (StateCensusAnalyserException e) { }
    }

    // T.C 7: TEST CASES TO CHECK FOR THE FIRST AND LAST INDIAN STATES BASED ON SQ. KM. AREA
    @Test
    public void givenIndianCensusStateAreaInKmSqData_WhenSortedAndFirstIndexProvided_ShouldReturnHighestAreaState() {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.AREA);
            CSVStateCensusIndia[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVStateCensusIndia[].class);
            Assert.assertEquals("Rajasthan", stateCensuses[stateCensuses.length - 1].state);
        } catch (CSVException | StateCensusAnalyserException e) { }
    }

    @Test
    public void givenIndianCensusStateAreaInKmSqData_WhenSortedAndLastIndexProvided_ShouldReturnLowestAreaState() {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.AREA);
            CSVStateCensusIndia[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVStateCensusIndia[].class);
            Assert.assertEquals("Arunachal Pradesh", stateCensuses[0].state);
        } catch (CSVException | StateCensusAnalyserException e) { }
    }

    @Test
    public void givenIndianCensusStateAreaInKmSqData_WhenSortedAndWrongProvided_ShouldReturnCustomException() throws StateCensusAnalyserException {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.AREA);
            CSVStateCensusIndia[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVStateCensusIndia[].class);
            Assert.assertNotEquals("Goa", stateCensuses[0].state);
        } catch (CSVException e) {
            Assert.assertEquals(CSVException.ExceptionType.INCORRECT_FILE, e.type);
        } catch (StateCensusAnalyserException e) { }
    }

    //  T.C 8: TEST CASES FOR US CENSUS DATA
    @Test
    public void givenUSCensusData_WhenCorrect_ShouldEnsureRecordMatches() {
        try {
            int totalCensusEntries = stateCensusAnalyser.CensusCSVData(US, US_CENSUS_CSV_FILE_PATH);
            Assert.assertEquals(51, totalCensusEntries);
        } catch (CSVException | StateCensusAnalyserException e) { }
    }

    @Test
    public void givenUSCensusData_WhenInCorrect_ShouldEnsureRecordMatches() {
        try {
            int totalCensusEntries = stateCensusAnalyser.CensusCSVData(US, US_CENSUS_CSV_FILE_PATH);
            Assert.assertNotEquals(50, totalCensusEntries);
        } catch (CSVException | StateCensusAnalyserException e) { }
    }

    //  T.C 9: TEST CASES FOR US CENSUS DATA BASED ON POPULATION
    @Test
    public void givenUSCensusStateData_WhenSortedOnPopulation_ShouldReturnMostPopulatedState() throws CSVException {
        try {
            stateCensusAnalyser.CensusCSVData(US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.POPULATION);
            CSVCensusUS[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVCensusUS[].class);
            Assert.assertEquals("California", stateCensuses[stateCensuses.length - 1].state);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
        }
    }

    @Test
    public void givenUSCensusStateData_WhenSortedOnPopulation_ShouldReturnLeastPopulatedState() {
        try {
            stateCensusAnalyser.CensusCSVData(US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.POPULATION);
            CSVCensusUS[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVCensusUS[].class);
            Assert.assertEquals("Wyoming", stateCensuses[0].state);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
        } catch (CSVException e) { }
    }

    @Test
    public void givenUSCensusStateData_WhenSortedOnPopulationAndWrongValueProvided_ShouldReturnCustomException() {
        try {
            stateCensusAnalyser.CensusCSVData(US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.POPULATION);
            CSVCensusUS[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVCensusUS[].class);
            Assert.assertNotEquals("Ohio", stateCensuses[0].state);
        } catch (StateCensusAnalyserException | CSVException e) { }
    }

    @Test
    public void givenUSCensusStateData_WhenSortedOnAreaAndFirstIndexProvided_ShouldReturnLargestAreaState() {
        try {
            stateCensusAnalyser.CensusCSVData(US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.AREA);
            CSVCensusUS[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVCensusUS[].class);
            Assert.assertEquals("Alaska", stateCensuses[stateCensuses.length - 1].state);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
        } catch (CSVException e) { }
    }

    @Test
    public void givenUSCensusStateData_WhenSortedOnAreaAndLastIndexProvided_ShouldReturnLowestAreaState() {
        try {
            stateCensusAnalyser.CensusCSVData(US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.AREA);
            CSVCensusUS[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVCensusUS[].class);
            Assert.assertEquals("District of Columbia", stateCensuses[0].state);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
        } catch (CSVException e) { }
    }

    @Test
    public void givenUSCensusStateData_WhenSortedOnAreaAndNothingProvided_ShouldReturnCustomException() {
        try {
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.AREA);
            CSVCensusUS[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVCensusUS[].class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals("No Census Data", e.getMessage());
        }
    }

    @Test

    public void givenUSCensusStateData_WhenSortedAlphabeticallyAndFirstIndexProvided_ShouldReturnStartingState() {
        try {
            stateCensusAnalyser.CensusCSVData(US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATE);
            CSVCensusUS[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVCensusUS[].class);
            Assert.assertEquals("Alabama", stateCensuses[0].state);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
        } catch (CSVException e) { }
    }

    @Test
    public void givenUSCensusStateData_WhenSortedAlphabeticallyAndLastIndexProvided_ShouldReturnLastState() {
        try {
            stateCensusAnalyser.CensusCSVData(US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATE);
            CSVCensusUS[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVCensusUS[].class);
            Assert.assertEquals("Wyoming", stateCensuses[stateCensuses.length - 1].state);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
        } catch (CSVException e) { }
    }

    @Test
    public void givenUSCensusStateData_WhenSortedAlphabeticallyAndNothingProvided_ShouldReturnCustomException() {
        try {
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATE);
            CSVCensusUS[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVCensusUS[].class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals("No Census Data", e.getMessage());
        }
    }

    @Test
    public void givenUSCensusStateData_WhenSortedOnPopulationDensityAndFirstIndexProvided_ShouldReturnLowestStateArea() {
        try {
            stateCensusAnalyser.CensusCSVData(US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.DENSITY);
            CSVCensusUS[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVCensusUS[].class);
            Assert.assertEquals("District of Columbia", stateCensuses[0].state);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
        } catch (CSVException e) { }
    }

    @Test
    public void givenUSCensusStateData_WhenSortedOnPopulationDensityAndLastIndexProvided_ShouldReturnHighestStateArea() {
        try {
            stateCensusAnalyser.CensusCSVData(US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.DENSITY);
            CSVCensusUS[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVCensusUS[].class);
            Assert.assertEquals("Alaska", stateCensuses[stateCensuses.length - 1].state);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
        } catch (CSVException e) { }
    }

    @Test
    public void givenUSCensusStateData_WhenSortedOnPopulationDensityAndNothingProvided_ShouldReturnCustomException() {
        try {
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.DENSITY);
            CSVCensusUS[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVCensusUS[].class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals("No Census Data", e.getMessage());
        }
    }

    @Test
    public void givenUSCensusStateData_WhenSortedAlphabeticallyAndFirstIndexProvided_ShouldReturnFirstStateCode() {
        try {
            stateCensusAnalyser.CensusCSVData(US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATECODE);
            CSVCensusUS[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVCensusUS[].class);
            Assert.assertEquals("AK", stateCensuses[0].stateId);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
        } catch (CSVException e) { }
    }

    @Test
    public void givenUSCensusStateData_WhenSortedAlphabeticallyAndLastIndexProvided_ShouldReturnLastStateCode() {
        try {
            stateCensusAnalyser.CensusCSVData(US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATECODE);
            CSVCensusUS[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVCensusUS[].class);
            Assert.assertEquals("WY", stateCensuses[stateCensuses.length -1].stateId);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
        } catch (CSVException e) { }
    }

    @Test
    public void givenUSCensusStateData_WhenSortedOnStateCodeAndNothingProvided_ShouldReturnCustomException() {
        try {
            String sortedCensusData = stateCensusAnalyser.getSortedCensusData(StateCensusAnalyser.SortingMode.STATECODE);
            CSVCensusUS[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVCensusUS[].class);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals("No Census Data", e.getMessage());
        }
    }

    @Test
    public void givenIndianCensusStateData_WhenSortedOnPopulationAndDensityAndFirstIndexProvided_ShouldReturnLowestState() {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedPopulationAndDensity();
            CSVStateCensusIndia[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVStateCensusIndia[].class);
            Assert.assertEquals("Sikkim", stateCensuses[0].state);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
        } catch (CSVException e) { }
    }

    @Test
    public void givenIndianCensusStateData_WhenSortedOnPopulationAndDensityAndLastIndexProvided_ShouldReturnHighestState() {
        try {
            stateCensusAnalyser.CensusCSVData(INDIA, INDIAN_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedPopulationAndDensity();
            CSVStateCensusIndia[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVStateCensusIndia[].class);
            Assert.assertEquals("Uttar Pradesh", stateCensuses[stateCensuses.length - 1].state);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
        } catch (CSVException e) { }
    }

    @Test
    public void givenUSCensusStateData_WhenSortedOnPopulationAndDensityAndFirstIndexProvided_ShouldReturnLowestState() {
        try {
            stateCensusAnalyser.CensusCSVData(US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedPopulationAndDensity();
            CSVCensusUS[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVCensusUS[].class);
            Assert.assertEquals("Wyoming", stateCensuses[0].state);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
        } catch (CSVException e) { }
    }

    @Test
    public void givenUSCensusStateData_WhenSortedOnPopulationAndDensityAndLastIndexProvided_ShouldReturnHighestState() {
        try {
            stateCensusAnalyser.CensusCSVData(US, US_CENSUS_CSV_FILE_PATH);
            String sortedCensusData = stateCensusAnalyser.getSortedPopulationAndDensity();
            CSVCensusUS[] stateCensuses = new Gson().fromJson(sortedCensusData, CSVCensusUS[].class);
            Assert.assertEquals("California", stateCensuses[stateCensuses.length - 1].state);
        } catch (StateCensusAnalyserException e) {
            Assert.assertEquals(StateCensusAnalyserException.ExceptionType.INCORRECT_FILE, e.type);
        } catch (CSVException e) { }
    }
}