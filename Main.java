public class Main {
    public static void main(String[] args) {
        Member[] members = new Member[3];
        members[0] = new Member();
        members[0].name = "김우용";
        members[0].age = 25;
        members[0].email = "wooyong@example.com";

        members[1] = new Member();
        members[1].name = "이철수";
        members[1].age = 30;
        members[1].email = "chulsoo@example.com";

        members[2] = new Member();
        members[2].name = "박영희";
        members[2].age = 22;
        members[2].email = "younghee@example.com";

        for (int i = 0; i < members.length; i++) {
            System.out.println("=== " + (i + 1) + "번째 회원 ===");
            members[i].introduce();
            System.out.println(); // 줄바꿈
        }
    }
}

