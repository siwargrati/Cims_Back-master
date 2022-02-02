package Cims.PFE.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import Cims.PFE.Dao.CompteRepository;
import Cims.PFE.Entities.Compte;

@Service
public class CompteDetailsServiceImpl implements UserDetailsService {
	@Autowired
	CompteRepository compteRepository;

	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Compte compte = compteRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("Compte Not Found with username: " + username));

		return CompteDetailsImpl.build(compte);
	}

}
