package algorithm;

/**
 * @author :覃玉锦
 * @create :2021-01-22 09:42:00
 * 棋盘递归回溯
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，
 * 每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则该路径不能再进入该格子。 例如
 * https://www.nowcoder.com/practice/c61c6999eecb4b8f88a98f66b273a3cc?tpId=13&tqId=11218&tPage=1&rp=1&ru=/ta/coding-interviews&qru=/ta/coding-interviews/question-ranking
 * 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行第二个格子之后
 * ，路径不能再次进入该格子。
 * <p>
 * 25
 */
public class Problem_12 {
    public static void main(String[] args) {
        /*char[][] matrix = {
                A B C E H J I G
                S F C S L O P Q
                A D E E M N O E
                A D I D E J F M
                V C E I F G G S
        };*/
        char[] matrix = "ABCEHJIGSFCSLOPQADEEMNOEADIDEJFMVCEIFGGS".toCharArray();
        char[] str = "SGGFIECVAASABCEHJIGQEM".toCharArray();
        int rows = 5,cols = 8;
        Problem_12 fpim = new Problem_12();
        boolean b = fpim.hasPath(matrix, rows, cols, str);
        if(b) System.out.println("找到");
        System.out.println("没有找到");
    }

    public boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        char[][] newMatrix = buildMatrix(matrix, rows, cols);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if(dfs(newMatrix,str,i,j,0)){
                    return true;
                }
            }
        }
        return false;
    }

    private char[][] buildMatrix(char[] matrix,int rows,int cols){
        char[][] retMatrix = new char[rows][cols];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                retMatrix[i][j] = matrix[index++];
            }
        }
        return retMatrix;
    }

    /**
     * bfs的基本思路就是
     *
     * @param matrix 二维矩阵
     * @param str    目标字符串
     * @param i      二位矩阵的行
     * @param j      二位矩阵的列
     * @param k      字符串的下标位置
     * @return
     */
    private boolean dfs(char[][] matrix, char[] str, int i, int j, int k) {
        //不符合的条件：1、边界。2、访问过。3、字符不匹配。
        if (i < 0 || i >= matrix.length || j < 0 || j >= matrix[0].length ||
//                matrix[i][j] == '\0' ||
                matrix[i][j] != str[k]) {
            return false;
        }
        //如果长度已经达到了，说明找到了
        if (k == str.length - 1) {
            return true;
        }
        //符合条件了之后，标记当前为已访问过，并且按照下、上、左、右顺序去接着找下一个字符
        matrix[i][j] = '\0';

        //之前的错误写法
        /*return dfs(matrix, str, i + 1, j, k + 1) || dfs(matrix, str, i - 1, j, k + 1) ||
                dfs(matrix, str, i, j - 1, k + 1) || dfs(matrix, str, i, j + 1, k + 1);*/

        boolean res = dfs(matrix, str, i + 1, j, k + 1) || dfs(matrix, str, i - 1, j, k + 1) ||
                dfs(matrix, str, i, j - 1, k + 1) || dfs(matrix, str, i, j + 1, k + 1);
        //为什么要加它：如果不还原，那么很多走不通的路就会被标记为被访问。比如我给的那个矩阵，右多个s，但只有最后一个s才是正确
        //的，但是如果不还原的话，之前的S就会被标记为以访问，所以在后序的查找中会出错。
        matrix[i][j] = str[k];
        return res;
    }
}
