import java.util.*;

public class LambdaExample {

    public static void main(String[] args) {

        //List는 뼈대고 <String>은 문자열만 담겠다는 제네릭
        List<String> names = Arrays.asList("우용", "민지", "지후");

        for (String name : names){
            System.out.println(name);
        }

        //람다식으로 매개변수 name을 사용해서 names의 0번부터 name에 담아 -> 내용을 반복
        names.forEach(name -> System.err.println(name));

        //List는 뼈대 <String>은 제네릭 문자열만을 받음, abc의 배열을 문자열 처럼 만들고 그 문자열을 진짜 문자열 객체로 복사함
        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
        
        //자바에서 리스트를 하나씩 꺼내기 위한 도구 이터레이터는 반복자라고도 불림, <String> 꺼낼값이 문자열을 의미, 이터레이터가 화살표 같은 느낌
        Iterator<String> iter = list.iterator();

        while (iter.hasNext()) {
            String s = iter.next();
            System.out.println(s);
        }
    }
}