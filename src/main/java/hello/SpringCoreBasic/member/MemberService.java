package hello.SpringCoreBasic.member;

public interface MemberService {
    void join(Member member);

    Member findMember(Long MemberId);
}
