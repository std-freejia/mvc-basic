package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

// lombok 에서 제공하는 annotation processing : Getter, Setter
@Getter
@Setter
public class HelloData {

    private String username;
    private int age;

    //getter setter (command + N)

    /*
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    */
}
