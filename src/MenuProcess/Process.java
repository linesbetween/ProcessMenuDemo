package MenuProcess;
import java.io.IOException;
import java.util.HashSet;
import java.util.HashMap;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;




public class Process {

	private static final int GOOD = 5;
	private static final int NEUTRUAL = 3;
	private static final int BAD = 1;
	
	static HashSet<Sample> tempMenu;//temporarily holds menu under processing. 
	//forget why use static instead of passing as argument...
	static HashMap<String, SingleMenuReport> reportCollection; //<store ID, menu items>, all single reports here
	
	//For single menu report. more criteria to add.
	public static  int rate(HashSet<Sample> menu) 
	{
		int highCals  =0  , lowCals = 0;
		
		for (Sample s: menu)
		{
			if (s.calories > 400)
				highCals++;
			else
				lowCals++;
		}
		
		if (highCals > (highCals + lowCals)* 0.7)
			return BAD;
		else if (highCals <= (highCals + lowCals)* 0.7 && highCals >= (highCals + lowCals)* 0.3)
			return NEUTRUAL;
		else 
			return GOOD;
		
	}
	
	
	public static void main(String args[]) throws JsonSyntaxException, IOException
	{
		tempMenu = new HashSet<Sample>();
		reportCollection = new HashMap<String, SingleMenuReport>();
		Gson gson;
		String jsonStr;
		
		//Process Json in each file. 
		for (String s: args)
		{
			System.out.println("\n\n" + s);
			UnWrap.json2Java(s);
			reportCollection.put(s, new SingleMenuReport(s,rate(tempMenu)));
			System.out.println("Rating is: " + rate(tempMenu));
			
			tempMenu.clear();
		}
		
		//Wrap all single reports into Json
		gson = new Gson();
		jsonStr = gson.toJson(reportCollection); // 
		
		System.out.println("\n\n Json string: ");
		System.out.println(jsonStr);

	}
}
