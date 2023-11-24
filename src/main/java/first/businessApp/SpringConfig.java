package first.businessApp;

//import first.businessApp.repository.JdbcTemplateMemberRepository;
import first.businessApp.domain.Member;
import first.businessApp.repository.JpaMemberRepository;
import first.businessApp.repository.MemberRepository;
//import first.businessApp.repository.MemoryMemberRepository;
import first.businessApp.service.MemberService;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

    private final MemberRepository memberRepository;

    public SpringConfig(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Bean
    public MemberService memberService() {

        return new MemberService(memberRepository);
    }

    //public MemberRepository memberRepository()

        // return new MemoryMemberRepository(); <spring makes this irrelevant
        // return new JdbcTemplateMemberRepository(dataSource);
        //return new JpaMemberRepository(em);

}
