package xupt.se.util;

// 使用此类生成返回消息
public class ApiResponseUtil
{

    /**
     * 获取请求成功响应的ApiResponse
     * @param data
     * @return
     */
    public static ApiResponse getApiResponse(Object data)
    {
        return getApiResponse(data, ApiResponseEnum.SUCCESS.getResCode(), ApiResponseEnum.SUCCESS.getResMsg());
    }

    /**
     * 获取其他请求响应的ApiResponse
     * @param code
     * @param msg
     * @return
     */
    public static ApiResponse getApiResponse(int code, String msg)
    {
        return getApiResponse(null, code, msg);
    }

    /**
     * 枚举信息转统一返回对象
     * @param apiResponseEnum
     * @return 例子：ApiResponse
     *         apiResponse=ApiResponseUtil.getApiResponse(ApiResponseEnum.AUTH_ERROR);
     */
    public static ApiResponse getApiResponse(ApiResponseEnum apiResponseEnum)
    {
        return getApiResponse(apiResponseEnum.getResCode(), apiResponseEnum.getResMsg());
    }

    public static ApiResponse getApiResponse(Object data, int code, String msg)
    {
        ApiResponse apiResponse=new ApiResponse(data);
        apiResponse.setResCode(code);
        apiResponse.setResMsg(msg);
        return apiResponse;
    }

}
