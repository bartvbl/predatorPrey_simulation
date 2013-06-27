package util;

import java.util.ArrayList;

public class Queue<DataType> {
	private ArrayList<DataType> queue = new ArrayList<DataType>();
	
	public void enqueue(DataType object)
	{
		this.queue.add(object);
	}
	
	public DataType dequeue()
	{
		assert(!this.queue.isEmpty());
		return this.queue.remove(0);
	}
	
	public boolean isEmpty()
	{
		return this.queue.isEmpty();
	}
	
	public void enqueueAll(DataType[] items) {
		for(DataType item : items) {
			enqueue(item);
		}
	}
	
	public DataType peek() {
		assert(!this.queue.isEmpty());
		return this.queue.get(0);
	}

	public void clear() {
		this.queue.clear();
	}
}
