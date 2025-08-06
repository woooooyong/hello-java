import java.util.*; // Scanner 사용을 위해 java.util 패키지 전체를 import

public class Main_8_5 {

    public static void main(String[] args) {

        // 사용자로부터 콘솔 입력을 받기 위한 Scanner 객체 생성
        Scanner sc = new Scanner(System.in);
        
        UserService_8_5 service = new UserServiceImpl_8_5();

        while (true) {

            System.out.println("1. 회원가입 | 2. 전체조회 | 3. 삭제 | 0. 종료");
            System.out.print("선택: ");
            int menu = Integer.parseInt(sc.nextLine());
            // 사용자에게 메뉴 선택을 받고 문자열을 정수로 변환
            // nextLine으로 받아야 숫자 입력 후 줄바꿈 버그를 피할 수 있음

            if (menu == 1) {
                System.out.print("ID: ");
                String id = sc.nextLine();

                System.out.print("이름: ");
                String name = sc.nextLine();

                System.out.print("나이: ");
                int age = Integer.parseInt(sc.nextLine());
                // 사용자로부터 입력을 받음

                try {
                    service.register(new User_8_5 (id, name, age));
                    // 입력받은 정보로 User 객체 생성 → register 호출
                    // 중복 ID나 음수 나이 입력 시 예외 발생함
                } catch (Exception e) {
                    System.out.println("[에러] " + e.getMessage());
                    // 예외가 발생했을 경우 에러 메시지 출력
                }
            }

             else if (menu == 2) {
                // 2번: 전체 회원 조회
                List<User_8_5> users = service.findAll();
                for (User_8_5 u : users) {
                    System.out.println("Id : " + u.getId() + ", Name : " + u.getName() + ", age : " + u.getAge());
                }
            }

            else if (menu == 3) {
                // 3번: 회원 삭제
                System.out.print("삭제할 ID: ");
                String id = sc.nextLine();
                service.delete(id);
                System.out.println(id + " 삭제 완료");
            }

            else if (menu == 0) {
                // 0번: 프로그램 종료
                System.out.println("프로그램 종료");
                break;
                // while 루프를 종료시켜 프로그램이 끝남
            }
        }
    }
}