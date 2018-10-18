import threads.ThreadMain;

/**
 * main class for calling {@link ThreadMain}
 * class and perform actions
 */
public class MainClass {
    public static void main(String[] args) {
        ThreadMain main = new ThreadMain(5);
        main.main();
    }
}
