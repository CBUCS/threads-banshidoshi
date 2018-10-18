package threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class YearMapper implements Runnable {

    public static final Logger logger = LogManager.getLogger(YearMapper.class);
    public static ConcurrentHashMap<Integer, Integer> mapper = new ConcurrentHashMap<Integer, Integer>();
    private int threadId = 1;
    private List<UFOSighting> UFOSightingList = new ArrayList<UFOSighting>();

    public YearMapper(List<UFOSighting> inputList, int threadID) {
        this.threadId = threadID;
        UFOSightingList.addAll(inputList);

    }


    public void run() {
        for (UFOSighting i : this.UFOSightingList) {
            int value;
            if (mapper.containsKey(i.getYear())) {
                value = mapper.get(i.getYear()) + 1;
            } else {
                mapper.put(i.getYear(), 0);
                value = 1;
            }
            mapper.put(i.getYear(), value);
        }
    }


    public void printYears() {
        for (Integer key : mapper.keySet()) {
            logger.info(key + "- " + mapper.get(key));
        }
    }


}
