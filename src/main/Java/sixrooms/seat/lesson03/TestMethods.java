package sixrooms.seat.lesson03;

public class TestMethods {
    public void method1() {
        System.out.println("method1执行了");
    }

    public void method2(int a) {
        System.out.println("method2执行了");
    }

    public void method3(int a, int b) {
        System.out.println("method3执行了");
    }

    public void method4(boolean a, boolean b, int c) {
        System.out.println("method4执行了");
    }

    public static void main(String[] args) {
        /**
         * 形参：定义方法时，在()写入的参数叫形参 实参：调用方法时：在()传递的数据叫实参
         *
         * 方法调用：对象名.方法名（实参列表）； 方法如果没有形参，则调用时不传递实参 方法如果有实参，则调用时，要按照参数的个数、类型
         * 顺序进行实参的传递 如果方法有多个形参，则传递实参时，每个实参都要用","分割
         */
        TestMethods tm = new TestMethods();
        tm.method1();
        tm.method2(1);
        tm.method3(2, 3);
        tm.method4(true, true, 0);
        /**
         * 权限修饰符 关键字 同一个类中 public protected 无访问修饰符(default) private
         */
    }
}

