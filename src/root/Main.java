package root;

public class Main {
	public static void main(String args[]){
		TheQueue newQueue = new TheQueue(10) ;
		newQueue.insert("1");
	
		newQueue.insert("2");
		newQueue.insert("3");
		newQueue.insert("4");
		newQueue.insert("5");
		newQueue.insert("6");
		TheQueue.displayTheQueue();
		newQueue.remove();
		newQueue.remove();

		TheQueue.displayTheQueue();
		
		newQueue.insert("7");
		newQueue.insert("8");
		newQueue.insert("9");
		newQueue.insert("10");
		
		TheQueue.displayTheQueue();
		newQueue.remove();
		newQueue.remove();

		System.out.println("size:"+newQueue.size());
		TheQueue.displayTheQueue();
		
		newQueue.insert("11");
		newQueue.insert("12");
		newQueue.insert("13");
		newQueue.insert("14");
		newQueue.insert("15");
		newQueue.insert("16");
		newQueue.insert("17");
		newQueue.insert("18");
		System.out.println("isEmpty"+newQueue.isEmpty());
		TheQueue.displayTheQueue();
		
		newQueue.remove();
		newQueue.remove();
		newQueue.remove();
		newQueue.remove();
		newQueue.remove();
		newQueue.remove();
		newQueue.remove();
		newQueue.remove();
		newQueue.remove();

		newQueue.remove();
		newQueue.remove();
		System.out.println("isEmpty:"+newQueue.isEmpty());

		
		TheQueue.displayTheQueue();
		newQueue.peek();
		TheQueue.displayTheQueue();
		newQueue.insert("19");
		newQueue.insert("20");
		newQueue.insert("21");
		newQueue.insert("22");
		newQueue.insert("23");
		newQueue.insert("24");
		newQueue.insert("25");
		newQueue.insert("26");
		newQueue.insert("27");
		TheQueue.displayTheQueue();
	}
	
	
}
