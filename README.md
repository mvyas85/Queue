# Queue

Queues

The queue is one of the most widely used data structures. So what are queues? Where are they used? How are they implemented?
The Basics

A queue is a FIFO sequence. Addition takes place only at the tail, and removal takes place only at the head.

queues.png

The basic operations are:

enqueue(x): add an item at the tail
dequeue: remove the item at the head
peek: return the item at the head (without removing it)
size: return the number of items in the queue
isEmpty: return whether the queue has no items
Usage

You tend to see queues often....

Line of cars at a light
Line of people at a deli or at Fry's
Printer buffer
Packets waiting at a router
Simulation
Many more examples
Representations

Wrapper around a built-in list object
Array-based, bounded or expandable (All operations in Θ(1))
arrayqueue.png

Linked, explicit head-tail or circular (All operations in Θ(1))
linkedqueue.png

Queues in Java

There's already a Queue interface in the Java Core API and a whole bunch of implementing subclasses (AbstractQueue, ArrayBlockingQueue, ArrayDeque, ConcurrentLinkedQueue, DelayQueue, LinkedBlockingDeque, LinkedBlockingQueue, LinkedList, PriorityBlockingQueue, PriorityQueue, SynchronousQueue), but we're going to write some queues from scratch because:

This is the only way for you to really learn how queues work
You need coding practice, especially with linked structures
Job interviews sometimes involve implementing such things on a whiteboard
You need to know how to code things up when you find yourself in a very restrictive environment that doesn't have a collections library.
First, an interface

Hey guess what! We're going to make queues that are NOT GENERIC! Fussing with generics will just get in the way of understanding how queues work, which is what's really important at the moment.
Queue.java
package edu.lmu.cs.collections;

/**
 * A small queue interface.  You can query the size of the queue and
 * ask whether it is empty, add and remove items, and peek at the front
 * item.
 */
public interface Queue {

    /**
     * Adds the given item to the rear of the queue.
     */
    void enqueue(Object item);

    /**
     * Removes the front item from the queue and returns it.
     * @exception java.util.NoSuchElementException if the queue is empty.
     */
    Object dequeue();

    /**
     * Returns the front item from the queue without popping it.
     * @exception java.util.NoSuchElementException if the queue is empty.
     */
    Object peek();

    /**
     * Returns the number of items currently in the queue.
     */
    int size();

    /**
     * Returns whether the queue is empty or not.
     */
    boolean isEmpty();
}
The Trivial Wrapper Implementation

SimpleQueue.java
package edu.lmu.cs.collections;

import java.util.LinkedList;

/**
 * A trivial implementation of the simple queue interface, built as
 * a wrapper around the LinkedList class from java.util.
 */
public class SimpleQueue implements Queue {
    private LinkedList<Object> data = new LinkedList<Object>();
    public void enqueue(Object item) {data.addLast(item);}
    public Object dequeue() {return data.removeFirst();}
    public Object peek() {return data.getFirst();}
    public int size() {return data.size();}
    public boolean isEmpty() {return data.isEmpty();}
}
This is a common design pattern and all developers are expected to know how to do this. Terminology associated with this technique:

"The queue wraps the list"
"The list is being adapted to use in a queue context"
"The queue delegates to the list"
"The list interface is being narrowed to that of a queue"
A Bounded Array Implementation

Things to note here:

The capacity is set at creation time
Adds have a precondition that the queue isn't already full
Adding to a full queue is a state exception, not an argument exception
The array itself is effectively circular
When removing, we take care to nullify the newly unused slot in the array to prevent a possible memory leak
BoundedQueue.java
package edu.lmu.cs.collections;

import java.util.NoSuchElementException;

/**
 * An implementation of a queue using a fixed, non-expandable array whose
 * capacity is set in its constructor.
 */
public class BoundedQueue implements Queue {
    private Object[] array;
    private int size = 0;
    private int head = 0; // index of the current front item, if one exists
    private int tail = 0; // index of next item to be added

    public BoundedQueue(int capacity) {
        array = new Object[capacity];
    }

    public void enqueue(Object item) {
        if (size == array.length) {
            throw new IllegalStateException("Cannot add to full queue");
        }
        array[tail] = item;
        tail = (tail + 1) % array.length;
        size++;
    }

    public Object dequeue() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot remove from empty queue");
        }
        Object item = array[head];
        array[head] = null;
        head = (head + 1) % array.length;
        size--;
        return item;
    }

    public Object peek() {
        if (size == 0) {
            throw new NoSuchElementException("Cannot peek into empty queue");
        }
        return array[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }
}
Exercise: Draw a series of pictures that trace the execution of the following code fragment:
    Queue q = new BoundedQueue(4);
    q.enqueue("A");
    q.enqueue("B");
    q.enqueue("C");
    q.dequeue();
    q.enqueue("D");
    q.dequeue();
    q.enqueue("E");
    q.enqueue("F");
A Linked Implementation

This particular version uses dual head and tail pointers. Note here:

Adding to an empty queue is a little different than adding to a queue that already has elements, because we have to update the head in the former case but not the latter.
Removal has three distinct cases: removing from a queue with more than one element, removing the last element, and removing from an empty queue.
LinkedQueue.java
package edu.lmu.cs.collections;

import java.util.NoSuchElementException;

/**
 * An implementation of a queue using singly linked nodes.  The
 * queue itself maintains links to both the head and the tail
 * node, so that both enqueuing and dequeueing are O(1).
 */
public class LinkedQueue implements Queue {
    private class Node {
        public Object data;
        public Node next;
        public Node(Object data, Node next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node head = null;
    private Node tail = null;

    public void enqueue(Object item) {
        Node newNode = new Node(item, null);
        if (isEmpty()) {head = newNode;} else {tail.next = newNode;}
        tail = newNode;
    }

    public Object dequeue() {
        if (isEmpty()) {
            throw new NoSuchElementException();
        }
        Object item = head.data;
        if (tail == head) {
            tail = null;
        }
        head = head.next;
        return item;
    }

    public Object peek() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        return head.data;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int size() {
        int count = 0;
        for (Node node = head; node != null; node = node.next) {
            count++;
        }
        return count;
    }
}
Class Diagram

Exercise: Draw a class diagram for the interface and three implementing classes above.
Unit Testing

Here is what we need to test:

A queue is empty on construction
A queue has size 0 on construction
After n enqueues to an empty queue, n > 0, the queue is not empty and its size is n
If one enqueues x then dequeues, the value dequeued is x.
If one enqueues x then peeks, the value returned is x, but the size stays the same
If the size is n, then after n dequeues, the stack is empty and has a size 0
If one enqueues the values 1 through 50, in order, into an empty queue, then if 50 dequeues are done the values dequeues are 1 through 50.
Dequeueing from an empty queue does throw a NoSuchElementException
Peeking into an empty queue does throw a NoSuchElementException
For bounded queues only, pushing onto a full stack does throw an IllegalStateException
We really have three classes to test, but the test cases share a bunch of things in common; the common tests can go in a base class. Note how the base test class tests any kind of queue.

QueueTest.java
package edu.lmu.cs.collections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.NoSuchElementException;

import org.junit.Test;

/**
 * Base test for any class that implements the Queue interface.
 */
public abstract class QueueTest {

    /**
     * The queue to use in all the tests: set this in subclasses.
     */
    protected Queue q;

    @Test
    public void testNewQueueIsEmpty() {
        assertTrue(q.isEmpty());
        assertEquals(q.size(), 0);
    }

    @Test
    public void testInsertsToEmptyQueue() {
        int numberOfInserts = 6;
        for (int i = 0; i < numberOfInserts; i++) {
            q.enqueue("zzz");
        }
        assertTrue(!q.isEmpty());
        assertEquals(q.size(), numberOfInserts);
    }

    @Test
    public void testEnqueueThenDequeue() {
        String message = "hello";
        q.enqueue(message);
        assertEquals(q.dequeue(), message);
    }

    @Test
    public void testEnqueueThenPeek() {
        String message = "hello";
        q.enqueue(message);
        int size = q.size();
        assertEquals(q.peek(), message);
        assertEquals(q.size(), size);
    }

    @Test
    public void testFiftyInThenFiftyOut() {
        for (int i = 0; i < 50; i++) {
            q.enqueue(i);
        }
        for (int i = 0; i < 50; i++) {
            assertEquals(((Integer)q.dequeue()).intValue(), i);
        }
    }

    @Test
    public void testRemovingDownToEmpty() {
        int numberOfRemoves = (int)(Math.random() * 20 + 1);
        for (int i = 0; i < numberOfRemoves; i++) {
            q.enqueue("zzz");
        }
        for (int i = 0; i < numberOfRemoves; i++) {
            q.dequeue();
        }
        assertTrue(q.isEmpty());
        assertEquals(q.size(), 0);
    }

    @Test(expected=NoSuchElementException.class)
    public void testRemoveOnEmptyQueue() {
        assertTrue(q.isEmpty());
        q.dequeue();
    }

    @Test(expected=NoSuchElementException.class)
    public void testPeekIntoEmptyQueue() {
        assertTrue(q.isEmpty());
        q.dequeue();
    }
}
The concrete test classes instantiate the specific kind of queue. Because they are subclasses of the base test class, they inherit all of the the common test cases for free!

SimpleQueueTest.java
package edu.lmu.cs.collections;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for SimpleQueue.
 */
public class SimpleQueueTest extends QueueTest {

    @Before
    public void makeSimpleQueue() {
        q = new SimpleQueue();
    }

    @Test public void stupidPieceOfCrapMethodForEclipse() {}
}
LinkedQueueTest.java
package edu.lmu.cs.collections;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for LinkedQueue.
 */
public class LinkedQueueTest extends QueueTest {

    @Before
    public void makeLinkedQueue() {
        q = new LinkedQueue();
    }

    @Test public void stupidPieceOfCrapMethodForEclipse() {}
}
BoundedQueueTest.java
package edu.lmu.cs.collections;

import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for BoundedQueue.
 */
public class BoundedQueueTest extends QueueTest {
    private static int CAPACITY = 60;

    @Before
    public void makeBoundedQueue() {
        q = new BoundedQueue(CAPACITY);
    }

    @Test(expected=IllegalStateException.class)
    public void testEnqueueToFullQueue() {
        for (int i = 0; i < CAPACITY; i++) {
            q.enqueue("abc");
        }
        q.enqueue("abc");
    }
}
