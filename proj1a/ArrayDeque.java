public class ArrayDeque<Beep> {
	
	private Beep array[];	// initializing a circular array
    private int size;
    private int nextFirst;		
    private int nextLast;

    /* creates an empty array of size 8 */
	public ArrayDeque(){
		array = (Beep[]) new Object[8];
		nextFirst = 3;
		nextLast = 4;
		size = 0;
	}

	/* checks if the deque is empty */
 	public boolean isEmpty(){
		return (size == 0);
	}

	/* returns the size of the deque */
	public int size(){
		return size;
	}

	/* helper function to calculate one index before given index */
	public int minusOne(int index){
		if (index == 0){	
        	index = array.length-1;
        }
        else {
        	index -= 1;
        }
        return index;
	}

	/* helper function to calculate one index after given index */
	public int plusOne(int index){
		if (index == array.length-1){	
        	index = 0;
        }
        else {
        	index += 1;
        }
        return index;
	}

	/* adds an element to the beginning of the deque */
    public void addFirst(Beep element) {
        if (size == array.length) {
        	sizeUP();
        }

        array[nextFirst] = element;	// add element to the front
        size += 1;	// increment size
        nextFirst = minusOne(nextFirst); // adjust nextFirst pointer
    }

    /* adds an element to the end of the deque */
    public void addLast(Beep element) {
        if (size == array.length){
        	sizeUP();
        }

        array[nextLast] = element;	// add element to the end
        size += 1;	// increment size
        nextLast = plusOne(nextLast);	// adjust nextLast pointer
    }

    /* removes an element from the front of the deque */
    public Beep removeFirst() {
    	Beep oldfirst = array[plusOne(nextFirst)];
    	array[plusOne(nextFirst)] = null;
    	nextFirst = plusOne(nextFirst);
    	size -= 1;

    	if (array.length/4 > size){
    		sizeDOWN();
    	}
    	
    	return oldfirst;
    }

    /* removes an element from the end of the deque */
    public Beep removeLast() {
    	Beep oldlast = array[minusOne(nextLast)];
    	array[minusOne(nextLast)] = null;
    	nextLast = minusOne(nextLast);
    	size -= 1;

    	if (array.length/4 > size){
    		sizeDOWN();
    	} 

    	return oldlast;
    }

    /* get quickly returns the element associated with the given index */
    public Beep get(int index){
    	return array[(index + plusOne(nextFirst)) % array.length];
    }

    /* doubles the length of an array that is full */
    public void sizeUP(){
    	Beep[] newarray = (Beep[]) new Object[array.length*2];
    	// create a new array twice the size of the current array
    	int firstindex = plusOne(nextFirst);
    	System.arraycopy(array, arrayfirst, newarray, 1, size);
    	nextFirst = 0;
    	nextLast = size + 1;
    	this.array = newarray;
    }

    /* halves the length of an array that is less than 25% utilized*/
    public void sizeDOWN(){
    	Beep[] newarray = (Beep[]) new Object[array.length/2];
    	// create a new array half the size of the current array
    	int arrayfirst = plusOne(nextFirst);
    	int newarrayfirst = arrayfirst%newarray.length;
    	System.arraycopy(array, firstindex, newarray, newarrayfirst, size);
    	nextFirst = nextFirst%newarray.length;
    	nextLast = nextLast%newarray.length;
    	this.array = newarray;
    }

}
