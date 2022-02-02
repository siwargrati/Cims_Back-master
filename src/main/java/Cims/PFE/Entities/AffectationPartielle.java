package Cims.PFE.Entities;

import java.io.ByteArrayOutputStream;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity  
@Table(name="AffectationPartielle")
public class AffectationPartielle {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
    @Column(name="id_affectation_p")
	private Long idAffect;
	
    @ManyToOne
    @JoinColumn(name = "id_site")
	private Site site;
    
	@ManyToOne
    @JoinColumn(name = "id_personnel")
	private Personnel personnel;
	
	@Column(name="dateDebut")
	private LocalDate  dateDebut;
	
    @Column(name="dateFin")
	private LocalDate  datefin;
    
    @JsonFormat(with = JsonFormat.Feature.ACCEPT_SINGLE_VALUE_AS_ARRAY)
    @ElementCollection
	private List<String> jour;
	
	@Column(name="sujet")
	private String sujet;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_etat", nullable = false)
	private Etat_AffectationP etat;
	
	
	@OneToMany(mappedBy = "AffectationPartielle")
	 private List<OrdreAffectationP> ordreAffP; 
	
	@OneToMany(mappedBy = "AffectationPartielle")
	 private List<OrdreAffectationPAtt> ordreAffPAtt; 
	
	@OneToMany(mappedBy = "affectationPartielle",fetch = FetchType.LAZY,
			cascade = CascadeType.ALL)
	 private List<Mission> Missions; 

	
	
	public Long getIdAffect() {
		return idAffect;
	}

	public void setIdAffect(Long idAffect) {
		this.idAffect = idAffect;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public Personnel getPersonnel() {
		return personnel;
	}

	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}
	public LocalDate getDateDebut() {
		return dateDebut;
	}


	public void setDateDebut(LocalDate dateDebut) {
		this.dateDebut = dateDebut;
	}


	public LocalDate getDatefin() {
		return datefin;
	}


	public void setDatefin(LocalDate datefin) {
		this.datefin = datefin;
	}	

	public List<String> getJour() {
		return jour;
	}

	public void setJour(List<String> jour) {
		this.jour = jour;
	}
	
	public List<String> getJourFr() {
		return jour;
	}

	public void setJourFr(List<String> jour) {
		this.jour = jour;
	}
	
	public String getSujet() {
		return sujet;
	}


	public void setSujet(String sujet) {
		this.sujet = sujet;
	}

	public List<OrdreAffectationP> getOrdre() {
		return ordreAffP;
	}


	public void setOrdre(List<OrdreAffectationP> ordre) {
		this.ordreAffP = ordre;
	}
	
	public List<OrdreAffectationPAtt> getOrdreAtt() {
		return ordreAffPAtt;
	}


	public void setOrdreAtt(List<OrdreAffectationPAtt> ordre) {
		this.ordreAffPAtt = ordre;
	}
	public AffectationPartielle() {
	}
	@Override
	public String toString() {
		return "AffectationPartielle [idAffect=" + idAffect + ", site=" + site + ", Personnel=" + personnel
				+ ", dateDebut=" + dateDebut + ", datefin=" + datefin + ", jour=" + jour + ", sujet=" + sujet
				+ ", etat=" + etat + ", ordreAffP=" + ordreAffP +  ", ordreAffPAtt=" + ordreAffPAtt + ", Missions=" + Missions + "]";
	}

	public List<OrdreAffectationP> getOrdreAffP() {
		return ordreAffP;
	}

	public void setOrdreAffP(List<OrdreAffectationP> ordreAffP) {
		this.ordreAffP = ordreAffP;
	}
	
	public List<OrdreAffectationPAtt> getOrdreAffPAtt() {
		return ordreAffPAtt;
	}

	public void setOrdreAffPAtt(List<OrdreAffectationPAtt> ordreAffPAtt) {
		this.ordreAffPAtt = ordreAffPAtt;
	}

	@JsonIgnore
	public List<Mission> getMissions() {
		return Missions;
	}

	public void setMissions(List<Mission> missions) {
		Missions = missions;
	}



	public AffectationPartielle(Long idAffect, Site site, Personnel personnel, LocalDate dateDebut, LocalDate datefin,
			List<String> jour, String sujet, Etat_AffectationP etat, List<OrdreAffectationP> ordreAffP,
			List<Mission> missions, List<OrdreAffectationPAtt> ordreAffPAtt) {
		super();
		this.idAffect = idAffect;
		this.site = site;
		this.personnel = personnel;
		this.dateDebut = dateDebut;
		this.datefin = datefin;
		this.jour = jour;
		this.sujet = sujet;
		this.etat = etat;
		this.ordreAffP = ordreAffP;
		this.ordreAffPAtt = ordreAffPAtt;

		Missions = missions;
	}

	public Etat_AffectationP getEtat() {
		return etat;
	}

	public void setEtat(Etat_AffectationP etat) {
		this.etat = etat;
	}





	

}