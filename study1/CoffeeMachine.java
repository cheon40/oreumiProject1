package project.study1;

import java.util.Scanner;

public class CoffeeMachine {
    public static void main(String[] args) {
        int money = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println("How much money do you have?");
        String moneyString = sc.nextLine();
        money = Integer.parseInt(moneyString);

        int coffeePrice = 500;

        while (money > 0) {
            System.out.println("Do you wanna coffee? Y/N");
            String yn = sc.nextLine();
            if (yn.equals("Y") || yn.equals("y")) {
                money -= coffeePrice;
                System.out.println("Here you are");
            }
        }
    }
}
