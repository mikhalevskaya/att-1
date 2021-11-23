package edu.csf.oop.java.durak;

import edu.csf.oop.java.durak.play.DemoBot;
import edu.csf.oop.java.durak.play.Fool;

import java.util.Scanner;

//import java.util.logging.Logger; //моё
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;


/*import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static java. lang.System.exit;*/

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int ans = -2;
        while ((ans > 2) || (ans < 0)) {
            System.out.println("Выберите действие: ");
            System.out.println("1 - игра");
            System.out.println("2 - демонстрация");
            System.out.println("0 - выход");
            ans = scan.nextInt();
            switch (ans) {
                case 1:
                    Fool f = new Fool();
                    break;
                case 2:
                    DemoBot db = new DemoBot();
                    break;
                case 0:
                    System.out.println("До встречи!");
                default:
                    System.out.println("Ошибка ввода");

            }
        }
    }
}



