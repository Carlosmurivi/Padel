package Config;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import Model.Tip;

public class HibernateDBTips {
	Session sesion;
	
	public HibernateDBTips(){
		sesion = HibernateDB.getSession(Tip.class);
	}
	
	public Tip leerTipPorId(int id) {
		Tip tip = null;
	    try {
	        sesion.beginTransaction();
	        tip = sesion.get(Tip.class, id);
	        sesion.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    }
	    return tip;
	}
	
	public List<Tip> leerTips() {
	    List<Tip> tips = null;
	    try {
	        sesion.beginTransaction();
	        tips = sesion.createQuery("from Tip", Tip.class).getResultList();
	        sesion.getTransaction().commit();
	    } catch (Exception e) {
	        e.printStackTrace();
	    } finally {
	    }
	    return tips;
	}
}
