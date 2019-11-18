package com.course.phonedirectory.config;


import com.course.phonedirectory.model.PhoneNumber;
import com.course.phonedirectory.model.User;
import com.course.phonedirectory.model.UserAccount;
import com.course.phonedirectory.rep.PhoneNumberRepository;
import com.course.phonedirectory.rep.UserAccountRepository;
import com.course.phonedirectory.rep.UserRepository;
//import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
//@Slf4j
public class DataForDB {
//    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository userrepository, PhoneNumberRepository phoneNumberRepository, UserAccountRepository userAccountRepository) {
        return args -> {

//            UserAccount alexAccount = userAccountRepository.save(new UserAccount(20));
//            UserAccount kateAccount = userAccountRepository.save(new UserAccount(100));
            PhoneNumber epam = phoneNumberRepository.save(new PhoneNumber("928402194214", "EPAM"));
            PhoneNumber number = phoneNumberRepository.save(new PhoneNumber("9348509345", "IT-School"));
            List<UserAccount> alexAccounts = Arrays.asList(
                    userAccountRepository.save(new UserAccount(200, epam)),
                    userAccountRepository.save(new UserAccount(10, number))
            );

            PhoneNumber robcode = phoneNumberRepository.save(new PhoneNumber("099808124", "ROBCODE"));
            PhoneNumber rms = phoneNumberRepository.save(new PhoneNumber("0239-324", "RMS"));
            PhoneNumber epam1 = phoneNumberRepository.save(new PhoneNumber("45092385093", "EPAM"));
            List<UserAccount> kateAccounts = Arrays.asList(
                    userAccountRepository.save(new UserAccount(400, robcode)),
                    userAccountRepository.save(new UserAccount(200, rms)),
                    userAccountRepository.save(new UserAccount(300, epam1))
            );

            //1234
            userrepository.save(new User("Alex", "$2a$10$PtSqRRkszANlUMpZ3NvayeNrxzsydhfWw5qmkKU2IXEvW4JXhVGlu",
                    "ROLE_REGISTERED_USER", alexAccounts));

            //pass = 123456
            userrepository.save(new User("Kate", "$2a$10$S6yhh0iXcRAMemqxOUNhgufX9l8Qql1lv7rGMgSMqHi4VHUBZ0V7y",
                    "ROLE_REGISTERED_USER,ROLE_BOOKING_MANAGER", kateAccounts));
        };


    }
}