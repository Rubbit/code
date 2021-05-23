package xupt.se.util;

public enum ApiResponseEnum
{
    /**
     * 返回类型
     */
    SUCCESS(10000, "请求成功"), FAIL(10001, "请求失败"), LOGIN_FAIL(10099, "登陆失败"), AUTH_ERROR(10100, "认证失败"), NO_TICKET(10101,
            "无票场次");

    private int resCode=0;

    private String resMsg;

    private ApiResponseEnum(int resCode, String resMsg)
    {
        this.resCode=resCode;
        this.resMsg=resMsg;
    }

    public int getResCode()
    {
        return resCode;
    }

    public void setResCode(int resCode)
    {
        this.resCode=resCode;
    }

    public String getResMsg()
    {
        return resMsg;
    }

    public void setResMsg(String resMsg)
    {
        this.resMsg=resMsg;
    }

}
