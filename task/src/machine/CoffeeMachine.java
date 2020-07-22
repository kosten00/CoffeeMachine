package machine;

import java.util.Scanner;

class Coffee {
    int waterCost;
    int milkCost;
    int coffeeBeansCost;
    int disposableCupsCost;
    int moneyCost;

    Coffee(int waterCost, int milkCost, int coffeeBeansCost, int disposableCupsCost, int moneyCost) {
        this.waterCost = waterCost;
        this.milkCost = milkCost;
        this.coffeeBeansCost = coffeeBeansCost;
        this.disposableCupsCost = disposableCupsCost;
        this.moneyCost = moneyCost;
    }
}

class CoffeeMachineLogic {
    static int waterAmount = 400;
    static int milkAmount = 540;
    static int coffeeBeansAmount = 120;
    static int disposableCupsCount = 9;
    static int moneyAmount = 550;

    public static boolean isAffordable(Coffee coffee) {
        if (coffee.waterCost > waterAmount) {
            sayWhatsMissing("water");
            return false;
        }

        if (coffee.milkCost > milkAmount) {
            sayWhatsMissing("milk");
            return false;
        }

        if (coffee.coffeeBeansCost > coffeeBeansAmount) {
            sayWhatsMissing("coffee beans");
            return false;
        }

        if (coffee.disposableCupsCost > disposableCupsCount) {
            sayWhatsMissing("disposable cups");
            return false;
        }

        if (coffee.moneyCost > moneyAmount) {
            sayWhatsMissing("money");
            return false;
        }

        return true;
    }

    private static void sayWhatsMissing(String resource) {
        System.out.println("Sorry, not enough " + resource + "!");
        System.out.println();
    }

    public static void printCurrentAmounts() {
        System.out.println();
        System.out.println(
                "The coffee machine has:\n" +
                        waterAmount + " of water\n" +
                        milkAmount + " of milk\n" +
                        coffeeBeansAmount + " of coffee beans\n" +
                        disposableCupsCount + " of disposable cups\n" +
                        "$" + moneyAmount + " of money"
        );
        System.out.println();
    }

    public static void buyCoffee(Coffee coffee) {
        System.out.println("I have enough resources, making you a coffee!");
        System.out.println();

        waterAmount -= coffee.waterCost;
        milkAmount -= coffee.milkCost;
        coffeeBeansAmount -= coffee.coffeeBeansCost;
        disposableCupsCount -= coffee.disposableCupsCost;
        moneyAmount += coffee.moneyCost;
    }
}

public class CoffeeMachine {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Coffee espresso = new Coffee(250, 0, 16, 1, 4);
        Coffee latte = new Coffee(350, 75, 20, 1, 7);
        Coffee cappuccino = new Coffee(200, 100, 12, 1, 6);


        while (true) {
            System.out.println("Write action (buy, fill, take, remaining, exit): ");
            String userInput = scanner.next();

            if (userInput.equals("exit")) {
                break;
            }

            switch (userInput) {
                case "buy":
                    System.out.println();
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");

                    String command = scanner.next();

                    if (command.equals("back")) {
                        break;
                    }

                    int coffeeType = Integer.parseInt(command);

                    if (coffeeType == 1 && CoffeeMachineLogic.isAffordable(espresso)) {
                        CoffeeMachineLogic.buyCoffee(espresso);
                    } else if (coffeeType == 2 && CoffeeMachineLogic.isAffordable(latte)) {
                        CoffeeMachineLogic.buyCoffee(latte);
                    } else if (coffeeType == 3 && CoffeeMachineLogic.isAffordable(cappuccino)) {
                        CoffeeMachineLogic.buyCoffee(cappuccino);
                    }

                    break;
                case "fill":
                    System.out.println();
                    System.out.println("Write how many ml of water do you want to add: ");
                    int addedWaterAmount = scanner.nextInt();
                    CoffeeMachineLogic.waterAmount += addedWaterAmount;

                    System.out.println("Write how many ml of milk do you want to add: ");
                    int addedMilkAmount = scanner.nextInt();
                    CoffeeMachineLogic.milkAmount += addedMilkAmount;

                    System.out.println("Write how many grams of coffee beans do you want to add: ");
                    int addedCoffeeBeansAmount = scanner.nextInt();
                    CoffeeMachineLogic.coffeeBeansAmount += addedCoffeeBeansAmount;

                    System.out.println("Write how many disposable cups of coffee do you want to add: ");
                    int addedCupsCount = scanner.nextInt();
                    CoffeeMachineLogic.disposableCupsCount += addedCupsCount;

                    break;
                case "take":
                    System.out.println();
                    System.out.println("I gave you $" + CoffeeMachineLogic.moneyAmount);
                    System.out.println();
                    CoffeeMachineLogic.moneyAmount = 0;

                    break;
                case "remaining":
                    CoffeeMachineLogic.printCurrentAmounts();
            }
        }
    }
}
