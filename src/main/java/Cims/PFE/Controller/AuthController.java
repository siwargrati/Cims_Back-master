package Cims.PFE.Controller;

import java.security.Principal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import Cims.PFE.Dao.RoleRepository;
import Cims.PFE.Dao.CompteRepository;
import Cims.PFE.Entities.ERole;
import Cims.PFE.Entities.Role;
import Cims.PFE.Entities.Compte;
import Cims.PFE.payload.request.LoginRequest;
import Cims.PFE.payload.request.SignupRequest;
import Cims.PFE.payload.response.JwtResponse;
import Cims.PFE.payload.response.MessageResponse;
import Cims.PFE.security.jwt.JwtUtils;
import Cims.PFE.security.services.CompteDetailsImpl;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	CompteRepository compteRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;
	
	
	@GetMapping("/profil")
	public CompteDetailsImpl showProfilePage( Principal principal) {
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String username = principal.getName();	
		CompteDetailsImpl user = (CompteDetailsImpl)auth.getPrincipal();
		return user;
	}
	
	@PutMapping("/updatePassword")
	public ResponseEntity<?> updatePassword(Principal principal, @RequestBody SignupRequest signUpRequest){
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();
		String username = principal.getName();	
		CompteDetailsImpl user = (CompteDetailsImpl)auth.getPrincipal();
		user.getUsername();
		Compte u=compteRepository.findByPassword(user.getPassword());
		u.setPassword( encoder.encode(signUpRequest.getPassword()));
		
		compteRepository.save(u);
		
		return ResponseEntity.ok(new MessageResponse("Mot de passe modifier!!"+u.getPassword()));
	}

	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest,SignupRequest signUpRequest) {
		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> role = new HashSet<>();
		if (compteRepository.existsByUsername("ResponsableRH")==false) {
		if((loginRequest.getUsername().equals("ResponsableRH") )&&(loginRequest.getPassword().equals("ResponsableRH"))) {			
			signUpRequest.setUsername(loginRequest.getUsername());
			signUpRequest.setPassword(loginRequest.getPassword());
			
			Compte compte = new Compte(signUpRequest.getUsername(), 
					 encoder.encode(signUpRequest.getPassword()),null);
			
			if (strRoles == null) {
				Role Role = roleRepository.findByName(ERole.ROLE_RH)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				role.add(Role);
				/*Role Rolepers = roleRepository.findByName(ERole.ROLE_PERSONNEL)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
				role.add(Rolepers);*/
			}
			compte.setRoles(role);
			compteRepository.save(compte);
			
			
		}
		}
		
		if (compteRepository.existsByUsername("ChefService")==false) {
			if((loginRequest.getUsername().equals("ChefService") )&&(loginRequest.getPassword().equals("ChefService"))) {
				signUpRequest.setUsername(loginRequest.getUsername());
				signUpRequest.setPassword(loginRequest.getPassword());
				Compte compte = new Compte(signUpRequest.getUsername(), 
						 encoder.encode(signUpRequest.getPassword()),null);
				
				if (strRoles == null) {
					Role Role = roleRepository.findByName(ERole.ROLE_CHEFSERVCE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					role.add(Role);
					/*Role Rolepers = roleRepository.findByName(ERole.ROLE_PERSONNEL)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					role.add(Rolepers);*/
				}
				compte.setRoles(role);
				compteRepository.save(compte);
				
			}
			}
		
		 if (compteRepository.existsByUsername("Directeur")==false) {
			if((loginRequest.getUsername().equals("Directeur") )&&(loginRequest.getPassword().equals("Directeur"))) {
				signUpRequest.setUsername(loginRequest.getUsername());
				signUpRequest.setPassword(loginRequest.getPassword());
				Compte compte = new Compte(signUpRequest.getUsername(),
						 encoder.encode(signUpRequest.getPassword()),null);
				
				if (strRoles == null) {
					Role Role = roleRepository.findByName(ERole.ROLE_DIRECTEUR)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					role.add(Role);
					/*Role Rolepers = roleRepository.findByName(ERole.ROLE_PERSONNEL)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					role.add(Rolepers);*/
				}
				compte.setRoles(role);
				compteRepository.save(compte);
				
			}
			}
		
		
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		
		CompteDetailsImpl userDetails = (CompteDetailsImpl) authentication.getPrincipal();		
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		
		
		
		
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(),
												 roles));
	}
	

	/*@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), 
							 encoder.encode(signUpRequest.getPassword()),null);

		String strRoles = signUpRequest.getUsername();
		Set<Role> roles = new HashSet<>();

			
				switch (strRoles) {
				case "chefService":
					Role chefRole = roleRepository.findByName(ERole.ROLE_CHEFSERVCE)
							.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(chefRole);

					break;
				case "directeur":
					Role directeurRole = roleRepository.findByName(ERole.ROLE_DIRECTEUR)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(directeurRole);

					break;
				case "ResponsableRH":
					Role RHRole = roleRepository.findByName(ERole.ROLE_RH)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(RHRole);

					break;
				default:
					Role userRole = roleRepository.findByName(ERole.ROLE_CORRESPONDANT)
						.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
					roles.add(userRole);
				}
			
		

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}*/
}
