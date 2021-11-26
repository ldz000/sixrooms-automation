package sixrooms.seat.lesson03;


public class Person {
    // 成员变量直接定义在类中 <->直接定义在类中的变量叫成员变量
    private int age = 10;

    public void doSomething() {
        // 局部变量定义在方法中 有效范围只是该方法内 <->直接定义在方法中的变量叫局部变量
        int no;
        int age = 20;
        // 成员变量会被默认初始化，
        System.out.println(age);
        no = 10;
        // 局部变量不会被自动初始化，必须先赋值才能使用
        System.out.println(no);
    }

    public void saySomething() {
        // 但局部变量的名字和成员变量的名字相同时，
        // 局部变量的有效范围会屏蔽调成员
        int age = 8888;
        System.out.println(age);
        // 局部变量的有效范围仅在定义这个局部变量的方法中
    }

    public static void main(String[] args) {
        Person person = new Person();
        person.saySomething();
        person.doSomething();
    }
}

