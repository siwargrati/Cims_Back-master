package Cims.PFE.Entities;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.transaction.Transactional;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="Mission") 
public class Mission {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)  
	@Column(name="idMission")
	private Long idMission;
	
	@Column(name="heureDepart")
	private String heureDepart;
	
	@Column(name="heureArrivee")
	private String heureArrivee;
	
	@Column(name="date")
	private LocalDate date;
	
	@Column(name="proprietaire")
	private String proprietaire;
	
	@Column(name="nbkm")
	private Long nbkm;
	
	@OneToMany(mappedBy = "Mission", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	 private List<OrdreMission> ordreMissions; 
	
	@ManyToOne
    @JoinColumn(name = "id_affectation_p")
	private AffectationPartielle affectationPartielle ;
	
	@ManyToOne
    @JoinColumn(name = "id_personnel_p")
	private Personnel personnel ;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Files file;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Files2 file2;
	
	@Column(name="etat_accomplie")
	private boolean etat_accomplie;
	
	public Long getIdMission() {
		return idMission;
	}
	public void setIdMission(Long idMission) {
		this.idMission = idMission;
	}
	public String getHeureDepart() {
		return heureDepart;
	}
	public void setHeureDepart(String heureDepart) {
		this.heureDepart = heureDepart;
	}
	public String getHeureArrivee() {
		return heureArrivee;
	}
	public void setHeureArrivee(String heureArrivee) {
		this.heureArrivee = heureArrivee;
	}
	
	public String getProprietaire() {
		return proprietaire;
	}
	public void setProprietaire(String proprietaire) {
		this.proprietaire = proprietaire;
	}
	
	public Long getNbkm() {
		return nbkm;
	}
	public void setNbkm(Long nbkm) {
		this.nbkm = nbkm;
	}
	
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	

	
	public Mission() {
		super();
	}
	
	@JsonIgnore
	public List<OrdreMission> getOrdreMissions() {
		return ordreMissions;
	}
	public void setOrdreMissions(List<OrdreMission> ordreMissions) {
		this.ordreMissions = ordreMissions;
	}
	
	public AffectationPartielle getAffectationPartielle() {
		return affectationPartielle;
	}
	public void setAffectationPartielle(AffectationPartielle affectationPartielle) {
		this.affectationPartielle = affectationPartielle;
	}
	
	public Personnel getPersonnel() {
		return personnel;
	}
	public void setPersonnel(Personnel personnel) {
		this.personnel = personnel;
	}
	
	 @Transactional
	public Files getFile() {
		return file;
	}
	public void setFile(Files file) {
		this.file = file;
	}
	
	 @Transactional
	public Files2 getFile2() {
		return file2;
	}
	public void setFile2(Files2 file2) {
		this.file2 = file2;
	}
	
	
	
	public boolean isEtat_accomplie() {
		return etat_accomplie;
	}
	public void setEtat_accomplie(boolean etat_accomplie) {
		this.etat_accomplie = etat_accomplie;
	}
	public Mission(String heureDepart, String heureArrivee, LocalDate date, List<OrdreMission> ordreMissions,
			AffectationPartielle affectationPartielle , Files file
			, boolean etat_accomplie, Personnel personnel, String proprietaire, Long nbkm, Files2 file2) {
		super();
		this.heureDepart = heureDepart;
		this.heureArrivee = heureArrivee;
		this.date = date;
		this.ordreMissions = ordreMissions;
		this.affectationPartielle = affectationPartielle;
		this.file = file;
		this.file2 = file2;
		this.etat_accomplie = etat_accomplie;
		this.personnel = personnel;
		this.proprietaire = proprietaire;
		this.nbkm = nbkm;
	}
	
	@Override
	public String toString() {
		return "Mission [idMission=" + idMission + ", heureDepart=" + heureDepart + ", heureArrivee=" + heureArrivee
				+ ", date=" + date + ", proprietaire=" + proprietaire +  ", nbkm=" + nbkm + ", ordreMissions=" + ordreMissions + ", affectationPartielle="
				+ affectationPartielle + "]";
	}
	}