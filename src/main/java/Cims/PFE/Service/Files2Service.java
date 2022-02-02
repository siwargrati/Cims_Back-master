package Cims.PFE.Service;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import Cims.PFE.Dao.Files2Repository;
import Cims.PFE.Dao.FilesRepository;
import Cims.PFE.Entities.Files;
import Cims.PFE.Entities.Files2;
import Cims.PFE.Exception.FileException;
import Cims.PFE.Exception.FileNotFoundException;

@Service
public class Files2Service {

	 @Autowired
	private Files2Repository files2Repository;
	
	 public Files2 save(Files2 f) {
		 return files2Repository.save(f);
	 }
	 public Files2 storeFile(MultipartFile file2) {
	        // Normalize file name
	        String fileName2 = StringUtils.cleanPath(file2.getOriginalFilename());

	        try {
	            // Check if the file's name contains invalid characters
	            if(fileName2.contains("..")) {
	                throw new FileException("Désolé! Le nom de fichier contient une séquence de chemin non valide " + fileName2);
	            }

	            Files2 File2 = new Files2(fileName2, file2.getContentType(), file2.getBytes());

	            return files2Repository.save(File2);
	        } catch (IOException ex) {
	            throw new FileException("Impossible de stocker le fichier " + fileName2 + ". Please try again!", ex);
	        }
	    }
	 @Transactional
	 public Files2 getFile2(Long fileId) {
	        return files2Repository.findById(fileId)
	                .orElseThrow(() -> new FileNotFoundException("Fichier introuvable avec id " + fileId));
	    }
}
