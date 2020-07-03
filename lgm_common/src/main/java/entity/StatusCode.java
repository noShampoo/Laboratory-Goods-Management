package entity;

/**
 * 状态码类
 */
public class StatusCode {
//    public static final int OK=20000;//成功
//    public static final int ERROR =20001;//失败
//    public static final int LOGINERROR =20002;//用户名或密码错误
//    public static final int ACCESSERROR =20003;//权限不足
//    public static final int REMOTEERROR =20004;//远程调用失败
//    public static final int REPERROR =20005;//重复操作

    public static final int OK = 1000;          //符合前端想要的就是1000
    public static final int ERROR = 1001;       //所有不符合想要的都是1001
    public static final int PERMISSION = 1002;  //权限问题
}
