package threads;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class TimeMapper implements Runnable {

    private static final Logger logger = LogManager.getLogger(TimeMapper.class);
    private static ConcurrentHashMap<Integer, Integer> mapper = new ConcurrentHashMap<Integer, Integer>();
    private int threadId = 1;
    private List<UFOSighting> UFOSightingList = new ArrayList<UFOSighting>();

    public TimeMapper(List<UFOSighting> input, int pID) {
        this.threadId = pID;
        UFOSightingList.addAll(input);
    }

    public int getId() {
        return this.threadId;
    }

    public void run() {
        for (UFOSighting i : this.UFOSightingList) {
            int value;
            int key = 0;
            if ((i.getHour() > 8 && i.getHour() < 12) || (i.getHour() == 8 && i.getMin() >= 1) || (i.getHour() == 12 && i.getMin() == 0)) {
                key = 1;
            } else if ((i.getHour() > 12 && i.getHour() < 16) || (i.getHour() == 12 && i.getMin() >= 1) || (i.getHour() == 16 && i.getMin() == 0)) {
                key = 2;
            } else if ((i.getHour() > 16 && i.getHour() < 20) || (i.getHour() == 16 && i.getMin() >= 1) || (i.getHour() == 20 && i.getMin() == 0)) {
                key = 3;
            } else if ((i.getHour() > 20 && i.getHour() <= 23) || (i.getHour() == 20 && i.getMin() >= 1) || (i.getHour() == 0 && i.getMin() == 0) || (i.getHour() == 24 && i.getMin() == 0)) {
                key = 4;
            } else if ((i.getHour() > 0 && i.getHour() < 8) || (i.getHour() == 0 && i.getMin() >= 1) || (i.getHour() == 8 && i.getMin() == 0)) {
                key = 5;
            }
            if (key == 0)
                logger.error(i.getStrDateTime());
            if (mapper.containsKey(key)) {
                value = mapper.get(key) + 1;
            } else {
                mapper.put(key, 0);
                value = 1;
            }
            mapper.put(key, value);
        }
    }

    public void printAllTiimings() {
        for (Integer key : mapper.keySet()) {

            switch (key) {
                case 0:
                    logger.info("UFOSightings between 8:01-12:00 - " + mapper.get(key));
                    break;
                case 1:
                    logger.info("UFOSightings between 12:01-16:00 - " + mapper.get(key));
                    break;
                case 2:
                    logger.info("UFOSightings between 16:01-20:00 - " + mapper.get(key));
                    break;
                case 3:
                    logger.info("UFOSightings between 20:01-00:00 - " + mapper.get(key));
                    break;
                case 4:
                    logger.info("UFOSightings between 00:01-8:00 - " + mapper.get(key));
                    break;
                default:
                    break;
            }
        }
    }


}
