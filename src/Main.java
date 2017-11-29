import configoration.Globals;
import logic.Manager;


/**
 * Created by dvir arad on 11/29/17.
 */
public class Main {
    public static void main(String[] args) {

        Manager manager = new Manager();
        manager.createMatcher(Globals.NUMBER_OF_THREAD);
        manager.startThreadMatcher();
        manager.writeSummary();

    }
}
