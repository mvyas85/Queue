package root;

import java.util.Arrays;

public class TheQueue {

	private static Object[] theQueue;
	private static int size;
	private static int CAPACITY,front,rear;

	public TheQueue(int capacity){
		CAPACITY = capacity;
		theQueue = new Object[CAPACITY];
		Arrays.fill(theQueue, "-1");
		size=0;
		front = 0;
		rear = 0;
	}
	
	public void insert(Object value){
		if(rear<CAPACITY){
			theQueue[size] = value;
			size++;
			rear++;
			System.out.println("Adding :"+value);
		}
		else{
			System.out.println("Ques is full");
		}
	}
	
	public void priorityInsert(Object value){
		
		if(CAPACITY==0){
			
		}
		
		else{
			System.out.println("Ques is full");
		}
	}
	
	
	public void remove(){
		if(size>0){
			System.out.println("Removing :"+theQueue[front]);
		theQueue[front]="-1";
		front++;
		size--;
		suffle();
		}
		else{
			System.out.println("Sorry the queue is empty");
		}
	}
	private void suffle(){
		if(front != rear){
			for(int i =0;i<rear-1;i++){
				theQueue[i] = theQueue[i+1];
				theQueue[i+1] = "-1";
			}
			front--;
			rear--;
			
		}
	}
	
	public void peek(){
		if(front>=0 && rear <CAPACITY)
			System.out.println("Peeking : "+theQueue[front]);
		else {
			System.out.println("Peeking : Queue is empty");
		}
	}
	
	public int size(){
		return size;
	}
	
	public boolean isEmpty(){
		if(size == 0)
			return true;
		return false;
	}
	
	public static void displayTheQueue(){
		
		for(int n = 0; n < 61; n++)System.out.print("-");
		
		System.out.println();
		
		for(int n = 0; n < CAPACITY; n++){
			
			System.out.format("| %2s "+ " ", n);
			
		}
		
		System.out.println("|");
		
		for(int n = 0; n < 61; n++)System.out.print("-");
		
		System.out.println();
		
		for(int n = 0; n < CAPACITY; n++){
			
			
			if(theQueue[n].equals("-1")) System.out.print("|     ");
			
			else System.out.print(String.format("| %2s "+ " ", theQueue[n]));
			
		}
		
		System.out.println("|");
		
		for(int n = 0; n < 61; n++)System.out.print("-");
		
		System.out.println();
		
		// Number of spaces to put before the F
		
		int spacesBeforeFront = 3*(2*(front+1)-1);
		
		for(int k = 1; k < spacesBeforeFront; k++)System.out.print(" ");
		
		System.out.print("F");
		
		// Number of spaces to put before the R
		
		int spacesBeforeRear = (2*(3*rear)-1) - (spacesBeforeFront);
		
		for(int l = 0; l < spacesBeforeRear; l++)System.out.print(" ");
		
		System.out.print("R");
		
		System.out.println("\n");
	
}
	
}
