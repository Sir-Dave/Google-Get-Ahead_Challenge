package com.sirdave.get_ahead;

public class NumberSpeller {
    public static String[] thousandSeparators = {"", " thousand", " million",
            " billion", " trillion", " quadrillion", " quintillion"
    };

    public static String[] smallNumbers = { "zero", "one", "two", "three", "four", "five",
            "six", "seven", "eight", "nine", "ten", "eleven", "twelve", "thirteen",
            "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
    };

    public static String[] tens = {"", "", "twenty", "thirty", "forty", "fifty",
            "sixty", "seventy", "eighty", "ninety"
    };

    public static String spellNumber(long num){
        if (num == 0){
            return smallNumbers[0];
        }
        String result = "";
        int i = 0;
        int threeDigits = (int) (num % 1000);
        long remainingDigits = num / 1000;

        while (threeDigits != 0 || remainingDigits != 0){
            if (threeDigits != 0){
                result = spellThreeNumbers(threeDigits) + thousandSeparators[i] + result;

                if (remainingDigits != 0) { //If we are going to add more to the left, append a space
                    result = " " + result;
                }
            }

            i++;
            threeDigits = (int) (remainingDigits % 1000);
            remainingDigits = remainingDigits/ 1000;

        }
        return result;
    }

    public static String spellThreeNumbers(int num){
        String result = "";
        if (num >= 100){
            result = smallNumbers[num / 100] + " hundred";
            num %= 100;
            if (num == 0)
                return result;

            result+= " ";
        }

        if (num < 20){
            result += smallNumbers[num];
        }
        else {
            result += tens[num/10];
            if (num % 10 != 0){
                result += " " + smallNumbers[num % 10];
            }
        }
        return result;
    }

    public static void main(String[] args){
        int num = 48203;
        String answer = NumberSpeller.spellNumber(num);
        System.out.println(answer);

    }
}
