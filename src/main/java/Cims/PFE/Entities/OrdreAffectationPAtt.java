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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import com.fasterxml.jackson.annotation.JsonIgnore;

import Cims.PFE.Dao.AffectationPartielleRepository;

@Entity  
@Table(name="ordreAffectationPAtt") 
public class OrdreAffectationPAtt {
	
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	@Column(name="idO_AffAtt")
	private Long idO_AffAtt;
	/*@OneToMany(mappedBy = "site")
	 private List<AffectationPartielle> affectationp;*/
	
	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_affectation_p")
	private AffectationPartielle AffectationPartielle ;
	

	public OrdreAffectationPAtt() {
	}
	
	
	public OrdreAffectationPAtt(Long idO_AffAtt, Cims.PFE.Entities.AffectationPartielle affectationPartielle) {
		super();
		this.idO_AffAtt = idO_AffAtt;
		AffectationPartielle = affectationPartielle;
	}

	public Long getIdO_AffAtt() {
		return idO_AffAtt;
	}


	public void setIdO_AffAtt(Long idO_AffAtt) {
		this.idO_AffAtt = idO_AffAtt;
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
		return "OrdreAffectationPAtt [idO_AffAtt=" + idO_AffAtt + ", AffectationPartielle=" + AffectationPartielle + "]";
	}


		

	
	
	
}
