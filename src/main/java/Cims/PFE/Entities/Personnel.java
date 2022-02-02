package Cims.PFE.Entities;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Set;
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
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.itextpdf.text.Phrase;
@Entity  
@Table(name="personnel",uniqueConstraints = { 
		@UniqueConstraint(columnNames = "email"),@UniqueConstraint(columnNames = "telephone")
	}) 
@Inheritance( strategy = InheritanceType.SINGLE_TABLE )
@DiscriminatorColumn( name="discriminator", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("Personnel")
public  class Personnel {
	@Id  
    @GeneratedValue(strategy=GenerationType.IDENTITY)  
	@Column(name="id_personnel")
	private Long id_personnel;
	
	@Column(name="nom")
	private String nom;
	@Column(name="prenom")
	private String prenom;
	@Column(name="sexe")
	private String sexe;
	@Column(name="telephone")
	private int telephone;
	@Column(name="date_recrutement")
	private LocalDate date_recrutement;
	@Column(name="solderepos")
	private double soldeRepos;
	@Column(name="soldeexceptionnel")
	private double soldeExceptionnel;
	@Column(name="soldeinitial")
	private double soldeinitial;
	@Column(name="email")
	@NotBlank
	private String email;
	@Column(name="nom_Ar")
	private String nom_AR;
	@Column(name="prenom_AR")
	private String prenom_AR;
	
	@Column(name="matricule_CNRPS")
	private int matricule_CNRPS;
	
	@Column(name="matricule_CNSS")
	private int matricule_CNSS;
	
	@Column(name="date_naissance")
	private LocalDate date_naissance;
	
	@Column(name="Adresse")
	private String Adresse;
	@Column(name="poste_occupe")
	private String poste_occupe;
	@Column(name="date_Promotion")
	private LocalDate date_Promotion;
	@Column(name="echelle")
	private String echelle;
	@Column(name="date_Echelle")
	private LocalDate date_Echelle;
	@Column(name="echellon")
	private String echellon;
	@Column(name="date_Echellon")
	private LocalDate date_Echellon;
	
	@Column(name="date_Affectation")
	private String date_Affectation;
	
	@Column(name="cin")
	private int cin;


	@ManyToOne(fetch = FetchType.LAZY, optional = true,cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_site", nullable=true)
	private Site site;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = true,cascade = CascadeType.ALL)
    @JoinColumn(name = "id_struct", nullable = true)
	private Structure structure;

	
	@ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "id_grade", nullable = true)
	private Grade grade;

	
	@ManyToOne(fetch = FetchType.LAZY, optional = true,cascade = CascadeType.MERGE)
    @JoinColumn(name = "id_fonction", nullable = true)
	private Fonction fonction;
	
	@OneToOne(cascade = CascadeType.ALL)
	private Compte compte;
	/*@ManyToOne
	@JoinColumn(name = "idO_Aff_Pers")
	 private OrdreAffectationPers ordreAffPers;*/
	@OneToMany(mappedBy="personnel", cascade = CascadeType.MERGE, fetch = FetchType.LAZY)
    private List<AffectationTotale> affectationt ;

	

	
	@OneToMany(mappedBy="personnel", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AffectationPartielle> affectationp ;

	
	@OneToMany(mappedBy = "personnel",orphanRemoval=true)
	 private List<OrdreAffectationPers> ordreAffPers; 

	
	@OneToMany(mappedBy = "personnel",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	 private List<Mission> Missions; 
	
	public Personnel() {
	}
	@Override
	public String toString() {
		return "Personnel [id_personnel=" + id_personnel + ", nom=" + nom + ", prenom=" + prenom + ", sexe=" + sexe
				+ ", telephone=" + telephone + ", date_recrutement=" + date_recrutement + ", date_Affectation=" + date_Affectation + ", site=" + site +", email=" + email
				+  ", user=" + compte  + ", ordreAffPers=" + ordreAffPers + "]";
	}
	public Personnel(Long id_personnel, String nom, String prenom, String sexe, int telephone, LocalDate date_recrutement,
			String email,Structure structure,Grade grade, Compte compte, AffectationTotale affectation_totale, Site site, Fonction fonction, String nom_AR, String prenom_AR, String Adresse, String echelle, String echellon, String poste_Occupe, int matricule_CNSS, int matricule_CNRPS, LocalDate date_Promotion, double soldeRepos, double soldeExceptionnel, LocalDate date_Echelle, LocalDate date_Echellon, double soldeinitial, int cin, LocalDate date_naissance, List<OrdreAffectationPers> ordreAffPers, String date_Affectation, List<Mission> Missions) {
		super();
		this.id_personnel = id_personnel;
		this.nom = nom;
		this.prenom = prenom;
		this.nom_AR = nom_AR;
	    this.prenom_AR = prenom_AR;
	    this.Adresse= Adresse;
	    this.echelle=echelle;
	    this.date_Echelle=date_Echelle;
	    this.date_Echellon=date_Echellon;
	    this.date_Affectation=date_Affectation;
	    this.echellon=echellon;
	    this.poste_occupe=poste_Occupe;
	    this.matricule_CNSS = matricule_CNSS;
	    this.matricule_CNRPS = matricule_CNRPS;
	    this.date_Promotion = date_Promotion;
	    this.soldeRepos = soldeRepos;
	    this.soldeExceptionnel =soldeExceptionnel;
		this.sexe = sexe;
		this.telephone = telephone;
		this.date_recrutement = date_recrutement;
		this.email = email;
		this.structure = structure;
		this.grade = grade;
		this.compte = compte;
		this.site = site;
		this.fonction = fonction;
		this.soldeinitial = soldeinitial;
		this.cin = cin;
		this.date_naissance= date_naissance;
		this.ordreAffPers = ordreAffPers;
		this.Missions = Missions;
	}
	public Long getId_personnel() {
		return id_personnel;
	}
	public void setId_personnel(Long id_personnel) {
		this.id_personnel = id_personnel;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getNom_AR() {
		return nom_AR;
	}
	public void setNom_AR(String nom_AR) {
		this.nom_AR = nom_AR;
	}
	public String getPrenom_AR() {
		return prenom_AR;
	}
	public void setPrenom_AR(String prenom_AR) {
		this.prenom_AR = prenom_AR;
	}
	public String getSexe() {
		return sexe;
	}
	public void setSexe(String sexe) {
		this.sexe = sexe;
	}
	
	public String getDateAffectation() {
		return date_Affectation;
	}
	public void setDateAffectation(String date_Affectation) {
		this.date_Affectation = date_Affectation;
	}
	
	public String getAdresse() {
		return Adresse;
	}
	public void setAdresse(String Adresse) {
		this.Adresse = Adresse;
	}
	public int getTelephone() {
		return telephone;
	}
	public void setTelephone(int telephone) {
		this.telephone = telephone;
	}
	public String getEchelle() {
		return echelle;
	}
	public void setEchelle(String echelle) {
		this.echelle = echelle;
	}

	
	public LocalDate getDateEchelle() {
		return date_Echelle;
	}
	public void setDateEchelle(LocalDate date_Echelle) {
		this.date_Echelle = date_Echelle;
	}

	
	public String getEchellon() {
		return echellon;
	}
	public void setEchellon(String echellon) {
		this.echellon = echellon;
	}

	
	public LocalDate getDateEchellon() {
		return date_Echellon;
	}
	public void setDateEchellon(LocalDate date_Echellon) {
		this.date_Echellon = date_Echellon;
	}
	public String getPoste_occupe() {
		return poste_occupe;
	}
	public void setPoste_occupe(String poste_occupe) {
		this.poste_occupe = poste_occupe;
	}
	public int getCin() {
		return cin;
	}
	public void setCin(int cin) {
		this.cin = cin;
	}
	public int getMatricule_CNSS() {
		return matricule_CNSS;
	}
	public void setMatricule_CNSS(int matricule_CNSS) {
		this.matricule_CNSS = matricule_CNSS;
	}
	public int getMatricule_CNRPS() {
		return matricule_CNRPS;
	}
	public void setMatricule_CNRPS(int matricule_CNRPS) {
		this.matricule_CNRPS = matricule_CNRPS;
	}

	
	public double getSoldeExceptionnel() {
		return soldeExceptionnel;
	}
	public void setSoldeExceptionnel(double soldeExceptionnel) {
		this.soldeExceptionnel = soldeExceptionnel;
	}
	public double getSoldeinitial() {
		return soldeinitial;
	}
	public void setSoldeinitial(double soldeinitial) {
		this.soldeinitial = soldeinitial;
	}
	public double getSolde_repos() {
		return soldeRepos;
	}
	public void setSolde_repos(double soldeRepos) {
		this.soldeRepos = soldeRepos;
	}
	public Site getSite() {
		return site;
	}
	public void setSite(Site site) {
		this.site = site;
	}
	public Fonction getFoncion() {
		return fonction;
	}
	public void setFonction(Fonction fonction) {
		this.fonction = fonction;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}



	public List<Mission> getMission() {
		return Missions;
	}
	public void setMission(List<Mission> Mission) {
		this.Missions = Mission;
	}

	
	public List<OrdreAffectationPers> getOrdreAffPers() {
		return ordreAffPers;
	}
	public void setOrdreAffPers(List<OrdreAffectationPers> ordreAffPers) {
		this.ordreAffPers = ordreAffPers;
	}
	@JsonIgnore
	public List<AffectationTotale> getAffectationt() {
		return affectationt;
	}
	public void setAffectationt(List<AffectationTotale> affectationt) {
		this.affectationt = affectationt;
	}
	@JsonIgnore
	public List<AffectationPartielle> getAffectationp() {
		return affectationp;
	}
	public void setAffectationp(List<AffectationPartielle> affectationp) {
		this.affectationp = affectationp;
	}

	
	public LocalDate getDateNaissance() {
		return date_naissance;
	}
	public void setDateNaissance(LocalDate date_naissance) {
		this.date_naissance = date_naissance;
	}
	
	
	public LocalDate getDate_recrutement() {
		return date_recrutement;
	}
	public void setDate_recrutement(LocalDate date_recrutement) {
		this.date_recrutement = date_recrutement;
	}
	
	
	
	
	public LocalDate getDate_promotion() {
		return date_Promotion;
	}
	public void setDate_promotion(LocalDate date_Promotion) {
		this.date_Promotion = date_Promotion;
	}

	public Structure getStructure() {
		return structure;
	}

	public void setStructure(Structure structure) {
		this.structure = structure;
	}


	@JsonIgnore
	public Compte getUser() {
		return compte;
	}
	public void setUser(Compte compte) {
		this.compte = compte;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}	
}