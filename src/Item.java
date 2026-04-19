public class Item {
	
	String addedDate = null;
	String expDate = null;
	int id = 0;
	
	public Item( String addedDate, String expDate, int id ) {
		this.addedDate = addedDate;
		this.expDate = expDate;
		this.id = id;
	}

	public String getAddedDate( ) {
		return addedDate;
	}

	public String getExpDate( ) {
		return expDate;
	}

	public int getId( ) {
		return id;
	}

	public void setAddedDate( String newAdded ) {
		addedDate = newAdded;
	}

	public void setExpDate( String newExp ) {
		expDate = newExp;
	}

	public void setId( int newId ) {
		id = newId;
	}
	
}
