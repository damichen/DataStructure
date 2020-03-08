package com.ds.heffumantreecode;

import java.io.*;
import java.util.*;

/**
 * 赫夫曼编码 -解码：这个有难度
 */
public class HeffumanCode {

    public static void main(String[] args) {
        String str = "i(cm) like like like java do you like a java";
        //byte[] contentCodes = str.getBytes();
        /**
         for (byte heffumanCode : contentCodes) {
         System.out.printf("%d(%c)\t", heffumanCode, ((char) heffumanCode));
         }
         System.out.println();
         List<Node> nodes = getNodes(contentCodes);
         System.out.println(nodes);
         Node root = creatHeffumanTree(nodes);
         System.out.println(root);
         //preOreder(root);
         Map<Byte, String> heffumanMap = getCodes(root);
         for (Map.Entry<Byte, String> entry : heffumanMap.entrySet()) {
         System.out.println("(k=" + entry.getKey() + ",v=" + entry.getValue() + ")");
         }
         byte[] zips = zip(contentCodes, heffumanCodes);*/
        /**
         byte[] zips = heffumanZip(contentCodes);
         System.out.println("数组：" + Arrays.toString(zips) + "数组长度:" + zips.length);
         byte[] sourceBytes = decode(heffumanCodes, zips);
         System.out.println("解码后的数组为" + new String(sourceBytes));*/
        //测试压缩文件
//        zipFile("e:\\src.bmp", "e:\\dst.zip");
        //测试解压文件
        unzipFile("e:\\dst.zip", "e:\\src22.bmp");
        System.out.println("解压完成");
    }

    //编写对文件进行解压
    public static void unzipFile(String zipFile, String dstFile) {
        ObjectInputStream ois = null;
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new FileInputStream(zipFile);
            ois = new ObjectInputStream(is);
            //读取压缩文件中的字节数组
            byte[] heffmanBytes = (byte[]) ois.readObject();
            heffumanCodes = (Map<Byte, String>) ois.readObject();
            byte[] bytes = decode(heffumanCodes, heffmanBytes);
            os = new FileOutputStream(dstFile);
            os.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    //编写方法，对文件压缩

    /**
     * @param srcFile 你传入的希望压缩的文件的全路径
     * @param dstFile 我们压缩后将压缩文件放到哪个目
     */
    public static void zipFile(String srcFile, String dstFile) {
        //读取文件，创建文件输入流
        InputStream is = null;
        OutputStream os = null;
        ObjectOutputStream oos = null;
        try {
            //以流的形式读取源文件
            is = new FileInputStream(srcFile);
            byte[] srcBytes = new byte[is.available()];
            int len = is.read(srcBytes);
            System.out.println("文件长度" + (float) (len / 1024) + "k");
            //直接对源文件压缩
            byte[] huffmanBytes = heffumanZip(srcBytes);
            //创建输出流用来存储文件
            os = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(os);
            oos.writeObject(huffmanBytes);
            //这里我们以对象流的方式写入 赫夫曼编码，是为了以后我们恢复源文件时使用
            //注意一定要把赫夫曼编码 写入压缩文件
            oos.writeObject(heffumanCodes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
                os.close();
                oos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


    //编写一个方法，完成对压缩数据的解码

    /**
     * @param huffmanCodes 赫夫曼编码表 map
     * @param huffmanBytes 赫夫曼编码得到的字节数组
     * @return 就是原来的字符串对应的数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        //1. 先得到 huffmanBytes 对应的 二进制的字符串 ， 形式 1010100010111...
        StringBuilder stringBuilder = new StringBuilder();
        //将 byte 数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean flag = (i == huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(!flag, b));
        }
        //把字符串安装指定的赫夫曼编码进行解码
        //把赫夫曼编码表进行调换，因为反向查询 a->100 100->a
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }
        //创建要给集合，存放 byte
        List<Byte> list = new ArrayList<>();
        //i 可以理解成就是索引,扫描 stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1; // 小的计数器
            boolean flag = true;
            Byte b = null;
            while (flag) {
                //1010100010111...
                //递增的取出 key 1
                String key = stringBuilder.substring(i, i + count);//i 不动，让 count 移动，指定匹配到一个字符
                b = map.get(key);
                if (b == null) {//说明没有匹配到
                    count++;
                } else {
                    //匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i 直接移动到 count
        }
        //当 for 循环结束后，我们 list 中就存放了所有的字符 "i like like like java do you like a java"
        //把 list 中的数据放入到 byte[] 并返回
        byte b[] = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /***解码**/
    /**
     * 将一个 byte 转成一个二进制的字符串, 如果看不懂，可以参考我讲的 Java 基础 二进制的原码，反码，
     * 补码
     *
     * @param b    传入的 byte
     * @param flag 标志是否需要补高位如果是 true ，表示需要补高位，如果是 false 表示不补, 如果是最后一
     *             个字节，无需补高位
     * @return 是该 b 对应的二进制的字符串，（注意是按补码返回）
     */
    public static String byteToBitString(boolean flag, byte b) {
        int temp = (int) b;
        if (flag) {
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if (flag) {
            return str.substring(str.length() - 8);
        } else {
            return str;
        }
    }

    /****编码****/
    public static byte[] heffumanZip(byte[] bytes) {
        List<Node> nodes = getNodes(bytes);
        //根据 nodes 创建的赫夫曼树
        Node huffmanTreeRoot = creatHeffumanTree(nodes);
        //对应的赫夫曼编码(根据 赫夫曼树)
        Map<Byte, String> huffmanCodes = getCodes(huffmanTreeRoot);
        //根据生成的赫夫曼编码，压缩得到压缩后的赫夫曼编码字节数组
        byte[] huffmanCodeBytes = zip(bytes, huffmanCodes);
        return huffmanCodeBytes;
    }

    /**
     * @param contentBytes 接收字节数组
     * @return 返回的就是 List 形式 [Node[date=97 ,weight = 5], Node[]date=32,weight = 9]......],
     */
    private static List<Node> getNodes(byte[] contentBytes) {
        LinkedList<Node> nodes = new LinkedList<>();
        if (contentBytes == null) {
            return null;
        }
        Map<Byte, Integer> weightCounts = new HashMap<>();
        for (byte val : contentBytes) {
            Integer count = weightCounts.get(val);
            if (count == null) {
                weightCounts.put(val, 1);
            } else {
                weightCounts.put(val, count + 1);
            }
        }
        for (Map.Entry<Byte, Integer> entry : weightCounts.entrySet()) {
            Node node = new Node(entry.getKey(), entry.getValue());
            nodes.add(node);
        }
        return nodes;
    }

    private static Node creatHeffumanTree(List<Node> nodes) {
        if (nodes == null || nodes.size() == 0) {
            System.out.println("list is null");
            return null;
        }
        //创建赫夫曼树,根据二叉树的规则创建
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            Node root = new Node(null, leftNode.weight + rightNode.weight);
            root.left = leftNode;
            root.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(root);
        }
        return nodes.get(0);
    }

    private static void preOreder(Node root) {
        if (root == null) {
            System.out.println("此二叉树为空");
        } else {
            root.preOreder(root);
        }
    }

    static Map<Byte, String> heffumanCodes = new HashMap<>();
    static StringBuilder sb1 = new StringBuilder();

    /**
     * 功能：将传入的 node 结点的所有叶子结点的赫夫曼编码得到，并放入到 huffmanCodes 集合
     *
     * @param node 传入结点
     * @param code 路径： 左子结点是 0, 右子结点 1
     * @param sb1  用于拼接路径
     */
    private static void getCodes(Node node, String code, StringBuilder sb1) {
        if (node == null) {
            return;
        }
        StringBuilder sb = new StringBuilder(sb1);
        if (code != null) {
            sb.append(code);
        }
        if (node.data == null) {
            getCodes(node.left, "0", sb);
            getCodes(node.right, "1", sb);
        } else {
            heffumanCodes.put(node.data, sb.toString());
        }
    }

    private static Map<Byte, String> getCodes(Node root) {
        if (root == null) {
            System.out.println("二叉树为空");
            return null;
        }
        getCodes(root.left, "0", sb1);
        getCodes(root.right, "1", sb1);
        return heffumanCodes;
    }

    public static byte[] zip(byte[] bytes, Map<Byte, String> heffumanCodes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            String val = heffumanCodes.get(b);
            sb.append(val);
        }
//        System.out.println(sb.toString() + "\tsb.len=" + sb.length());
        int len;
        if (sb.length() % 8 == 0) {
            len = sb.length() / 8;
        } else {
            len = sb.length() / 8 + 1;
        }
        byte[] heffumanCodesByte = new byte[len];
        int index = 0;
        for (int i = 0; i < sb.length(); i += 8) {
            if (i + 8 < sb.length()) {
                //八位数的二进制转换成byte类型的数据
                heffumanCodesByte[index] = (byte) Integer.parseInt(sb.toString().substring(i, i + 8), 2);
            } else {
                heffumanCodesByte[index] = (byte) Integer.parseInt(sb.toString().substring(i), 2);
            }
            index++;
        }
        return heffumanCodesByte;
    }
}

class Node implements Comparable<Node> {
    public Byte data; // 存放数据(字符)本身，比如'a' => 97 ' ' => 32
    public int weight; //权值, 表示字符出现的次数
    public Node left;//
    public Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    //前序遍历
    public void preOreder(Node node) {
        if (node == null) {
            return;
        }
        System.out.println(node);
        preOreder(node.left);
        preOreder(node.right);
    }

    @Override
    public int compareTo(Node o) {
// 从小到大排序
        return this.weight - o.weight;
    }

    public String toString() {
        return "Node [data = " + data + " weight=" + weight + "]";
    }
}
