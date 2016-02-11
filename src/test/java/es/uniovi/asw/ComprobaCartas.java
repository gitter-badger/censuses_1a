package es.uniovi.asw;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.*;

import es.uniovi.asw.parser.RCensus;
import es.uniovi.asw.parser.ReadCensus;
import es.uniovi.asw.logica.User;


public class ComprobaCartas {
	
	List<User>excel=null;
	List<File>cartas=new ArrayList<File>();
	
	public void rellenar() throws IOException
	{
		ReadCensus r=new RCensus("src/test/resources/Censos.xls");
		r.readCensus();
		excel=r.getUsuarios();
		for(User u:excel)
		{
			cartas.add(new File("cartas/"+u.getEmail()+".txt"));
			
		}
	}
	
	

	@Test
	public void testUsuario() throws IOException {
		rellenar();
		for(int i=0;i<cartas.size();i++)
		{
			FileReader fr=new FileReader(cartas.get(i));
			BufferedReader br=new BufferedReader(fr);
			List<String>trozos=new ArrayList<String>();
			while(br.ready())
				trozos.add(br.readLine());
			
			assertTrue(trozos.get(1).contains(excel.get(i).getEmail()));
				
		}
		
	}

}
