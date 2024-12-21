public class Product {
	
	String productName = null;

	public Product ( String name ) {
		this.productName = name;
	}
	
	Item[ ] items = { };

	public void addItem( String addedDate, String expDate ) {
		int id = 0;
		if ( items.length != 0 ) {
			id = items[ items.length - 1 ].getId( ) + 1;
		}
		Item item = new Item( addedDate, expDate, id );
		Item[ ] newItems = new Item[ items.length + 1 ];
		for ( int i = 0; i < items.length; i++ ) {
			newItems[ i ] = items[ i ];
		}
		newItems[ items.length ] = item;
		items = newItems;
	}

	public boolean itemExists( int id ) {
		for ( Item i : items ) {
			if ( i.getId( ) == id ) {
				return true;
			}
		}
		return false;
	}
	
	public Item getItem ( int id ) {
		if ( itemExists( id ) ) {
			int reps = 0;
			for ( Item i : items ) {
				if ( i.getId( ) == id ) {
					return items[ reps ];
				}
				reps++;
			}
			return null;
		}
		return null;
	}

	public boolean removeItem( int id ) {
		if ( itemExists( id ) ) {
			int index = 0;
			Item[ ] newItems = new Item[ items.length - 1 ];
			for ( Item i : items ) {
				if ( i.getId( ) != id ) {
					newItems[ index ] = i;
					index++;
				}
			}
			items = newItems;
			return true;
		}
		return false;
	}

	public Item[ ] getItems( ) {
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
		for ( Item i : items ) {
			printItemInfo( i.getId( ) );
		}
	}
}
