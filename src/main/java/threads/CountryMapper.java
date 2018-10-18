package threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * this class contains country mapper
 * where in how have seen
 */
public class CountryMapper implements Runnable {

    public static ConcurrentHashMap<String, Integer> concurrentHashMap = new ConcurrentHashMap<String, Integer>();
    public Logger logger = LogManager.getLogger(CountryMapper.class);
    private int threadId = 1;
    private List<UFOSighting> UFOSightingList = new ArrayList<UFOSighting>();

    public CountryMapper(List<UFOSighting> input, int threadId) {
        this.threadId = threadId;
        UFOSightingList.addAll(input);
    }

    public void run() {
        for (UFOSighting i : this.UFOSightingList) {
            int value;
            if (concurrentHashMap.containsKey(i.getStrCountry())) {
                value = concurrentHashMap.get(i.getStrCountry()) + 1;
            } else {
                concurrentHashMap.put(i.getStrCountry(), 0);
                value = 1;
            }
            concurrentHashMap.put(i.getStrCountry(), value);
        }
    }

    public void printCountryInfo() {
        for (String key : concurrentHashMap.keySet()) {
            if (key.equals(""))
                logger.info("Unknown Countries are " + "- " + concurrentHashMap.get(key));
            else if (key.equals("gb"))
                logger.info("UK" + "- " + concurrentHashMap.get(key));
            else
                logger.info(key + "- " + concurrentHashMap.get(key));
        }
    }
}
