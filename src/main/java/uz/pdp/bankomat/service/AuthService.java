package uz.pdp.bankomat.service;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import uz.pdp.bankomat.entity.enums.RoleName;
import uz.pdp.bankomat.payload.LoginDto;
import uz.pdp.bankomat.payload.RegistorDto;
import uz.pdp.bankomat.payload.Result;
import uz.pdp.bankomat.repository.RoleRepo;
import uz.pdp.bankomat.repository.UserRepo;
import uz.pdp.bankomat.security.JwtProvider;


import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

public class AuthService implements UserDetailsService {

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final RoleRepo roleRepo;

    private final JavaMailSender javaMailSender;

    private final AuthenticationManager authenticationManager;

    private final JwtProvider jwtProvider;

    public AuthService(UserRepo userRepo, PasswordEncoder passwordEncoder, RoleRepo roleRepo,
                       JavaMailSender javaMailSender, AuthenticationManager authenticationManager,
                       JwtProvider jwtProvider) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
        this.javaMailSender = javaMailSender;
        this.authenticationManager = authenticationManager;
        this.jwtProvider = jwtProvider;

    }

    public Result registerUser(RegistorDto registrDto) {

        boolean byEmail = userRepo.existsByEmail(registrDto.getEmail());
        if (byEmail) {
            return new Result("Bunday user allaqachon mavjud", false);
        }
        uz.pdp.bankomat.entity.User user = new uz.pdp.bankomat.entity.User();
        user.setFirstName(registrDto.getFirstName());
        user.setLastName(registrDto.getLastName());
        user.setEmail(registrDto.getEmail());
        user.setPassword(passwordEncoder.encode(registrDto.getPassword()));

        user.setRoles(Collections.singleton(roleRepo.findByName(RoleName.ROLE_USER)));

        user.setEmailCode(UUID.randomUUID().toString());
        userRepo.save(user);
        sendEmail(user.getEmail(), user.getEmailCode());


        return new Result("Successfully", true);
    }


    public Boolean sendEmail(String sendEmail, String emailCode) {
        try {
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            simpleMailMessage.setFrom("test@gmail.com");
            simpleMailMessage.setTo(sendEmail);
            simpleMailMessage.setSubject("Tasdiqlash");
            simpleMailMessage.setText("<a href='http://localhost:8080/api/auth/veryEmail?emailCode=" +
                    emailCode + "+&email=" + sendEmail + "'>Tasdiqlash</a>");
            javaMailSender.send(simpleMailMessage);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

    public Result verifyEmail(String emailCode, String email) {
        Optional<uz.pdp.bankomat.entity.User> optionalUser = userRepo.findByEmailAndEmailCode(emailCode, email);
        if (optionalUser.isPresent()) {
            uz.pdp.bankomat.entity.User user = optionalUser.get();
            user.s(true);
            user.setEmailCode(null);
            userRepo.save(user);
            return new Result("Account tasdiqlandi", true);
        }
        return new Result("Account tasdiqlanmadi", false);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<uz.pdp.bankomat.entity.User> optionalUser = userRepo.findByEmail(username);
        if (optionalUser.isPresent())
            return optionalUser.get();
        throw new UsernameNotFoundException(username + "Topilmadi");

    }

    public Result login(LoginDto loginDto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    loginDto.getUsername(),
                    loginDto.getPassword()));
            User user = (User) authentication.getPrincipal();
            String token = jwtProvider.generateToken(loginDto.getUsername(), user.getRole);

            return new Result("Token", true, token);
        } catch (BadCredentialsException badCredentialsException) {
            return new Result("Parol yoki login xato", false);
        }
    }
}
