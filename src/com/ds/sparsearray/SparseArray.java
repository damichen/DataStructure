package com.ds.sparsearray;

public class SparseArray {

    public static void main(String[] args) {
        //创建一个11*11的二维数组
        int chessRow = 11;
        int chessCol = 11;
        //0：表示没有棋子 ，1：表示黑子 2表示蓝子
        int chessArr1[][] =new int[chessRow][chessCol];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[3][4] = 1;

        //输出原始数组
        printfArr(chessArr1);

       //获取棋盘有效个数
        int sum=0;
        for (int[] rows : chessArr1) {
            for (int data : rows) {
                if (data!=0){
                    sum++;
                }
            }
        }
        System.out.println("\n棋子个数为"+sum);
        //将二维数组转换为稀疏数组
        //创建稀疏数组,并且给它的第一行赋值，记录棋盘的行列，有效值个数
        int spareArr[][] = new int[sum+1][3];
        spareArr[0][0] = chessRow;
        spareArr[0][1] = chessCol;
        spareArr[0][2] = sum;
        //将非0的值给稀疏数组赋值
        int count=0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[i].length; j++) {
                if (chessArr1[i][j]!=0){
                    count++;
                    spareArr[count][0] = i;
                    spareArr[count][1] = j;
                    spareArr[count][2] = chessArr1[i][j];
                }
            }
        }

        System.out.println();
        System.out.println("得到的稀疏数组为:");
        for (int i = 0; i < spareArr.length; i++) {
            System.out.printf("%d\t%d\t%d\n",spareArr[i][0],spareArr[i][1],spareArr[i][2]);
        }

        //将稀疏数组还原成原来的二维数组
        //利用稀疏数组的第一行创建一个二维数组
        int chessArr2[][] = new int[spareArr[0][0]][spareArr[0][1]];
        //将稀疏数组的值还原到二维数组中去，这里从i=1开始
        for (int i = 1; i < spareArr.length; i++) {
            int row = spareArr[i][0];
            int col = spareArr[i][1];
            chessArr2[row][col] = spareArr[i][2];
        }
        System.out.println("还原后的数组为：");
        //输出还原后的数组
        printfArr(chessArr2);
    }


    private static void printfArr(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf("%d\t",arr[i][j]);
            }
            System.out.println();
        }
    }

}
