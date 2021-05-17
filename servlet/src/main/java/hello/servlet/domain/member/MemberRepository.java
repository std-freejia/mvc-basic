package hello.servlet.domain.member;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * 동시성 문제가 고려되어 있지 않음. 실무에서는 ConcurrentHashMap, AtomicLong 사용 고려.
 */
public class MemberRepository {

    private Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    private static final MemberRepository instance = new MemberRepository();

    // 싱글톤
    public static MemberRepository getInstance(){
        return instance;
    }

    private MemberRepository(){
    }

    // 저장
    public Member save(Member member){
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    // 찾기
    public Member findById(Long id){
        return store.get(id);
    }

    // 전체조회
    public List<Member> findAll(){
        // store 에 있는 values()리스트를 건드리지 않고 new 로 생성하여 반환
        return new ArrayList<>(store.values());
    }

    public void clearStore(){ // 전부 삭제. (테스트 용도)
        store.clear();
    }
}
