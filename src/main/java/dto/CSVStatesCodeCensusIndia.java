package dto;

import com.opencsv.bean.CsvBindByName;

public class CSVStatesCodeCensusIndia {
    @CsvBindByName(column = "SrNo", required = true)
    public Integer srNo;

    @CsvBindByName(column = "StateName", required = true)
    public String stateName;

    @CsvBindByName(column = "TIN", required = true)
    public Integer tin;

    @CsvBindByName(column = "StateCode", required = true)
    public String stateCode;

    public CSVStatesCodeCensusIndia() {
    }

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
