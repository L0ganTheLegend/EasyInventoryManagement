import java.util.*;

public class InventoryManager {
	
	Node first = null;

	public static void main ( String[ ] args ) {
		if ( args[ 0 ].equals( "commandLineVersion" ) ) {
			String productName = null;
			int id = 0;
			InventoryManager im = new InventoryManager( );
			Scanner input = new Scanner( System.in );
			System.out.println( "*** EASY INVENTORY MANAGEMENT ***" );
			System.out.println( );
			boolean exit = false;
			do {
				System.out.println( "VALID COMMANDS: new, list, remove, exit" );
				switch ( input.next( ).toLowerCase( ) ) {
					case "new":
						System.out.println("VALID OPTIONS: product, item" );
						switch ( input.next( ).toLowerCase( ) ) {
							case "product":
								System.out.println( "Enter product name: " );
								productName = input.next( ).toLowerCase( );
								if ( im.productExists( productName ) ) {
									System.out.println( "Product already exists!" );
									break;
								}
								im.addProduct( productName );
								System.out.println( "Product \"" + productName + "\"" +
										" created." );
								break;
							case "item":
								System.out.println( "Enter the name of the product " +
										"in which you wish to add an item: " );
								System.out.println( "VALID PRODUCTS: " );
								im.printProductNames( );
								productName = input.next( ).toLowerCase( );
								if ( !im.productExists( productName ) ) {
									System.out.println( "Product not found!" );
									break;
								}
								System.out.println( "Enter the date (MM/DD/YYYY): " );
								String addedDate = input.next( );
								System.out.println( "Enter the expiration date (" +
										"MM/DD/YYY): " );
								String expDate = input.next( );
								im.getProduct( productName ).addItem( addedDate, 
										expDate );
								Item[ ] items = im.getProduct( productName )
									.getItems( );
								System.out.println( "Added item with id " + 
										items[ items.length - 1 ].getId( ) );
								break;
							default:
								System.out.println( "Invalid Input!" );
						}
						break;
					case "list":
						System.out.println( "VALID OPTIONS: products, items" );
						switch ( input.next( ).toLowerCase( ) ) {
							case "products":
								System.out.println( "ALL PRODUCTS: " );
								im.printProductNames( );
								break;
							case "items":
								System.out.println( "Enter the name of the product " +
										"in which you wish to list the items: " );
								System.out.println( "VALID PRODUCTS: " );
								im.printProductNames( );
								productName = input.next( ).toLowerCase( );
								if ( !im.productExists( productName ) ) {
									System.out.println( "Product not found!" );
									break;
								}
								System.out.println( "ALL ITEMS IN " +
										productName + ":" );
								im.getProduct( productName ).printAllItems( );
								break;
							default:
								System.out.println( "Invalid Input!" );
						}
						break;
					case "exit":
						exit = true;
						break;
					case "remove":
						System.out.println( "VALID OPTIONS: product, item" );
						switch ( input.next( ).toLowerCase( ) ) {
							case "product":
								System.out.println( "Enter the name of the product " +
										"in which you wish to remove: " );
								System.out.println( "VALID PRODICTS: " );
								im.printProductNames( );
								productName = input.next( ).toLowerCase( );
								if ( !im.productExists( productName ) ) {
									System.out.println( "Product not found!" );
									break;
								}
								System.out.println( "WARNING: This will erase all " +
										"items within the product! Do you wish to " +
										"proceed? (y/n): " );
								switch ( input.next( ).toLowerCase( ) ) {
									case "y":
										im.removeProduct( productName );
										System.out.println( "Removed product " + 
												productName );
										break;
									case "n":
										System.out.println( "Operation cancelled" );
										break;
									default:
										System.out.println( "Invalid input!" );
										System.out.println( "Operation cancelled" );
										break;
								}
								break;
							case "item":
								System.out.println( "Select the product from which " +
										"you would like to remove an item: " );
								System.out.println( "VALID PRODUCTS: " );
								im.printProductNames( );
								productName = input.next( ).toLowerCase( );
								if ( !im.productExists( productName ) ) {
									System.out.println( "Product not found!" );
									break;
								}
								System.out.println( "ITEMS IN " + productName );
								im.getProduct( productName ).printAllItems( );
								System.out.println( "Type the ID of the item you " +
										"wish to remove: " );
								id = Integer.parseInt( input.next( ) );
								if ( im.getProduct( productName ).removeItem( id ) ) {
									System.out.println( "Item with ID " + id +
											" successfully removed" );
								} else {
									System.out.println( "Item with ID " + id +
											" could not be found!" );
								}
								break;
							default:
								System.out.println( "Invalid Input!" );
								break;
						}
						break;
					default:
						System.out.println( "Invalid command!" );
						break;
				}
			} while ( !exit );
		}
	}

	class Node {
		Product p;
		Node next;

		Node(Product p, Node next) {
			this.p = p;
			this.next = next;
		}

		void setProduct(Product p) {
			this.p = p;
		}

		void setNext(Node n) {
			this.next = n;
		}

		Product product() {
			return p;
		}

		Node next() {
			return next;
		}
	}

	public void addProduct( String name ) {
		Product product = new Product( name );
		if(first == null) {
			first = new Node(product, null);
		} else {
			Node cursor = first;
			while(cursor.next() != null) {
				cursor = cursor.next();
			}
			cursor.setNext(new Node(product, null));
		}
	}

	public boolean productExists( String name ) {
		Node cursor = first;
		while(cursor != null) {
			if(cursor.product().getName().equals(name)) {
				return true;
			}
			cursor = cursor.next();
		}

		return false;
	}	

	public void removeProduct( String name ) {
		Node cursor = first;
		while(cursor != null) {
			if(cursor.product().getName().equals(name)) {
				if(cursor.equals(first)) {
					first = first.next();
				} else {
					cursor = first;
					while(!cursor.next().product().getName().equals(name)) {
						cursor = cursor.next();
					}
					cursor.setNext(cursor.next().next());
				}
				break;
			}
			cursor = cursor.next();
		}
	}

	public void printProductNames( ) {
		Node cursor = first;
		while(cursor != null) {
			System.out.println(cursor.product().getName());
			cursor = cursor.next();
		}
	}

	public Product getProduct( String name ) {
		Node cursor = first;
		while(cursor != null) {
			if(cursor.product().getName().equals(name)) {
				return cursor.product();
			}
			cursor = cursor.next();
		}

		return null;
	}
}
