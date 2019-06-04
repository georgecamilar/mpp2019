package model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Bilet")
public class Bilet implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    
    @Column(name = "nume")
    private String nume;
    
    @Column(name = "loc")
    private Integer loc;
    
    @ManyToOne
    @JoinColumn(name = "idCursa")
    private Cursa cursa;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public Integer getLoc() {
        return loc;
    }

    public void setLoc(Integer loc) {
        this.loc = loc;
    }

    public Cursa getCursa() {
        return cursa;
    }

    public void setCursa(Cursa cursa) {
        this.cursa = cursa;
    }
}
