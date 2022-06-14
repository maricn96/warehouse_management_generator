package mbrs.tim9.model;

import javax.persistence.*;
import java.util.*;
import org.springframework.format.annotation.DateTimeFormat;
import mbrs.tim9.enums.JedinicaEnum;
import mbrs.tim9.enums.KategorijaEnum;
import mbrs.tim9.enums.VrstaPrometaEnum;

@Entity
@Table
public class MagacinskaKartica {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer promet_ulaza_kolicina;
   
    @Column
    private Integer promet_izlaza_kolicina;
   
    @Column
    private Integer ukupna_kolicina;
   
    @Column
    private float promet_ulaza_vrednost;
   
    @Column
    private float promet_izlaza_vrednost;
   
    @Column
    private float ukupna_vrednost;
   

    @ManyToMany
    (
        mappedBy = "magacinskeKartice"
    )
    private Set<StavkaPrometnogDokumenta> stavkaPrometnogDokumenta;

    @OneToOne
    private Roba roba;

    public Long getId(){
        return id;
    }
    public void setId(Long id){
        this.id = id;
    }
    public Integer getPromet_ulaza_kolicina(){
        return promet_ulaza_kolicina;
    }
    public void setPromet_ulaza_kolicina(Integer promet_ulaza_kolicina){
        this.promet_ulaza_kolicina = promet_ulaza_kolicina;
    }
    public Integer getPromet_izlaza_kolicina(){
        return promet_izlaza_kolicina;
    }
    public void setPromet_izlaza_kolicina(Integer promet_izlaza_kolicina){
        this.promet_izlaza_kolicina = promet_izlaza_kolicina;
    }
    public Integer getUkupna_kolicina(){
        return ukupna_kolicina;
    }
    public void setUkupna_kolicina(Integer ukupna_kolicina){
        this.ukupna_kolicina = ukupna_kolicina;
    }
    public float getPromet_ulaza_vrednost(){
        return promet_ulaza_vrednost;
    }
    public void setPromet_ulaza_vrednost(float promet_ulaza_vrednost){
        this.promet_ulaza_vrednost = promet_ulaza_vrednost;
    }
    public float getPromet_izlaza_vrednost(){
        return promet_izlaza_vrednost;
    }
    public void setPromet_izlaza_vrednost(float promet_izlaza_vrednost){
        this.promet_izlaza_vrednost = promet_izlaza_vrednost;
    }
    public float getUkupna_vrednost(){
        return ukupna_vrednost;
    }
    public void setUkupna_vrednost(float ukupna_vrednost){
        this.ukupna_vrednost = ukupna_vrednost;
    }
    public Set<StavkaPrometnogDokumenta> getStavkaPrometnogDokumenta(){
        return stavkaPrometnogDokumenta;
    }
    public void setStavkaPrometnogDokumenta(Set<StavkaPrometnogDokumenta> stavkaPrometnogDokumenta){
        this.stavkaPrometnogDokumenta = stavkaPrometnogDokumenta;
    }

}
