import configuration.Globals;
import logic.Manager;


/**
 * Created by Dvir Arad on 11/29/17.
 *
 * The Project responsibility is to find specific strings in a large text.
 *
 * System configuration located at configuration/Globals.java
 */
public class Main {
    public static void main(String[] args) {

        Manager manager = new Manager();
        manager.createMatcher(Globals.NUMBER_OF_THREAD);
        manager.startThreadMatcher();
        manager.writeSummary();

    }
}
