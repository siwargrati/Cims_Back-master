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
import com.fasterxml.jackson.annotation.JsonIgnore;
@Entity  
@Table(name="ordreAffectationP") 
public class OrdreAffectationP {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	@Column(name="idO_Aff")
	private Long idO_Aff;
	/*@OneToMany(mappedBy = "site")
	 private List<AffectationPartielle> affectationp;*/
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_affectation_p")
	private AffectationPartielle AffectationPartielle ;

	
	public OrdreAffectationP() {
	}

	
	public OrdreAffectationP(Long idO_Aff, Cims.PFE.Entities.AffectationPartielle affectationPartielle) {
		super();
		this.idO_Aff = idO_Aff;
		AffectationPartielle = affectationPartielle;
	}
	public Long getIdO_Aff() {
		return idO_Aff;
	}

	public void setIdO_Aff(Long idO_Aff) {
		this.idO_Aff = idO_Aff;
	}
	@JsonIgnore
	public AffectationPartielle getAffectationPartielle() {
		return AffectationPartielle;
	}
	public void setAffectationPartielle(AffectationPartielle affectationPartielle) {
		AffectationPartielle = affectationPartielle;
	}

	@Override
	public String toString() {
		return "OrdreAffectationP [idO_Aff=" + idO_Aff + ", AffectationPartielle=" + AffectationPartielle + "]";
	}



	

	
}