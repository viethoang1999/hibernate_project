import DAO.HoaDonDao;
import DAO.NguoiMuaVeDAO;
import DAO.VeTauDAO;
import entity.HoaDon;
import entity.NguoiMuaVe;
import entity.VeTau;


import java.util.List;
import java.util.Scanner;

public class Main {
    public static NguoiMuaVeDAO nguoiMuaVeDAO = new NguoiMuaVeDAO();
    public static VeTauDAO veTauDAO = new VeTauDAO();
    public static HoaDonDao hoaDonDao = new HoaDonDao();

    public static int choose() {
        System.out.println("Phan mem quan ly ban ve");
        System.out.println("1.Hien thi thong tin khach hang");
        System.out.println("2.Hien thi thong tin ve");
        System.out.println("3.Hien thi danh sach mua ve");
        System.out.println("4. Them mới 1 khách");
        System.out.println("5.Them moi ve");
        System.out.println("6.Them danh sach mua ve");
        System.out.println("7.Sx theo so luong");
        System.out.println("8.Sx theo ten");
        System.out.println("9. Hoa don");
        System.out.println("10.Thoat");
        System.out.println("Moi chon chuc nang");
        Scanner scanner = new Scanner(System.in);
        int choose = 0;
        boolean check = true;
        do {
            try {
                choose = new Scanner(System.in).nextInt();
                check = true;
            } catch (Exception e) {
                System.out.println("Không được nhập ký tự khác ngoài số! Nhập lại: ");
                continue;
            }
            if (choose <= 0 || choose > 11) {
                System.out.print("Nhập số trong khoảng từ 1 đến 6! Nhập lại: ");
                check = false;
            } else break;
        } while (!check);
        return choose;
    }

    public static void menu() {
        do {
            int functionChoice = choose();
            switch (functionChoice) {
                case 1:
                    NguoiMuaVeDAO.themMoi();
                    break;
                case 2:
                    hienThiThongTinNguoiMuaVe();
                    break;
                case 3:
                    VeTauDAO.themMoi();
                    break;
                case 4:
                    hienThiVe();
                    break;
                case 5:
                    hienThiHoaDon();
                    break;
                case 6:
                    themMoi();
                    break;
                case 7:
                    HoaDonDao.sapXepTheoTongSoVe();
                    break;
                case 8:
                    sapXepTen();
                    break;
                case 9:
                    hoaDon();
                    break;
                case 10:
                    System.exit(0);
            }
        } while (true);
    }

    public static void main(String[] args) {
        menu();
    }

    public static void hienThiThongTinNguoiMuaVe() {
        List<NguoiMuaVe> nguoiMuaVes = nguoiMuaVeDAO.getAll();
        if (nguoiMuaVes != null) {
            System.out.println("Danh sach nguoi mua ve: ");
            nguoiMuaVes.forEach(System.out::println);

        } else {
            System.out.println("Khong co ban ghi nao trong csdl");
        }
//        menu();
    }

    public static void hienThiVe() {
        List<VeTau> veTaus = veTauDAO.getAll();
        if (!(veTaus.isEmpty() || veTaus == null)) {
            System.out.println("Danh sach ve tau: ");
            veTaus.forEach(System.out::println);
        } else {
            System.out.println("KHong o ban ghi nao");
        }
    }

    public static void hienThiHoaDon() {
        List<HoaDon> hoaDons = hoaDonDao.getAll();
        if (!(hoaDons.isEmpty() || hoaDons == null)) {
            System.out.println("Hoa don: ");
            hoaDons.forEach(System.out::println);
        } else {
            System.out.println("Khong co ban ghi nao");
        }
    }

    public static void sapXepTen() {
        List<HoaDon> hoaDons = hoaDonDao.sapXepTen();
        if (!(hoaDons.isEmpty() || hoaDons == null)) {
            System.out.println("Danh sach sau khi sx");
            hoaDons.forEach(System.out::println);
        } else {
            System.out.println("Khong co ban ghi nao");
        }

    }

    public static void hoaDon() {
        List<NguoiMuaVe> nguoiMuaVes = nguoiMuaVeDAO.getAll();
        for (NguoiMuaVe nguoimuave : nguoiMuaVes
        ) {
            double tong = nguoimuave.getHoaDons().stream().mapToDouble(c -> c.getVeTau().getPrice() * c.getSoluong()).sum();
            System.out.println("Nguoi mua ve: " + nguoimuave.getId() + " " + nguoimuave.getName() + " " + tong);

        }
    }

    public static void themMoi() {
        Scanner scanner = new Scanner(System.in);
        NguoiMuaVe nguoiMuaVe = null;
        VeTau veTau = null;
        System.out.println("Nhap id khach hang: ");
        int idnguoimua = 0;
        int idve;
        boolean check = true;
        boolean check1 = false;

        do {
            try {
                String id = scanner.nextLine();
                idnguoimua = Integer.parseInt(id);
                nguoiMuaVe = nguoiMuaVeDAO.timTheoId(idnguoimua);
                int number = HoaDonDao.kiemTraSoLuongVeTau(idnguoimua);
                if (nguoiMuaVe == null || number >= 5) {
                    System.out.println("Nhap lai id: ");
                    check = false;
                } else {
                    check = true;
                }
            } catch (Exception e) {
                System.out.println("Khong nhap ky tu khac ngoai so");
                check = false;
                continue;
            }
        } while (!check);
        System.out.println("Nhap id ve tau: ");
        do {
            try {
                String id = scanner.nextLine();
                idve = Integer.parseInt(id);
                veTau = veTauDAO.timTheoId(idve);
                if (veTau == null) {
                    System.out.println("nhap lai id: ");
                    check1 = false;
                } else {
                    check1 = true;
                }
            } catch (Exception e) {
                System.out.println("Khong nhap ky tu khac ngoai so");
                check1 = false;
                continue;
            }
        } while (!check1);
        System.out.println("Nhap so luong ve: ");
        int soluong = 0;
        do {
            try {
                soluong = scanner.nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Khong nhap ky tu khac ngoai so");
                check = false;
                continue;
            }
            if (soluong < 0 || soluong > 20) {
                System.out.println("So luong phai lon hon 0 va nho hon 20");
                check = false;
                continue;
            }
        } while (!check);
        boolean check2 = hoaDonDao.themMoi(new HoaDon(nguoiMuaVe, veTau, soluong));
        if (check2 == true) {
            System.out.println("Them moi thanh cong");

        } else {
            System.out.println("That bai");
        }
    }
}
