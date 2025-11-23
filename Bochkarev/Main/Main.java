package ru.Bochkarev.Main;

import ru.Bochkarev.Chek.Check;
import ru.Bochkarev.City.City;
import ru.Bochkarev.City.Way;
import ru.Bochkarev.Fraction.Fraction;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;

public class Main {

    // Главный метод
    public static void main(String[] args) {

        // Возведение в степень
        if (args.length >= 2) {
        String xStr = args[0];
        String yStr = args[1];
        double result = Power(xStr, yStr);
        System.out.printf("Результат возведения " + xStr + " в степень " + yStr + ": " + result);
        System.out.println();
        }

        // Консоль
        System.out.println("Выберите задание: ");
        System.out.println("1) Задание 1. №4 ");
        System.out.println("2) Задание 1. №10 ");
        System.out.println("3) Задание 2. №5 ");
        System.out.println("4) Задание 5. №1");
        System.out.println("5) Задание 6. №5");
        System.out.println("6) Задание 8. №3");
        System.out.println("Выберите задание: ");

        Check chek = new Check();
        Scanner scan = new Scanner(System.in);
        Scanner scanner = new Scanner(System.in);

        if (scan.hasNextInt()) {
            int choice = scan.nextInt();

            switch (choice) {

                case 1: {
                    System.out.println("Задание 1. №4");

                    try {
                        // Первая дробь
                        System.out.print("Введите числитель: ");
                        int numerator = chek.readInt(scanner);
                        System.out.print("Введите знаменатель: ");
                        int denominator = chek.readInt(scanner);
                        Fraction fraction = new Fraction(numerator, denominator);
                        System.out.println("Создана дробь: " + fraction);

                        // Вторая дробь
                        System.out.print("Введите числитель второй дроби: ");
                        int numerator2 = chek.readInt(scanner);
                        System.out.print("Введите знаменатель второй дроби: ");
                        int denominator2 = chek.readInt(scanner);
                        Fraction fraction2 = new Fraction(numerator2, denominator2);
                        System.out.println("Создана дробь: " + fraction2);

                        // Операции с дробями
                        System.out.println("Сложение дробей: " + fraction.add(fraction2));
                        System.out.println("Вычитание дробей: " + fraction.subtract(fraction2));
                        System.out.println("Умножение дробей: " + fraction.multiply(fraction2));
                        System.out.println("Деление дробей: " + fraction.divide(fraction2));

                        // Операции с целым числом
                        System.out.print("Введите целое число для операции: ");
                        int number = chek.readInt(scanner);

                        System.out.println("Сложение с числом: " + fraction.add(number));
                        System.out.println("Вычитание с числом: " + fraction.subtract(number));
                        System.out.println("Умножение на число: " + fraction.multiply(number));
                        System.out.println("Деление на число: " + fraction.divide(number));

                        // Преобразование в разные типы
                        System.out.println("Реализация методов Number: ");
                        System.out.println("intValue: " + fraction.intValue());
                        System.out.println("longValue: " + fraction.longValue());
                        System.out.println("floatValue: " + fraction.floatValue());
                        System.out.println("doubleValue: " + fraction.doubleValue());

                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }

                    break;
                }
                case 2: {
                    System.out.println("Задание 1. №10");

                    System.out.print("Сколько городов создать? ");
                    int cityCount = chek.readInt(scanner);

                    if (cityCount < 2) {
                        System.out.println("Ошибка: для работы с городами нужно минимум 2 города");
                        break;
                    }

                    City[] cities = new City[cityCount];
                    for (int i = 0; i < cityCount; i++) {
                        System.out.print("Введите название города " + (i + 1) + ": ");
                        String cityName = scanner.nextLine();

                        if (cityName == null || cityName.trim().isEmpty()) {
                            System.out.println("Ошибка: название города не может быть пустым");
                            i--;
                            continue;
                        }

                        try {
                            cities[i] = new City(cityName);
                        } catch (Exception e) {
                            System.out.println("Ошибка создания города: " + e.getMessage());
                            i--;
                        }
                    }

                    System.out.print("Сколько дорог добавить? ");
                    int roadCount = chek.readInt(scanner);

                    for (int i = 0; i < roadCount; i++) {
                        System.out.println("Дорога " + (i + 1) + ":");

                        System.out.print("Введите номер первого города (1-" + cityCount + "): ");
                        int city1Index = chek.readInt(scanner) - 1;

                        if (city1Index < 0 || city1Index >= cityCount) {
                            System.out.println("Ошибка: номер города должен быть от 1 до " + cityCount);
                            i--;
                            continue;
                        }

                        System.out.print("Введите номер второго города (1-" + cityCount + "): ");
                        int city2Index = chek.readInt(scanner) - 1;

                        if (city2Index < 0 || city2Index >= cityCount) {
                            System.out.println("Ошибка: номер города должен быть от 1 до " + cityCount);
                            i--;
                            continue;
                        }

                        if (city1Index == city2Index) {
                            System.out.println("Ошибка: нельзя создать дорогу из города в себя");
                            i--;
                            continue;
                        }

                        System.out.print("Введите расстояние: ");
                        int distance = chek.readInt(scanner);

                        try {
                            cities[city1Index].addWays(cities[city2Index], distance);
                            System.out.println("Дорога добавлена успешно!");
                        } catch (Exception e) {
                            System.out.println("Ошибка: " + e.getMessage());
                            i--;
                        }
                    }

                    System.out.println("Результат:");
                    City.printRoads(cities);

                    break;
                }
                case 3: {
                    System.out.println("Задание 2. №5");

                    City A = new City("A");
                    City B = new City("B");
                    City C = new City("C");
                    City D = new City("D");
                    City E = new City("E");
                    City F = new City("F");

                    try {
                        A.addWays(B, 5);
                        A.addWays(D, 6);
                        A.addWays(F, 1);
                        B.addWays(C, 3);
                        C.addWays(D, 4);
                        D.addWays(E, 2);
                        E.addWays(F, 2);
                    } catch (Exception e) {
                        System.out.println("Ошибка создания карты: " + e.getMessage());
                        break;
                    }

                    System.out.println("Карта городов:");
                    City.printRoads(A, B, C, D, E, F);

                    System.out.print("Введите номер начального города (A=1, B=2, C=3, D=4, E=5, F=6): ");
                    int startIndex = chek.readInt(scanner) - 1;

                    System.out.print("Введите номер конечного города (A=1, B=2, C=3, D=4, E=5, F=6): ");
                    int endIndex = chek.readInt(scanner) - 1;

                    City[] allCities = {A, B, C, D, E, F};

                    if (startIndex < 0 || startIndex >= allCities.length || endIndex < 0 || endIndex >= allCities.length) {
                        System.out.println("Ошибка: номер города должен быть от 1 до " + allCities.length);
                        break;
                    }

                    try {
                        Way route = new Way(allCities[startIndex], allCities[endIndex]);
                        System.out.println("Маршрут: " + route.toString());
                    } catch (Exception e) {
                        System.out.println("Ошибка создания маршрута: " + e.getMessage());
                    }

                    break;
                }
                case 4:
                {
                    System.out.println("Задание 5. №1");

                    try {
                        // Создание объектов
                        Fraction f1 = new Fraction(3, 5);
                        Fraction f2 = new Fraction(49, 12);
                        Fraction f3 = new Fraction(3, 2);
                        Fraction f4 = new Fraction(1, 3);

                        // Выполнение сложений
                        double result1 = Fraction.sum(2, f1, 2.3); // 2 + 3/5 + 2.3
                        double result2 = Fraction.sum(3.6, f2, 3, f3); // 3.6 + 49/12 + 3 + 3/2
                        double result3 = Fraction.sum(f4, 1); // 1/3 + 1

                        // Вывод результатов
                        System.out.println("Результат 1: " + result1);
                        System.out.println("Результат 2: " + result2);
                        System.out.println("Результат 3: " + result3);

                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }

                    break;

                }
                case 5: {
                    System.out.println("Задание 6. №5");

                    System.out.print("Сколько городов создать для сравнения? ");
                    int compareCount = chek.readInt(scanner);

                    if (compareCount < 2) {
                        System.out.println("Для сравнения нужно минимум 2 города!");
                        break;
                    }

                    City[] compareCities = new City[compareCount];
                    for (int i = 0; i < compareCount; i++) {
                        System.out.print("Введите название города " + (i + 1) + ": ");
                        String name = scanner.nextLine();

                        if (name == null || name.trim().isEmpty()) {
                            System.out.println("Ошибка: название города не может быть пустым");
                            i--;
                            continue;
                        }

                        compareCities[i] = new City(name);
                    }

                    System.out.println("Созданные города:");
                    for (int i = 0; i < compareCities.length; i++) {
                        System.out.println((i + 1) + ". " + compareCities[i].getName());
                    }

                    System.out.println("Добавление дорог к городу " + compareCities[0].getName() + " ---");
                    System.out.print("Сколько дорог добавить? ");
                    int firstRoads = chek.readInt(scanner);

                    for (int i = 0; i < firstRoads; i++) {
                        System.out.println("Дорога " + (i + 1) + ":");

                        StringBuilder availableCities = new StringBuilder();
                        for (int j = 1; j < compareCount; j++) {
                            if (!compareCities[0].hasWayTo(compareCities[j])) {
                                if (!availableCities.isEmpty()) availableCities.append(", ");
                                availableCities.append(j + 1);
                            }
                        }

                        if (availableCities.isEmpty()) {
                            System.out.println("Нет доступных городов для подключения!");
                            break;
                        }

                        System.out.print("К какому городу подключить? (введите номер " + availableCities + "): ");
                        int targetIndex = chek.readInt(scanner) - 1;

                        if (targetIndex <= 0 || targetIndex >= compareCount || compareCities[0].hasWayTo(compareCities[targetIndex])) {
                            System.out.println("Ошибка: можно подключать только к городам " + availableCities);
                            i--;
                            continue;
                        }

                        System.out.print("Введите расстояние: ");
                        int dist = chek.readInt(scanner);

                        try {
                            compareCities[0].addWays(compareCities[targetIndex], dist);
                            System.out.println("Дорога к " + compareCities[targetIndex].getName() + " добавлена");
                        } catch (Exception e) {
                            System.out.println("Ошибка: " + e.getMessage());
                            i--;
                        }
                    }

                    System.out.println("Добавление дорог к городу " + compareCities[1].getName());
                    System.out.print("Сколько дорог добавить? ");
                    int secondRoads = chek.readInt(scanner);

                    for (int i = 0; i < secondRoads; i++) {
                        System.out.println("Дорога " + (i + 1) + ":");

                        StringBuilder availableCities = new StringBuilder();
                        for (int j = 0; j < compareCount; j++) {
                            if (j != 1 && !compareCities[1].hasWayTo(compareCities[j])) {
                                if (!availableCities.isEmpty()) availableCities.append(", ");
                                availableCities.append(j + 1);
                            }
                        }

                        if (availableCities.isEmpty()) {
                            System.out.println("Нет доступных городов для подключения!");
                            break;
                        }

                        System.out.print("К какому городу подключить? (введите номер " + availableCities + "): ");
                        int targetIndex = chek.readInt(scanner) - 1;

                        if (targetIndex < 0 || targetIndex >= compareCount || targetIndex == 1 || compareCities[1].hasWayTo(compareCities[targetIndex])) {
                            System.out.println("Ошибка: можно подключать только к городам " + availableCities);
                            i--;
                            continue;
                        }

                        System.out.print("Введите расстояние: ");
                        int dist = chek.readInt(scanner);

                        try {
                            compareCities[1].addWays(compareCities[targetIndex], dist);
                            System.out.println("✓ Дорога к " + compareCities[targetIndex].getName() + " добавлена");
                        } catch (Exception e) {
                            System.out.println("✗ Ошибка: " + e.getMessage());
                            i--;
                        }
                    }

                    System.out.println("Результат");
                    City.printRoads(compareCities);

                    System.out.println("Сравнение");
                    System.out.println(compareCities[0].getName() + " vs " + compareCities[3].getName() + ":");
                    System.out.println("• sameWays (по путям): " + compareCities[0].sameWays(compareCities[3]));
                    System.out.println("• equalsByWays (универсальное): " + compareCities[0].equalsByWays(compareCities[3]));
                    System.out.println("• equals (по имени): " + compareCities[0].equals(compareCities[3]));

                    break;
                }
                case 6: {
                    System.out.println("Задание 8. №3");

                    Fraction f1 = new Fraction(3, 5);
                    Fraction f2 = f1.clone(); // Клонируем f1

                    System.out.println("Оригинал: " + f1);
                    System.out.println("Клон: " + f2);

                    // Проверим, что это разные объекты
                    System.out.println("Ссылки равны? " + (f1 == f2)); // false

                    break;
                }
                default: {
                    break;
                }
            }
        }
    }

    public static double Power(String x, String y) {
        int x1 = parseInt(x);
        int y1 = parseInt(y);
        return pow(x1, y1);
    }
}
