## 도메인 설계에서 아래 세개를 그린다
- 회원 도메인 협력 관계
- 회원 클래스 다이어그램
- 회원 객체 다이어그램

### 회원 도메인 협력 관계
```
클라이언트 --- 회원 서비스 --- 회원 저장소
                    - 메모리 회원 저장소 (개발용으로 임시 저장소)
                    - DB 회원 저장소 (어떤 DB를 쓸지 확정이 되면 교체하도록)
                    - 외부 시스템 연동 회원 저장소 (외부 시스템과 연동될 여지가 있다 가정하고)
```

### 회원 클래스 다이어그램
```
  <인터페이스>                     <인터페이스>
MemberService ---------- MemberServiceRepository
      |                             |
MemberServiceImpl        - MemMoryMemberRepository
  (구현 클래스)             - DBMemberRepository

```
### 회원 객체 다이어그램
```
클라이언트 --- 회원 서비스 --- 메모리 회원 저장소
```
- 개발 단계에서는 메모리 회원 저장소를 먼저 사용할끄니까
- 회원 서비스 : MemberServiceImpl
***
***
<br/>
<br/>

## 좋은 객체 지향 설계의 5가지 원칙
- SRP 단일 책임 원칙
- OCP 개방 - 폐쇄 원칙
- LSP 리스코프 치환 원칙
- ISP 인터페이스 분리 원칙
- DIP 의존관계 역전 원칙

### SRP 단일 책임 원칙
- 정의 : 하나의 클래스는 하나의 책임만 가져야한다
- 변경이 있을 때 수정 사항이 적으면 SRP 원칙을 잘 지킨것


### OCP 개방 - 폐쇄 원칙 (젤 중요)
- 정의 : 소프트웨어 요소는 확장에는 열려있어야 하나 확장에는 닫혀 있어야한다
- 다형성을 뜻하는 말
- 역할과 구현을 분리하여 개발하면 OCP 를 지킬 수 있다

### LSP 리스코프 치환 원칙원칙
- 정의 : 기능적으로 그 규약을 맞춰줘야한다
- 엑셀 기능을 구현하는데 뒤로가는 엑셀을 구현했다면 LSP를 위반한 것이 되는것

### ISP 인터페이스 분리 원칙
- 정의 : 특정 클라이언트를 위한 인터페이스가 여러 개가 범용인터페이스 하나보다 낫다
- 인터페이스가 명확해지고 대체 가능성이 높아짐

### DIP 의존관계 역전 원칙 (젤 중요)
- 정의 : 추상화에 의존해야지, 구체화에 의존하면 안된다
- 클라이언트는 구현 클래스에 의존하지 말고 인터페이스에 의존해야한다
- 역할에 의존해야한다 구현에 의존하면 안된다!
- MemberService(클라이언트)는 MemberRepository 인터페이스를 보고있지만 동시에 구현체(MemoryMemberRepository)를 의존하고 있다 => DIP 위반! 
```java
public class MemberService {
    private MemberRepository memberRepository = new MemoryMemberRepository();
}
public class MemberService {
    // private MemberRepository memberRepository = new MemoryMemberRepository();
    private MemberRepository memberRepository = new JdbcMemberRepository();
}

```
