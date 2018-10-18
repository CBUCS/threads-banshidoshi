package threads;

import org.apache.commons.lang3.StringUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * this class is main thread class
 * which runs threads while checking for UFO sightings
 * based ion Country, State, Month,Year and Given timings
 */
public class ThreadMain {
    //number of threads are 5 as to show different categories
    private int numThreads = 5;

    // Hold all our threads.
    private List<Thread> threads = new ArrayList<Thread>();

    //path for the txt file under resourcw
    private File inputFile = new File("src/main/resources/scrubbed.txt");

    //this lists the UFO sighting
    private ArrayList<UFOSighting> UFOListing = new ArrayList<UFOSighting>();

    public ThreadMain(int threads) {
        this.numThreads = threads;
    }

    public void main() {
        UFOListing = new ArrayList<UFOSighting>();
        FileInputStream fis;
        BufferedReader bis;

        try {
            fis = new FileInputStream(inputFile);
            bis = new BufferedReader(new InputStreamReader(fis));

            String line = bis.readLine();

            while (line != null) {
                if (!StringUtils.isEmpty(line)) {
                    List<String> splitArray = Arrays.asList(line.split(","));
                    UFOSighting ufo = new UFOSighting(splitArray);
                    UFOListing.add(ufo);
                }
                line = bis.readLine();
            }

            bis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        StateMapper statesMapper = new StateMapper(UFOListing, 1);
        CountryMapper countryMapper = new CountryMapper(UFOListing, 2);
        MonthMapper monthsMapper = new MonthMapper(UFOListing, 3);
        YearMapper yearsMapper = new YearMapper(UFOListing, 4);
        TimeMapper timeMapper = new TimeMapper(UFOListing, 5);

        Thread threadStart = new Thread(statesMapper);
        Thread threadCountry = new Thread(countryMapper);
        Thread threadMonths = new Thread(monthsMapper);
        Thread threadYear = new Thread(yearsMapper);
        Thread threadTime = new Thread(timeMapper);

        this.threads.add(threadStart);
        this.threads.add(threadCountry);
        this.threads.add(threadMonths);
        this.threads.add(threadYear);
        this.threads.add(threadTime);

        for (Thread t : threads)
            t.start();
        try {
            for (Thread t : threads)
                t.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        statesMapper.printStates();
        countryMapper.printCountryInfo();
        monthsMapper.printMonths();
        yearsMapper.printYears();
        timeMapper.printAllTiimings();

    }
}
