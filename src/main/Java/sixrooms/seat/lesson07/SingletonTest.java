package sixrooms.seat.lesson07;


public class SingletonTest {

    private SingletonTest() {

    }

    private static class SingletonHolder {
        private static final SingletonTest singleton2 = new SingletonTest();
    }

    public static SingletonTest getInstance() {
        return SingletonHolder.singleton2;
    }

}

