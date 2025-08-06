import java.io.*; //io는 input output으로 메모리에서하는 것이 아닌 파일에서 데이터를 불러오는 기술

public class FileWriteExample {

    //파일을 일거나 쓸 때, 에러가 날 수 있음 그래서 반드시 throws or try catch로 예외처리해야함
    public static void main(String[] args) throws IOException{

        //파일 쓰기 모드로 열고 없다면 새로운 파일 생성
        FileWriter fw = new FileWriter("test_8_6.txt");
        //속도 향상(한 줄씩 쓰고 버퍼에서 모아서 처리함
        BufferedWriter bw = new BufferedWriter(fw);

        //문자열을 씀
        bw.write("첫 번째 줄입니다");
        //줄바꿈
        bw.newLine();
        bw.write("두 번째 줄입니다");

        //꼭 닫아야 진짜로 파일에 저장됨
        bw.close();
    }
}