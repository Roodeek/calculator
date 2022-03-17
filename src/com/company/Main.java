package com.company;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите пример: ");
        try {
            Calculator calc = new Calculator(scanner.nextLine().replace(" ", ""));
            System.out.println(calc.getResult());
        } catch (NumberFormatException e) {
            System.out.println(e.getMessage());
        }
    }
}