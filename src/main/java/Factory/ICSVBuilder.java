package Factory;
import Exception.CSVException;

import java.io.Reader;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public interface ICSVBuilder<T> {
   //  GENERIC METHOD TO READ AND ITERATE CSV CONTENTS
   public Iterator getCSVFileIterator(Reader reader, Class csvStatesClass);

   public <T> List<T> getCSVFileList(Reader reader, Class<T> csvStatesClass) throws CSVException;

   public <T> Map<T,T> getCSVFileMap(Reader reader, Class<T> csvStatesClass) throws CSVException;
}