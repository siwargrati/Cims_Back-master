package Cims.PFE.Entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "Files")
public class Files {
	@Id  
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="id_file")
	private Long id_file;
	
	private String fileName;

    private String fileType;

    @Lob
    private byte[] data;

  /*  @OneToOne
    private Mission mission;*/
	
	public Files() {
		super();
	}



	public Files( String fileName, String fileType, byte[] data//,Mission m
			) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.data = data;
		//this.mission=m;
	}



	public Long getId_file() {
		return id_file;
	}



	public void setId_file(Long id_file) {
		this.id_file = id_file;
	}



	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public String getFileType() {
		return fileType;
	}



	public void setFileType(String fileType) {
		this.fileType = fileType;
	}



	public byte[] getData() {
		return data;
	}



	public void setData(byte[] data) {
		this.data = data;
	}


/*
	public Mission getMission() {
		return mission;
	}



	public void setMission(Mission mission) {
		this.mission = mission;
	}


*/
	
}
