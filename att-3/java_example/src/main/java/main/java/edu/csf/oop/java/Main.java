package main.java.edu.csf.oop.java;


import lib.my.MyHMultiSet;
import lib.my.MyHSet;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        GUI gui = new GUI();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите коллекцию ");
        System.out.println("1. HashSet");
        System.out.println("2. HashMultiSet");
        String line = scanner.nextLine();
        switch (line){
            case "1" -> gui.printHashSet();
            case "2" -> gui.printHashMultiSet();
        }
    }
}
