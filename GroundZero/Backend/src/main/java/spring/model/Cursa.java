package spring.model;

import javax.persistence.*;

@Entity
@Table(name = "Cursa")
public class Cursa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    
    @Column(name = "destinatie")
    private String destinatie;
    
    @Column(name = "plecare")
    private String plecare;
    
    @Column(name = "dataPlecare")
    private String dataPlecare;
    
    @Column(name = "locuriDisponibile")
    private Integer locuriDisponibile;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDestinatie() {
        return destinatie;
    }

    public void setDestinatie(String destinatie) {
        this.destinatie = destinatie;
    }

    public String getPlecare() {
        return plecare;
    }

    public void setPlecare(String plecare) {
        this.plecare = plecare;
    }

    public String getDataPlecare() {
        return dataPlecare;
    }

    public void setDataPlecare(String dataPlecare) {
        this.dataPlecare = dataPlecare;
    }

    public Integer getLocuriDisponibile() {
        return locuriDisponibile;
    }

    public void setLocuriDisponibile(Integer locuriDisponibile) {
        this.locuriDisponibile = locuriDisponibile;
    }
}
