import java.util.*; // 자바 유틸 패키지의 리스트 컬렉션을 사용하기 위해 import

// 인터페이스는 뼈대(무슨 기능을 할건지만)만을 정의 ( 기능명 + 파라미터 + 리턴값 )
public interface UserService_8_5 {

    // 회원 등록(회원가입)
    // 유저 객체를 파라미터로 받아서 등록 처리한다
    void register(User_8_5 user);

    // 모든 회원을 조회하는 기능
    // 등록된 모든 유저 객체들을 리스트에 담아 반환한다
    List<User_8_5> findAll();

    // 회원 삭제 기능
    //삭제할 대상을 파라미터로 받아 처리
    void delete(String id);
}