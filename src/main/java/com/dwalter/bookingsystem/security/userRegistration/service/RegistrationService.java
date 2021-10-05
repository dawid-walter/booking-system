package com.dwalter.bookingsystem.security.userRegistration.service;

import com.dwalter.bookingsystem.service.message.EmailSender;
import com.dwalter.bookingsystem.service.message.EmailValidator;
import com.dwalter.bookingsystem.service.message.domain.Mail;
import com.dwalter.bookingsystem.security.user.model.User;
import com.dwalter.bookingsystem.security.user.model.UserRole;
import com.dwalter.bookingsystem.security.user.service.UserDbService;
import com.dwalter.bookingsystem.security.userRegistration.model.LoginCredentials;
import com.dwalter.bookingsystem.security.userRegistration.token.model.AuthenticationToken;
import com.dwalter.bookingsystem.security.userRegistration.token.service.AuthenticationTokenService;
import com.dwalter.bookingsystem.util.Constants;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class RegistrationService {
    private static final String EMAIL_NOT_VALID = "Email not valid";
    private static final String EMAIL_TAKEN = "Email already exist";
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationTokenService authenticationTokenService;
    private final UserDbService userDbService;
    private final EmailValidator emailValidator;
    private final EmailSender emailSender;

    public AuthenticationToken register(LoginCredentials request) {
        /*boolean isValidEmail = emailValidator.test(request.getEmail());

        if (!isValidEmail) {
            throw new IllegalStateException(EMAIL_NOT_VALID);
        }*/

        AuthenticationToken registerToken = registerUser(User.builder()
                .username(request.getUsername())
                .password(bCryptPasswordEncoder.encode(request.getPassword()))
                .userRole(UserRole.USER)
                .build());

        emailSender.send(emailGenerator(request, registerToken.getToken()));

        return registerToken;
    }

    public AuthenticationToken registerUser(User user) {
        boolean userExists = userDbService
                .findByUsername(user.getUsername())
                .isPresent();

        if (userExists) {
            throw new IllegalStateException(EMAIL_TAKEN);
        }

        userDbService.save(user);

        AuthenticationToken token = AuthenticationToken.builder()
                .token(UUID.randomUUID().toString())
                .created(LocalDateTime.now())
                .expiring(LocalDateTime.now().plusMinutes(Constants.REGISTRATION_EMAIL_EXPIRATION_TIME))
                .user(user)
                .build();

        authenticationTokenService.saveToken(token);
        log.info("Token created " + token.getToken());
        return token;
    }

    private Mail emailGenerator(LoginCredentials request, String token) {
        String link = "http://localhost:8080/api/v1/registration?token=" + token;
        return Mail.builder()
                .mailTo("cos@cos.cos")
                .subject("Message from booking system")
                .message(emailMessage(request.getUsername(), link))
                .build();
    }

    private String emailMessage(String name, String link) {
        return "<div style=\"font-family:Helvetica,Arial,sans-serif;font-size:16px;margin:0;color:#0b0c0c\">\n" +
                "\n" +
                "<span style=\"display:none;font-size:1px;color:#fff;max-height:0\"></span>\n" +
                "\n" +
                "  <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;min-width:100%;width:100%!important\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"100%\" height=\"53\" bgcolor=\"#0b0c0c\">\n" +
                "        \n" +
                "        <table role=\"presentation\" width=\"100%\" style=\"border-collapse:collapse;max-width:580px\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" align=\"center\">\n" +
                "          <tbody><tr>\n" +
                "            <td width=\"70\" bgcolor=\"#0b0c0c\" valign=\"middle\">\n" +
                "                <table role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td style=\"padding-left:10px\">\n" +
                "                  \n" +
                "                    </td>\n" +
                "                    <td style=\"font-size:28px;line-height:1.315789474;Margin-top:4px;padding-left:10px\">\n" +
                "                      <span style=\"font-family:Helvetica,Arial,sans-serif;font-weight:700;color:#ffffff;text-decoration:none;vertical-align:top;display:inline-block\">Confirm your email</span>\n" +
                "                    </td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "              </a>\n" +
                "            </td>\n" +
                "          </tr>\n" +
                "        </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td width=\"10\" height=\"10\" valign=\"middle\"></td>\n" +
                "      <td>\n" +
                "        \n" +
                "                <table role=\"presentation\" width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse\">\n" +
                "                  <tbody><tr>\n" +
                "                    <td bgcolor=\"#1D70B8\" width=\"100%\" height=\"10\"></td>\n" +
                "                  </tr>\n" +
                "                </tbody></table>\n" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\" height=\"10\"></td>\n" +
                "    </tr>\n" +
                "  </tbody></table>\n" +
                "\n" +
                "\n" +
                "\n" +
                "  <table role=\"presentation\" class=\"m_-6186904992287805515content\" align=\"center\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-collapse:collapse;max-width:580px;width:100%!important\" width=\"100%\">\n" +
                "    <tbody><tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "      <td style=\"font-family:Helvetica,Arial,sans-serif;font-size:19px;line-height:1.315789474;max-width:560px\">\n" +
                "        \n" +
                "            <p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\">Hi " + name + ",</p><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> Thank you for registering. Please click on the below link to activate your account: </p><blockquote style=\"Margin:0 0 20px 0;border-left:10px solid #b1b4b6;padding:15px 0 0.1px 15px;font-size:19px;line-height:25px\"><p style=\"Margin:0 0 20px 0;font-size:19px;line-height:25px;color:#0b0c0c\"> <a href=\"" + link + "\">Activate Now</a> </p></blockquote>\n Link will expire in 15 minutes. <p>See you soon</p>" +
                "        \n" +
                "      </td>\n" +
                "      <td width=\"10\" valign=\"middle\"><br></td>\n" +
                "    </tr>\n" +
                "    <tr>\n" +
                "      <td height=\"30\"><br></td>\n" +
                "    </tr>\n" +
                "  </tbody></table><div class=\"yj6qo\"></div><div class=\"adL\">\n" +
                "\n" +
                "</div></div>";
    }
}
