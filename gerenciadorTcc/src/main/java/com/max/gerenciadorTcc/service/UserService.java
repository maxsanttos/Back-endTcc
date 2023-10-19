package com.max.gerenciadorTcc.service;

import com.max.gerenciadorTcc.domain.repository.UserRepository;
import com.max.gerenciadorTcc.domain.users.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserService {

   private final UserRepository userRepository;
   private final EmailService emailService;

   public UserService(UserRepository userRepository, EmailService emailService){
       this.userRepository = userRepository;
       this.emailService = emailService;
   }

   public User getLoggedInUser(){
       Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
       if (principal instanceof UserDetails){
           String login = ((UserDetails) principal).getUsername();
           // Consultar o repositorio de usuarios para obter o objeto user com base no login
           return  userRepository.findUserByLogin(login);

       }
       return null;
   }

    public void sendNotificationEmail(String subject, String message) {
        User loggedInUser = getLoggedInUser();
        if (loggedInUser != null) {
            String recipient = loggedInUser.getLogin(); // Obtém o e-mail do usuário
            emailService.sendEmail(recipient, subject, message);
        }
    }

}
