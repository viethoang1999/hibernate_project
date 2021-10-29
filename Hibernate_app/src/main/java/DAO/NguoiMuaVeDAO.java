package DAO;

import entity.NguoiMuaVe;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUltil;

import java.util.List;

public class NguoiMuaVeDAO {
    public List<NguoiMuaVe> getAll() {
        Session session = HibernateUltil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<NguoiMuaVe> nguoiMuaVes = session.createQuery("from NguoiMuaVe ").list();
            session.getTransaction().commit();
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    public static boolean themMoi() {
        Session session = HibernateUltil.getSessionFactory().openSession();
        NguoiMuaVe nguoiMuaVe = new NguoiMuaVe();
        nguoiMuaVe.input();
        try {
            session.beginTransaction();
            session.save(nguoiMuaVe);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return false;
    }

    public NguoiMuaVe timTheoId(int id) {
        Session session = HibernateUltil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query<NguoiMuaVe> query = session.createQuery("select  c from NguoiMuaVe c where c.id= :p_id");
            query.setParameter("p_id", id);
            NguoiMuaVe nguoiMuaVe = query.getSingleResult();
            session.getTransaction().commit();
            return nguoiMuaVe;
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            session.close();
        }
        return null;
    }
}
