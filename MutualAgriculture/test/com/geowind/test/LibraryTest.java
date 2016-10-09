package com.geowind.test;

import com.geowind.hunong.entity.Library;
import com.geowind.hunong.service.LibraryService;
import com.geowind.hunong.service.impl.LibraryServiceImpl;

public class LibraryTest {

	public static void main(String[] args) {
		LibraryService service = new LibraryServiceImpl();
		Library library = service.getTitles(1);
		System.out.println(library);
	}
	
}
