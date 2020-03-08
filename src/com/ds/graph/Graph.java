package com.ds.graph;

import java.util.*;

/**
 * 图
 */
public class Graph {
    public List<String> vertexList;
    public int[][] edges;
    public int numOfEdges;
    public boolean isVisited[];

    public static void main(String[] args) {

//        String vertexs[] = {"A", "B", "C", "D", "E"};
        String vertexs[] = {"1", "2", "3", "4", "5", "6", "7", "8"};
        //创建图对象
        Graph graph = new Graph(vertexs.length);
        //循环的添加顶点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        //添加边
        //A-BA-C B-C B-D B-E
//        graph.insertEdge(0, 1, 1); //A-B
//        graph.insertEdge(0, 2, 1); //
//        graph.insertEdge(1, 2, 1); //
//        graph.insertEdge(1, 3, 1); //
//        graph.insertEdge(1, 4, 1); //

        //更新边的关系
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);
        graph.print();
        System.out.println("深度遍历");
        graph.dfs();
//        graph.bfs();
    }

    /**
     * @param n 有n个顶点
     */
    public Graph(int n) {
        vertexList = new ArrayList<>(n);
        edges = new int[n][n];
        numOfEdges = 0;
        isVisited = new boolean[n];
    }

    public void print() {
        for (int[] edge : edges) {
            System.err.println(Arrays.toString(edge));
        }
    }

    //得到第一个邻接结点的下标 w

    /**
     * @param index
     * @return 如果存在就返回对应的下标，否则返回-1
     */
    private int getFirstNeighbor(int index) {
        for (int i = 0; i < vertexList.size(); i++) {
            if (edges[index][i] > 0) {
                return i;
            }
        }
        return -1;
    }

    //根据前一个邻接结点的下标来获取下一个邻接结点

    /**
     * @param v1 对应数组的横坐标
     * @param v2 对应数组的纵坐标
     * @return 返回下一个邻接节点的坐标
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int i = v2 + 1; i < vertexList.size(); i++) {
            int val = edges[v1][i];
            if (val > 0) {
                return i;
            }
        }
        return -1;
    }

    /**
     * 对一个结点进行广度优先遍历的方法
     *
     * @param isVisited
     * @param i
     */
    private void bfs(boolean isVisited[], int i) {
        int u; // 表示队列的头结点对应下标
        int w; // 邻接结点 w
        System.out.print(vertexList.get(i) + "->");
        u = i;
        LinkedList<Integer> queue = new LinkedList<>();
        isVisited[i] = true;
        queue.addLast(i);
        while (!queue.isEmpty()) {
            u = queue.removeFirst();
            w = getFirstNeighbor(u);
            while (w != -1) {
                if (!isVisited[w]) {
                    System.out.print(vertexList.get(w) + "->");
                    isVisited[w] = true;
                    queue.addLast(w);
                }
                w = getNextNeighbor(u, w);
            }
        }
    }

    public void bfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                bfs(isVisited, i);
            }
        }
    }

    /**
     * 图的深度遍历
     * 1) 访问初始结点 v，并标记结点 v 为已访问。
     * 2) 查找结点 v 的第一个邻接结点 w。
     * 3) 若 w 存在，则继续执行 4，如果 w 不存在，则回到第 1 步，将从 v 的下一个结点继续。
     * 4) 若 w 未被访问，对 w 进行深度优先遍历递归（即把 w 当做另一个 v，然后进行步骤 123）。
     * 5) 查找结点 v 的 w 邻接结点的下一个邻接结点，转到步骤 3
     *
     * @param i
     * @param isVisited
     */
    private void dfs(int i, boolean isVisited[]) {
        //访问该节点
        System.out.print(getVertexByIndex(i) + "->");
        isVisited[i] = true;
        int w = getFirstNeighbor(i);
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(w, isVisited);
            }
            w = getNextNeighbor(i, w);
        }
    }

    //遍历所有的节点并且进行dfs
    public void dfs() {
        for (int i = 0; i < vertexList.size(); i++) {
            if (!isVisited[i]) {
                dfs(i, isVisited);
            }
        }
    }

    //插入顶点
    public void insertVertex(String val) {
        vertexList.add(val);
    }

    //插入边的关系

    /**
     * @param v1     第一个顶点
     * @param v2     第二个顶点
     * @param weight 边权值 1 代表有 ，0代表没有
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    //按照下标获取顶点的值
    public String getVertexByIndex(int index) {
        if (vertexList == null || vertexList.size() <= index) {
            return null;
        }
        return vertexList.get(index);
    }

    //获取v1，v2的权值
    public int getWewight(int v1, int v2) {
        return edges[v1][v2];
    }
}
