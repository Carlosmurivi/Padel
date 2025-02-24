package Config;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Model.Pista;
import Model.Tip;

public class HibernateDBReservation {
	Session sesion;
	
	public HibernateDBReservation(){
		sesion = HibernateDB.getSession(Pista.class);
	}
	
	public List<Pista> leerTodasLasPistas() {
		List<Pista> pista= null;
		
		try {
			sesion.beginTransaction();
			pista = sesion.createQuery("from pistas" , Pista.class).getResultList();
			sesion.getTransaction().commit();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return pista;
	}
	
	
}
