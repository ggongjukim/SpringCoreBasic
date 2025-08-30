package hello.SpringCoreBasic.member;

public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository = new MemoryMemberRepository(); // 구현체 없으면 null pointer exception 됨
    // MemberRepository추상화에도 의지하고 MemoryMemberRepository구체화에도 의지하고 있는 상태

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
