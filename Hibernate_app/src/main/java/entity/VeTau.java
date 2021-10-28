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

@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name = "vetau")
public class VeTau implements Serializable {
    @Id
    @Column(name = "id", nullable = false)
    int id;
    @Column(name = "typechair", nullable = false)
    String typechair;
    @Column(name = "price", nullable = false)
    double price;
    // @Transient

    public static int autoincrement = 10000;

    @OneToMany(mappedBy = "veTau", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<HoaDon> hoaDons;

    public VeTau() {
    }

    public void input() {
        Scanner scanner = new Scanner(System.in);
        this.id = autoincrement++;
        System.out.println("Nhập loại ghế: ");
        boolean check = true;
        int choose;
        do {
            try {
                choose = new Scanner(System.in).nextInt();
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Nhap lai so");
                check = false;
                continue;
            }
            if (choose > 5 || choose <= 0) {
                System.out.println("Nhap tu 1-5");
                check = false;
                continue;
            }
            switch (choose) {
                case 1:
                    this.setTypechair(LoaiVeTau.HANG_VE1);
                    System.out.println("Hang 1");
                case 2:
                    this.setTypechair(LoaiVeTau.HANG_VE2);
                    System.out.println("hang 2");
                case 3:
                    this.setTypechair(LoaiVeTau.HANG_VE3);
                    System.out.println("Hang 3");
                case 4:
                    this.setTypechair(LoaiVeTau.HANG_VE4);
                    System.out.println("Hang 4");
                case 5:
                    this.setTypechair(LoaiVeTau.HANG_VE5);
                    System.out.println("Hang 5");
                default:
                    System.out.println("Nhap sai nhap lai tu 1-5");
                    break;
            }
        } while (!check);
        System.out.println("Nhập giá: ");
        this.price = scanner.nextDouble();
    }

    public List<HoaDon> getHoaDon() {
        return hoaDons;
    }

    @Override
    public String toString() {
        return "VeTau{" +
                "id=" + id +
                ", typechair='" + typechair + '\'' +
                ", price=" + price +
                '}';
    }
}
