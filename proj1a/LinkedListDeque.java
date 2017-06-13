public class LinkedListDeque<Beep> {
    private ItemNode sentinel;
    private int size;
    
	    /* hides the terrible truth of 
	    the doubly linked list */
	    private class ItemNode {
	        public Beep item;
	        public ItemNode prev;
	        public ItemNode next;

	        public ItemNode(Beep i, ItemNode p, ItemNode n) {
	            this.item = i;
	            this.prev = p;
	            this.next = n;
	        }
	    }
 
    /* creates an empty circular deque linked list */
    public LinkedListDeque() {
        sentinel = new ItemNode(null, null, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
        // sentinel.next & sentinel.prev will point... 
        // ...to itself until the first element is addded
    }

	/* adds an element to the beginning of the deque */
    public void addFirst(Beep element) {
        ItemNode first = new ItemNode(element, sentinel, sentinel.next);
        sentinel.next.prev = first;
        sentinel.next = first;
        size += 1;
    }

	/* adds an element to the end of the deque */
	public void addLast(Beep element){
		ItemNode last = new ItemNode(element, sentinel.prev, sentinel);
		sentinel.prev.next = last;
		sentinel.prev = last;
		size += 1;
	}
	
	/* checks if the deque is empty */
	public boolean isEmpty(){
		return (size == 0);
	}
	
	/* returns the size of the deque */
	public int size(){
		return size;
	}

	/* removes and returns the first element of in the deque */
	public Beep removeFirst(){
		if (this.isEmpty()){
			return null;
		}
		Beep first = sentinel.next.item;
		sentinel.next = sentinel.next.next;
		sentinel.next.prev = sentinel;
		size -= 1;
		return first;
	}

	/* removes and returns the first element of in the deque */
	public Beep removeLast(){
		if (this.isEmpty()){
			return null;
		}
		Beep last = sentinel.prev.item;
		sentinel.prev = sentinel.prev.prev;
		sentinel.prev.next = sentinel;
		size -= 1;
		return last;
	}

	/* returns the element at that index in the deque using iteration */
	public Beep get(int index){
		// creates a pointer to prevent altering the deque.
		ItemNode pointer = sentinel;
		// if element is near the end of the deque, ...
		// ...iterate backwards startng from the last element
		if (index > ((size-1)/2)){
			pointer = pointer.prev;
			while ((size-index-1) != 0){
				pointer = pointer.prev;
				index += 1;
			}
		}
		// if element is near the front of the deque, ...
		// ...iterate forwards startng from the first element
		else {
			pointer = pointer.next;
			while (index != 0){
				pointer = pointer.next;
				index -= 1;
			}
		}
		// returns the element the pointer points to
		return pointer.item;
	}

	/* returns the element at that index in the deque using recursion */
	public Beep getRecursive(int index){
		return getRecursive(index, sentinel.next);
	}
		// Helper method for getRecursive(int index)
		public Beep getRecursive(int index, ItemNode pointer){
			if(index == 0) {
				return pointer.item;
			}
			return getRecursive(index-1, pointer = pointer.next);
		}

	/* prints all elements of deque in order */ 
	public void printDeque() {
		int count = 0;
		ItemNode pointer = sentinel.next;
		while (count <= (size-1)) {
			System.out.print(pointer.item + " ");
			pointer = pointer.next;
			count ++;
		}
	}
}