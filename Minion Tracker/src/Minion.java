/**
 * Created by Jordan Ho
 */

public class Minion
{
    /**
     * @param name is the name of the minion, can be more than one word
     * @param height is the height of the minion
     * @param num_evildeed is the number of evildeeds this minion has commited
     */
    private String name;
    private double height;
    private int num_evildeed = 0;

    /**
     * Method constructs the Minion
     * @param name is the name of the minion
     * @param height is the height of the minion
     */
    Minion (String name, double height)
    {
        this.name = name;
        this.height = height;
    }


    /**
     * Method returns Minion's name
     * @return Minion's name
     */
    public String getName()
    {
        return name;
    }


    /**
     * Method returns Evil deed Count
     * @return returns num_evildeed
     */
    public int getEvilDeedCount()
    {
        return num_evildeed;
    }


    /**
     * Method returns Minion's Height in Meters
     * @return returns the Minion's height
     */
    public double getHeightinM()
    {
        return height;
    }


    /**
     * Method Increments the number of evil_deeds of a minion
     */
    public void incrementEvilDeeds()
    {
        num_evildeed++;
    }


    /**
     * Method of toString, returns package name, class name, all attribvute names
     * @return returns string with the above
     */
    @Override
    public String toString()
    {
        return getClass().getName() + "[Name: " + name + ", Height: " + height + ", # Evildeeds: " + num_evildeed + "]";
    }
}
