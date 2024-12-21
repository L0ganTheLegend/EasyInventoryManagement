import java.util.*;

public class InventoryManager {
	
	Product[ ] products = { };

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

	public void addProduct( String name ) {
		Product product = new Product( name );
		Product[ ] newProducts = new Product[ products.length + 1 ];
		int index = 0;
		for ( Product p : products ) {
			newProducts[ index ] = products[ index ];
			index++;
		}
		newProducts[ products.length ] = product;
		products = newProducts;
	}

	public boolean productExists( String name ) {
		for ( Product p : products ) {
			if ( p.getName( ).equals( name ) ) {
				return true;
			}
		}
		return false;
	}

	public void removeProduct( String name ) {
		int index = 0;
		Product[ ] newProducts = new Product[ products.length - 1 ];
		for ( Product p : products ) {
			if ( !p.getName( ).equals( name ) ) {
				newProducts[ index ] = p;
				index++;
			}
		}
		products = newProducts;
	}

	public void printProductNames( ) {
		for ( Product p : products ) {
			System.out.println( p.getName( ) );
		}
	}

	public Product getProduct( String name ) {
		for ( Product p : products ) {
			if ( p.getName( ).equals( name ) ) {
				return p;
			}
		}
		return null;
	}
}
