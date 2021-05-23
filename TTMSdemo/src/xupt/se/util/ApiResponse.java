package xupt.se.util;

public class ApiResponse
{
    private int resCode=0;

    private String resMsg;

    private Object data;// JSONObject or null "操作成功"

    public ApiResponse()
    {}

    public ApiResponse(Object data)
    {
        this.data=data;
    }

    public ApiResponse(ApiResponseEnum apiResponseEnum)
    {
        this.resCode=apiResponseEnum.getResCode();
        this.resMsg=apiResponseEnum.getResMsg();
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

    public Object getData()
    {
        return data;
    }

    public void setData(Object data)
    {
        this.data=data;
    }

    @Override
    public String toString()
    {
        return "ApiResponse{" + "resCode=" + resCode + ", resMsg='" + resMsg + '\'' + ", data=" + data + '}';
    }
}
