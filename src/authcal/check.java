package authcal;
import java.util.ArrayList;

public class check {
    private ArrayList<String> returnList = new ArrayList<>();
    private ArrayList<String> txtList = new ArrayList<>();
    private ArrayList<String> ansList = new ArrayList<>();
    private ArrayList<String[]> ansFoList = new ArrayList<>();

    /**
     * 生成暂存题集、答案集
     * @param n 为 需要的式子总数
     * @param r 为 式子中操作数的范围
     * @return returnList 为 题集&答案集
     */
    public ArrayList generate(int n,int r) {
        RandGenerated create = new RandGenerated();
        //生成n条不重复的式子
        for(int i=0;i<n;){
            //随机生成式子
            String[] ansFormula = create.Formula(r);
            //判断生成的式子是否重复
            if (ansFormula!=null)
                if (!ifRepeat(ansFormula)) i++;
        }

        //把式子及运算结果添加到returnList
        for (int i =0; i<2*n;i++) {
            if(i<n) {
                returnList.add(txtList.get(i));
            } else {
                returnList.add(ansList.get(i - n));
            }
        }
        return returnList;
    }

    /**
     * 判断式子是否重复
     * @param ansFormula 为 后缀表达式、运算结果、式子 的 数组
     * @return ifRepeat 表示当前式子是否重复
     */
    private boolean ifRepeat(String[] ansFormula) {
        String formula = ansFormula[ansFormula.length-1];
        String[] rPNotation = new String[ansFormula.length-1];
        System.arraycopy(ansFormula, 0, rPNotation, 0, ansFormula.length-1);
        boolean ifRepeat = false;

        for (String[] ansFo: ansFoList) {
            if (ansFo == rPNotation) ifRepeat =true;//直接一一对应比较
            else if (ansFo.length == rPNotation.length && ansFo[rPNotation.length-1].equals(rPNotation[rPNotation.length-1])){//若运算结果及长度一致，则式子可能，进一步比较
                for (int j=0;j<rPNotation.length;j=j+3) {
                    //运算符前后两个操作数交换比较
                    if (ansFo[j].equals(rPNotation[j+1]) && ansFo[j+1].equals(rPNotation[j]) && ansFo[j+2].equals(rPNotation[j+2]))
                        ifRepeat =true;
                }
            }
        }

        if (!ifRepeat) {
            this.txtList.add(formula);
            this.ansList.add(rPNotation[rPNotation.length-1]);
            this.ansFoList.add(rPNotation);
        }
        return ifRepeat;
    }
}