package ru.Bochkarev.Main;

import ru.Bochkarev.Chek.Chek;
import ru.Bochkarev.City.City;
import ru.Bochkarev.City.CityDiff;
import ru.Bochkarev.City.NewCity;
import ru.Bochkarev.City.Route;
import ru.Bochkarev.Fraction.Fraction;

import java.util.Arrays;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

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

        Chek chek = new Chek();
        Scanner scan = new Scanner(System.in);

        if (scan.hasNextInt()) {
            int choice = scan.nextInt();

            switch (choice) {

                case 1: {
                    System.out.println("Задание 1. №4");

                    Scanner scanner = new Scanner(System.in);

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

                    Scanner scanner = new Scanner(System.in);

                    // Создаём карту городов
                    Map<String, City> cities = new HashMap<>();

                    System.out.print("Введите название города: ");
                    String firstCity = scanner.nextLine();
                    City cityF = new City(firstCity);
                    cities.put(firstCity, cityF);
                    System.out.println("Город " + firstCity + " создан.");

                    while (true) {
                        System.out.println("Выберите действие:");
                        System.out.println("1. Создать новый город");
                        System.out.println("2. Добавить дорогу между городами");
                        System.out.println("3. Удалить дорогу между городами");
                        System.out.println("4. Показать все города");
                        System.out.println("5. Выйти");


                        int choose = chek.readInt(scanner);
                        scanner.nextLine();  // Очистить буфер после nextInt()

                        switch (choose) {
                            case 1: {
                                System.out.print("Введите название города: ");
                                String cityName = scanner.nextLine();
                                if (!cities.containsKey(cityName)) { // Проверяем на уникальность
                                    City city = new City(cityName);
                                    cities.put(cityName, city);
                                    System.out.println("Город " + cityName + " создан.");
                                } else {
                                    System.out.println("Город с таким названием уже существует.");
                                }
                                break;
                            }
                            case 2: {
                                System.out.print("Введите название первого города: ");
                                String city1Name = scanner.nextLine();
                                System.out.print("Введите название второго города: ");
                                String city2Name = scanner.nextLine();

                                if (cities.containsKey(city1Name) && cities.containsKey(city2Name)) {
                                    System.out.print("Введите расстояние между городами: ");
                                    int distance = chek.readInt(scanner);
                                    scanner.nextLine();  // Очистка буфера
                                    City city1 = cities.get(city1Name);
                                    City city2 = cities.get(city2Name);
                                    city1.addRoad(city2, distance);
                                } else {
                                    System.out.println("Один или оба города не существуют.");
                                }
                                break;
                            }
                            case 3: {
                                System.out.print("Введите название первого города: ");
                                String cityToRemove1 = scanner.nextLine();
                                System.out.print("Введите название второго города: ");
                                String cityToRemove2 = scanner.nextLine();

                                if (cities.containsKey(cityToRemove1) && cities.containsKey(cityToRemove2)) {
                                    City city1ToRemove = cities.get(cityToRemove1);
                                    City city2ToRemove = cities.get(cityToRemove2);
                                    city1ToRemove.removeRoad(city2ToRemove);
                                } else {
                                    System.out.println("Один или оба города не существуют.");
                                }
                                break;
                            }
                            case 4: {
                                System.out.println("Все города и их дороги:");
                                for (City city : cities.values()) {
                                    System.out.println(city);
                                }
                                break;
                            }
                            case 5: {
                                System.out.println("Выход...");
                                scanner.close();
                                return;
                            }
                            default: {
                                System.out.println("Неверный выбор. Пожалуйста, выберите действие снова.");

                            }
                        }
                    }
                }
                case 3: {
                    System.out.println("Задание 2. №5");

                    System.out.println("Карта города:");

                    // Создаём города (A, B, C, D, E, F)
                    NewCity A = new NewCity("A");
                    NewCity B = new NewCity("B");
                    NewCity C = new NewCity("C");
                    NewCity D = new NewCity("D");
                    NewCity E = new NewCity("E");
                    NewCity F = new NewCity("F");

                    // Добавляем ребра согласно схеме

                    A.addPath(B, 5);
                    A.addPath(D, 6);
                    A.addPath(F, 1);

                    B.addPath(A, 5);
                    B.addPath(C, 3);

                    C.addPath(B, 3);
                    C.addPath(D, 4);

                    D.addPath(A, 6);
                    D.addPath(C, 4);
                    D.addPath(E, 2);

                    E.addPath(F, 2);

                    F.addPath(B, 1);
                    F.addPath(E, 2);

                    System.out.println(A);
                    System.out.println(B);
                    System.out.println(C);
                    System.out.println(D);
                    System.out.println(E);
                    System.out.println(F);

                    // Демонстрация: найти маршрут из F в D
                    Route route = new Route(F, D);
                    NewCity[] path = route.getRoute();

                    System.out.println("Путь из F -> D");
                    System.out.println("Маршрут (объект ru.Bochkarev.City.Route): " + route);
                    System.out.println("Массив городов: " + Arrays.toString(path));

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

                    Scanner scanner = new Scanner(System.in);

                    // Создаём карту городов
                    Map<String, CityDiff> cities = new HashMap<>();

                    // Инициализируем первый город
                    System.out.print("Введите название города: ");
                    String firstCity = scanner.nextLine();
                    CityDiff cityF = new CityDiff(firstCity);
                    cities.put(firstCity, cityF);
                    System.out.println("Город " + firstCity + " создан.");

                    while (true) {
                        System.out.println("Выберите действие:");
                        System.out.println("1. Создать новый город");
                        System.out.println("2. Добавить дорогу между городами");
                        System.out.println("3. Показать все города");
                        System.out.println("4. Сравнить города");
                        System.out.println("5. Выйти");

                        int choose = chek.readInt(scanner);
                        scanner.nextLine();  // Очистить буфер после nextInt()

                        switch (choose) {
                            case 1: {
                                System.out.print("Введите название города: ");
                                String cityName = scanner.nextLine();
                                if (!cities.containsKey(cityName)) { // Проверяем на уникальность
                                    CityDiff city = new CityDiff(cityName);
                                    cities.put(cityName, city);
                                    System.out.println("Город " + cityName + " создан.");
                                } else {
                                    System.out.println("Город с таким названием уже существует.");
                                }
                                break;
                            }
                            case 2: {
                                System.out.print("Введите название первого города: ");
                                String city1Name = scanner.nextLine();
                                System.out.print("Введите название второго города: ");
                                String city2Name = scanner.nextLine();

                                if (cities.containsKey(city1Name) && cities.containsKey(city2Name)) {
                                    System.out.print("Введите расстояние между городами: ");
                                    int distance = chek.readInt(scanner);
                                    scanner.nextLine();  // Очистка буфера
                                    CityDiff city1 = cities.get(city1Name);
                                    CityDiff city2 = cities.get(city2Name);
                                    city1.addWays(city2, distance);
                                } else {
                                    System.out.println("Один или оба города не существуют.");
                                }
                                break;
                            }
                            case 3: {
                                System.out.println("Все города и их дороги:");
                                for (CityDiff city : cities.values()) {
                                    System.out.println(city);
                                }
                                break;
                            }
                            case 4: {
                                System.out.print("Введите название первого города для сравнения: ");
                                String cityName1 = scanner.nextLine();
                                System.out.print("Введите название второго города для сравнения: ");
                                String cityName2 = scanner.nextLine();

                                if (cities.containsKey(cityName1) && cities.containsKey(cityName2)) {
                                    CityDiff city1 = cities.get(cityName1);
                                    CityDiff city2 = cities.get(cityName2);
                                    if (city1.equals(city2)) {
                                        System.out.println("Города одинаковые.");
                                    } else {
                                        System.out.println("Города разные.");
                                    }
                                } else {
                                    System.out.println("Один или оба города не существуют.");
                                }
                                break;
                            }
                            case 5: {
                                System.out.println("Выход...");
                                scanner.close();
                                return;
                            }
                            default: {
                                System.out.println("Неверный выбор. Пожалуйста, выберите действие снова.");
                            }
                        }
                    }
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
