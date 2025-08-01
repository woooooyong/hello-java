public class Notification {

    private MessageService service;

    public Notification(MessageService service){
        this.service = service;
    }
    
    public void alert(String msg){
        service.send(msg);
    }
}