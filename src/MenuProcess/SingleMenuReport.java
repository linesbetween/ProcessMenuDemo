package MenuProcess;

public class SingleMenuReport 
{
		
		String storeID; //Unique store name as primary key
		int rating;
		
		public SingleMenuReport()
		{rating = 0;}
		
		public SingleMenuReport(String ID, int r)
		{
			storeID = ID;
			this.rating = r;
		}

}

