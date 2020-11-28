package dataStructure;

/**
 * @author :覃玉锦
 * @create :2020-11-04 20:32:00
 * 实现二维数组转换为稀疏数组
 * 应用场景：棋盘的存储，使用稀疏数组
 * 模拟场景：0代表没有棋子，1代表黑子，2代表白子
 */
public class SparseArr {
    public static void main(String[] args) {
        int[][] oldArr = new int[11][11];
        int count = 0,nonZeroCount = 0;
        oldArr[1][2] = 1;
        oldArr[2][3] = 2;
        oldArr[2][5] = 2;

        System.out.println("oldArr:");
        for (int i = 0; i < oldArr.length; i++) {
            for (int j = 0; j < oldArr[i].length; j++) {
                System.out.print(oldArr[i][j]);
                System.out.print(" ");
                count++;
                if(oldArr[i][j]!=0)nonZeroCount++;
            }
            System.out.println();
        }

        System.out.println("非空数："+nonZeroCount);
        //sparseArr 行 列 值
        int[][] sparseArr = new int[nonZeroCount+1][3];
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=nonZeroCount;

        int index = 0;
        for (int i = 0; i < oldArr.length; i++) {
            for (int j = 0; j < oldArr[i].length; j++) {
                if(oldArr[i][j]!=0) {
                    index++;
                    sparseArr[index][0] = i;
                    sparseArr[index][1] = j;
                    sparseArr[index][2] = oldArr[i][j];
                }
            }
        }

        System.out.println("sparseArr:");
        for (int[] ints : sparseArr) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
            }
            System.out.println();
        }

        int[][] newArr = new int[sparseArr[0][0]][sparseArr[0][1]];
        for (int i = 1; i < sparseArr.length; i++) {
            newArr[sparseArr[i][0]][sparseArr[i][1]]=sparseArr[i][2];
        }
        System.out.println("newArr:");
        for (int[] ints : newArr) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
            }
            System.out.println();
        }

    }
}
