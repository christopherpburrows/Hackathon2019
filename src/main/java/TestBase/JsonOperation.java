package TestBase;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

import Helper.suggestionJson;


public class JsonOperation {
	
	
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static List<?> deSerailseJsonfile(String jsonString, Object obj){

		try {
			mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			return  mapper.readValue(jsonString,
					TypeFactory.defaultInstance().constructCollectionType(List.class,obj.getClass()));
		} catch (Exception e) {
			Assert.fail("Unable to deserialize", e);
			return null;
		}


	}

	public static String serailseObject(Object obj){

		try {
			mapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
			return  mapper.writerWithDefaultPrettyPrinter().writeValueAsString(obj);
		} catch (Exception e) {
			Assert.fail("Unable to Serialize", e);
			return null;
		}

	}
	
	public static String createJsonString(String searchVideoName, List<String>upcomingvideos) {
		
		suggestionJson sug = new suggestionJson();
	
		
		sug.setTeam("Philips");
		sug.setVideo(searchVideoName);
		sug.setUpcoming_videos(upcomingvideos);
	
		return serailseObject(sug);
		
	}
	
	public static String writetofile(String jsonString) {
		
		String path = System.getProperty("user.dir")+File.pathSeparator+"philipsTeam.json";
	     //Write JSON file
        try (FileWriter file = new FileWriter(path+"philips.json")) {
 
            file.write(jsonString);
            file.flush();
 
        } catch (Exception e) {
            e.printStackTrace();
        }
        return path+"philips.json";
	}
	public static void main(String[] args) {
		
		List<String> names = new ArrayList<String>();
		
		names.add("movie1");
		names.add("movie2");
		suggestionJson sug = new suggestionJson();
		
		sug.setTeam("Philips");
		sug.setVideo("Our video");
		sug.setUpcoming_videos(names);
		writetofile(serailseObject(sug));
		//System.out.println(serailseObject(sug));
		
	}
}
