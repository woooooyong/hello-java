import java.io.*; // 입출력 관련 기능들을 불러온다
import java.net.*; // 네트워크 관련 기능들을 불러온다 HTTP 요청을 보내는 데 필요한 클래스들이 이 패키지에 포함되어있다

//자바 클래스 선언으로 퍼블릭을 제일 개방적인 클래스
public class HttpGetExample {

    //자바 프로그램의 진입점 (메인 함수) 실행하면 여기부터 실행 
    public static void main(String[] args) {
        
        //네트워크 통신은 예외(에러)가 자주 발생하기 때문에 try catch 로 감싸서 안전하게 처리
        try {
            
            //URL 객체 생성(문자열 주소를 자바가 이해할 수 있는 형태로 바꿈) post/1 은 게시글 1번째 데이터를 의미하고 htpps 는 보안 연결이라느 뜼
            URL url = new URL("https://jsonplaceholder.typicode.com/posts/1"); // URL 객체

            //URL 객체에서 서버와 연결을 연다? 이때 반환되는건 URLConnection 인데 우리는 HTTP 요청을 다루고 싶으니 형뱐환 해줌 다움케스팅
            HttpURLConnection conn = (HttpURLConnection) url.openConnection(); // 올바른 객체


            //요청 방식 설정, GET은 데이터를 조회한다는 의미 (가장 기본적인 HTTP 메서드)
            conn.setRequestMethod("GET");

            //헤더 추가: 서버에게 "나는 JSON 현식 응답 받고싶어" 라고 말한 것
            conn.setRequestProperty("Accepet", "application/json");


            //서버에서 응답한 HTTP 상태 코드를 받아옴, 이 숫자만으로 성공/실패를 구분 가능
            int responseCode = conn.getResponseCode();

            //응답 코드를 콘솔에 출력
            System.out.println("응답 코드 : " + responseCode);


            //서버에서 보낸 응답 내용을 한 줄씩 읽기 위한 스트림 구성, conn.getInputStream() → 서버가 준 데이터 바이트 형태로 받음, InputStreamReader(...) → 바이트를 문자로 바꿈, BufferedReader(...) → 문자들을 한 줄씩 쉽게 읽을 수 있게 함
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            
            //한줄을 읽을때 임시로 저장받을변서
            String line;
            
            //문자열을 계속 이어붙이기 위한 객체, 일반 문자열 + 보다 성능이 좋다
            StringBuilder response = new StringBuilder();


            //서버 응답을 한 줄씩 읽고 더 이상 줄이 없을때 까지
            while ((line = br.readLine()) != null) {

                //읽은 줄을 리스폰 이라는 문자열 덩어리에 추가 
                response.append(line);                
            }


            //사용한 리소스 정리, 메모리 누수 방지를 위한 좋은 습관
            br.close();
            System.out.println("응답 본문:");

            //전체 응답 내용을 콘솔에 출력
            System.out.println(response.toString());


        //위에서 발생한 어떤 예외도 여기서 잡는다
        } catch (Exception e) {
            // 에러가 났다면 콘솔에 에러 로그 출력해서 어디서 에러가 났는지 추적 가능
            e.printStackTrace();
        }
    }
}