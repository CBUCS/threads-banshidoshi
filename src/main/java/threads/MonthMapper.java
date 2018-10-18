package threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * this class is used for getting months happened
 * for UFO
 */
public class MonthMapper implements Runnable {

    public static final Logger logger = LogManager.getLogger(MonthMapper.class);
    public static ConcurrentHashMap<Integer, Integer> mapper = new ConcurrentHashMap<Integer, Integer>();
    private int threadId = 1;
    private List<UFOSighting> UFOSightingList = new ArrayList<UFOSighting>();

    public MonthMapper(List<UFOSighting> input, int threadId) {
        this.threadId = threadId;
        UFOSightingList.addAll(input);
    }

    public void run() {

        for (UFOSighting i : this.UFOSightingList) {
            int value;
            if (mapper.containsKey(i.getMonth())) {
                value = mapper.get(i.getMonth()) + 1;
            } else {
                mapper.put(i.getMonth(), 0);
                value = 1;
            }
            mapper.put(i.getMonth(), value);
        }
    }

    public void printMonths() {
        for (Integer key : mapper.keySet()) {
            logger.info(key + "- " + mapper.get(key));
        }
    }

}
