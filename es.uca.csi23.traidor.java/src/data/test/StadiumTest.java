package data.test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;

import data.Stadium;

class StadiumTest {

	@Test
	void testConstructor() {	
		Stadium stadium = new Stadium("Estadio del Lord");
		
		assertEquals("Estadio del Lord", stadium.getName());
		assertNull(stadium.getId());
		assertNull(stadium.getDeletedAt());
	}
	
	@Test
	void testSet() {
		Stadium stadium = new Stadium("Estadio de Galletas");
		stadium.setName("Estadio de los no Galletas");
		
		assertEquals("Estadio de los no Galletas", stadium.getName());
	}
	
	@Test
	void testGet() throws IOException, SQLException {
		Stadium stadium = Stadium.Get(3);
			
		assertEquals("Ogro Stadium", stadium.getName());
		assertEquals(3, stadium.getId());
		assertNull(Stadium.Get(-7));
	}
	
	@Test
	void testSaveDelete() throws IOException, SQLException {
		Stadium stadium = new Stadium("O'Connell");
			
		stadium.Save();
		int iId = stadium.getId();
		assertEquals("O'Connell", Stadium.Get(iId).getName());
		assertEquals(iId, Stadium.Get(iId).getId());
			
		stadium.setName("Caballito de mar");
		stadium.Save();
		assertEquals("Caballito de mar", Stadium.Get(iId).getName());
			
		stadium.Delete();
		assertNotNull(stadium.getDeletedAt());
		assertNull(Stadium.Get(iId));
	}
	
	@Test
	void testSearch() throws IOException, SQLException{ 
		ArrayList<Stadium> aStadium = Stadium.Search(null);
		
		assertEquals(3, aStadium.size());
		assertEquals("Estadio de las Hadas futboleras", aStadium.get(0).getName());
		assertEquals("Estadio Olímpico de Shrek", aStadium.get(1).getName());
		
		aStadium = Stadium.Search("Estadio de las Hadas futboleras");
		assertEquals(1, aStadium.size());
		assertEquals("Estadio de las Hadas futboleras", aStadium.get(0).getName());
		
		aStadium = Stadium.Search("Estadio");
		assertEquals(2, aStadium.size());
		assertEquals("Estadio de las Hadas futboleras", aStadium.get(0).getName());
		assertEquals("Estadio Olímpico de Shrek", aStadium.get(1).getName());
		
		aStadium = Stadium.Search("Paquito Stadium");
		assertEquals(0, aStadium.size());
	}
	
}
