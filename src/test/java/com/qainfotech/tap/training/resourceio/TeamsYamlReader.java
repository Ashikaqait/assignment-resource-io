package com.qainfotech.tap.training.resourceio;

import com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException;
import com.qainfotech.tap.training.resourceio.model.Individual;
import com.qainfotech.tap.training.resourceio.model.Team;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.omg.CORBA.portable.InputStream;
//import org.testng.internal.Yaml;

import org.yaml.snakeyaml.Yaml;

/**
 *
 * @author Ramandeep RamandeepSingh AT QAInfoTech.com
 */
public class TeamsYamlReader {
	List<Individual> list1 = new ArrayList<Individual>();
	//List<Individual> active_list = new ArrayList<Individual>();
	List<Team> list2 = new ArrayList<Team>();
	Yaml yaml = new Yaml();
	@SuppressWarnings("rawtypes")
	Map<String, Object> values1;
	@SuppressWarnings("rawtypes")
	Map<String, Object> values2;

	/**
	 * get a list of individual objects from db yaml file
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	TeamsYamlReader()
	{
	//	List list12 = new ArrayList();
		List<Individual> list11 = null;
		try {
			values1 = (Map<String, Object>) ((org.yaml.snakeyaml.Yaml) yaml).load(new FileInputStream(new File(
					"C:\\Users\\ashikasrivastava\\Downloads\\assignment-resource-io-master\\assignment-resource-io-master\\src\\main\\resources\\db.yaml")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList var2 = (ArrayList) values1.get("teams");
		System.out.println(var2.size());
		for (int i = 0; i < var2.size(); i++) {

			Map var1 = (Map<String, Object>) var2.get(i);
			System.out.println(var1);
			Integer id = ((Integer) var1.get("id")).intValue();
			String name = var1.get("name").toString();

			List var3 = (List) var1.get("members");
			 list11 = new ArrayList<Individual>();
			for (int j = 0; j < var3.size(); j++) {
				
				System.out.println("var3:"+var3+""+var3.size());
				
				
				Integer idd = (Integer) var3.get(j);
//				Integer idd = ((Integer) var1.get("id")).intValue();

				
				try {
					Individual ind = getIndividualById(idd);
					list11.add(ind);
				} catch (ObjectNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
System.out.println("list:"+list11);
			}

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id", id);
			map.put("name", name);
			map.put("members", list11);

			Team tm = new Team(map);

			list2.add(tm);
			System.out.println("list:"+list2);
		}

		
	}
		
	
	public List<Individual> getListOfIndividuals() {
		// throw new UnsupportedOperationException("Not implemented.");
		/*
		 * FileInputStream input = new FileInputStream(new
		 * File("src/main/resources/db.yaml")); Map mymap =
		 * (Map)yaml.load(input);
		 */
		list1.clear();
		try {
			values1 = (Map<String, Object>) ((org.yaml.snakeyaml.Yaml) yaml).load(new FileInputStream(new File(
					"C:\\Users\\ashikasrivastava\\Downloads\\assignment-resource-io-master\\assignment-resource-io-master\\src\\main\\resources\\db.yaml")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList var2 = (ArrayList) values1.get("individuals");
		for (int i = 0; i < var2.size(); i++) {

			Map var1 = (Map<String, Object>) var2.get(i);
/*
			Integer id = ((Integer) var1.get("id")).intValue();
			String name = var1.get("name").toString();
			Boolean active = (Boolean) var1.get("active");

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id", id);
			map.put("name", name);
			map.put("active", active);
*/
			Individual ind = new Individual(var1);

			list1.add(ind);

		}

		return list1;

	}

	/**
	 * get individual object by id
	 * 
	 * @param id
	 *            individual id
	 * @return
	 * @throws com.qainfotech.tap.training.resourceio.exceptions.ObjectNotFoundException
	 */
	public Individual getIndividualById(Integer id) throws ObjectNotFoundException {
		// throw new UnsupportedOperationException("Not implemented.");
		list1.clear();
		list1 = getListOfIndividuals();
		Individual in = null;
		int flag = 0;
		Iterator<Individual> itr = list1.iterator();
		while (itr.hasNext()) {
			in = itr.next();
			int a = id;
			int b = in.getId();
			System.out.println("rdgsrdg"+b);
			if (a == b) {
				flag = 1;
				System.out.println("fsrdgsfgf     dgdrd");
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
	public Individual getIndividualByName(String name) throws ObjectNotFoundException {
		//throw new UnsupportedOperationException("Not implemented.");
		list1.clear();
		list1 = getListOfIndividuals();
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
			//System.out.println(flag);
		}
		if (flag == 0)
			throw new ObjectNotFoundException("Individual", "Name", name);
		else
			return in;

	}

	/**
	 * get a list of individual objects who are not active
	 * 
	 * @return List of inactive individuals object
	 */
	public List<Individual> getListOfInactiveIndividuals() {
		//throw new UnsupportedOperationException("Not implemented.");
		list1.clear();
		list1 = getListOfIndividuals();
		List<Individual> list11 = new ArrayList<Individual>();
    	Individual ind = null;
		Iterator<Individual> itr = list1.iterator();
		while (itr.hasNext()) {
					    ind = itr.next();
			Boolean a = false;
			Boolean b = ind.isActive();
			if (a == b) {
				list11.add(ind);
			}}
		
		return list11;
	}

	/**
	 * get a list of individual objects who are active
	 * 
	 * @return List of active individuals object
	 */
	public List<Individual> getListOfActiveIndividuals() {
		//throw new UnsupportedOperationException("Not implemented.");
		list1.clear();
		list1 = getListOfIndividuals();
		List<Individual> list12 = new ArrayList<Individual>();
		  
		Iterator<Individual> itr = list1.iterator();
	//	System.out.println("yo");
		while (itr.hasNext()) {
			Individual ind = itr.next();
			//System.out.println("hdfghsdfdsffd"+ind);
			Boolean a = true;
			Boolean b = ind.isActive();
		  	
			if (a == b) {
				list12.add(ind);
				
				//System.out.println("hdfghfd"+list12);
			}

		}
		return list12;
	}

	/**
	 * get a list of team objects from db yaml
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public List<Team> getListOfTeams() {
		// throw new UnsupportedOperationException("Not implemented.");
	/*	list2.clear();
		list1.clear();
		
		List list12 = new ArrayList();
		List list11 = new ArrayList();
		try {
			values1 = (Map<String, Object>) ((org.yaml.snakeyaml.Yaml) yaml).load(new FileInputStream(new File(
					"C:\\Users\\ashikasrivastava\\Downloads\\assignment-resource-io-master\\assignment-resource-io-master\\src\\main\\resources\\db.yaml")));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		ArrayList var2 = (ArrayList) values1.get("teams");
		System.out.println(var2.size());
		for (int i = 0; i < var2.size(); i++) {

			Map var1 = (Map<String, Object>) var2.get(i);
			System.out.println(var1);
			Integer id = ((Integer) var1.get("id")).intValue();
			String name = var1.get("name").toString();

			List var3 = (List) var1.get("members");
			
			for (int j = 0; j < var3.size(); j++) {
				System.out.println("var3:"+var3+""+var3.size());
				
				
				Integer idd = (Integer) var3.get(j);
//				Integer idd = ((Integer) var1.get("id")).intValue();

				
				try {
					Individual ind = getIndividualById(idd);
					list11.add(ind);
				} catch (ObjectNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
System.out.println("list:"+list11);
			}

			Map<String, Object> map = new HashMap<String, Object>();

			map.put("id", id);
			map.put("name", name);
			map.put("members", list11);

			Team tm = new Team(map);

			list12.add(tm);

		}*/

		return list2;
	}
}
