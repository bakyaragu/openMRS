package dataprovider;

import java.io.File;

import java.io.IOException;

import java.util.HashMap;
import java.util.List;


import com.fasterxml.jackson.core.type.TypeReference;

import com.fasterxml.jackson.databind.ObjectMapper;


public class GetDataFromJSON {

	public static List<HashMap<String, String>> jsonFileToMap(String filepath)  {
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> list = null;
		try {
			list = mapper.readValue(new File(filepath), 
					new TypeReference<List<HashMap<String, String>>>() {});
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return list;
	}
		
}
	
