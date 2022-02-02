package Cims.PFE.Service;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Cims.PFE.Dao.SiteRepository;
import Cims.PFE.Entities.Site;

@Service
public class SiteService {
	@Autowired
	private SiteRepository siteRepository;
	
	public List<Site> listAll(){
		List<Site> sites = new ArrayList<>();
		siteRepository.findAll().forEach(sites::add);
		return sites;
	}
	public Site save(Site s) {
		return siteRepository.save(s);
	}
	public Site update(Long id,Site s){
		s.setIdSite(id);
		return siteRepository.findById(id).get();
	}
	public Site getById(Long id) {
		return siteRepository.findById(id).get();
	}
	public boolean delete(Long id){
		siteRepository.deleteById(id);
		if(siteRepository.existsById(id)==false) {
			return true;
		}
		return false;
	}


}
