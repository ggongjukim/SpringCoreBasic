

## 회원 도메인 실행과 테스트
### 회원 가입 main 만들어서 실행하는 방법
- `MemberApp` 만들기 (`psvm` + `enter`)



<br/>
<br/>

## 회원 도메인 개발
### 회원 엔티티 만들기
- member 패키지 만들기
- 회원 속성 : id, name, Grade
- Grade 는 VIP, BASIC 밖에 없으니 열거형 `enum` 으로
- 생성자 만들기
    > 🌮 단축키 
    > - 생성자 만들기 - `command` + `N`. 
    > - kemap 에서 단축키 설정하기 - `Settings`
- 데이터를 가지고오고 뽑는 `Getter` , `Setter` 만들기

### 회원 저장소 - 인터페이스, 구현체
##### 인터페이스 `MemberRepository`
- 회원 등록(C)
- 회원 조회(R)
##### 구현체 `MemoryMemberRepository`
- 저장소니까 `Map` 이 있어야함 
- 실습에서는 `HashMap` 을 썼지만 
  - 동시성 이슈 때문에 `ConcurrentHashMap` 을 쓰는 것이 사실 좋음
> 🌮 단축키   
> 빨간줄 뜨고 안되면 `option` + `enter` 누르세요


### 회원 서비스 - 인터페이스, 구현체
#### 인터페이스 `MemberService`
- 회원 가입
- 회원 조회
#### 구현체 `MemberServiceImpl`
- 저장소와 연결
> 🌮 단축키  
> 회원 저장소 구현체랑 연결하고  `;` 콜론까지 붙이고 싶으면  
> `command` + `shift` + `enter` 누르세요


<br/>
<br/>

***

<br/>
<br/>

## 비즈니스 요구사항과 설계
- 회원
    - 회원을 가입하고 조회할 수 있다.
    - 회원은 일반과 VIP 두 가지 등급이 있다. 
        => Grade : VIP, BASIC
    - 회원 데이터는 자체 DB를 구축할 수 있고, 외부 시스템과 연동할 수 있다. (미확정)
- 주문과 할인 정책  
  - 회원은 상품을 주문할 수 있다.
  - 회원 등급에 따라 할인 정책을 적용할 수 있다.
  - 할인 정책은 모든 VIP는 1000원을 할인해주는 고정 금액 할인을 적용해달라. (나중에 변경 될 수 있다.) 
  - 할인 정책은 변경 가능성이 높다. 회사의 기본 할인 정책을 아직 정하지 못했고, 오픈 직전까지 고민을 미루 고 싶다. 최악의 경우 할인을 적용하지 않을 수 도 있다. (미확정)

<br/>
<br/>

***

<br/>
<br/>

## 도메인 설계에서 아래 세개를 그린다
- 1. 회원 도메인 협력 관계
- 2. 회원 클래스 다이어그램
- 3. 회원 객체 다이어그램

### 1. 회원 도메인 협력 관계
```
클라이언트 --- 회원 서비스 --- 회원 저장소
             회원가입      - 메모리 회원 저장소 (개발용으로 임시 저장소)
             회원조회      - DB 회원 저장소 (어떤 DB를 쓸지 확정이 되면 교체하도록)
                         - 외부 시스템 연동 회원 저장소 (외부 시스템과 연동될 여지가 있다 가정하고)
```

### 2. 회원 클래스 다이어그램
```
  <인터페이스>                     <인터페이스>
MemberService -------------- MemberRepository
      |                             |
MemberServiceImpl        - MemMoryMemberRepository
  (구현 클래스)             - DBMemberRepository

```
### 3. 회원 객체 다이어그램
```
클라이언트 --- 회원 서비스 --- 메모리 회원 저장소
```
- 개발 단계에서는 메모리 회원 저장소를 먼저 사용할끄니까
- 회원 서비스 : MemberServiceImpl

<br/>
<br/>

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
