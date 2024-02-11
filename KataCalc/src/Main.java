import java.util.Scanner;

public class Main {
    private static final String[] romanArray = {
            "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X",
            "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
            "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX",
            "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
            "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L",
            "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
            "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
            "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
            "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
            "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"
    };


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        boolean isRomanInput = isRoman(input.split(" ")[0]) && isRoman(input.split(" ")[2]);
        boolean isArabicInput = !isRomanInput;

        String result = calc(input, isRomanInput, isArabicInput);
        System.out.println(result);
    }

    public static String calc(String input, boolean isRomanInput, boolean isArabicInput) {
        String[] elements = input.split(" ");
        char operator = elements[1].charAt(0);
        int num1;
        int num2;
        int result;


        if (elements.length != 3) {
            throw new IllegalArgumentException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        }

        if ((isRomanInput && isArabic(elements[0])) || (isArabicInput && isRoman(elements[0]))) {
            throw new IllegalArgumentException("Введены числа разных систем счисления");
        }

        try {
            num1 = isRomanInput ? convertToArabic(elements[0]) : Integer.parseInt(elements[0]);
            num2 = isRomanInput ? convertToArabic(elements[2]) : Integer.parseInt(elements[2]);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Используются одновременно разные системы счисления");
        }

        if (num1 < 1 || num1 > 10 || num2 < 1 || num2 > 10) {
            throw new IllegalArgumentException("Числа 1-10");
        }

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                result = num1 / num2;
                break;
            default:
                throw new IllegalArgumentException("Некорректный оператор");
        }

        if (isRomanInput) {
            return convertToRoman(result);
        } else {
            return Integer.toString(result);
        }
    }

    public static int convertToArabic(String numeral) {
        for (int i = 1; i <= 100; i++) {
            if (romanArray[i].equals(numeral)) {
                return i;
            }
        }
        throw new IllegalArgumentException("Некорректное римское число");
    }


    public static String convertToRoman(int number) {
        if (number < 1 || number > 100)
            throw new IllegalArgumentException("Число должно быть от 1 до 100");
        return romanArray[number];
    }


    public static boolean isRoman(String numeral) {
        for (String romanNumeral : romanArray) {
            if (romanNumeral.equals(numeral)) {
                return true;
            }
        }
        return false;
    }


    public static boolean isArabic(String numeral) {
        try {
            int num = Integer.parseInt(numeral);
            return num >= 1 && num <= 10;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}