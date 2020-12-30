package dataStructure.huffmancode;

import java.io.*;
import java.util.*;

/**
 * @author :覃玉锦
 * @create :2020-12-11 16:27:00
 * 赫夫曼编码，利用赫夫曼树的性质对传输数据进行编码
 */
public class HuffManCode {
    //赫夫曼编码表
    private static Map<Byte,String> huffmanCodeTab = new HashMap<>();
    //赫夫曼树编码的长度
    private static int huffmanCodeBitLength;

    public static void main(String[] args) throws Exception{
        String contents = "i like like like java do you like a java";
        System.out.println("原始字符串："+contents);
        System.out.println("原始字符串对应字节序列："+Arrays.toString(contents.getBytes())+" 原始字符串字节序列长度："+contents.getBytes().length);
        byte[] bytes = huffmanZip(contents.getBytes());
        System.out.println("赫夫曼编码后的序列："+Arrays.toString(bytes)+" 序列的长度："+bytes.length);
        System.out.println("压缩率："+((float)bytes.length/(float)contents.getBytes().length));
        System.out.println("还原后的字符串："+new String(decode(bytes, huffmanCodeTab)));


        /*        测试文件压缩          */
        /*String srcFile = "E:\\srcFile.txt";
        String dstFile = "E:\\dstFile.zip";
        zipFile(srcFile, dstFile);*/

        /*        测试文件解压          */
        /*String zipFile = "E:\\dstFile.zip";
        String dstFile = "E:\\srcFile2.txt";
        unzipFile(zipFile,dstFile);*/

    }

    /**
     * 使用赫夫曼编码对文件进行压缩
     * @param srcFile 需要压缩的文件全路径
     * @param dstFile 目标文件全路径
     */
    public static void zipFile(String srcFile,String dstFile){
        FileInputStream fis = null;
        OutputStream fos = null;
        ObjectOutputStream oos = null;
        try {
            fis = new FileInputStream(srcFile);
            //读取文件
            byte[] b = new byte[fis.available()];
            fis.read(b);
            //压缩
            byte[] huffmanBytes = huffmanZip(b);
            //把压缩后的数据写入新文件，需要写入的东西右1、原始数据 2、赫夫曼编码表
            fos = new FileOutputStream(dstFile);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(huffmanBytes);
            oos.writeObject(huffmanCodeTab);
            //记录长度
            oos.writeObject(HuffManCode.huffmanCodeBitLength);
            System.out.println("压缩成功");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                fis.close();
                fos.close();
                oos.close();
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 解压文件
     * @param zipFile 需要解压的文件
     * @param dstFile 解压到哪个路径
     */
    public static void unzipFile(String zipFile,String dstFile){
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(zipFile);
            ois = new ObjectInputStream(fis);
            byte[] bytes = (byte[]) ois.readObject();
            Map<Byte,String> code = (Map<Byte,String>)ois.readObject();
            //记录长度
            int length = (int)ois.readObject();
            HuffManCode.huffmanCodeBitLength = length;
            byte[] decode = decode(bytes, code);
            fos = new FileOutputStream(dstFile);
            fos.write(decode);
            System.out.println("解压成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                fos.close();
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据传入的赫夫曼表进行解码，如果原始数据有中文可能会报错
     * @param huffmanBytes 赫夫曼编码字节序列
     * @param huffmanCode 赫夫曼编码表
     * @return
     */
    public static byte[] decode(byte[] huffmanBytes,Map<Byte,String> huffmanCode){
        StringBuilder stringBuilder = new StringBuilder();
        //还原成二进制
        //如果最后一位是01，这里解码会解成1，会导致少一位,最后一个字节需要单独处理
        for (int i = 0; i < huffmanBytes.length-1; i++) {
            boolean flag = (i == huffmanBytes.length-1);
            stringBuilder.append(byteToString(!flag, huffmanBytes[i]));
        }
        //最后一位根据赫夫曼树编码长度来判断
        String lastByteStr = byteToString(false, huffmanBytes[huffmanBytes.length - 1]);
        //如果长度正好，直接添加
        if(stringBuilder.length() + lastByteStr.length() == HuffManCode.huffmanCodeBitLength){
            stringBuilder.append(lastByteStr);
        }
        //长度不够
        else {
            //根据文件记录的长度来判断，不够的0填充
            while (stringBuilder.length() + lastByteStr.length() < HuffManCode.huffmanCodeBitLength){
                stringBuilder.append("0");
            }
            stringBuilder.append(lastByteStr);
        }

        Map<String,Byte> map = new HashMap<>();
        //把赫夫曼表的key，value倒置一下，map形式为[01:32],[100:97]...
        for (Map.Entry<Byte, String> entry : huffmanCode.entrySet()) {
            map.put(entry.getValue(),entry.getKey());
        }
        List<Byte> list = new ArrayList<>();
        //根据map，从赫夫曼编码二进制串还原成原始的字符转对应字节序列
        for (int i = 0; i < stringBuilder.length();) {
            int count = 1;
            boolean flag = true;
            String key;
            Byte b =null;
            while (flag){
                key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if(b==null){
                    count++;
                }
                else {
                    flag = false;
                }
            }
            list.add(b);
            i+=count;
        }
        byte[] bytes = new byte[list.size()];
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = list.get(i);
        }
        return bytes;
    }

    /**
     * 把字节转换成相应二进制，正数需要补位，最后一个字节不需要补
     * @param flag 是否需要补位
     * @param b 需要转换的字节
     * @return
     */
    public static String byteToString(boolean flag,byte b){
        int temp = b;
        if(flag){
            // 10000000 10000000 | 00000001 = 10000001
            temp |= 256;
        }
        String str = Integer.toBinaryString(temp);
        if(flag){
            return str.substring(str.length()-8);
        }
        else {
            return str;
        }
    }

    /**
     * 把所有方法封装一下，便于调用
     * 赫夫曼编码步骤：
     * 1、统计数据中每个字符的出现次数，并且存入list<Node> 形式：Node{data:32,weight:1}
     * 2、根据list的权值构建赫夫曼树
     * 3、基于构建好的赫夫曼树，构建出赫夫曼编码表，具体实现思路为左0右1。
     * 4、根据构建的赫夫曼编码表，把原始字符串转换成相应的编码字符串
     * 5、把编码字符串转化为字节，8位为一个字节。
     * @param bytes 原始数据对应的字节
     * @return 返回经过赫夫曼树编码后的字节
     */
    public static byte[] huffmanZip(byte[] bytes){
        List<Node> nodes = getNodes(bytes);
        Node root = createHuffManTree(nodes);
        StringBuilder stringBuilder = new StringBuilder();
        getCodes(root,"",stringBuilder);
        byte[] huffmanBytes = huffmanEncode(bytes);
        return huffmanBytes;
    }

    /**
     * 使用赫夫曼编码对数据编码
     * @param bytes
     * @return
     */
    public static byte[] huffmanEncode(byte[] bytes){
        StringBuilder huffcode = new StringBuilder();
        for (byte b : bytes) {
            String r = huffmanCodeTab.get(b);
            huffcode.append(r);
        }
//        System.out.println("赫夫曼编码二进制形式："+huffcode);
        //需要把这个编码每8位转成byte类型
        //用 (huffcode.length()+7)/8也可以
        int len = (huffcode.length()+7)/8;
        /*if(huffcode.length() % 8 ==0){
            len = huffcode.length()/8;
        } else {
            len = huffcode.length()/8+1;
        }*/
        byte[] huffmanCodeBytes = new byte[len];
        int index = 0;
        for (int i = 0; i < huffcode.length(); i+=8) {
            String strByte;
            if(i+8 > huffcode.length()){
                strByte = huffcode.substring(i);
            }else {
                strByte = huffcode.substring(i,i+8);
            }
            huffmanCodeBytes[index++] = (byte) Integer.parseInt(strByte,2);
        }
        HuffManCode.huffmanCodeBitLength = huffcode.length();
        return huffmanCodeBytes;
    }

    public static void preOrder(Node root){
        if(root==null){
            System.out.println("根节点为空");
        }
        else {
            root.preOrder();
        }
    }

    /**
     * 根据赫夫曼树生成赫夫曼编码表
     * @param node
     * @param code
     * @param stringBuilder
     */
    public static void getCodes(Node node,String code,StringBuilder stringBuilder){
        StringBuilder stringBuilder2 = new StringBuilder(stringBuilder);
        stringBuilder2.append(code);
        //非叶子节点，往下走，找到叶子节点为止
        if(node.data == null){
            //左0右1
            getCodes(node.left,"0",stringBuilder2);
            getCodes(node.right,"1",stringBuilder2);
        }
        //叶子节点，返回值就行
        else {
            huffmanCodeTab.put(node.data,stringBuilder2.toString());
        }
    }

    /**
     * 构建赫夫曼树
     * @param nodes List<Node> 的节点
     * @return
     */
    public static Node createHuffManTree(List<Node> nodes){
        //只剩一个数，表示构建树完成
        while (nodes.size()>1){
            //排序，从小到大
            Collections.sort(nodes);
            //取到两个最小的
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            //父节点没有数据，只有权值
            Node parent = new Node(null,leftNode.weight+rightNode.weight );
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * 把byte 数据转换成List<Node> 形式为 node[data=97,weight=15],node[data=32,weight=5]....
     * @param bytes
     * @return
     */
    public static List<Node> getNodes(byte[] bytes){
        Map<Byte,Integer> map = new HashMap<>();
        //统计每个字符出现个数
        for (byte b : bytes) {
            Integer counts = map.get(b);
            if(counts==null){
                map.put(b, 1);
            }
            else {
                map.put(b,counts+1);
            }
        }
        //把map添加进数组里
        List<Node> nodes = new ArrayList<>();
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }
}

class Node implements Comparable<Node>{
    //传输的字符
    Byte data;
    //字符对应的权值
    int weight;
    Node left;
    Node right;

    public void preOrder(){
        System.out.println(this);
        if(this.left!=null){
            this.left.preOrder();
        }
        if(this.right!=null){
            this.right.preOrder();
        }
    }

    @Override
    public int compareTo(Node o) {
        return this.weight - o.weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }
}
