package sixrooms.seat.lesson02;

/**
 * 1.Java程序是由JVM进行运行的
 * 2.计算机的存储空间分为两大块：硬盘和内存
 *   硬盘：是用于存储文件的
 *   内存：是用于运行软件的
 * 3.JVM也是一种软件，运行时也要占用内存空间
 *
 * 4.JVM会将其占用的空间分为三大块：栈区，堆区和方法区，所有new出来的对象都存在于堆区。
 *
 * 5.在java中创建对象有两种写法：
 *   (1)类名 对象名 = new 类名（）；
 *   (2)类名 对象名； 对象名 = new 类名（）；
 *   记清：何时执行到new关键字，何时才会分配内存。
 *   其中对象名实际学名叫引用变量。
 *  6.创建对象时内存中实际变化为四个步骤：
 *     （1）在堆区为对象开辟内存空间，并给属性赋予默认值
 *     （2）执行显示初始化。所谓显示初始化，就是在定义属性时，直接给属性赋值。
 *     （3）执行构造方法
 *     （4）将对象的地址（对象在堆区的地址，是每一个对象的唯一标识），赋值给引用变量。
 *
 */

public class Student {

    private char firstName;
    private char no;
    private int age;

    public char getFirstName() {
        return firstName;
    }

    public void setFirstName(char firstName) {
        this.firstName = firstName;
    }

    public char getNo() {
        return no;
    }

    public void setNo(char no) {
        this.no = no;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {
        // 创建对象的一般形式：类名 对象名 = new 类名（）；
        Student liuxiang = new Student();
        // 创建对象的第二种写法
        Student yaoming;
        yaoming = new Student();
    }

}

