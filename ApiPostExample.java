import java.io.*;                      // 자바의 입출력(IO) 패키지 전체 임포트: InputStream, OutputStream, Reader/Writer, BufferedReader 등 사용
import java.net.*;                     // 네트워크 패키지 임포트: URL, HttpURLConnection 등 HTTP 통신에 필요
import org.json.JSONObject;           // 외부 라이브러리(org.json)의 JSONObject 클래스 임포트: JSON 객체를 다루기 위해 필요

public class ApiPostExample {          // 클래스 선언: 파일명과 동일해야 하며, 실행 엔트리포인트(main)를 포함
    public static void main(String[] args) {   // 자바 프로그램 시작점: JVM이 처음 호출하는 메서드
        HttpURLConnection conn = null;         // 나중에 finally에서 연결 해제를 위해 참조를 잡아둘 변수(초기엔 null)
        try {                                  // 네트워크/IO는 예외가 자주 나므로 try-catch로 감쌈
            URL url = new URL("https://jsonplaceholder.typicode.com/posts"); // 요청할 주소를 URL로 파싱해 객체화(문자열→URL 구조체)
            conn = (HttpURLConnection) url.openConnection();                 // URL에 연결 열기: 프로토콜에 맞는 Conn 반환(HTTPS면 내부적으로 HttpsURLConnection)

            conn.setRequestMethod("POST");                                   // HTTP 메서드 지정: 반드시 대문자(“POST”)
            conn.setRequestProperty("Content-Type", "application/json; charset=utf-8"); // 요청 바디의 데이터 타입과 문자셋 명시(서버가 바디 파싱하는 힌트)
            conn.setRequestProperty("Accept", "application/json");           // 서버에게 “난 JSON 응답 원해”라고 힌트 제공(협상용 헤더)
            conn.setDoOutput(true);                                          // 바디를 보낼 것이므로 출력 스트림 사용 허용(POST/PUT 시 필수)

            conn.setConnectTimeout(5000);                                    // 연결 시도 타임아웃(ms): 서버가 죽었을 때 영원히 대기 방지
            conn.setReadTimeout(5000);                                       // 응답 본문 읽기 타임아웃(ms): 느린 응답으로 무한대기 방지

            JSONObject json = new JSONObject();                               // 전송할 JSON 객체 생성(메모리 상의 key-value 맵)
            json.put("title", "first post of wooyong");                      // JSON에 필드 추가: "title": "first post of wooyong"
            json.put("body", "this is data that java send");                 // JSON에 필드 추가: "body": "...”
            json.put("userId", 99);                                          // JSON에 필드 추가: "userId": 99 (숫자)

            try (OutputStream os = conn.getOutputStream()) {                 // 요청 바디 전송을 위한 출력 스트림 획득(try-with-resources로 자동 close)
                byte[] input = json.toString().getBytes("utf-8");            // JSONObject → 문자열(JSON 텍스트) → UTF-8 바이트 배열로 인코딩
                os.write(input, 0, input.length);                            // 실제 전송: HTTP 요청 바디에 바이트 쓰기(헤더의 Content-Type과 일치)
            }                                                                 // os 자동 닫힘: 리소스 누수 방지

            int code = conn.getResponseCode();                               // 서버가 보낸 상태코드(200, 201, 400, 500…) 수신
            System.out.println("응답 코드: " + code);                         // 디버깅 출력: 요청 성공/실패를 빠르게 파악

            InputStream is = (code >= 200 && code < 300)                     // 상태코드가 2xx면 성공 스트림, 아니면 에러 스트림을 선택
                    ? conn.getInputStream()
                    : conn.getErrorStream();

            StringBuilder response = new StringBuilder();                    // 응답 본문을 점진적으로 이어붙일 버퍼(문자열 누적용)
            try (BufferedReader br = new BufferedReader(                      // 바이트 스트림 → 문자 스트림(UTF-8) → 버퍼링하여 라인 단위로 읽음
                    new InputStreamReader(is, "utf-8"))) {
                String line;                                                 // 한 줄씩 임시 저장할 변수
                while ((line = br.readLine()) != null) {                     // EOF(더 읽을 줄이 없음)까지 반복
                    response.append(line.trim());                            // 라인 끝 개행 제거 겸 trim으로 앞뒤 공백 정리해 누적(용량 절약/보기 편함)
                }                                                            // (※ 원문 포맷 보존이 필요하면 trim은 빼도 됨)
            }                                                                // br 자동 닫힘: 스트림 자원 정리

            JSONObject responseJson = new JSONObject(response.toString());   // 응답 문자열을 JSON 객체로 파싱(문자열 → 구조화 데이터)
            System.out.println("응답 JSON: " + responseJson.toString(2));    // 보기 좋게 들여쓰기(2칸)로 pretty-print 후 콘솔 출력

        } catch (Exception e) {                                              // 위 과정 중 어떤 예외든 잡아 로그로 확인
            e.printStackTrace();                                             // 스택트레이스 출력(디버깅 용이)
        } finally {                                                          // 성공/실패와 무관하게 자원 정리
            if (conn != null) conn.disconnect();                             // HTTP 연결 해제: 커넥션 풀/소켓 자원 반환(명시적으로 습관화)
        }
    }
}
