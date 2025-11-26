package io.github.mahjoubech.smartshop.utils;

import io.github.mahjoubech.smartshop.model.entity.Admin;
import io.github.mahjoubech.smartshop.model.enums.Roles;
import io.github.mahjoubech.smartshop.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class DataAdminUtil  implements CommandLineRunner {
    private static final Logger logger = LoggerFactory.getLogger(DataAdminUtil.class);
    private final AdminRepository adminRepository ;

    @Override
    public void run(String... args) throws Exception {
        if(adminRepository.count()==0){
        String salt = BCrypt.gensalt();
        String hach = BCrypt.hashpw("admin123", salt);
            Admin admin = Admin.builder()
                    .userName("admin")
                    .password(hach)
                    .role(Roles.ADMIN)
                    .build();
            adminRepository.save(admin);
            logger.info("Admin user created with username: admin and password: admin123");
        }

    }
}
