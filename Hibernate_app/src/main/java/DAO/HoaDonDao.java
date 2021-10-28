package DAO;

import entity.HoaDon;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUltil;

import java.util.List;

public class HoaDonDao {

    public List<HoaDon> getAll() {
        Session session = HibernateUltil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<HoaDon> hoaDons = session.createQuery("from  HoaDon ").list();
            session.getTransaction().commit();
            return hoaDons;

        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    public static boolean sapXepTheoTongSoVe() {
        String query = "select nguoimuave.id, nguoimuave.name,nguoimuave.address, nguoimuave.phone,nguoimuave.typecustomer, sum(soluong) from HoaDon, NguoiMuaVe \n" +
                "group by nguoimuave.id,nguoimuave.name,nguoimuave.address, nguoimuave.phone,nguoimuave.typecustomer \n" +
                "order by sum(soluong) asc";
        Session session = HibernateUltil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.createSQLQuery(query).list().forEach(System.out::println);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return false;
    }

    public List<HoaDon> sapXepTen() {
        Session session = HibernateUltil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            List<HoaDon> hoaDons = session.createQuery("from HoaDon order by  nguoiMuaVe.name").list();
            session.getTransaction().commit();
            return hoaDons;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return null;
    }

    public static boolean themMoi(HoaDon hoaDon) {
        Session session = HibernateUltil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(hoaDon);
            session.getTransaction().commit();
            return true;

        } catch (Exception e) {
        } finally {
            session.close();
        }
        return false;
    }

    public static int kiemTraSoLuongVeTau(int id) {
        Session session = HibernateUltil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            Query<Integer> query = session.createQuery("select count(VeTau.id)from HoaDon where  nguoiMuaVe.id= :p_id group by nguoiMuaVe.id");
            query.setParameter("p_id", id);
            int temp = query.getSingleResult();
            session.getTransaction().commit();
            return temp;
        } catch (Exception e) {
        } finally {
            session.close();
        }
        return 0;
    }
}
