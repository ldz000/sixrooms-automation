package sixrooms.seat.lesson04;

/**
 * 子类通过extends关键字可以标识自己的父类是谁，但作为父类无法知道自己有多少个子类 进而子类可以使用父类的财产，但父类无法访问和使用子类独有的成员
 *
 * 父类中的属性和方法可以被子类所继承，但如果被private修饰的成员，子类无法继承
 *
 * A extends B和B extends C，则B称为A的直接父类，C称为A的间接父类，Java是单继承的
 *
 * 在创建子类对象时，会先创建一个父类对象，这个父类对象会包含在子类对象所占用的内存之中
 *
 *
 */
public class Person {
    int age;
    String name;
    int height;

    public void dosomething() {
        System.out.println("人类在做事");
    }

    public Person() {
        System.out.println("Person的构造方法执行了");
    }

    public static void main(String[] args) {
        Student student = new Student();
    }
}

class Student extends Person {
    int scoreJava;
    int no;

    public void introduce() {
        System.out.println("姓名:" + name + ",学号:" + no + ",年龄:" + age + ",身高:" + height + ",分数:" + scoreJava);
    }
}

class Teacher extends Person {

}
