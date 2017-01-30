/**
 * Created by Jordan Ho
 */

import java.util.Scanner;
import java.util.List;

/**
 * MinionTextMenu Class provides the methods for all the display text, obtain data
 */


public class MinionTextMenu {
    /**
     * @param title is the main menu title box
     * @param options is the string array of menu options
     * @param minion_list is the Minion list
     */
    private String title;
    private String[] options;
    private List<Minion> minion_list;


    /**
     * Method constructor for main menu string
     * @param title is the main menu title box string
     * @param options is the options list
     */
    public MinionTextMenu(String title, String[] options)
    {
        this.title = title;
        this.options = options;
    }


    /**
     * Method constructor for main menu string
     * @param title is the main menu title box string
     * @param minion_list is the minion list
     */
    public MinionTextMenu(String title, List<Minion> minion_list)
    {
        this.title = title;
        this.minion_list = minion_list;
    }


    /**
     * Method constructs and prints the menu box with title inside it
     * @param title is the menu title string
     */
    public void menuTitle(String title)
    {
        System.out.print("\n");
        int padding_border = 4;
        String Stars = "";
        for (int i = 0; i < title.length() + padding_border; i++)
        {
            Stars = Stars + "*";
        }
        System.out.println(Stars);
        System.out.println("* " + title + " *");
        System.out.println(Stars);
    }


    /**
     * Method to create the menu options page
     * @param options is the list of options avaliable in the title menu
     * @return returns the title string
     */
    public void menuOptions(String[] options)
    {
        for (int i = 0; i < options.length; i++)
        {
            System.out.println( i + 1 + ". " + options[i]);
        }
    }


    /**
     * Method to Display menu title
     * @param minion_list is the current list of minions
     */
    public void display(List<Minion> minion_list)
    {
        int again = 0;

        while(again != options.length)
        {
            menuTitle(title);
            menuOptions(options);
            again = getSelection(minion_list);
        }
        System.exit(0);
    }



    //===================================================================
    //=============== Main Functions ====================================
    // ==================================================================




    /**
     * Method for asking user which option to select and runs the method based on that decision
     * @param minion_list is the current list of minions
     * @return decision which is the decision the user inputted
     */
    public int getSelection(List<Minion> minion_list)
    {
        int flag = 0;
        int decision = 0;

        while(flag == 0)
        {
            String test_string = userInput("Which option do you choose? (Choose the Number): ");
            if(isNumber_int(test_string))
            {
                decision = Integer.parseInt(test_string);
                if(decision > 0 &&  decision < options.length + 1)
                {
                    flag = 1;
                }
                else
                {
                    errorMsg(0, 1, options.length);
                }
            }
            else
            {
                errorMsg(1, 1, options.length);
            }
        }
        if(decision == 1)
        {
            listMinion(minion_list);
        }
        else if(decision == 2)
        {
            addMinion(minion_list);
        }
        else if(decision == 3)
        {
            removeMinion(minion_list);
        }
        else if(decision == 4)
        {
            addAttEvilDeed(minion_list);
        }
        else if(decision == 5)
        {
            debug(minion_list);
        }
        return decision;
    }


    /**
     * Method to return a list of the current minions
     * @param minion_list is the current list of minions
     */
    public void listMinion(List<Minion> minion_list)
    {
        menuTitle("Minion List:");

        if(minion_list.size() == 0)
        {
            System.out.println("The Current Minion List is empty");
        }
        else
        {
            System.out.println("# - Name - Height - EvilDeed");

            for(int i = 0; i < minion_list.size(); i++)
            {
                System.out.println((i + 1) + ". " + minion_list.get(i).getName() + " " + minion_list.get(i).getHeightinM() + " " + minion_list.get(i).getEvilDeedCount());
            }
        }
    }


    /**
     * Method to add a minion to the list
     * @param minion_list is the current list of minions
     */
    public void addMinion(List<Minion> minion_list)
    {
        menuTitle("Add a Minion");

        int isNum = 0;
        String Minion_Name = "";
        double Minion_Height = 0;

        System.out.println("Please Enter the Following Minion's Information: ");
        Minion_Name = userInput("What is the Minion's Name?: ");

        while(isNum == 0)
        {
            String test_string = userInput("What is the Minion's height?: ");
            if(!isNumber_double(test_string))
            {
                errorMsg(1, 1, options.length);
            }

            else
            {
                Minion_Height = Double.parseDouble(test_string);
                if(Minion_Height <= 0)
                {
                    errorMsg(0, 1, options.length);
                }
                else
                {
                    isNum = 1;
                }
            }
        }
        minion_list.add(new Minion(Minion_Name, Minion_Height));
        System.out.println("The current list after addition of a minion is: ");
        listMinion(minion_list);
        space();
    }


    /**
     * Method to remove a minion to the list
     * @param minion_list is the current list of minions
     */
    public void removeMinion(List<Minion> minion_list)
    {
        menuTitle("Remove a Minion");

        int isNum = 0;
        int minion_choice = 0;

        System.out.println("Which Minion do you want to remove in the current list?: ");
        listMinion(minion_list);
        while(isNum == 0)
        {
            String test_string = userInput("Please enter the Minion Number you would like to remove (Or enter 0 to cancel): ");
            if(!isNumber_int(test_string))
            {
                errorMsg(1, 1, options.length);
            }

            else
            {
                minion_choice = Integer.parseInt(test_string);
                if(minion_choice == 0)
                {
                    isNum = 1;
                    System.out.println("Cancelled Remove action");
                }
                else if(minion_choice <= 0 || minion_choice > minion_list.size())
                {
                    errorMsg(0, 1, options.length);
                }
                else
                {
                    isNum = 1;
                    minion_list.remove(minion_choice - 1);
                    System.out.println("The current list after removal is: ");
                    listMinion(minion_list);
                }
            }
        }
        space();
    }


    /**
     * Method to increment evil deeds attributes
     * @param minion_list is the current list of minions
     */
    public void addAttEvilDeed(List<Minion> minion_list)
    {
        menuTitle("Add Evil Deed");

        int isNum = 0;
        int minion_choice = 0;

        System.out.println("Which Minion do you want to add an Evildeed to in the current list?: ");
        listMinion(minion_list);
        while(isNum == 0)
        {
            String test_string = userInput("Please enter the Minion Number you would like to increment the Evil Deed for (Or enter 0 to cancel): ");
            if(!isNumber_int(test_string))
            {
                errorMsg(1, 1, options.length);
            }

            else
            {
                minion_choice = Integer.parseInt(test_string);
                if(minion_choice == 0)
                {
                    isNum = 1;
                    System.out.println("Cancelled Increment action");
                }
                else if(minion_choice < 0 || minion_choice > minion_list.size())
                {
                    errorMsg(0, 1, options.length);
                }
                else
                {
                    isNum = 1;
                    minion_list.get(minion_choice - 1).incrementEvilDeeds();
                    System.out.println("The current list after increment of Evildeed is: ");
                    listMinion(minion_list);
                }
            }
        }
        space();
    }


    /**
     * Method to print out debug minion list
     * @param minion_list is the current list of minions
     */
    public void debug(List<Minion> minion_list) {
        menuTitle("Debug");

        int i = 1;

        System.out.println("All minion objects: ");
        for (Minion minion_it : minion_list)
        {
            System.out.println(i + ". " + minion_it.toString());
            i++;
        }
        space();
    }


    //====================================================================
    //==================== Helper Functions ==============================
    //====================================================================


    /**
     method tests if a string contains only numbers
     @param test_string which a string that is either numeric or not
     @return boolean value if string is numeric or not
     */
    public static boolean isNumber_int(String test_string)
    {
        try
        {
            int decision = Integer.parseInt(test_string);
        }
        catch(NumberFormatException Fail)
        {
            return false;
        }
        return true;
    }


    /**
     method tests if a string contains only numbers
     @param test_string which a string that is either numeric or not
     @return boolean value if string is numeric or not
     */
    public static boolean isNumber_double(String test_string)
    {
        try
        {
            double decision = Double.parseDouble(test_string);
        }
        catch(NumberFormatException Fail)
        {
            return false;
        }
        return true;
    }


    /**
     * Helper method to print incorrect input
     * @param choice 0 for range error, 1 for not a number error, anything else for other errors
     * @param min which is in this case 1
     * @param max which is the largest option number or the number of items in the list
     * prints an error msg for incorrect input
     */
    public void errorMsg(int choice, int min, int max)
    {
        if(choice == 0) //range error if choice == 0
        {
            System.out.print("Out of Range Error: Please Enter a number between " + min + " and" + max + " again: ");
        }
        else if(choice == 1) //Not a number if choice == 1
        {
            System.out.print("Invalid Input Error: Not a Number, Please Enter a number between " + min + " and" + max + " again: ");
        }
        else  //invalid input
        {
            System.out.println("Invalid Input Error: Please try again.");
        }
    }

    /**
     * Helper method to print spacing in between menus
     */
    public void space() { System.out.print("\n"); }


    /**
     * Method to obtain user Input and returned as a string
     * @return test_string a string where we can test if its a number or text only
     */
    public String userInput(String msg)
    {
        System.out.println(msg);
        System.out.print(">");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }
}
