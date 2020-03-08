package cn.ds.recursion;

import java.util.Arrays;

/**
 * 递归的应用--迷宫问题
 */
public class MiGong {
    public static void main(String[] args) {
        int row = 8;//行
        int col = 7;//列
        int[][] map = new int[row][col];
        //设置墙 --值为1
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (i == 0 || i == row - 1 || j == 0 || j == col - 1) {
                    map[i][j] = 1;
                }
            }
        }
        map[3][1] = 1;
        map[3][2] = 1;
        map[2][3] = 1;
//        map[1][2] = 1;
        for (int[] item : map) {
            System.out.println(Arrays.toString(item));
        }
        boolean b = setWay2(map, 1, 1);
        System.out.println(b);
        for (int[] item : map) {
            System.out.println(Arrays.toString(item));
        }
    }

    /**
     * 策略，下右上左
     *
     * @param map 地图
     * @param i   出发的横坐标
     * @param j   出发点的纵坐标
     *            约定： 当 map[i][j] 为 0 表示该点没有走过 当为 1 表示墙 ； 2 表示通路可以走 ； 3 表示该点已经
     *            走过，但是走不通;
     *            如果小球能到 map[6][5] 位置，则说明通路找到
     */
    public static boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {//找到出口了
            return true;
        } else {
            //这个还没走过，代表可以走 ---这样写不会回溯 --需要改进
            if (map[i][j] == 0) {
                map[i][j] = 2;
                if (i + 1 < map.length && map[i + 1][j] == 0) {
                    return setWay(map, i + 1, j);
                } else if (j + 1 < map[i].length && map[i][j + 1] == 0) {
                    return setWay(map, i, j + 1);
                } else if (i - 1 > 0 && map[i - 1][j] == 0) {
                    return setWay(map, i - 1, j);
                } else if (j - 1 > 0 && map[i][j - 1] == 0) {
                    return setWay(map, i, j - 1);
                } else {
                    //四面都不能走
                    map[i][j] = 3;
                }
            }
            return false;
        }
    }

    //策略与上述方法一样，但是对上边方法进行了改进
    public static boolean setWay2(int map[][], int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {
                map[i][j] = 2;
                //代表向下能走  ,这边能走的外边都是墙 ，所以i+1 或者j+1不会越界 ，不能走的时候会碰到墙
                if (setWay2(map, i + 1, j)) {
                    return true;
                } else if (setWay2(map, i, j + 1)) {
                    return true;
                } else if (setWay2(map, i - 1, j)) {
                    return true;
                } else if (setWay2(map, i, j - 1)) {
                    return true;
                } else {
                    //四方都走不通返回false;
                    map[i][j] = 3;
                    return false;
                }
            } else {
                //1,2,3 说明这条路是死路
                return false;
            }
        }
    }

}
