public class Human implements Workable, Eatable{

    public void work(){
        System.out.println("사람이 열심히 일합니다.");
    }

    @Override
    public void eat(){
        System.out.println("사람이 점심을 먹습니다.");
    }
}