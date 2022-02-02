package Cims.PFE.Entities;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
@Entity  
@Table(name="AffectationTotale")
public class AffectationTotale{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
    @Column(name="id_affectationT")
	private Long idAffectT;
	
	@Column(name="date_Affectation")
	private LocalDate date_Affectation;
	
	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_site")
	private Site site;
    
    @ManyToOne(cascade = {CascadeType.MERGE})
    @JoinColumn(name = "id_personnel")
	private Personnel personnel;
	
	
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_struct")
	private Structure structure;
	
	@OneToMany(mappedBy = "AffectationTotale",orphanRemoval=true)
	 private List<OrdreAffectationTot> ordreAffTot; 

	public AffectationTotale(Long idAffectT, Site site, Cims.PFE.Entities.Personnel personnel, Structure structure, LocalDate date_Affectation, List<OrdreAffectationTot> ordreAffTot) {
		super();
		this.idAffectT = idAffectT;
		this.date_Affectation=date_Affectation;
		this.site = site;
		this.personnel = personnel;
		this.structure = structure;
		this.ordreAffTot= ordreAffTot;
	}

	public AffectationTotale() {
	}

	public Long getIdAffectT() {
		return idAffectT;
	}

	public void setIdAffectT(Long idAffectT) {
		this.idAffectT = idAffectT;
	}
	public LocalDate getDateAffectation() {
		return date_Affectation;
	}
	public void setDateAffectation(LocalDate date_Affectation) {
		this.date_Affectation = date_Affectation;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	
	public Structure getStructure() {
		return structure;
	}

	public void setStructure(Structure structure) {
		this.structure = structure;
	}
	
	
	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}
	public List<OrdreAffectationTot> getOrdreAffTot() {
		return ordreAffTot;
	}
	public void setOrdreAffTot(List<OrdreAffectationTot> ordreAffTot) {
		this.ordreAffTot = ordreAffTot;
	}

	@Override
	public String toString() {
		return "Affectation [idAffectT=" + idAffectT + ", date_Affectation=" + date_Affectation + ", ordreAffTot=" + ordreAffTot + ", site=" + site + ", Personnel=" + personnel + "]";
	}
	

}
