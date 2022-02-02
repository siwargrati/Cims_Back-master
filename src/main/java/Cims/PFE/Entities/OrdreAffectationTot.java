package Cims.PFE.Entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity  
@Table(name="ordreAffectationTot") 
public class OrdreAffectationTot {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	@Column(name="idO_Aff_Tot")
	private Long idO_Aff_Tot;
	
	
	@ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "id_affectationT")
	private AffectationTotale AffectationTotale ;
	
	

	public OrdreAffectationTot() {
	}
	
	
	public OrdreAffectationTot(Long idO_Aff_Tot, Cims.PFE.Entities.AffectationTotale AffectationTotale) {
		super();
		this.idO_Aff_Tot = idO_Aff_Tot;
		this.AffectationTotale = AffectationTotale;
	}
	

	public Long getIdO_Aff_Tot() {
		return idO_Aff_Tot;
	}


	public void setIdO_Aff_Tot(Long idO_Aff_Tot) {
		this.idO_Aff_Tot = idO_Aff_Tot;
	}

	@JsonIgnore
	public AffectationTotale getAffectationTotale() {
		return AffectationTotale;
	}

	public void setAffectationTotale(AffectationTotale AffectationTotale) {
		this.AffectationTotale = AffectationTotale;
	}


	@Override
	public String toString() {
		return "OrdreAffectationTot [idO_Aff_Tot=" + idO_Aff_Tot + ", AffectationTotale=" + AffectationTotale + "]";
	}


	
	
	
}
