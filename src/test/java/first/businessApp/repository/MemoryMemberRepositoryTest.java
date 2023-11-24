package first.businessApp.repository;

import first.businessApp.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach() {
        repository.clearStore();
    }
    @Test
    void save() {
        //given
        Member member = new Member();
        member.setName("Hamin");

        //when
        repository.save(member);

        //then
        Member result = repository.findById(member.getId()).get();
        assertThat(result).isEqualTo(member);
    }

    @Test
    void findByName() {

        //given
        Member member1 = new Member();
        member1.setName("Lee");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Kim");
        repository.save(member2);

        //when
        Member result = repository.findByName("Lee").get();

        //then
        assertThat(result).isEqualTo(member1);

    }

    @Test
    void findAll() {

        //given
        Member member1 = new Member();
        member1.setName("Lee");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("Kim");
        repository.save(member2);

        //when
        List<Member> result = repository.findAll();

        //then
        assertThat(result.size()).isEqualTo(2);

    }


}