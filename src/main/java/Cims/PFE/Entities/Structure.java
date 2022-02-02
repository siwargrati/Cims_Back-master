package Cims.PFE.Entities;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.Proxy;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
@Entity  
@Table(name="structure") 
@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Proxy(lazy = false)
public class Structure {
	@Id  
	@SequenceGenerator(name="seqs", initialValue=2, allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seqs")
	@Column(name="id_struct",updatable=false)
	private Long id_struct;

	@Column(name="direction",updatable=false)
	private String direction;
	
	@Column(name="direction_a",updatable=false)
	private String direction_a;
	
	@Column(name="division",updatable=false)
	private String division;
	
	@Column(name="division_a",updatable=false)
	private String division_a; 
	
	@Column(name="service",updatable=false)
	private String service;
	
	@Column(name="service_a",updatable=false)
	private String service_a;
	
	@OneToMany(mappedBy="structure", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AffectationTotale> AffectationTotales ;
	
	@OneToMany(mappedBy="structure", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	    private List<Personnel> personnels ;
	public Structure(Long id_struct,String direction, String direction_a,String division,String division_a, String service,String service_a) {
		super();
		this.id_struct = id_struct;
		this.direction = direction;
		this.direction_a= direction_a;
		this.division= division;
		this.division_a= division_a;
		this.service=service;
		this.service_a=service_a;
		//this.personnels = personnels;
	}
	public Structure() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Long getId_struct() {
		return id_struct;
	}
	public void setId_struct(Long id_struct) {
		this.id_struct = id_struct;
	}
	
	public String getDirection_a() {
		return direction_a;
	}
	public void setDirection_a(String nom_dept_a) {
		this.direction_a = nom_dept_a;
	}
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getDivision() {
		return division;
	}
	public void setDivision(String division) {
		this.division = division;
	}
	public void setDivision_a(String division_a) {
		this.division_a = division_a;
	}
	public String getDivision_a() {
		return division_a;
	}

	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getService_a() {
		return service_a;
	}
	public void setService_a(String service_a) {
		this.service_a = service_a;
	}
	@Override
	public String toString() {
		return "Gouvernorat [id_struct=" + id_struct + ", direction=" + direction + ", division=" + division +  ", service=" + service + ", personnels="  + "]";
	}

	

}