package com.profiles.Spring_Profiles.controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
//import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.filter.OncePerRequestFilter;

//import com.example.pdfgenerator.service.PDFGeneratorService;
//import com.jwt.config.JwtAuthenticationFilter;
import com.profiles.Spring_Profiles.config.JwtAuthenticationFilter;
import com.lowagie.text.Document;
import com.lowagie.text.Font;
import com.lowagie.text.FontFactory;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import com.profiles.Spring_Profiles.helper.JwtUtil;
//import com.profiles.Spring_Profiles.model.SaveToken;
import com.profiles.Spring_Profiles.model.User;
import com.profiles.Spring_Profiles.model.UserDetailDTO;
import com.profiles.Spring_Profiles.model.UserDetails;
import com.profiles.Spring_Profiles.model.UserLoginToken;
import com.profiles.Spring_Profiles.repo.UserDetailsRepo;
import com.profiles.Spring_Profiles.repo.UserRepository;
import com.profiles.Spring_Profiles.repo.UserTokenRepo;
import com.profiles.Spring_Profiles.response.AddDetailResponse;
import com.profiles.Spring_Profiles.tinyURL.TinyUrlBody;
import com.profiles.Spring_Profiles.tinyURL.TinyUrlResponse;

@RestController
@CrossOrigin(origins = "*")
public class UserDetailsController {

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private UserDetailsRepo userDetailsRepo;

	@Autowired
	private UserTokenRepo userTokenRepo;

	@Autowired
	private RestTemplate restTemplate;

	public String tinyUrl(String urlLink) {
		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(List.of(MediaType.ALL));
		headers.set("Content-Type", MediaType.APPLICATION_JSON_VALUE);
		TinyUrlBody tinyUrlBody = new TinyUrlBody();
		tinyUrlBody.setUrl(urlLink);
		HttpEntity<TinyUrlBody> entity = new HttpEntity<TinyUrlBody>(tinyUrlBody, headers);
		String tinyUlr = "https://api.tinyurl.com/create?api_token=xZNcKWT9B0Kapik0WkHZeKEbZN4PTSfqrQj8sCvP9oi3h6p3Iy9s6ymaA9Rc";
		TinyUrlResponse tinyUrlResponse = restTemplate.exchange(tinyUlr, HttpMethod.POST, entity, TinyUrlResponse.class)
				.getBody();
		return tinyUrlResponse.getData().getTiny_url();

	}

	@PostMapping("/savedetails")
	public AddDetailResponse saveUserDeatailsHandler(@RequestBody UserDetailDTO userDetailDTO,
			@RequestHeader String token) {

		System.out.println("TOKEN SIBTAIN ===> " + token.getClass().getSimpleName() + "====>" + token
				+ "=============================================");

		token = token.replaceAll("Bearer ", "");

		UserLoginToken userLoginToken = userTokenRepo.findByAuthToken(token);

		AddDetailResponse addDetailResponse = new AddDetailResponse();

		UserDetails userDetails = new UserDetails();
		System.out.println("==========>userId--" + userLoginToken.getUserId() + "<==========");
		Optional<UserDetails> opt = userDetailsRepo.findByUserId(userLoginToken.getUserId());
		System.out.println("==========>" + userLoginToken.getUserId() + "<==========");
		System.out.println("==========>" + opt + "<==========");

		String tiny = tinyUrl(userDetailDTO.getLinkedIn());

		if (opt.isPresent()) {

			UserDetails userDetails2 = opt.get();
			System.out.println("<<<<<<<<<<<<<<<<<<<<<<<" + userDetails2);

			userDetails2.setUserId(userLoginToken.getUserId());
			userDetails2.setName(userDetailDTO.getName());
			userDetails2.setEmail(userDetailDTO.getEmail());
			userDetails2.setPhone(userDetailDTO.getPhone());
			userDetails2.setLinkedIn(tiny);
			userDetails2.setCreatedAt(LocalDateTime.now());

			userDetailsRepo.save(userDetails2);

			addDetailResponse.setResponseCode(200);
			addDetailResponse.setResponseMessage("Successfully updated the data");

			return addDetailResponse;
		} else {

			userDetails.setUserId(userLoginToken.getUserId());
			userDetails.setName(userDetailDTO.getName());
			userDetails.setEmail(userDetailDTO.getEmail());
			userDetails.setPhone(userDetailDTO.getPhone());
			userDetails.setLinkedIn(userDetailDTO.getLinkedIn());
			userDetails.setCreatedAt(LocalDateTime.now());

			userDetailsRepo.save(userDetails);

			addDetailResponse.setResponseCode(200);
			addDetailResponse.setResponseMessage("Successfully added the data");
			return addDetailResponse;
		}

	}

	@GetMapping("/pdf/generate")
	public void generatePDF(HttpServletResponse response, @RequestHeader String token) throws IOException {

		token = token.replaceAll("Bearer ", "");

		String userName = jwtUtil.getUsernameFromToken(token);

		UserLoginToken userLoginToken = userTokenRepo.findByAuthToken(token);

		Optional<UserDetails> opt = userDetailsRepo.findByUserId(userLoginToken.getUserId());

		UserDetails userDetails = opt.get();

		response.setContentType("application/pdf");
		DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");
		String currentDateTime = dateFormatter.format(new Date());

		String headerKey = "Content-Disposition";
		String headerValue = "attachment; filename=" + userName + "_" + currentDateTime + ".pdf";
		response.setHeader(headerKey, headerValue);

		Document document = new Document(PageSize.A4);
		PdfWriter.getInstance(document, response.getOutputStream());

		document.open();
		Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		fontTitle.setSize(18);

		Paragraph paragraph = new Paragraph("Details", fontTitle);
		paragraph.setAlignment(Paragraph.ALIGN_CENTER);

		Font fontParagraph = FontFactory.getFont(FontFactory.HELVETICA);
		fontParagraph.setSize(12);

		Paragraph blank = new Paragraph("       ", fontParagraph);
		blank.setAlignment(Paragraph.ALIGN_LEFT);

		Paragraph id = new Paragraph("id : " + userLoginToken.getUserId(), fontParagraph);
		id.setAlignment(Paragraph.ALIGN_LEFT);

		Paragraph name = new Paragraph("name : " + userDetails.getName(), fontParagraph);
		id.setAlignment(Paragraph.ALIGN_LEFT);

		Paragraph email = new Paragraph("email : " + userDetails.getEmail(), fontParagraph);
		id.setAlignment(Paragraph.ALIGN_LEFT);

		Paragraph phone = new Paragraph("phone : " + userDetails.getPhone(), fontParagraph);
		id.setAlignment(Paragraph.ALIGN_LEFT);

		Paragraph linkedIn = new Paragraph("linkedIn : " + userDetails.getLinkedIn(), fontParagraph);
		id.setAlignment(Paragraph.ALIGN_LEFT);

		document.add(paragraph);
		document.add(blank);
		document.add(id);
		document.add(name);
		document.add(email);
		document.add(phone);
		document.add(linkedIn);
		document.close();
	}

}
