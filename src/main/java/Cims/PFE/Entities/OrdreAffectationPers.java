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
@Table(name="ordreAffectationPers") 
public class OrdreAffectationPers {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	@Column(name="idO_Aff_Pers")
	private Long idO_Aff_Pers;
	
	
	@ManyToOne(cascade = CascadeType.ALL )
    @JoinColumn(name = "id_personnel_p")
	private Personnel personnel ;
	
	

	public OrdreAffectationPers() {
	}
	
	
	public OrdreAffectationPers(Long idO_Aff_Pers, Cims.PFE.Entities.Personnel personnel) {
		super();
		this.idO_Aff_Pers = idO_Aff_Pers;
		this.personnel = personnel;
	}
	

	public Long getIdO_Aff_Pers() {
		return idO_Aff_Pers;
	}


	public void setIdO_Aff_Pers(Long idO_Aff_Pers) {
		this.idO_Aff_Pers = idO_Aff_Pers;
	}

	@JsonIgnore
	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}


	@Override
	public String toString() {
		return "OrdreAffectationPers [idO_Aff_Pers=" + idO_Aff_Pers + ", Personnel=" + personnel + "]";
	}


	
	
	
}
