package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsJsonReader{
	List<Individual> list1=new ArrayList<Individual>();
	List<Team> list2;
	

    /**
     * get a list of individual objects from db json file
     * 
     * @return 
     */
	public TeamsJsonReader() {
		try {
			FileReader fr = new FileReader(new File("C:\\Users\\ashikasrivastava\\Downloads\\assignment-resource-io-master\\assignment-resource-io-master\\src\\test\\resources\\db.json"));
			JSONParser parser = new JSONParser();
			Object ob = parser.parse(fr);
			
			JSONObject jsonobject1 = (JSONObject) ob;
			list1 = new ArrayList<Individual>();
			JSONArray jsonarray = (JSONArray) jsonobject1.get("individuals");
			JSONObject jsonobject2[] = new JSONObject[jsonarray.size()];
			
			for (int i = 0; i < jsonarray.size(); i++)
			{
				jsonobject2[i] = (JSONObject)jsonarray.get(i);
				
				Integer id = ((Long) jsonobject2[i].get("id")).intValue();
				String name = jsonobject2[i].get("name").toString();
				Boolean active = (Boolean) jsonobject2[i].get("active");
				
				Map<String, Object> map = new HashMap<String, Object>();
				
				map.put("id", id);
				map.put("name", name);
				map.put("active", active);
				
				Individual ind = new Individual(map);
				
				list1.add(ind);

			}
			
			list2 = new ArrayList<Team>();
			List<Individual> list1;

			JSONArray jsonarray1 = (JSONArray) jsonobject1.get("teams");
			JSONObject jsonobject[] = new JSONObject[jsonarray1.size()];
	
			for (int i = 0; i < jsonarray1.size(); i++)
			{
				list1 = new ArrayList<Individual>();
				jsonobject[i] = (JSONObject) jsonarray1.get(i);
				
				Integer id = ((Long) jsonobject[i].get("id")).intValue();
				String name = jsonobject[i].get("name").toString();

				JSONArray jsonarray2 = (JSONArray) jsonobject[i].get("members");
				for (int j = 0; j < jsonarray2.size(); j++) {
					Integer idd = ((Long) jsonarray2.get(j)).intValue();
					Individual ind = getIndividualById(idd);
					list1.add(ind);

				}
				
				Map<String, Object> map = new HashMap<String, Object>();

				map.put("id", id);
				map.put("name", name);
				map.put("members", list1);

				Team team1 = new Team(map);
				list2.add(team1);

			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}

    public List<Individual> getListOfIndividuals(){
		return list1;
    	  //throw new UnsupportedOperationException("Not implemented.");
    	
			

    	}
   
    
    /**
     * get individual object by id
     * 
     * @param id individual id
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     */
    public Individual getIndividualById(Integer id) throws ObjectNotFoundException{
		
       // throw new UnsupportedOperationException("Not implemented.");
    	
    	Individual in = null;
		int flag = 0;
		Iterator<Individual> itr = list1.iterator();
		while (itr.hasNext()) {
			in = itr.next();
			int a = id;
			int b = in.getId();
			if (a == b) {
				flag = 1;
				break;
			}

		}
		if (flag == 1)
			return in;
		else
			throw new ObjectNotFoundException("Individual", "id", id.toString());

	}
    	
    
    
    
    /**
     * get individual object by name
     * 
     * @param name
     * @return 
     * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException 
     */
    public Individual getIndividualByName(String name) throws ObjectNotFoundException{
        //throw new UnsupportedOperationException("Not implemented.");
    	Individual in = null;
		Iterator<Individual> itr = list1.iterator();
		int flag = 0;

		while (itr.hasNext()) {
			//System.out.println("yo");
			in = itr.next();
			String a = name;
			String b = in.getName();

			if (a.equalsIgnoreCase(b)) {
				flag = 1;
				break;
			}

		}
		if (flag == 0)
			throw new ObjectNotFoundException("Individual", "name", name);
		else
			return in;

	}
    
    
    
    /**
     * get a list of individual objects who are not active
     * 
     * @return List of inactive individuals object
     */
    public List<Individual> getListOfInactiveIndividuals(){
       // throw new UnsupportedOperationException("Not implemented.");

    	List<Individual> list1 = new ArrayList<Individual>();
    	Individual ind = null;
		Iterator<Individual> itr = list1.iterator();
		while (itr.hasNext()) {
			System.out.println("yo");
		    ind = itr.next();
			Boolean a = false;
			Boolean b = ind.isActive();
			if (a == b) {
				list1.add(ind);
			}}
		
		return list1;

	}
    
    
    /**
     * get a list of individual objects who are active
     * 
     * @return List of active individuals object
     */
    public List<Individual> getListOfActiveIndividuals(){
    	
       // throw new UnsupportedOperationException("Not implemented.");
    	List<Individual> list1 = new ArrayList<Individual>();
  
		Iterator<Individual> itr = list1.iterator();
	//	System.out.println("yo");
		while (itr.hasNext()) {
			Individual ind = itr.next();
			
			Boolean a = true;
			Boolean b = ind.isActive();
		  	
			if (a == b) {
				list1.add(ind);
			}

		}
		return list1;

		

	}
    
    
    /**
     * get a list of team objects from db json
     * 
     * @return 
     */
    public List<Team> getListOfTeams(){
		return list2;
       // throw new UnsupportedOperationException("Not implemented.");
    

    	}
}
