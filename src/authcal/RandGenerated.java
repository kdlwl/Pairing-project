package authcal;

import java.util.Random;

public class RandGenerated {
    public String[] Formula(int r){
        Random random = new Random();
        String[] operator = {"＋","－","×","÷","＝"};

        //运算符 && 操作数 && 式子
        String[] totalOperator = new String[1 + random.nextInt(3)];
        String[] totalFraction = new String[totalOperator.length+1];
        String formula = new String();
        //是否包含分数
        Boolean hasFraction = false;

        //随机产生需要运算的数：
        for (int i=0;i<totalFraction.length;i++) {

            int fractionOrNot = random.nextInt(2);
            if (fractionOrNot == 0) { //生成整数
                int integralPart = random.nextInt(r);  //r以内的整数（不包括r)
                totalFraction[i] = String.valueOf(integralPart);
            } else { //生成分数
                int denominator = 1+random.nextInt(r);
                int molecule = random.nextInt(denominator);
                int integralPart = random.nextInt(r);

                //化简分数
                if (molecule!=0) {
                    int commonFactor = commonFactor(denominator, molecule);
                    denominator /= commonFactor;
                    molecule /= commonFactor;
                }

                //输出最简分数
                if (integralPart == 0 && molecule > 0) {
                    totalFraction[i] = molecule + "/" + denominator;
                    hasFraction = true;
                }
                else if (molecule == 0)
                    totalFraction[i] = String.valueOf(integralPart);
                else {
                    totalFraction[i] = integralPart + "'" + molecule + "/" + denominator;
                    hasFraction = true;
                }
            }
        }

        //随机生成运算符：
        for (int i=0;i < totalOperator.length;i++) {
            if (hasFraction)
                totalOperator[i] = operator[random.nextInt(2)];
            else
                totalOperator[i] = operator[random.nextInt(4)];
        }

        //随机选择式子括号起始位置；当式子形如 a+b= 时，不加括号
        int choose = totalFraction.length;
        if (totalFraction.length != 2 )
            choose = random.nextInt(totalFraction.length);

        //合成式子 formula
        for (int i=0;i<totalFraction.length;i++) {
            if (i == choose && choose<totalOperator.length) {
                formula = formula + "(" + totalFraction[i] + totalOperator[i] ;
            } else if (i == totalFraction.length - 1 && i == choose+1 && choose<totalOperator.length) {
                formula = formula + totalFraction[i] + ")" + "=";
            } else if (i == choose+1 && choose<totalOperator.length) {
                formula = formula + totalFraction[i] + ")" + totalOperator[i];
            } else if (i == totalFraction.length - 1) {
                formula = formula + totalFraction[i] + "=";
            } else {
                formula = formula + totalFraction[i] + totalOperator[i];
            }
        }

        //检查运算结果
        Calculate checkAns = new Calculate();
        String[] ansFormula = checkAns.checkout(formula,3*totalOperator.length+2+1);
        if (ansFormula!=null)
            return ansFormula;
        return null;
    }

    /**
     * 求最大公因数，以化简分数
     * @param x 为 操作数 的 分母
     * @param y 为 操作数 的 分子
     * @return y 为 最大公因数
     */
    public int commonFactor(int x,int y) {
        while(true)
        {
            if(x%y==0)return y;
            int temp=y;
            y=x%y;
            x=temp;
        }
    }
}

