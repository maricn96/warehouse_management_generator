package mbrs.tim9.model;

import javax.persistence.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import mbrs.tim9.enums.JedinicaEnum;
import mbrs.tim9.enums.KategorijaEnum;
import mbrs.tim9.enums.VrstaPrometaEnum;

@Entity
@Table
public class StavkaPrometnogDokumenta {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer kolicina;
   
    @Column
    private float ukupna_cena;
   
    @Column
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date datum_i_vreme;
   
    @Column
    private VrstaPrometaEnum vrsta_prometa;
   

    @ManyToMany
    @JoinTable(name="dokument_kartica")
    private Set<MagacinskaKartica> magacinskeKartice;

    @ManyToMany
    (
        mappedBy = "stavkaPrometnogDokumenta"
    )
    private Set<Roba> roba;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public Integer getKolicina(){
        return kolicina;
    }
    public void setKolicina(Integer kolicina){
        this.kolicina = kolicina;
    }
    public float getUkupna_cena(){
        return ukupna_cena;
    }
    public void setUkupna_cena(float ukupna_cena){
        this.ukupna_cena = ukupna_cena;
    }
    public Date getDatum_i_vreme(){
        return datum_i_vreme;
    }
    public void setDatum_i_vreme(Date datum_i_vreme){
        this.datum_i_vreme = datum_i_vreme;
    }
    public VrstaPrometaEnum getVrsta_prometa(){
        return vrsta_prometa;
    }
    public void setVrsta_prometa(VrstaPrometaEnum vrsta_prometa){
        this.vrsta_prometa = vrsta_prometa;
    }
    public Set<MagacinskaKartica> getMagacinskeKartice(){
        return magacinskeKartice;
    }
    public void setMagacinskeKartice(Set<MagacinskaKartica> magacinskeKartice){
        this.magacinskeKartice = magacinskeKartice;
    }
    public Set<Roba> getRoba(){
        return roba;
    }
    public void setRoba(Set<Roba> roba){
        this.roba = roba;
    }

}
