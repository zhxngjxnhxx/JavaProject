package pro.user.registerforexam;

import pro.user.login.Login;

public class RegisterForExam {
    Login login;//用户
    String Province;
    int Number;//考试人数
    //考虑设置报名人数最大值，报名人数满之后就不能在报名
    boolean RFESucess=false;//考试报名成功与否
    public void setProvince(String province){
        this.Province=province;
    }
    public void setNumber(int number){
        this.Number=number;
    }
    public void setLogin(Login login){
        this.login=login;
    }
    public String getProvince(){
        return Province;
    }
    public int getNumber(){
        return Number;
    }
    public Login getLogin(){
        return login;
    }
    public void setRFESucess(boolean bo){
        this.RFESucess=bo;
    }
    public boolean getLoginSucess(){
        return RFESucess;
    }
}
