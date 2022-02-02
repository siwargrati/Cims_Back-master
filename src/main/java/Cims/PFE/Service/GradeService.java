package Cims.PFE.Service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Cims.PFE.Dao.GradeRepository;
import Cims.PFE.Entities.Grade;

@Transactional
@Service
public class GradeService {

	@Autowired
	private GradeRepository gradeRepository ;
	
	public List<Grade> listAll(){
		List<Grade> grades = new ArrayList<>();
		gradeRepository.findAll().forEach(grades::add);
		return grades;
	}
	
	public Grade save(Grade g) {
		return gradeRepository.save(g);
	}
	
	public Grade update(Long id, Grade g){
		g.setId_grade(id);
		return gradeRepository.findById(id).get();
	}
	public Grade getById(Long id) {
		return gradeRepository.findById(id).get();
	}
	public boolean delete(Long id){
		gradeRepository.deleteById(id);
		if(gradeRepository.existsById(id)==false) {
			return true;
		}
		return false;
	}
}
