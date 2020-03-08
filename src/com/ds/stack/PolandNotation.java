package com.ds.stack;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 栈的应用-逆波兰表达式
 */
public class PolandNotation {
    public static void main(String[] args) {
        //完成将一个中缀表达式转成后缀表达式的功能
        //说明
        //1. 1+((2+3)×4)-5 => 转成 1 2 3 + 4 × + 5 –
        //2. 因为直接对 str 进行操作，不方便，因此 先将 "1+((2+3)×4)-5" =》 中缀的表达式对应的 List
        // 即 "1+((2+3)×4)-5" => ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        //3. 将得到的中缀表达式对应的 List => 后缀表达式对应的 List
        // 即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5] =》 ArrayList [1,2,3,+,4,*,+,5,–]
        String expression = "1+((2+3)*4)-5";//注意表达式
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的 List=" + infixExpressionList); //ArrayList [1,+,(,(,2,+,3,),*,4,),-,5]
        List<String> suffixExpreesionList = parseSuffixExpreesionList(infixExpressionList);
        System.out.println("后缀表达式对应的 List" + suffixExpreesionList); //ArrayList [1,2,3,+,4,*,+,5,–]
        System.out.printf("expression=%d", calculate(suffixExpreesionList)); // ?
    }

    //即 ArrayList [1,+,(,(,2,+,3,),*,4,),-,5] =》 ArrayList [1,2,3,+,4,*,+,5,–]
    //方法：将得到的中缀表达式对应的 List => 后缀表达式对应的 List
    public static List<String> parseSuffixExpreesionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<String>(); // 符号栈
        //说明：因为 s2 这个栈，在整个转换过程中，没有 pop 操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里我们就不用 Stack<String> 直接使用 List<String> s2
        //Stack<String> s2 = new Stack<String>(); // 储存中间结果的栈 s2
        List<String> s2 = new ArrayList<String>(); // 储存中间结果的 Lists2
        for (String l : ls) {
            //是数字直接加入到s2
            if (l.matches("\\d+")) {
                s2.add(l);
            } else if (l.equals("(")) {
                s1.push(l);
            } else if (l.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                //最后将"("弹出栈
                s1.pop();
            } else {
                //这是普通的标点符号,这个时候需要判断其优先级,先将栈中优先级高的给弹出栈
                while (s1.size() > 0 && Operation.getValue(s1.peek()) >= Operation.getValue(l)) {
                    s2.add(s1.pop());
                }
                //把当前符号压入s1中
                s1.push(l);
            }
        }
        //循环结束，将所有的符号加入到s2中
        while (s1.size() > 0) {
            s2.add(s1.pop());
        }
        return s2;
    }

    public static List<String> toInfixExpressionList(String s) {
        //定义一个 List,存放中缀表达式 对应的内容
        List<String> ls = new ArrayList<>();
        int i = 0; //这时是一个指针，用于遍历 中缀表达式字符串
        StringBuilder str = null; // 对多位数的拼接
        char c; // 每遍历到一个字符，就放入到 c
        while (i < s.length()) {
            c = s.charAt(i);
            //如果 c 是一个非数字，我需要加入到 ls
            if (c < 47 || c > 58) {
                ls.add(c + "");
                i++;
            } else {
                //是数字，需要判断后边是不是数字，是的话得拼接一下
                str = new StringBuilder();
                while (i < s.length() && (s.charAt(i) > 47 && s.charAt(i) < 58)) {
                    str.append(s.charAt(i) + "");
                    i++;
                }
                ls.add(str.toString());
            }
        }
        return ls;
    }

    //将一个逆波兰表达式， 依次将数据和运算符 放入到 ArrayList 中
    public static List<String> getListString(String suffixExpression) {
        //将 suffixExpression 分割
        String[] split = suffixExpression.split(" ");
        List<String> list = new ArrayList<String>();
        for (String ele : split) {
            list.add(ele);
        }
        return list;
    }

    //完成对逆波兰表达式的运算
    /*
    * 1)从左至右扫描，将 3 和 4 压入堆栈；
    2)遇到+运算符，因此弹出 4 和 3（4 为栈顶元素，3 为次顶元素），计算出 3+4 的值，得 7，再将 7 入栈；
    3)将 5 入栈；
    4)接下来是×运算符，因此弹出 5 和 7，计算出 7×5=35，将 35 入栈；
    5)将 6 入栈；
    6)最后是-运算符，计算出 35-6 的值，即 29，由此得出最终结果
    */
    public static int calculate(List<String> ls) {
        Stack<String> s1 = new Stack<>();
        for (String item : ls) {
            //说明不是符号，直接压栈
            if (Operation.getValue(item) == 0) {
                s1.push(item);
            } else {
                //是运算符,在栈中取出两个来计算，并且把结果放入 栈中
                if (s1.size() > 1) {
                    int o1 = Integer.parseInt(s1.pop());
                    int o2 = Integer.parseInt(s1.pop());
                    int val = 0;
                    if (item.equals("+")) {
                        val = o1 + o2;
                    } else if (item.equals("-")) {
                        val = o2 - o1;
                    } else if (item.equals("*")) {
                        val = o2 * o1;
                    } else if (item.equals("/")) {
                        val = o2 / o1;
                    } else {
                        throw new RuntimeException("符号错误...");
                    }
                    s1.push(val + "");
                }

            }
        }
        return Integer.parseInt(s1.pop());
    }
}

//编写一个类 Operation 可以返回一个运算符 对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}