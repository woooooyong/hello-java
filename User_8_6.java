import java.io.Serializable;

public class User_8_6 implements Serializable{

    private String name;
    private int age;

    public User_8_6(String name, int age){
        this.name = name;
        this.age = age;
    }

    public String toString(){
        return name +  "(" + age + ")";
    }
}