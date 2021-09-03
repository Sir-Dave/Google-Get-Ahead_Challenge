package com.sirdave.get_ahead;

import java.util.Stack;

public class BalancedString {
    public static int longestString(String string){
        Stack<Integer> stack = new Stack<>();
        int longest = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == '(') {
                stack.push(i);
            }
            else if (string.charAt(i) == ')' && !stack.isEmpty()) {
                int open_i = stack.pop();
                int length = i - open_i + 1;
                if (length > longest)
                    longest = length;
            }
        }
        return longest;
    }

    public static void main(String[] args){
        System.out.println(longestString("()()()"));
        System.out.println(longestString("(((())))))))))()"));

    }
}
