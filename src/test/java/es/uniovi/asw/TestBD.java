package es.uniovi.asw;

import static org.junit.Assert.*;

import java.util.List;

import javax.persistence.EntityManager;

import org.junit.Before;
import org.junit.Test;

import es.uniovi.asw.dbUpdate.Jpa;
import es.uniovi.asw.logica.User;
import es.uniovi.asw.parser.GeneradorCartasTXT;
import es.uniovi.asw.parser.RCensus;
import es.uniovi.asw.parser.ReadCensus;

public class TestBD {
	
	EntityManager e=null;
	List<User> excel=null;
	List<User> db=null;
	@Before
	public void inicializar()
	{
		e=Jpa.createEntityManager();
		ReadCensus readCensus = new RCensus("Censos.xls",new GeneradorCartasTXT());
		readCensus.readCensus();
		 excel = readCensus.getUsuarios();
		db=e.createNamedQuery("User.findAll").getResultList();
	}

	@Test
	public void testNumeroDatos() {
		
		assertEquals(excel.size(), db.size());
	}
	@Test
	public void coincidenciaDatosExcelDB()
	{
		for(int i=1;i<excel.size();i++)
		{
			for(int j=0;j<db.size();j++)
			{
				if(excel.get(i).getNIF().equals(db.get(j).getNIF()) && excel.get(i).getName().equals(db.get(j).getName()) )
				{
					assertEquals(excel.get(i).getName(),db.get(j).getName());
					assertEquals(excel.get(i).getNIF(),db.get(j).getNIF());
					assertEquals(excel.get(i).getEmail(),db.get(j).getEmail());
					assertEquals(excel.get(i).getCodigoMesa(),db.get(j).getCodigoMesa());
				}
			}
		}
	}
	
	
	

}