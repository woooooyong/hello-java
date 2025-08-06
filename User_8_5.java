//모두가 접근할 수 있게 유저 클래스를 선언, solid 원칙에 의거하여 클래스는 한 명의 사용자 정보를 담는 역할만 한다
public class User_8_5 {

    // 사용자의 정보(오직 이 클래스에서만 접근 가능 캡슐화)
    private String id;
    private String name; 
    private int age;

    //생성자 : 객체를 만들 때 필드를 초기화
    public User_8_5(String id, String name, int age){

        //방어 코드 : 나이가 음수면 예외를 던져서 객체 생성을 막는다
        if (age < 0 ) {

            //예외를 강제로 발생 예외 객체 생성 예외 메시지 
            throw new IllegalArgumentException("Invalid age: " + age + ". Must be >= 0.");
        }

        //생성자 파라미터를 필드에 저장 (매개변수와 필드를 구분)
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public String getId(){
        return id;
    }

    public String getName(){
        return name;
    }

    public int getAge(){
        return age;
    }
}
