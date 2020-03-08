package cn.ds.recursion;

//8皇后问题
public class Quuen8 {

    //定义一个 max 表示共有多少个皇后
    int max = 8;
    //定义数组 array, 保存皇后放置位置的结果,比如 arr = {0 , 4, 7, 5, 2, 6, 1, 3}
    int[] array = new int[max];
    static int count = 0;
    static int judgeCount = 0;

    public static void main(String[] args) {
        Quuen8 quuen8 = new Quuen8();
        quuen8.check(0);
        System.out.println("共有" + count + "种解法");
        System.out.println("执行判断" + judgeCount + "次");
    }

    //编写一个方法，放置第 n 个皇后
    //特别注意： check 是 每一次递归时，进入到 check 中都有 for(int i = 0; i < max; i++)，因此会有回溯
    private void check(int n) {
        if (n == max) {
            print();
            return;
        }
        for (int i = 0; i < max; i++) {
            array[n] = i;
            //下一个可以摆酒继续递归
            if (judge(n)) {
                check(n + 1);
            }
            //否则继续遍历 ，即 array[n] = i+1
        }


    }

    //查看当我们放置第 n 个皇后, 就去检测该皇后是否和前面已经摆放的皇后冲突

    /**
     * @param n 表示第 n 个皇后
     * @return
     */
    private boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            //表示这个位置不行
            if (array[i] == array[n] || (Math.abs(i - n) == Math.abs(array[i] - array[n]))) {
                return false;
            }
        }
        return true;
    }

    //写一个方法，可以将皇后摆放的位置输出
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
