package com.company;

class Calculator {
    private int result;
    //переменная для проверки на разность системы исчисления
    private int romanTrigger = 0;

    Calculator(String str) {
        int operand1;
        int operand2;
        String operator;
        if (str.contains("+")) operator = "\\+";
        else if (str.contains("-")) operator = "-";
        else if (str.contains("*")) operator = "\\*";
        else if (str.contains("/")) operator = "/";
        else throw new NumberFormatException("Строка не является математической операцией");
        String[] operands = str.split(operator);
        if (operands.length < 2) throw new NumberFormatException("Строка не является математической операцией");
        else if (operands.length > 2)
            throw new NumberFormatException("Формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        operand1 = setArabic(operands[0]);
        operand2 = setArabic(operands[1]);
        if (operand1 > 10 || operand1 < 1 || operand2 > 10 || operand2 < 1)
            throw new NumberFormatException("Числа должны быть от 1 до 10");
        calculation(operand1, operand2, operator);
    }

    //Функции для преобразования строк в арабские или римские числа
    int setArabic(String str) {
        int number;
        try {
            number = Integer.parseInt(str);
        } catch (NumberFormatException ex) {
            number = setRoman(str);
        }
        return number;
    }

    int setRoman(String roman) {
        int arabic = 0;
        if (roman.length() == 0) throw new NumberFormatException("Римские цифры не найдены");
        roman = roman.toUpperCase();
        int oldNumber = 0;
        int cnt = 0;
        for (char c : roman.toCharArray()) {
            int number = charToNumber(c);
            if (number < 1) throw new NumberFormatException("Не правильный символ \"" + c + "\" в римской системе");
            //проверка что нет больше 3х одинаковых римских цифр подряд
            if (oldNumber == number) cnt++;
            if (cnt >= 3) throw new NumberFormatException("Римские цифры введены с ошибкой \"" + roman + "\"");

            if (number > oldNumber) {
                //проверка правильного написания римских цифр
                if (oldNumber > 0)
                    if (cnt >= 1 || (number / oldNumber) == 2)
                        throw new NumberFormatException("Римские цифры введены с ошибкой \"" + roman + "\"");
                arabic = number - oldNumber;
            } else {
                //проверка правильного написания римских цифр
                if ((arabic + number) == oldNumber)
                    throw new NumberFormatException("Римские цифры введены с ошибкой \"" + roman + "\"");
                arabic += number;
            }
            oldNumber = number;
        }
        romanTrigger++;
        return arabic;
    }

    // функция возвращает арабскую цифру вместо римского символа
    private int charToNumber(char ch) {
        // проверка есть ли символ "ch" в перечислении
        String str = Character.toString(ch);
        for (RomanNumbers RN : RomanNumbers.values()) {
            if (RN.name().equals(str)) {
                return RN.getArabic();
            }
        }
        return -1;
    }

    // функция вычисления результата выражения
    void calculation(int op1, int op2, String operator) {
        if (operator.contains("+")) result = op1 + op2;
        else if (operator.contains("-")) result = op1 - op2;
        else if (operator.contains("*")) result = op1 * op2;
        else if (operator.contains("/")) {
            if (op2 == 0) throw new NumberFormatException("Деление на ноль запрещено");
            result = op1 / op2;
        } else throw new NumberFormatException("Оператор неверный");
    }

    // функция возвращает результат выражения
    String getResult() {
        String strResult = "";
        if (romanTrigger == 1) throw new NumberFormatException("Используются разные системы счисления");
        else if (romanTrigger >= 2) {
            if (result < 1) throw new NumberFormatException("В римской системе нет отрицательных чисел и ноля");
            strResult += getArabic();
        } else strResult += result;
        return strResult;
    }

    // функция перевода из арабских цифр в римские
    String getArabic() {
        String roman = "";
        int N = result;
        while (N > 0) {
            for (RomanNumbers RN : RomanNumbers.values()) {
                while (N >= RN.getArabic()) {
                    roman += RN;
                    N = N - RN.getArabic();
                }
            }
        }
        return roman;
    }
}