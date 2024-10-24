package com.adbms.evaidhya.controller;

import com.adbms.evaidhya.enumerations.ROLE;
import com.adbms.evaidhya.repository.UserRepository;
import com.adbms.evaidhya.requestDTO.AuthRequest;
import com.adbms.evaidhya.requestDTO.signUpRequestDTO;
import com.adbms.evaidhya.responseDTO.userResponseDTO;
import com.adbms.evaidhya.service.PatientService;
import com.adbms.evaidhya.service.UserService;
import com.adbms.evaidhya.service.jwt.UserDetailsImpl;
import com.adbms.evaidhya.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.adbms.evaidhya.entity.User;

import java.io.IOException;


@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientService patientService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsImpl userDetailsService;

    public static final String TOKEN_BEARER = "Bearer ";

    public static final String TOKEN_HEADER="Authorization";

    @PostMapping("/patient/signup")
    public ResponseEntity<?> signUpPatient(@RequestBody signUpRequestDTO request){
        if(userService.emailExists(request.getEmail()))
            return new ResponseEntity<>("User already exists with the email!", HttpStatus.NOT_ACCEPTABLE);
        userResponseDTO userResponse = userService.signUpPatient(request);
        return new ResponseEntity<>(userResponse,HttpStatus.CREATED);
    }

    @PostMapping("/doctor/signup")
    public ResponseEntity<?> signUpDoctor(@RequestBody signUpRequestDTO request){
        if(userService.emailExists(request.getEmail()))
            return new ResponseEntity<>("Doctor already exists with the email!", HttpStatus.NOT_ACCEPTABLE);
        userResponseDTO userResponse = userService.signUpDoctor(request);
        return new ResponseEntity<>(userResponse,HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public void createAuthenticationToken(@RequestBody AuthRequest authRequest, HttpServletResponse response) throws Exception {
            try{
                authenticationManager.authenticate(new
                        UsernamePasswordAuthenticationToken(authRequest.getUsername(),authRequest.getPassword()));

            }
            catch(BadCredentialsException e){
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"error\":\"Incorrect username or password\"}");
                return;
        }
            final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
            final String jwt = jwtUtil.generateToken(userDetails.getUsername());
            User user = userRepository.findFirstByEmail(authRequest.getUsername());
            if (user == null) {
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"error\":\"User not found\"}");
                return;
            }
            if(user.getUserRole()== ROLE.PATIENT){
                Long patientId = patientService.getPatientId(user.getId());
                response.getWriter().write(new JSONObject()
                        .put("userId",user.getId())
                        .put("role",user.getUserRole())
                        .put("patientId",patientId)
                        .toString()
                );
            }

            if(user.getUserRole()== ROLE.DOCTOR) {
                response.getWriter().write(new JSONObject()
                        .put("userId", user.getId())
                        .put("role", user.getUserRole())
                        .toString()
                );
            }

            response.setContentType("application/json");
            response.addHeader("Access-Control-Expose-Headers", "Authorization");
            response.addHeader("Access-Control-Expose-Headers","Authorization,"+
                    "X-PINGOTHER,Origin, X-Requested-With, Content-Type, Accept, X-Custom-header");
            response.addHeader(TOKEN_HEADER, TOKEN_BEARER+jwt);

    }
}
