package sixrooms.seat.lesson04;

public class Father {
    int age = 99;
    String name;
    int height;
    int weight;

    public Father() {

    }

    public Father(int age, String name, int height, int weight) {
        this.age = age;
        this.name = name;
        this.height = height;
        this.weight = weight;
    }

    public static void main(String[] args) {
        Son son = new Son();
        son.introduce();
    }
}

class Son extends Father {
    int no;
    int age = 200;
    public void introduce(){
        System.out.println("当前年龄:"+super.age);
    }
    public Son() {

    }

    public Son(int no, int age, String name, int height, int weight) {
        // super();
        // super(age,name,height,weight);
        this.no = no;
    }
}

