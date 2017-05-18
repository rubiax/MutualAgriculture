package com.geowind.hunong.jpa;

import java.util.List;

import org.junit.Test;

public class PestQuestionTest {
	
	@Test
	public void testGetAll(){
		
		PestquestionDAO pestquestionDAO = new PestquestionDAO();
		List<Pestquestion> pestquestionList =  pestquestionDAO.findAll();
		System.out.println(pestquestionList.size());
		
		for(int i = 0; i< pestquestionList.size();i++){
			System.out.println(pestquestionList.get(i).getQid());
		}
		
	}

}
