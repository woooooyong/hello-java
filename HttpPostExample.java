import java.io.*; //입출력 관련 클래스를 전부 가져옴
import java.net.*; //네트워크 통신 관련 클래스를 전부 가져옴

public class HttpPostExample {
    public static void main(String[] args) {
        try {

            //post 요청을 보낼 대상의 URL 생성
            URL url = new URL("https://jsonplaceholder.typicode.com/posts");
            
            //위에서 만든 유알엘 객체의 HTTP 연결을 열고 반환된 유알엘 케넥션을 HTTP 방식을 쓰기위해 다운캐스팅
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();


            //HTTP 요청 방식 설정 POST 데이터를 서버에 보내서 생성을 요청 
            conn.setRequestMethod("POST");

            //보내는 데이터가 JSON이라는 걸 명시 utf-8은 한글 꺠짐 방지
            conn.setRequestProperty("Content-Type", "application/json; utf-8");
            
            //서버로부터 응답을 받을 때 JSON포맷으로 받고싶다는 뜻
            conn.setRequestProperty("Accept", "application/json");
            
            //이제 데이터를 보낼 거라고 선언, 기본은 GET 요청처럼 데이터 없이 보내는 건데, POST처럼 데이터를 포함시킬 땐 이걸 꼭 true 로 설정
            conn.setDoOutput(true); // POST 방식은 body를 써야 하므로 true 설정

            // JSON 형식으로 구성 바디 부분 실무에서는 오브젝 보다 제이슨으로 변환해서 사용
            String jsonInputString = "{ \"title\": \"우용의 첫 포스트\", \"body\": \"Hello World!\", \"userId\": 1 }";


            // 서버로 데이터를 보내는 출력 스트림 열고 try with resources 문법으로 자동으로 닫아줌 (자원 누수 방지)
            try (OutputStream os = conn.getOutputStream()) {

                //문자열을 바이트 배열로 변환 네트워크에서는 바이트 단위로 데이터를 주고받기 때문
                byte[] input = jsonInputString.getBytes("utf-8");

                //바이트 배열을실제로서버로 전송, POST 요청의 핵심
                os.write(input, 0, input.length);
            }

            // 응답 코드 확인
            int responseCode = conn.getResponseCode();
            System.out.println("응답 코드: " + responseCode);

            // 응답 본문 읽기
            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                response.append(line.trim());
            }
            br.close();

            System.out.println("응답 본문:");
            System.out.println(response.toString());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

/*
[URL 생성]
    ↓
[HttpURLConnection 열기]
    ↓
[HTTP 메서드 및 헤더 설정]
    ↓
[POST라면 setDoOutput(true) + OutputStream으로 데이터 전송]
    ↓
[서버 응답 코드 확인]
    ↓
[InputStream → BufferedReader로 응답 본문 읽기]
    ↓
[출력]
 */