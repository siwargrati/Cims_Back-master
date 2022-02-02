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
@Table(name = "Files2")
public class Files2 {
	@Id  
    @GeneratedValue(strategy=GenerationType.IDENTITY) 
	@Column(name="id_file2")
	private Long id_file2;
	
	private String fileName2;

    private String fileType2;

    @Lob
    private byte[] data2;

  /*  @OneToOne
    private Mission mission;*/
	
	public Files2() {
		super();
	}



	public Files2( String fileName2, String fileType2, byte[] data2//,Mission m
			) {
		super();
		this.fileName2 = fileName2;
		this.fileType2 = fileType2;
		this.data2 = data2;
		//this.mission=m;
	}



	public Long getId_file2() {
		return id_file2;
	}



	public void setId_file2(Long id_file2) {
		this.id_file2 = id_file2;
	}



	public String getFileName2() {
		return fileName2;
	}



	public void setFileName2(String fileName2) {
		this.fileName2 = fileName2;
	}



	public String getFileType2() {
		return fileType2;
	}



	public void setFileType2(String fileType2) {
		this.fileType2 = fileType2;
	}



	public byte[] getData2() {
		return data2;
	}



	public void setData2(byte[] data2) {
		this.data2 = data2;
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
