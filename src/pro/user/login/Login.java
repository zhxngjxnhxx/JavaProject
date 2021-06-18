package pro.user.login;

public class Login {
    boolean loginSucess=false;
    String id;
    String password;
    String province="";
    int level=0;
    public void setID(String id){
        this.id=id;
    }
    public void setPassword(String pwd){
        this.password=pwd;
    }
    public void setprovince(String prv){
        province=prv;
    }
    public void setLevel(int i){level=i;}
    public String getId(){
        return id;
    }
    public String getPassword(){
        return password;
    }
    public String getProvince(){
        return province;
    }
    public int getLevel(){return level;}
    public void setLoginSucess(boolean bo){
        this.loginSucess=bo;
    }
    public boolean getLoginSucess(){
        return loginSucess;
    }
}
