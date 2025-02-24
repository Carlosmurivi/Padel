package Config;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import Model.Pista;

public class HibernateDBReservation {
    private Session sesion;

    public HibernateDBReservation() {
        sesion = HibernateDB.getSession(Pista.class);
    }

    public List<Pista> leerTodasLasPistas() {
        List<Pista> pistas = null;
        
        try {
            sesion.beginTransaction();
            pistas = sesion.createQuery("from Pista", Pista.class).getResultList();
            sesion.getTransaction().commit();
        } catch (Exception e) {
            if (sesion.getTransaction() != null) {
                sesion.getTransaction().rollback(); // Rollback en caso de error
            }
            e.printStackTrace();
        } finally {
            if (sesion != null && sesion.isOpen()) {
                sesion.close(); // Cerrar sesión para evitar fugas
            }
        }
        
        return pistas;
    }
}
