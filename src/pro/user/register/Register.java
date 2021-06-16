package pro.user.register;

public class Register {
    String id;
    String password;
    String birth;
    public void setId(String id){
        this.id=id;
    }
    public void setPassword(String pwd){
        this.password=pwd;
    }
    public void setBirth(String birth){
        this.birth=birth;
    }
    public String getId(){
        return id;
    }
    public String getPassword(){
        return password;
    }
    public String getBirth(){
        return birth;
    }
}
