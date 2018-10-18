package threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;


/**
 * This ios used for getting state number mapper
 */
public class StateMapper implements Runnable {

    public static final Logger logger = LogManager.getLogger(StateMapper.class);
    public static ConcurrentHashMap<String, Integer> mapper = new ConcurrentHashMap<String, Integer>();
    private int threadId = 1;
    private List<UFOSighting> UFOSightingList = new ArrayList<UFOSighting>();

    public StateMapper(List<UFOSighting> input, int threadId) {
        this.threadId = threadId;
        UFOSightingList.addAll(input);
    }

    public void run() {
        for (UFOSighting i : this.UFOSightingList) {
            int value;
            if (mapper.containsKey(i.getStrState())) {
                value = mapper.get(i.getStrState()) + 1;
            } else {
                mapper.put(i.getStrState(), 0);
                value = 1;
            }
            mapper.put(i.getStrState(), value);
        }
    }


    public void printStates() {
        for (String key : mapper.keySet()) {
            if (key.equals(""))
                logger.info("Not A State" + "- " + mapper.get(key));
            else
                logger.info(key + ": " + mapper.get(key));
        }
    }

}
