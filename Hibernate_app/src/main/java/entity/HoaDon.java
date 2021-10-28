package entity;

import javax.persistence.*;
import java.io.Serializable;
@Table(name = "hoadon")
@Entity
public class HoaDon implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "manguoimuave", referencedColumnName = "id")
    private NguoiMuaVe nguoiMuaVe;
    @Id
    @ManyToOne
    @JoinColumn(name = "mave", referencedColumnName = "id")
    private VeTau veTau;
    @Column(name = "soluong", nullable = false)
    private  int soluong;

    public HoaDon(NguoiMuaVe nguoiMuaVe, VeTau veTau, int soluong) {
        this.nguoiMuaVe = nguoiMuaVe;
        this.veTau = veTau;
        this.soluong = soluong;
    }
    public HoaDon(){}

    public NguoiMuaVe getNguoiMuaVe() {
        return nguoiMuaVe;
    }

    public void setNguoiMuaVe(NguoiMuaVe nguoiMuaVe) {
        this.nguoiMuaVe = nguoiMuaVe;
    }

    public VeTau getVeTau() {
        return veTau;
    }

    public void setVeTau(VeTau veTau) {
        this.veTau = veTau;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }

//    public void themHoaDon(NguoiMuaVe nguoiMuaVe, VeTau veTau, int soluong){
//        this.nguoiMuaVe=nguoiMuaVe;
//        this.veTau=veTau;
//        this.soluong=soluong;
//    }

}
