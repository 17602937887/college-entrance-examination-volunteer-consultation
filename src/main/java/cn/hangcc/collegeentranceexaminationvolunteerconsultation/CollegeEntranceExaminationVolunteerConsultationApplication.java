package cn.hangcc.collegeentranceexaminationvolunteerconsultation;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("cn.hangcc.collegeentranceexaminationvolunteerconsultation.dao")
@SpringBootApplication(exclude = {
        org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration.class
})
public class CollegeEntranceExaminationVolunteerConsultationApplication {

    public static void main(String[] args) {
        SpringApplication.run(CollegeEntranceExaminationVolunteerConsultationApplication.class, args);
    }

}
