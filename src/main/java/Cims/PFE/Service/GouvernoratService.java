package Cims.PFE.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Cims.PFE.Dao.GouvernoratRepository;
import Cims.PFE.Entities.Gouvernorat;

@Service
public class GouvernoratService {
	@Autowired
	private GouvernoratRepository gouvernoratRepository;
	public List<Gouvernorat> listAll(){
		List<Gouvernorat> gouvernorats = new ArrayList<>();
		gouvernoratRepository.findAll().forEach(gouvernorats::add);
		return gouvernorats;
	}

}
