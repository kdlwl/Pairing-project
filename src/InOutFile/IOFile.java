package InOutFile;

import authcal.check;

import java.io.*;
import java.util.ArrayList;

public class IOFile {
    /**
     * 生成并输出Exercises.txt、Answer.txt
     * @param n 为 需要的式子总数
     * @param r 为 式子中操作数的范围
     */
    public void createProblemSet(int n,int r){
        check temporarySet = new check();
        ArrayList returnList = temporarySet.generate(n,r);
        ArrayList<String> txtList = new ArrayList<>();
        ArrayList<String> ansList = new ArrayList<>();

        //获取题集、答案集
        for (int i =0;i<2*n;i++) {
            if(i<n) txtList.add(returnList.get(i).toString());
            else ansList.add(returnList.get(i).toString());
        }
        //输出题集、答案集
        createEXEFile(txtList);
        createAnsFile(ansList);
    }

    //生成并输出Exercises.txt
    private void createEXEFile(ArrayList txtList){
        try{
            File exTXT = new File("D:\\Download\\java_idea\\AuthCal\\Exercises.txt");

            //如果文件已存在，则删除文件
            if (exTXT.exists()) {
                exTXT.delete();
            }
            if(exTXT.createNewFile()){
                System.out.println("创建Exercises.txt:");
                FileOutputStream txtFile = new FileOutputStream(exTXT);
                PrintStream q = new PrintStream(exTXT);

                for(int i=0;i<txtList.size();i++){
                    System.out.print(">");
                    q.println(i+1 + ". " +txtList.get(i));
                    System.out.println(i+1 + ". " +txtList.get(i));
                }

                txtFile.close();
                q.close();
                System.out.println("Exercises.txt 创建成功！");
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }


     //生成并输出Answer.txt
    private void createAnsFile(ArrayList ansList){
        try{
            File ansTXT = new File("D:\\Download\\java_idea\\AuthCal\\Answer.txt");

            //如果文件已存在，则删除文件
            if (ansTXT.exists()) {
                ansTXT.delete();
            }

            if(ansTXT.createNewFile()){
                System.out.print("创建Answer.txt:");
                FileOutputStream ansFile = new FileOutputStream(ansTXT);
                PrintStream p = new PrintStream(ansTXT);
                p.println("答案：\n");

                for(int i=0;i<ansList.size();i++){//正常运行
                    System.out.print(">");
                    p.println(i+1 + ". " +ansList.get(i));
                }
                ansFile.close();
                p.close();
                System.out.println("创建成功！");
            }
        }
        catch(IOException ioe) {
            ioe.printStackTrace();
        }
    }
}