package com.sirdave.get_ahead;

import java.util.ArrayList;

class Coord{
    int x = 0;
    int y = 0;
}

class Rect{
    Coord topLeft = new Coord();
    Coord bottomRight = new Coord();
}

class Result{
    int sum;
    Rect rectangle = new Rect();
}


public class RectangleSums {
    static void printResult(Result result) {
        System.out.println(
                "Sum is "
                        + result.sum
                        + " {"
                        + result.rectangle.topLeft.y
                        + ","
                        + result.rectangle.topLeft.x
                        + "} - {"
                        + result.rectangle.bottomRight.y
                        + ","
                        + result.rectangle.bottomRight.x
                        + "}");
    }

    public static Result findLargestSum(int[][] input) {
        Result largestResult = new Result();
        ArrayList<Integer> prevLargestSums = new ArrayList<>();
        // Iterate over all possible bottom right corners, row by row
        for (int i = 0; i < input.length; i++) {
            ArrayList<Integer> largestSums = new ArrayList<>();
            int currentRowSum = 0;
            for (int j = 0; j < input[i].length; j++) {
                // Keep track of the sums of num
                currentRowSum += input[i][j];

                int biggestRectangleSum = currentRowSum;

                if (j < prevLargestSums.size()) {
                    biggestRectangleSum += prevLargestSums.get(j);
                }

                largestSums.add(biggestRectangleSum);
                System.out.println("largestSums is " + largestSums);
                if (biggestRectangleSum > largestResult.sum) {
                    largestResult.sum = biggestRectangleSum;
                    largestResult.rectangle.bottomRight.y = i;
                    largestResult.rectangle.bottomRight.x = j;

                    //printResult(largestResult);
                }
            }
            prevLargestSums = largestSums;
        }
        for (int i = largestResult.rectangle.bottomRight.y - 1; i >= 0; i--) {
            if (input[i].length < largestResult.rectangle.bottomRight.x) {
                // Empty cell hit! We can't grow any taller.
                largestResult.rectangle.topLeft.y = i + 1;
                break;
            }
        }
        return largestResult;
    }

    public static void main(String[] args){
        int[][] numbers = new int[3][2];
        numbers[0][0] = 1;
        numbers[0][1] = 2;
        numbers[1][0] = 3;
        numbers[1][1] = 4;
        numbers[2][0] = 5;
        numbers[2][1] = 6;

        findLargestSum(numbers);

    }
}
