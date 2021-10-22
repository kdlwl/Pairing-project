package authcla.main;

import InOutFile.IOFile;
import java.util.Scanner;

public class Maincal {
    public static void main(String[] args) {
       // while(true) {
            /* **** 输入指令 **** */
           /* System.out.println("请输入生成题目数-n");
            Scanner inputn=new Scanner(System.in);
            int n=inputn.nextInt();
            System.out.println("请输入生成题目参数范围-r");
            Scanner inputr=new Scanner(System.in);
            int r=inputr.nextInt();


            /* **** 执行函数 **** */
          //  System.out.println("n: " + n + ", r: " + r);
        int n=0;
        int r=0;
        if(0 != args.length){
            for(int i=0;i<args.length;i++){ //将参数输入改为命令行输入
                if(args[i].equals("-r")){r=Integer.parseInt(args[i+1]);}
                if(args[i].equals("-n")){n=Integer.parseInt(args[i+1]);}
                //if(args[i].equals("-e")){}
                //if(args[i].equals("-a")){}
            }
        }
        else
            System.out.println("参数有误,请重新输入！");
            IOFile makefile = new IOFile();
            makefile.createProblemSet(n,r);

    }

}
