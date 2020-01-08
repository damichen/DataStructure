package com.ds.linkedlist;

import java.util.Stack;

public class StackTest {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<>();
        stack.push("a");
        stack.push("b");
        stack.push("c");

        while (stack.size()>0){
            System.out.println(stack.pop());
        }

    }
}
