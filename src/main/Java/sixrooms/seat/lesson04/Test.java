package sixrooms.seat.lesson04;

/**
 * 继承：子承父业
 * 继承是一种手段，也是一种关系
 * 继承关系着两个类：一个父类，负责提供财产，一个子类，负责接收财产
 * 而父类所能给予的财产：属性和方法(类的组成部分)
 * 通过继承这种手段，可以将一段代码写在父类中，那么所有该父类的子类都能够使用(代码复用)
 */
public class Test {

    public static void main(String[] args) {
        Student student = new Student();
        System.out.println(student.age);
        student.dosomething();
        Teacher teacher = new Teacher();
        teacher.dosomething();
        student.introduce();
    }
}
