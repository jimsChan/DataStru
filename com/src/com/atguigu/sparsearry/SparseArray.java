package com.atguigu.sparsearry;

public class SparseArray {
    public static void main(String[] args) {
        //先创建一个原始的二维数组11*11
        //0：表示没有没有棋子，1：表示黑子，2：表示蓝子
        int chessArr1[][]=new int[11][11];
        chessArr1[1][2]=1;
        chessArr1[2][3]=2;
        //输出原始的二维数组
        for (int[] row :chessArr1){
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        //将原始二维数组变成稀疏数组
        //1.先遍历二维数组 得到非0数据的个数
        int sum=0;
        for (int i=0;i<11;i++){
            for (int j=0;j<11;j++){
                if (chessArr1[i][j]!=0){
                    sum++;
                }
            }
        }
        System.out.println("sum="+sum);
        //luanlaide-------------------------------
        System.out.println("sum="+sum);
        for (int i=0;i<11;i++){
            for (int j=0;j<11;j++){
                if (chessArr1[i][j]!=0){
                    sum++;
                }
            }
        }

        //luanlaide End---------------------------

        //2.创建对应的稀疏数组
        int sparseArr[][] =new int[sum+1][3];
        //给稀疏数组赋值
        sparseArr[0][0]=11;
        sparseArr[0][1]=11;
        sparseArr[0][2]=sum;
        //遍历二维数组，将非0的值存放到稀疏数组中去
        int count=0;//count 用于记录第几个非0数据
        for (int i=0;i<11;i++){
            for (int j=0;j<11;j++){
                if (chessArr1[i][j]!=0){
                    count++;
                    sparseArr[count][0]=i;
                    sparseArr[count][1]=j;
                    sparseArr[count][2]=chessArr1[i][j];
                }
            }
        }
        //输出稀疏数组
        System.out.println();
        System.out.println("得到的稀疏数组为~~~~~~");
        for (int i=0;i<sparseArr.length;i++){
            System.out.printf("%d\t%d\t%d\t\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        System.out.println();

        //将稀疏数组--》恢复成原始的二维数组
        /*
        * 1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        * 2.在读取稀疏数组后几行的数据，并赋给原始的二维数组即可。
        * */

        //1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int cheessArr2[][]=new int[sparseArr[0][0]][sparseArr[0][1]];

        for (int i=1;i<chessArr1.length;i++){
            cheessArr2[chessArr1[i][0]][chessArr1[i][1]]=chessArr1[i][2];
        }

        //2.输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组");
        for (int[] row:cheessArr2){
            for (int data:row){
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }

    }
}
