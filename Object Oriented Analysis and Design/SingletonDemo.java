public class SingletonDemo {
	//Privately calls itself
    private SingletonDemo() {}

    //Privately creates a single instance of itself and holds that instance's value
    private static class SingletonHolder {
        private static final SingletonDemo INSTANCE = new SingletonDemo();
    }

    //Publicly gives access to a global method to call upon the instance created privately
    public static SingletonDemo getInstance() {
        return SingletonHolder.INSTANCE;
    }
}