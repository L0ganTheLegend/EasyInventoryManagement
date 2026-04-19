public class Product {
	
	String productName = null;

	public Product ( String name ) {
		this.productName = name;
	}
	
	Node first = null;
	int currId = 0;

	class Node {
		Item item;
		Node next;
		
		Node(Item i, Node n) {
			this.item = i;
			this.next = n;
		}

		void setItem(Item i) {
			this.item = i;
		}

		void setNext(Node n) {
			this.next = n;
		}

		Item item() {
			return item;
		}

		Node next() {
			return next;
		}
	}
	
	public void addItem( String addedDate, String expDate ) {
		Item newItem = new Item(addedDate, expDate, currId++);
		if(first == null) {
			first = new Node(newItem, null);
		} else {
			Node cursor = first;
			while(cursor.next() != null) {
				cursor = cursor.next();
			}
			cursor.setNext(new Node(newItem, null));
		}
	}

	public boolean itemExists( int id ) {
		Node cursor = first;
		while(cursor != null) {
			if(id == cursor.item().getId()) {
				return true;
			}
			cursor = cursor.next();
		}

		return false;
	}
	
	public Item getItem ( int id ) {
		if ( itemExists( id ) ) {
			Node cursor = first;
			while(cursor != null) {
				if(cursor.item().getId() == id) {
					return cursor.item();
				}
				cursor = cursor.next();
			}
		}
		return null;
	}

	public boolean removeItem( int id ) {
		if ( itemExists( id ) ) {
			Node cursor = first;
			while(cursor != null) {
				if(cursor.item().getId() == id) {
					if(cursor.equals(first)) {
						first = first.next();
					} else {
						cursor = first;
						while(cursor.next().item().getId() != id) {
							cursor = cursor.next();
						}
						cursor.setNext(cursor.next().next());
					}
					
					return true;
				}
			}	
		}
		return false;
	}
	
	public Item[ ] getItems( ) {
		Node cursor = first;
		int size = 0;
		while(cursor != null) {
			size++;
			cursor = cursor.next();
		}
		Item[] items = new Item[size];
		cursor = first;
		for(int i = 0; i < size; i++) {
			items[i] = cursor.item();
			cursor = cursor.next();
		}

		return items;	
	}

	public String getName( ) {
		return productName;
	}

	public void printItemInfo( int id ) {
		Item i = getItem( id );
		System.out.println( "Added Date: " + i.getAddedDate( ) +
				" | Expiration Date: " + i.getExpDate( ) + " | ID: " + id );
	}

	public void printAllItems( ) {
		Item[] items = getItems();
		for ( Item i : items ) {
			printItemInfo( i.getId( ) );
		}
	}

	int getCurrId() {
		return currId;
	}

	void setCurrId(int id) {
		currId = id;
	}
}
