package com.adbms.evaidhya.controller;

import com.adbms.evaidhya.enumerations.ROLE;
import com.adbms.evaidhya.repository.UserRepository;
import com.adbms.evaidhya.requestDTO.AuthRequest;
import com.adbms.evaidhya.requestDTO.ChangePasswordRequestDTO;
import com.adbms.evaidhya.requestDTO.UpdatePasswordRequestDTO;
import com.adbms.evaidhya.requestDTO.signUpRequestDTO;
import com.adbms.evaidhya.responseDTO.MessageRes;
import com.adbms.evaidhya.responseDTO.userResponseDTO;
import com.adbms.evaidhya.service.DoctorService;
import com.adbms.evaidhya.service.PatientService;
import com.adbms.evaidhya.service.UserService;
import com.adbms.evaidhya.service.jwt.UserDetailsImpl;
import com.adbms.evaidhya.util.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import com.adbms.evaidhya.entity.User;


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
    private DoctorService doctorService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsImpl userDetailsService;

    public static final String TOKEN_BEARER = "Bearer ";

    public static final String TOKEN_HEADER="Authorization";

    @GetMapping("/")
    public ResponseEntity<?> homePage(){
        return new ResponseEntity<>("Welcome to Evaidya",HttpStatus.OK);
    }

    @PostMapping("/patient/signup")
    public ResponseEntity<?> signUpPatient(@RequestBody signUpRequestDTO request){
        if(userService.emailExists(request.getEmail()))
            return new ResponseEntity<>("User already exists with the email!", HttpStatus.NOT_ACCEPTABLE);
        userResponseDTO userResponse = userService.signUpPatient(request);
        return new ResponseEntity<>(userResponse,HttpStatus.CREATED);
    }

    @PostMapping("/api/user/changePassword")
    public ResponseEntity<?> changePassword(@RequestBody ChangePasswordRequestDTO request){
        MessageRes response = userService.changePassword(request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/user/updatePassword")
    public ResponseEntity<?> updatePassword(@RequestBody UpdatePasswordRequestDTO request){
        MessageRes response = userService.updatePassword(request);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @PostMapping("/doctor/signup")
    public ResponseEntity<?> signUpDoctor(@RequestBody signUpRequestDTO request){
        if(userService.emailExists(request.getEmail()))
            return new ResponseEntity<>("Doctor already exists with the email!", HttpStatus.NOT_ACCEPTABLE);
        userResponseDTO userResponse = userService.signUpDoctor(request);
        return new ResponseEntity<>(userResponse,HttpStatus.CREATED);
    }

    @PostMapping("/admin/signup")
    public ResponseEntity<?> signUpAdmin(@RequestBody signUpRequestDTO request){
        if(userService.emailExists(request.getEmail()))
            return new ResponseEntity<>("Admin already exists with the email!", HttpStatus.NOT_ACCEPTABLE);
        userResponseDTO userResponse = userService.signUpAdmin(request);
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
                Long doctorId = doctorService.getDoctorByUserId(user.getId()).getId();
                response.getWriter().write(new JSONObject()
                        .put("userId", user.getId())
                        .put("role", user.getUserRole())
                        .put("doctorId",doctorId)
                        .toString()
                );
            }

        if(user.getUserRole()== ROLE.ADMIN) {
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
