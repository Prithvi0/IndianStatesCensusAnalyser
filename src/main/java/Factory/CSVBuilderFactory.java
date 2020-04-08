package Factory;

public class CSVBuilderFactory {
    public static ICSVBuilder createCSVBuilder() {
        return new CSVSingleResponsibiltyAnalyser();
    }
}