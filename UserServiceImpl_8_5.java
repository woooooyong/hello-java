import java.util.*; // 자바의 유틸의 패키지를 전부 임포트

// 유저 서비스를 상속해서 유저 기능구현 클래스 선언
public class UserServiceImpl_8_5 implements UserService_8_5{

    // 사용자의 정보를 저장할 리스트 생성
    // List는 인터페이스, ArrayList는 그 구현체 (다형성 적용)
    // 지금은 DB가 없으니 메모리 내에 유저들을 저장
    private List<User_8_5> users = new ArrayList<>();

    //사용자 등록 기능 (인터페이스에서 선언한 메서드를 오버라이드)
    @Override
    public void register(User_8_5 user){

        // for 문으로 유저 객체의 Id를 처음부터 끝까지 동일한게 있는지 확인 
        for (User_8_5 u : users) {

            //만약 있다면 런타임 에러 객체를 만들어서 메세지 출력 실행 중단
            if (u.getId().equals(user.getId())){
                throw new RuntimeException("This Id already exist");
            }
        }

        // 중복이 없으면 유저 객체에 추가 
            users.add(user);
        
    }

    @Override
    // 지금까지 등록된 모든 사용자 리스트를 반환, 메인 클레스에서 이걸 출력에 활용가능
    public List<User_8_5> findAll() {
        return users;
    }

    @Override
    // 람다식: 조건에 맞는 요소를 제거
    // removeIf는 조건을 만족하는 요소만 삭제해줌
    // 여기선 id가 일치하는 유저만 삭제
    public void delete(String id) {
        users.removeIf(u -> u.getId().equals(id));
    }
}