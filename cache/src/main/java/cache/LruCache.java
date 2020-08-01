/**
 * 
 */
package cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author rohith
 *
 */
public class LruCache<K,V> {
	static Logger LOGGER = LoggerFactory.getLogger(LruCache.class);
	//will have the latest modified entry
	private Node head;
	//least recently used node
	private Node tail;
	
	//hash map for retrieving and getting data easly.
	private HashMap <K,Node> elements ;
	
	
	//capacity of the cache
	private int capacity = 0;
	
	public LruCache(){
		elements = new HashMap<K, Node>();
	}
	
	
	
	
	public void setCapacity(int capacity) {
		this.capacity = capacity;
	}
	
	
	/** get the record from cache with key
	 * @param key
	 * @return value from cache of found or null
	 */
	public V get(K key) {
		Node selectedNode = elements.get(key);
		if (selectedNode != null) {
			LOGGER.info("# Cache hit  "+key);
			//hit so move the node to head
			moveNodeToHead(selectedNode);
		}else {
			LOGGER.info("# Cache miss  "+key);
		}
		return selectedNode != null ? selectedNode.getData() : null;
		
	}
	
	public V getLru() {
		return tail != null ? tail.getData() : null; 
	}
	
	public void invalidate(K key) {
		if(elements.containsKey(key)) {
			remove(elements.get(key)); 
			elements.remove(key);
		} 
	}
	
	public void clearCache() {
		head = tail = null;
		elements = new HashMap<>();
	}
	
	public boolean containsKey(K key) {
		return elements.containsKey(key);
	}
	
	/** get the record from cache with key
	 * @param key
	 * @return value from cache of found or null
	 */
	public V set(K key, V value) {
		Node selectedNode = elements.get(key);
		if (selectedNode != null) {
			//hit so move the node to head
			selectedNode.setData(value);
		}else {
			selectedNode = new Node(key,value);
			if(isFull()) {
				elements.remove(tail.getKey());
				remove(tail);
			}
			elements.put(key, selectedNode);
		}
		moveNodeToHead(selectedNode);
		return selectedNode.getData();
		
	}
	
	public boolean isFull() {
		return this.elements.size() == capacity;
	}
	
	private void remove(Node node) {
		if(node.getNext() == null)
			tail = node.getPrevious();
		else {
			node.getNext().setPrevious(node.getPrevious());
		}
		if(node.getPrevious() == null)
			head = node.getNext();
		else {
			node.getPrevious().setNext(node.getNext());
		}
		
	}
	

	/** move the node to head
	 * @param node
	 */
	private void moveNodeToHead(Node node) {
		//if either nodes are null (first entry)
		if((tail == null && head == null)) {
			head = tail = node;
			return;
		}
		if(head == node)
			return ;
		if( node.getPrevious() != null )
			node.getPrevious().setNext(node.getNext());			
		if( node.getNext() != null)
			node.getNext().setPrevious(node.getPrevious());
		if(node == tail)
			tail = node.getPrevious();
		node.setPrevious(null);
		node.setNext(head);
		if(head != null)
			head.setPrevious(node);
		head = node;
	}
	
	public List<V> getAsList(){
		ArrayList<V>values = new ArrayList<>();
		Node start = head;
		while(start != null) {
			values.add(start.getData());
			start = start.getNext();
		}
		return values;
	}
	
	private class Node {
		private K key;
		//for storing data
		private V data ;
		// for storing next value in liked list
		private Node next;
		//for saving previous value in linked list
		private Node previous;
		
		public Node(K key, V data) {
			super();
			this.key = key;
			this.data = data;
		}

		public V getData() {
			return data;
		}

		public void setData(V data) {
			this.data = data;
		}

		public Node getNext() {
			return next;
		}
		
		public K getKey() {
			return key;
		}

		public void setKey(K key) {
			this.key = key;
		}

		public void setNext(Node next) {
			this.next = next;
		}

		public Node getPrevious() {
			return previous;
		}

		public void setPrevious(Node previous) {
			this.previous = previous;
		}

		@Override
		public String toString() {
			return "Node [key=" + key + ", data=" + data + "]";
		}

	}
	
	
	
	
//	@Override
//	public String toString() {
//		return elements.keySet().toString();
//	}




//	public static void main(String[] args) {
//		LruCache<Integer,String>cache = new LruCache<Integer, String>();
//		cache.setCapacity(3);
//		cache.set(1, "One");
//		cache.set(2, "two");
//		cache.set(3, "three");
//		cache.set(4, "four");
//		//System.out.println(cache);
//		
//		//cache.set(1, "one");
//		cache.get(2);
//		cache.set(1, "One");
//		cache.set(2, "Two0");
//		//cache.get(6);
//		//cache.get(23);
//		//cache.remove(23);
//		
//		//cache.set(1, "One");
//		
//		
//		System.out.println(cache.getAsList());		
//		System.out.println(cache.getLru());
//	}

}
