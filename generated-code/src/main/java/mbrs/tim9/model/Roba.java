package mbrs.tim9.model;

import javax.persistence.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import mbrs.tim9.enums.JedinicaEnum;
import mbrs.tim9.enums.KategorijaEnum;
import mbrs.tim9.enums.VrstaPrometaEnum;

@Entity
@Table
public class Roba {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column
    private String naziv;
   
    @Column
    private String opis;
   
    @Column
    private String sifra;
   
    @Column
    private float cena;
   
    @Enumerated(EnumType.STRING)
    private JedinicaEnum jedinica_mere;

    @Enumerated(EnumType.STRING)
    private KategorijaEnum kategorija;


    @OneToOne
    private MagacinskaKartica magacinskaKartica;

    @OneToMany
    private Set<StavkaPrometnogDokumenta> stavkePrometnogDokumenta;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public String getNaziv(){
        return naziv;
    }
    public void setNaziv(String naziv){
        this.naziv = naziv;
    }
    public String getOpis(){
        return opis;
    }
    public void setOpis(String opis){
        this.opis = opis;
    }
    public String getSifra(){
        return sifra;
    }
    public void setSifra(String sifra){
        this.sifra = sifra;
    }
    public float getCena(){
        return cena;
    }
    public void setCena(float cena){
        this.cena = cena;
    }
    public JedinicaEnum getJedinica_mere(){
        return jedinica_mere;
    }
    public void setJedinica_mere(JedinicaEnum jedinica_mere){
        this.jedinica_mere = jedinica_mere;
    }
    public KategorijaEnum getKategorija(){
        return kategorija;
    }
    public void setKategorija(KategorijaEnum kategorija){
        this.kategorija = kategorija;
    }
    public Set<StavkaPrometnogDokumenta> getStavkePrometnogDokumenta(){
        return stavkePrometnogDokumenta;
    }
    public void setStavkePrometnogDokumenta(Set<StavkaPrometnogDokumenta> stavkePrometnogDokumenta){
        this.stavkePrometnogDokumenta = stavkePrometnogDokumenta;
    }

}
