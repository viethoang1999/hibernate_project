package DAO;

import entity.VeTau;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUltil;

import java.util.List;

public class VeTauDAO {
    public List<VeTau> getAll() {
        Session session = HibernateUltil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<VeTau> veTaus = session.createQuery("from VeTau ").list();
            session.getTransaction().commit();
            return veTaus;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    public static boolean themMoi() {
        Session session = HibernateUltil.getSessionFactory().openSession();
        VeTau veTau = new VeTau();
        veTau.input();
        try {
            session.beginTransaction();
            session.save(veTau);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return false;
    }

    public VeTau timTheoId(int id) {
        Session session = HibernateUltil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query<VeTau> query = session.createQuery("select  c from VeTau  c where c.id= :p_id");
            query.setParameter("p_id", id);
            VeTau veTau = query.getSingleResult();
            session.getTransaction().commit();
            return veTau;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return null;

    }
}
