package first.businessApp.service;

import first.businessApp.domain.Member;
import first.businessApp.repository.MemberRepository;
import first.businessApp.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /**
     * Sign Up
     */
    public Long join(Member member)
    {
        long start = System.currentTimeMillis();

        try
        {
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        }
        finally
        {
            long finish = System.currentTimeMillis();
            System.out.println("took " + (finish - start) + " ms");
        }

    }

    private void validateDuplicateMember(Member member)
    {

        long start = System.currentTimeMillis();
        try
        {
            memberRepository.findByName(member.getName())
                    .ifPresent(m -> {
                        throw new IllegalStateException("This member already exists.");
                    });
        }
        finally
        {
            long finish = System.currentTimeMillis();
            System.out.println("took " + (finish - start) + " ms");
        }

    }

    /**
     * See all registered names
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}

