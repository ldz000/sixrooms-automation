package sixrooms.seat.lesson05;

/**
 * 多态：同一个方法被不同类型的对象调用，产生不同的结果
 *
 * 多态的核心：动态方法绑定
 *
 * 动态方法绑定：在编译时只检查语法规则，判断引用变量所对应的类型，是否拥有被调用的方法，如果拥有则符合语法规范，编译通过。
 * 在运行时会检查该引用变量所指向的实际对象的类型，调用对象实际类型中的方法。
 *
 * 多态的作用：在保证原有代码不变的情况下，增加新的功能->开闭原则
 *
 */
public class Animal {

    public void cry(){
        System.out.println("呜呜");
    }

    public static void main(String[] args) {
        Animal a1 = new Animal();
        Animal a2 = new Cat();
        Animal a3 = new Dog();
        Animal a4 = new Snake();
        a1.cry();
        a2.cry();
        a3.cry();
        a4.cry();
    }

}

class Cat extends Animal{
    @Override
    public void cry() {
        System.out.println("喵喵");
    }

}

class Dog extends Animal{
    @Override
    public void cry() {
        System.out.println("汪汪");
    }

}

class Snake extends Animal{
    @Override
    public void cry() {
        System.out.println("嘶嘶");
    }

}

