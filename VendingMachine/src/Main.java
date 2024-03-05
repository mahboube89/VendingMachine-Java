import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
    static String[] allDrinks = {
            "Cola",
            "Fanta",
            "Ice Tee",
            "Limonade",
            "Wasser"
    };
    static double price = 2.0;

    public static void main(String[] args) {
        printAppName();
        startApp();
    }


    private static void printAppName() {
        System.out.println("""
                
                |==========================|
                |    Welcome to Vending    |
                |          Machine         |
                |==========================|
                |   Discover our drinks    |
                |           and            |
                |   satisfy your thirst!   |
                |==========================|
                """);
    }


    /**
     * Starting the vending machine application by displaying the available products,
     * prompting the user to select a product, and handling the user's menu selection.
     * The user's chosen product and selection are processed accordingly.
     */
    private static void startApp() {


        showProducts(); // Display the available products to the user

        promptForProductNumber(); // Prompt the user to select a product and store the chosen product index

        handleUserSelection(); // Handle the user's menu selection based on the chosen product
    }


    /**
     * Displays the list of available products in the vending machine along with their prices.
     * The product index, name, and price are formatted and printed to the console.
     */
    private static void showProducts() {
        System.out.print("\n");
        for (int i = 0; i < allDrinks.length; i++) {
            System.out.printf("[%d] %-10s \t %.2f $ \n", i + 1, allDrinks[i], price);
        }
    }


    /**
     * Prompts the user to enter the number of the selected product in the List.
     */
    private static void promptForProductNumber() {
        int userInput = 0;
        boolean isValidInput;

        do {
            System.out.print("\nPlease enter the number of the product:  ");
            Scanner scanNumber = new Scanner(System.in);

            if (scanNumber.hasNextInt()) { // Check if the next input is an integer
                userInput = scanNumber.nextInt();
                // Check if the input is a valid product number
                isValidInput = checkValidNumber(userInput);
            } else {
                // Display an error message for non-integer inputs
                System.out.println("Error: Invalid input. Please enter a valid number.");
                isValidInput = false;
                // Consume the invalid input to avoid an infinite loop
                scanNumber.next();
            }

        } while (!isValidInput);

        // Display the user's choice after a valid input is provided
        System.out.printf("Your Choice:\n[%d] %-10s\t%.2f $ ", userInput, allDrinks[userInput - 1], price);
    }


    /**
     * Checks if the provided user input is a valid product number.
     *
     * @param userInput The user's input to be validated.
     * @return True if the input is a valid product number; otherwise, false.
     */
    private static boolean checkValidNumber(int userInput) {
        return userInput >= 1 && userInput <= allDrinks.length;
    }


    /**
     * Handles the user's menu selection
     * Displays the menu options, prompts the user to choose, and executes the selected action accordingly.<br/>
     * Options:
     * <li>Purchase: Proceeds to the buying process.</li>
     * <li>Get Change: Returns to the main menu and select again.</li>
     * <li>Exit: Cancels the transaction and exits the program.</li>
     *
     */
    private static void handleUserSelection() {
        System.out.println("\n\n=> Choose an option: ");
        System.out.println("[1] Purchase \n[2] Get Change \n[3] Exit");

        System.out.print("=> Enter your Choice: ");
        Scanner scanner = new Scanner(System.in);

        if (scanner.hasNextInt()) {
            int userChoice = scanner.nextInt();
            switch (userChoice) {
                case 1:
                    handleBuying();
                    break;
                case 2:
                    System.out.println("\nReturning to the main menu.");
                    startApp();
                    break;
                case 3:
                    System.out.println("\nTransaction canceled!!");
                    System.out.println("See you next time!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } else {
            System.out.println("Error: Invalid input. Please enter a valid number.");
            scanner.nextInt();
            handleUserSelection();
        }
    }


    /**
     * Handel the process of purchasing a drink.
     * Prompts the user to enter the required amount and completes the purchase.
     * Displays relevant messages based on the entered amount.
     */
    private static void handleBuying() {
        Scanner scanner = new Scanner(System.in);
        DecimalFormat currencyFormat = new DecimalFormat("#0.00");

        System.out.print("\n=> Please enter the money: ");
        double userMoney = scanner.nextDouble();

        while (userMoney < price && !(userMoney < 0)) {
            System.out.println("Please add " + currencyFormat.format(price - userMoney) + " €");
            userMoney += scanner.nextDouble();

        }

        if (userMoney == price) {

            System.out.println("Thanks for your purchase.");
            System.out.println("Please take your drink!\uD83E\uDD64 Enjoy your drink!\n");
            System.out.println("-------------------------------------------\n");

            handelContinueDecision();

        } else if (userMoney > 2) {

            System.out.println((userMoney - price) + " € change.");
            System.out.println("Please take your drink!\uD83E\uDD64 Enjoy your drink!");
            System.out.println("-------------------------------------------\n");
            handelContinueDecision();

        } else {

            System.out.println("Error: Insufficient funds. Please try again.");
            handleBuying();
        }
    }

    /**
     * Handles the user's decision on whether to continue with the vending machine application.
     * Prompts the user to enter 'y' (yes) or 'n' (no) to determine the next action:
     * <li>'y' or 'yes': Restarts the vending machine application.</li>
     * <li>'n' or 'no': Displays a message.</li>
     * <li>Other input: Displays an error message for invalid input.</li>
     *
     */
    private static void handelContinueDecision() {
        System.out.print("=> Do you want to continue? (y/n) : ");
        Scanner stringScanner = new Scanner(System.in);
        String userString = stringScanner.next();
        if (userString.equalsIgnoreCase("y") || userString.equalsIgnoreCase("yes")) {
            startApp();
        } else if(userString.equalsIgnoreCase("n") || userString.equalsIgnoreCase("no" )){
            System.out.println("See you next time!");
        } else {
            System.out.println("Error: Invalid input.");
        }

    }
}