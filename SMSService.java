public class SMSService implements MessageService{

    public void send(String message) {
        System.out.println("📱 문자 전송: " + message);
    }
}