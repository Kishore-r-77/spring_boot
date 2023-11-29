package com.futura.Purple.Auth.controllers;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.futura.Purple.Auth.exception.TokenRefreshException;
import com.futura.Purple.Auth.models.RefreshToken;
import com.futura.Purple.Auth.models.User;
import com.futura.Purple.Auth.payload.request.LogOutRequest;
import com.futura.Purple.Auth.payload.request.LoginRequest;
import com.futura.Purple.Auth.payload.request.SignupRequest;
import com.futura.Purple.Auth.payload.request.TokenRefreshRequest;
import com.futura.Purple.Auth.payload.response.JwtResponse;
import com.futura.Purple.Auth.payload.response.MessageResponse;
import com.futura.Purple.Auth.payload.response.TokenRefreshResponse;
import com.futura.Purple.Auth.repository.UserRepository;
import com.futura.Purple.Auth.security.jwt.JwtUtils;
import com.futura.Purple.Auth.security.services.RefreshTokenService;
import com.futura.Purple.Auth.security.services.UserDetailsImpl;
import com.futura.Purple.Email.EmailService;

import net.bytebuddy.utility.RandomString;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	EmailService email;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@Autowired
	RefreshTokenService refreshTokenService;

//	@Autowired
//	private UserGroupRepository userGroupRepo;
//	
//	@Autowired
//	private CompanyRepository companyrepo;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

		String jwt = jwtUtils.generateJwtToken(userDetails);

		RefreshToken refreshToken = refreshTokenService.createRefreshToken(userDetails.getId());

		return ResponseEntity
				.ok(new JwtResponse(jwt, refreshToken.getToken(), userDetails.getId(), userDetails.getUsername(),
						userDetails.getEmail(), userDetails.getUserGroup(), userDetails.getSpecialAccess()));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(), signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()), signUpRequest.getUserGroupId(),
				signUpRequest.getCompanyId(), signUpRequest.getProfile());
		user.setValidFlag(3);
		String random = RandomString.make();
		user.setVerificationCode(random);
		userRepository.save(user);
		sendVerificationMail(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	public void sendVerificationMail(User user) {
		String msg1 = "You are one step away from regstration.Please click this link to verify your account:";
		String msg2 = msg1 + "http://localhost:3000/verify";
		String msg3 = msg2 + " Your Verification Code is:" + user.getVerificationCode();
		email.sendSimpleEmail(user.getEmail(), msg3, "Kotak Test Prototype");
	}

	@PatchMapping("/user/resetPassword/{code}")
	public void user(@PathVariable String code, @RequestBody User user) {
		User euser = userRepository.findByVerificationCode(code);
		System.out.println(" **************** Pass before increpting  *************** " + user.getPassword());
		String pass = encoder.encode(user.getPassword());
		System.out.println(" **************** Pass After increpting  *************** " + pass);
		euser.setPassword(pass);
		userRepository.save(euser);
		System.out.println("########### password ############ " + euser.getPassword());
	}

	@PatchMapping("/user/reset/{mail}")
	public String sendResetPasswordMail(@PathVariable String mail) {
		System.out.println("########### user Email : ######## " + mail);
		User user = userRepository.findByMail(mail);
		System.out.println("########### user name : ######## " + user.getUsername());
		String random = RandomString.make();
		user.setVerificationCode(random);
		userRepository.save(user);
		String msg1 = "You attempted to change your password.";
		String msg2 = msg1 + "Use this reset code to verify your email: ";
		String msg3 = msg2 + user.getVerificationCode();
		email.sendSimpleEmail(mail, msg3, "Project Purple");

		return user.getVerificationCode();

	}

	@PostMapping("/refreshtoken")
	public ResponseEntity<?> refreshtoken(@Valid @RequestBody TokenRefreshRequest request) {
		String requestRefreshToken = request.getRefreshToken();

		return refreshTokenService.findByToken(requestRefreshToken).map(refreshTokenService::verifyExpiration)
				.map(RefreshToken::getUser).map(user -> {
					String token = jwtUtils.generateTokenFromUsername(user.getUsername());
					return ResponseEntity.ok(new TokenRefreshResponse(token, requestRefreshToken));
				})
				.orElseThrow(() -> new TokenRefreshException(requestRefreshToken, "Refresh token is not in database!"));
	}

	@PostMapping("/logout")
	public ResponseEntity<?> logoutUser(@Valid @RequestBody LogOutRequest logOutRequest) {

		if (refreshTokenService.findByToken(logOutRequest.getRefreshToken()).isPresent()) {
			refreshTokenService.deleteByUserId(logOutRequest.getUserId());
			return ResponseEntity.ok(new MessageResponse("Log out successful!"));
		} else {
			return ResponseEntity.ok(new MessageResponse("Cannot Log out ...No Login Record Found"));
		}

	}

	@GetMapping("/user/getall")
	public List<User> getall() {
		return userRepository.findAll();
	}

	@PatchMapping("/user/verify/{code}")
	public void verifyuser(@PathVariable String code) {
		User euser = userRepository.findByVerificationCode(code);
		euser.setValidFlag(1);
		userRepository.save(euser);
//		  JOptionPane.showMessageDialog(null, "Go to Login Page");
	}

	@GetMapping("/user/{id}")
	public Optional<User> getbyid(@PathVariable Long id) {
		return userRepository.findById(id);
	}

	@PatchMapping("/user/update/{id}")
	public void updateUser(@PathVariable Long id, @RequestBody User user) {
		User euser = userRepository.getById(id);
		if (user.getProfile() != null) {
			euser.setProfile(user.getProfile());
		}
		if (user.getUsername() != null) {
			euser.setUsername(user.getUsername());
		}
		if (user.getEmail() != null) {
			euser.setEmail(user.getEmail());
		}
		if (user.getPassword() != null) {
			euser.setPassword(user.getPassword());
		}

		userRepository.save(euser);
	}

//	  @GetMapping(value = "/generatePdf/{ansId}", produces = MediaType.APPLICATION_PDF_VALUE)
//	  public ResponseEntity<InputStreamResource> generatePdf(@PathVariable Long ansId) throws IOException {
//		  
//		  AnswerSheet answersheet = answerRepo.getById(ansId);
//		  
//		  User user = userRepository.getById(answersheet.getUserId());
//		  StudentClass student = studentrepo.getActiveById(user.getStudentId()); 
//		  QuizHeader header = quizrepo.getActiveQuizHeader(answersheet.getQuizId());
//		  Company company = companyrepo.getActiveById(header.getCompanyId());
//		  
////		  byte[] image = Base64.decodeBase64(company.getCompanyLogo());
//		  
////		  System.out.println("converted Image "+ image);
//		  
////		  
////		  InputStream inputStream = new ByteArrayInputStream(image); // byte[] byteArr
////		
////			BufferedImage bufferImage = ImageIO.read(inputStream);
//		  
//		  String s1= company.getCompanyLogo().split(",")[1];
//		  
//		  byte[] image = javax.xml.bind.DatatypeConverter.parseBase64Binary(s1);
//		  
////		  InputStream in = new ByteArrayInputStream(image);//Use b as the input stream;
////		  
////		  System.out.println("IN "+in.toString());
//
//		  BufferedImage image1 = ImageIO.read(new ByteArrayInputStream(image));
//		  
//		  System.out.println("buffered Image "+ image1);
//		
//		  
//		  String userName = user.getUsername();
//		  String companyName = company.getCompanyLongName();
//		  
//		  
//		  
//		  ByteArrayInputStream bis = PdfGeneration.studentPdfGeneration(student,header,answersheet,userName,companyName,image1);
//			HttpHeaders headers = new HttpHeaders();
//	        headers.add("Content-Disposition", "inline; filename=demo.pdf");
//	        return ResponseEntity.ok().headers(headers).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(bis));
//		  
//	  }

	@GetMapping("/getByUserGroup/{id}")
	public List<User> getByUserGroup(@PathVariable Long id) {
		return userRepository.getByUserGroupId(id);
	}

//  @PostMapping("TokenCheck")
//  public ResponseEntity<?> TokenCheck(@RequestBody String jwt){
//
//    if(jwtUtils.validateJwtToken(jwt)) return ResponseEntity.ok(new MessageResponse("Valid access token")) ;
//    return ResponseEntity.ok(new MessageResponse("Invalid access token"));
//
//  }

}
