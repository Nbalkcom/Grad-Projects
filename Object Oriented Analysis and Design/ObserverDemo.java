import java.util.ArrayList;
import java.util.List;

class Subject{
	private List<Observer> observers = new ArrayList<>();
    private int state;
	
	public void add(Observer o) {
	    observers.add(o);
	}
	public int getState() {
	    return state;
	}

	public void setState(int value) {
	    this.state = value;
	    execute();
	}

	private void execute() {
	    for (Observer observer : observers) {
	        observer.update();
	    }
	}	
}
abstract class Observer{
	protected Subject subject;
    public abstract void update();
}
class MultiplyBy2Observer extends Observer {
    public MultiplyBy2Observer(Subject subject) {
        this.subject = subject;
        this.subject.add(this);
    }

    public void update() {
    	System.out.println("Multiply By 2 Observer");
        System.out.println(subject.getState() + " times 2 = " + (subject.getState()*2));
    }
}
class MultiplyBy3Observer extends Observer {
    public MultiplyBy3Observer(Subject subject) {
        this.subject = subject;
        this.subject.add(this);
    }

    public void update() {
    	System.out.println("Multiply By 3 Observer");
        System.out.println(subject.getState() + " times 3 = " + (subject.getState()*3));
    }
}
class MultiplyBy4Observer extends Observer {
    public MultiplyBy4Observer(Subject subject) {
        this.subject = subject;
        this.subject.add(this);
    }

    public void update() {
    	System.out.println("Multiply By 4 Observer");
        System.out.println(subject.getState() + " times 4 = " + (subject.getState()*4));
    }
}
public class ObserverDemo {

	public static void main(String[] args) {
		Subject num = new Subject();
		new MultiplyBy2Observer(num);
		new MultiplyBy3Observer(num);
		new MultiplyBy4Observer(num);
		
		System.out.println(" Subject State is set to 0");
		num.setState(0);
		System.out.println("\n Subject State sets to 1");
		num.setState(1);
		System.out.println("\n Subject State sets to 2");
		num.setState(2);
	}

}
