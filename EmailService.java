public class EmailService implements MessageService{
    public void send(String message){
        System.out.println("📧 이메일 전송: " + message);
    }
}