package root;

import java.util.Arrays;

public class TheQueue {

	private static String[] theQueue;
	private static int numOfItems;
	private static int queueSize,front,rear;
	
	public static void main(String args[]){
		TheQueue newQueue = new TheQueue(10) ;
		newQueue.insert("10");
	
		newQueue.insert("10");
		newQueue.insert("15");
		newQueue.insert("60");
		newQueue.insert("13");
		newQueue.insert("56");
		
		newQueue.remove();
		newQueue.remove();
		
		newQueue.insert("60");
		newQueue.insert("13");
		newQueue.insert("56");
		newQueue.insert("10");
		
		newQueue.remove();
		newQueue.remove();
		
		newQueue.insert("15");
		newQueue.insert("60");
		newQueue.insert("13");
		newQueue.insert("56");
		newQueue.insert("10");
		newQueue.insert("15");
		newQueue.insert("60");
		newQueue.insert("13");
		
		newQueue.remove();
		newQueue.remove();
		newQueue.remove();
		newQueue.remove();
		newQueue.remove();
		newQueue.remove();
		newQueue.peek();
		
		newQueue.insert("13");
		newQueue.insert("15");
		newQueue.insert("60");
		newQueue.insert("13");
		newQueue.insert("56");
		newQueue.insert("10");
		newQueue.insert("15");
		newQueue.insert("60");
		newQueue.insert("13");
		displayTheQueue(theQueue);
	}

	public TheQueue(int size){
		queueSize = size;
		theQueue = new String[queueSize];
		
		Arrays.fill(theQueue, "-1");
		numOfItems=0;
		front = 0;
		rear = 0;
	}
	
	public void insert(String value){
		
		if(rear<queueSize){
			theQueue[numOfItems] = value;
			numOfItems++;
			rear++;
			
		}
		else{
			System.out.println("Ques is full");
		}
	}
	
	public void priorityInsert(String value){
		
		if(queueSize==0){
			
			
		}
		
		else{
			System.out.println("Ques is full");
		}
	}
	
	
	public void remove(){
		if(numOfItems>0){
		theQueue[front]="-1";
		front++;
		numOfItems--;
		}
		else{
			System.out.println("Sorry the queue is empty");
		}
	}
	
	public void peek(){
		if(front>0 && front <queueSize)
			System.out.println("Peeking : "+theQueue[front]);
		else {
			System.out.println("Peeking : Queue is empty");
		}
	}
	
	public static void displayTheQueue(String[] queueArray){
		
		for(int n = 0; n < 61; n++)System.out.print("-");
		
		System.out.println();
		
		for(int n = 0; n < queueSize; n++){
			
			System.out.format("| %2s "+ " ", n);
			
		}
		
		System.out.println("|");
		
		for(int n = 0; n < 61; n++)System.out.print("-");
		
		System.out.println();
		
		for(int n = 0; n < queueSize; n++){
			
			
			if(queueArray[n].equals("-1")) System.out.print("|     ");
			
			else System.out.print(String.format("| %2s "+ " ", queueArray[n]));
			
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
