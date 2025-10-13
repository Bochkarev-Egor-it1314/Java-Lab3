package ru.Bochkarev.Fraction;

import ru.Bochkarev.Main.Summable;

public final class Fraction extends Number implements Summable, Cloneable {
    private final int numerator;  // числитель
    private final int denominator; // знаменатель

    // Конструктор с числителем и знаменателем
    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен нулю.");
        }
        // Если знаменатель отрицательный, меняем знак на числителе
        if (denominator < 0) {
            this.numerator = -numerator;
            this.denominator = -denominator;
        } else {
            this.numerator = numerator;
            this.denominator = denominator;
        }
        //simplify();
    }

    // Геттеры (сеттеров нет)
    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    /*
    // Метод упрощения дроби
    private void simplify() {
        int gcd = gcd(Math.abs(numerator), Math.abs(denominator));
        numerator /= gcd;
        denominator /= gcd;
        // Переносим знак в числитель
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
    }

    // НОД
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
    */

    // Переопределение toString
    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    // Метод сложения двух дробей
    public Fraction add(Fraction other) {
        int newNumerator = this.numerator * other.denominator + this.denominator * other.numerator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    // Метод вычитания двух дробей
    public Fraction subtract(Fraction other) {
        int newNumerator = this.numerator * other.denominator - this.denominator * other.numerator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    // Метод умножения дробей
    public Fraction multiply(Fraction other) {
        int newNumerator = this.numerator * other.numerator;
        int newDenominator = this.denominator * other.denominator;
        return new Fraction(newNumerator, newDenominator);
    }

    // Метод деления дробей
    public Fraction divide(Fraction other) {
        if (other.numerator == 0) {
            throw new IllegalArgumentException("Деление на ноль невозможно.");
        }
        int newNumerator = this.numerator * other.denominator;
        int newDenominator = this.denominator * other.numerator;
        return new Fraction(newNumerator, newDenominator);
    }

    // Метод сложения дроби и целого числа
    public Fraction add(int number) {
        int newNumerator = this.numerator + number * this.denominator;
        return new Fraction(newNumerator, this.denominator);
    }

    // Метод вычитания дроби и целого числа
    public Fraction subtract(int number) {
        int newNumerator = this.numerator - number * this.denominator;
        return new Fraction(newNumerator, this.denominator);
    }

    // Метод умножения дроби и целого числа
    public Fraction multiply(int number) {
        int newNumerator = this.numerator * number;
        return new Fraction(newNumerator, this.denominator);
    }

    // Метод деления дроби на целое число
    public Fraction divide(int number) {
        if (number == 0) {
            throw new IllegalArgumentException("Деление на ноль невозможно.");
        }
        return new Fraction(this.numerator, this.denominator * number);
    }

    // Переопределение equals
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Fraction other = (Fraction) obj;
        return numerator * other.denominator == denominator * other.numerator;
    }

    // Переопределение hashCode
    @Override
    public int hashCode() {
        return 31 * numerator + denominator;
    }

    // Переопределение абстрактных методов Number
    @Override
    public int intValue() {
        return numerator / denominator; // возвращаем целую часть
    }

    @Override
    public long longValue() {
        return (long) numerator / denominator; // возвращаем целую часть как long
    }

    @Override
    public float floatValue() {
        return (float) numerator / denominator; // возвращаем значение как float
    }

    @Override
    public double doubleValue() {
        return (double) numerator / denominator; // возвращаем значение как double
    }

    // Реализация метода интерфейса ru.Bochkarev.Main.Summable
    @Override
    public double toDouble() {
        return (double) numerator / denominator;
    }

    // Метод для суммирования различных типов значений
    // Универсальный метод sum для любых объектов: ru.Bochkarev.Fraction.Fraction, ru.Bochkarev.Main.Summable, числа
    public static double sum(Object... values) {
        double total = 0.0;
        for (Object value : values) {
            if (value instanceof Summable s) {
                total += s.toDouble();
            } else if (value instanceof Number n) {
                total += n.doubleValue();
            } else {
                throw new IllegalArgumentException("Невозможно преобразовать объект в число: " + value);
            }
        }
        return total;
    }

    // Переопределение метода clone()
    @Override
    public Fraction clone() {
        try {
            // Можно вызвать super.clone(), но проще — создать новый объект вручную
            return new Fraction(this.numerator, this.denominator);
        } catch (Exception e) {
            throw new AssertionError("Ошибка клонирования: " + e.getMessage());
        }
    }

}