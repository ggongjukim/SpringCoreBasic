package hello.SpringCoreBasic.member;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* DB가 확정이 안되서 임시로 쓰는 개발용 */
public class MemoryMemberRepository implements  MemberRepository{

    private static Map<Long, Member> store = new HashMap<>(); // 동시성 이슈 때문에 ConcurrentHashMap 을 쓰는 것이 맞다

    @Override
    public void save(Member member) {
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }
}
