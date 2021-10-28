package entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Scanner;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "nguoimuave")
public class NguoiMuaVe implements Serializable {

    public static int autoincrement = 10000;
    @Id
    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    @Column(name = "name", nullable = false)
    String name;
    @Column(name = "address", nullable = false)
    String address;
    @Column(name = "phone", nullable = false)
    String phone;
    @Column(name = "typecustomer", nullable = false)
    String typecustomer;
    @OneToMany(mappedBy = "nguoiMuaVe",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<HoaDon> hoaDons;


    public void input() {
        Scanner scanner = new Scanner(System.in);
        this.id = autoincrement++;
        System.out.println("Nhaajp teen: ");
        this.name = scanner.nextLine();
        System.out.println("Nhap dia chi: ");
        this.address = scanner.nextLine();
        System.out.println("Nhap so dien thoai: ");
        this.phone = scanner.nextLine();
        System.out.println("Nhap loai nguoi mua ve: ");
        boolean check=true;
        int choose;
        do {
           try {
               choose=scanner.nextInt();
               scanner.nextLine();
           }catch (Exception e){
               System.out.println("Khong nhap ki tu khac ngoai so");
               check=false;
               continue;
           }
           if (choose<0||choose>3){
               System.out.println("Nhap lai tu 1-5");
               check=false;
               continue;
           }
           switch (choose){
               case 1:
                   this.setTypecustomer(LoaiNguoiMua.MUA_LE);
                   System.out.println("Mua le");
                   break;
               case 2:
                   this.setTypecustomer(LoaiNguoiMua.MUA_ONLINE);
                   System.out.println("Mua online");
                   break;
               case 3:
                   this.setTypecustomer(LoaiNguoiMua.MUA_TAP_THE);
                   System.out.println("Mua tap the");
                   break;
               default:
                   System.out.println("Nhap tu 1-3");
                   check=false;
                   break;
           }
        }while (!true);
    }
    public List<HoaDon> getHoaDon(){
        return hoaDons;
    }

    @Override
    public String toString() {
        return "NguoiMuaVe{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", typecustomer='" + typecustomer + '\'' +
                '}';
    }
}
