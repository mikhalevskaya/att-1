package main.java.edu.csf.oop.java;

import lib.my.MyHMultiSet;
import lib.my.MyHSet;

import java.util.Scanner;

public class GUI {
    public void demoInterfaceString(MyHSet<String> hSet) {
        Scanner scanner = new Scanner(System.in);
        print();
        String line = scanner.nextLine();
        switch (line) {
            case "1" -> {
                System.out.println("Введите ключ: ");
                String key = scanner.nextLine();
                hSet.add(key);
            }
            case "2" -> {
                System.out.println("Введите ключ");
                String key2 = scanner.nextLine();
                hSet.remove(key2);
            }

            case "3" -> {
                System.out.println("Введите ключ");
                String key3 = scanner.nextLine();
                if (hSet.contains(key3)) {
                    System.out.println("Ключ " + key3 + " найден!");
                } else {
                    System.out.println("Ключ не найден!");
                }
            }

            case "4" -> System.out.println(hSet.toString());

            case "5" -> System.exit(0);
        }
        demoInterfaceString(hSet);
    }

    public void demoInteger(MyHSet<Integer> hSet) {
        Scanner scanner = new Scanner(System.in);
        print();
        String line = scanner.nextLine();
        switch (line) {
            case "1" -> {
                System.out.println("Введите ключ:");
                String key = scanner.nextLine();
                hSet.add(Integer.parseInt(key));
            }
            case "2" -> {
                System.out.println("Введите ключ");
                String key2 = scanner.nextLine();
                hSet.remove(Integer.parseInt(key2));
            }

            case "3" -> {
                System.out.println("Введите ключ");
                String key3 = scanner.nextLine();
                if (hSet.contains(Integer.parseInt(key3))) {
                    System.out.println("Ключ " + key3 + " найден!");
                } else {
                    System.out.println("Ключ не найден!");
                }
            }

            case "4" -> System.out.println(hSet.toString());

            case "5" -> System.exit(0);
        }
        demoInteger(hSet);
    }

    public void demoDouble(MyHSet<Double> hSet) {
        Scanner scanner = new Scanner(System.in);
        print();
        String line = scanner.nextLine();
        switch (line) {
            case "1" -> {
                System.out.println("Введите ключ:");
                String key = scanner.nextLine();
                hSet.add(Double.parseDouble(key));
            }
            case "2" -> {
                System.out.println("Введите ключ");
                String key2 = scanner.nextLine();
                hSet.remove(Double.parseDouble(key2));
            }

            case "3" -> {
                System.out.println("Введите ключ");
                String key3 = scanner.nextLine();
                if (hSet.contains(Double.parseDouble(key3))) {
                    System.out.println("Ключ " + key3 + " найден!");
                } else {
                    System.out.println("Ключ не найден!");
                }
            }

            case "4" -> System.out.println(hSet.toString());

            case "5" -> System.exit(0);
        }
        demoDouble(hSet);
    }


    public void demoLong(MyHSet<Long> hSet) {
        Scanner scanner = new Scanner(System.in);
        print();
        String line = scanner.nextLine();
        switch (line) {
            case "1" -> {
                System.out.println("Введите ключ:");
                String key = scanner.nextLine();
                hSet.add(Long.parseLong(key));
            }
            case "2" -> {
                System.out.println("Введите ключ");
                String key2 = scanner.nextLine();
                hSet.remove(Long.parseLong(key2));
            }

            case "3" -> {
                System.out.println("Введите ключ");
                String key3 = scanner.nextLine();
                if (hSet.contains(Long.parseLong(key3))) {
                    System.out.println("Ключ " + key3 + " найден!");
                } else {
                    System.out.println("Ключ не найден!");
                }
            }

            case "4" -> System.out.println(hSet.toString());

            case "5" -> System.exit(0);
        }
        demoLong(hSet);
    }

    public void demoFloat(MyHSet<Float> hSet) {
        Scanner scanner = new Scanner(System.in);
        print();
        String line = scanner.nextLine();
        switch (line) {
            case "1" -> {
                System.out.println("Введите ключ:");
                String key = scanner.nextLine();
                hSet.add(Float.parseFloat(key));
            }
            case "2" -> {
                System.out.println("Введите ключ");
                String key2 = scanner.nextLine();
                hSet.remove(Float.parseFloat(key2));
            }

            case "3" -> {
                System.out.println("Введите ключ");
                String key3 = scanner.nextLine();
                if (hSet.contains(Float.parseFloat(key3))) {
                    System.out.println("Ключ " + key3 + " найден!");
                } else {
                    System.out.println("Ключ не найден!");
                }
            }

            case "4" -> System.out.println(hSet.toString());

            case "5" -> System.exit(0);
        }
        demoFloat(hSet);
    }

    public void print() {
        System.out.println("Выберите действие:");
        System.out.println("1. Добавить");
        System.out.println("2. Удалить");
        System.out.println("3. Поиск");
        System.out.println("4. Распечатать коллекцию");
        System.out.println("5. Выйти");
    }


    public void printHashSet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите тип ключа");
        types();
        String line = scanner.nextLine();
        switch (line) {
            case "1" -> {
                MyHSet<String> setString = new MyHSet<>();
                demoInterfaceString(setString);
            }

            case "2" -> {
                MyHSet<Integer> setInt = new MyHSet<>();
                demoInteger(setInt);
            }

            case "3" -> {
                MyHSet<Double> setDouble = new MyHSet<>();
                demoDouble(setDouble);
            }

            case "4" -> {
                MyHSet<Long> setLong = new MyHSet<>();
                demoLong(setLong);
            }

            case "5" -> {
                MyHSet<Float> setFloat = new MyHSet<>();
                demoFloat(setFloat);
            }
        }
    }

    public void types() {
        System.out.println("1. String");
        System.out.println("2. Integer");
        System.out.println("3. Double");
        System.out.println("4. Long");
        System.out.println("5. Float");
    }

    public void demoInterfaceString(MyHMultiSet<String> hMultiSet) {
        Scanner scanner = new Scanner(System.in);
        print();
        String line = scanner.nextLine();
        switch (line) {
            case "1" -> {
                System.out.println("Введите ключ: ");
                String key = scanner.nextLine();
                hMultiSet.add(key);
            }
            case "2" -> {
                System.out.println("Введите ключ");
                String key2 = scanner.nextLine();
                hMultiSet.remove(key2);
            }

            case "3" -> {
                System.out.println("Введите ключ");
                String key3 = scanner.nextLine();
                if (hMultiSet.contains(key3)) {
                    System.out.println("Ключ " + key3 + " найден!");
                } else {
                    System.out.println("Ключ не найден!");
                }
            }

            case "4" -> System.out.println(hMultiSet.toString());

            case "5" -> System.exit(0);
        }
        demoInterfaceString(hMultiSet);
    }

    public void demoInteger(MyHMultiSet<Integer> hMultiSet) {
        Scanner scanner = new Scanner(System.in);
        print();
        String line = scanner.nextLine();
        switch (line) {
            case "1" -> {
                System.out.println("Введите ключ:");
                String key = scanner.nextLine();
                hMultiSet.add(Integer.parseInt(key));
            }
            case "2" -> {
                System.out.println("Введите ключ");
                String key2 = scanner.nextLine();
                hMultiSet.remove(Integer.parseInt(key2));
            }

            case "3" -> {
                System.out.println("Введите ключ");
                String key3 = scanner.nextLine();
                if (hMultiSet.contains(Integer.parseInt(key3))) {
                    System.out.println("Ключ " + key3 + " найден!");
                } else {
                    System.out.println("Ключ не найден!");
                }
            }

            case "4" -> System.out.println(hMultiSet.toString());

            case "5" -> System.exit(0);
        }
        demoInteger(hMultiSet);
    }

    public void demoDouble(MyHMultiSet<Double> hMultiSet) {
        Scanner scanner = new Scanner(System.in);
        print();
        String line = scanner.nextLine();
        switch (line) {
            case "1" -> {
                System.out.println("Введите ключ:");
                String key = scanner.nextLine();
                hMultiSet.add(Double.parseDouble(key));
            }
            case "2" -> {
                System.out.println("Введите ключ");
                String key2 = scanner.nextLine();
                hMultiSet.remove(Double.parseDouble(key2));
            }

            case "3" -> {
                System.out.println("Введите ключ");
                String key3 = scanner.nextLine();
                if (hMultiSet.contains(Double.parseDouble(key3))) {
                    System.out.println("Ключ " + key3 + " найден!");
                } else {
                    System.out.println("Ключ не найден!");
                }
            }

            case "4" -> System.out.println(hMultiSet.toString());

            case "5" -> System.exit(0);
        }
        demoDouble(hMultiSet);
    }


    public void demoLong(MyHMultiSet<Long> hMultiSet) {
        Scanner scanner = new Scanner(System.in);
        print();
        String line = scanner.nextLine();
        switch (line) {
            case "1" -> {
                System.out.println("Введите ключ:");
                String key = scanner.nextLine();
                hMultiSet.add(Long.parseLong(key));
            }
            case "2" -> {
                System.out.println("Введите ключ");
                String key2 = scanner.nextLine();
                hMultiSet.remove(Long.parseLong(key2));
            }

            case "3" -> {
                System.out.println("Введите ключ");
                String key3 = scanner.nextLine();
                if (hMultiSet.contains(Long.parseLong(key3))) {
                    System.out.println("Ключ " + key3 + " найден!");
                } else {
                    System.out.println("Ключ не найден!");
                }
            }

            case "4" -> System.out.println(hMultiSet.toString());

            case "5" -> System.exit(0);
        }
        demoLong(hMultiSet);
    }

    public void demoFloat(MyHMultiSet<Float> hMultiSet) {
        Scanner scanner = new Scanner(System.in);
        print();
        String line = scanner.nextLine();
        switch (line) {
            case "1" -> {
                System.out.println("Введите ключ:");
                String key = scanner.nextLine();
                hMultiSet.add(Float.parseFloat(key));
            }
            case "2" -> {
                System.out.println("Введите ключ");
                String key2 = scanner.nextLine();
                hMultiSet.remove(Float.parseFloat(key2));
            }

            case "3" -> {
                System.out.println("Введите ключ");
                String key3 = scanner.nextLine();
                if (hMultiSet.contains(Float.parseFloat(key3))) {
                    System.out.println("Ключ " + key3 + " найден!");
                } else {
                    System.out.println("Ключ не найден!");
                }
            }

            case "4" -> System.out.println(hMultiSet.toString());

            case "5" -> System.exit(0);
        }
        demoFloat(hMultiSet);
    }

    public void printHashMultiSet() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Выберите тип ключа");
        types();
        String line = scanner.nextLine();
        switch (line) {
            case "1" -> {
                MyHMultiSet<String> setString = new MyHMultiSet<>();
                demoInterfaceString(setString);
            }

            case "2" -> {
                MyHMultiSet<Integer> setInt = new MyHMultiSet<>();
                demoInteger(setInt);
            }

            case "3" -> {
                MyHMultiSet<Double> setDouble = new MyHMultiSet<>();
                demoDouble(setDouble);
            }

            case "4" -> {
                MyHMultiSet<Long> setLong = new MyHMultiSet<>();
                demoLong(setLong);
            }

            case "5" -> {
                MyHMultiSet<Float> setFloat = new MyHMultiSet<>();
                demoFloat(setFloat);
            }
        }
    }
}
