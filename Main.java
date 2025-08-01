public class Main {
    public static void main(String[] args) {
        // ✅ LSP 테스트
        Workable[] workers = { new Human(), new Robot() };
        for (Workable w : workers) {
            w.work(); // 모두 문제없이 작동해야 함
        }

        // ✅ ISP: 사람만 밥 먹게
        Human h = new Human();
        h.eat();

        // ✅ DIP: 의존성 주입으로 메시지 발송
        Notification n1 = new Notification(new EmailService());
        n1.alert("이메일 알림 테스트");

        Notification n2 = new Notification(new SMSService());
        n2.alert("문자 알림 테스트");
    }
}


