package dto;

import com.opencsv.bean.CsvBindByName;

public class CSVStatesCodeCensusIndia {
    @CsvBindByName(column = "SrNo", required = true)
    private Integer srNo;

    @CsvBindByName(column = "StateName", required = true)
    private String stateName;

    @CsvBindByName(column = "TIN", required = true)
    private Integer tin;

    @CsvBindByName(column = "StateCode", required = true)
    private String stateCode;

    // METHODS TO GET THE DATA FROM THE CSV FILE
    public Integer getSrNo() {
        return srNo;
    }

    public String getStateName() {
        return stateName;
    }

    public Integer getTin() {
        return tin;
    }

    public String getStateCode() {
        return stateCode;
    }
}
