package sixrooms.seat.lesson03;

public class Student {
    private int no;
    private String firstName;
    private int scoreJava;

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public int getScoreJava() {
        return scoreJava;
    }

    public void setScoreJava(int scoreJava) {
        this.scoreJava = scoreJava;
    }

    public String getInfo(int no, String firstName, int scoreJava) {
        return "zenyongming  " + firstName + "\n" + "xuehao  " + no + "\n" + "chengji	" + "\n" + scoreJava;
    }

    public static void main(String[] args) {
        // 创建学生对象叫liuxiang
        Student liuxiang = new Student();
        // 调用对象的属性： 对象名.属性名
        // 内存过程： 根据引用变量中存储的地址找到
        // 位于堆内存中的Student对象，然后取得该对象的
        // no属性值
        // 强调：引用变量里面存储的永远是地址
        int no1 = liuxiang.no;
        /*
         * liuxiang.firstName="zhizhang"; liuxiang.scoreJava=20;
         */
        String name = liuxiang.getInfo(1, "dalao", 2);
        System.out.println(name);
        Student yaomin = new Student();
        int no2 = yaomin.no;
        System.out.println(no1);
        System.out.println(no2);
        liuxiang.no = 1001;
        yaomin.no = 1002;
        no1 = liuxiang.getNo();
        no2 = yaomin.getNo();
        System.out.println(no1);
        System.out.println(no2);
    }
}
