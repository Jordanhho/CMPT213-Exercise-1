import java.util.ArrayList;
import java.util.List;
/**
 * Created by Jordan Ho
 */

public class MinionTrackerTester {
    public static final String[] OPTIONS =
            {
                    "List minions",
                    "Add a new minion",
                    "Remove a minion",
                    "Add Evil Deed to a Minion",
                    "DEBUG: Dump objects (toString)",
                    "Exit"
            };

    public static void main (String[] args)
    {
        MinionTextMenu menu = new MinionTextMenu("Main Menu", OPTIONS);
        List<Minion> minion_list = new ArrayList<>();
        menu.display(minion_list);
    }
}
