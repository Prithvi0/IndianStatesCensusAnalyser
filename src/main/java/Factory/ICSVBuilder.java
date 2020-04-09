package Factory;
import Exception.CSVException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;

public interface ICSVBuilder<T> {
   public <T> List getCSVFileList(Reader reader, Class<T> csvStatesClass) throws CSVException;

   //  GENERIC METHOD TO READ AND ITERATE CSV CONTENTS
   Iterator getCSVFileIterator(Reader reader, Class csvStatesClass) throws CSVException;
}