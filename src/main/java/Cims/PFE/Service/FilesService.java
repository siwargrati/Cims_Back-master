package Cims.PFE.Service;

import java.io.IOException;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;


import Cims.PFE.Dao.FilesRepository;
import Cims.PFE.Entities.Files;
import Cims.PFE.Exception.FileException;
import Cims.PFE.Exception.FileNotFoundException;

@Service
public class FilesService {

	 @Autowired
	private FilesRepository filesRepository;
	
	 public Files save(Files f) {
		 return filesRepository.save(f);
	 }
	 public Files storeFile(MultipartFile file) {
	        // Normalize file name
	        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

	        try {
	            // Check if the file's name contains invalid characters
	            if(fileName.contains("..")) {
	                throw new FileException("Désolé! Le nom de fichier contient une séquence de chemin non valide " + fileName);
	            }

	            Files File = new Files(fileName, file.getContentType(), file.getBytes());

	            return filesRepository.save(File);
	        } catch (IOException ex) {
	            throw new FileException("Impossible de stocker le fichier " + fileName + ". Please try again!", ex);
	        }
	    }
	 @Transactional
	 public Files getFile(Long fileId) {
	        return filesRepository.findById(fileId)
	                .orElseThrow(() -> new FileNotFoundException("Fichier introuvable avec id " + fileId));
	    }
}
