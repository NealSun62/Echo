package cn.sits.rjb.common.data;

/**
 * 响应的返回结果集
 */
public class BaseResponse {
    public Long totalCount;
    public Long elapseTime;
    public Boolean isSuccess;
    public String message;
    public Object data;
    public String token;
    public Integer code;  //0 为正常  其余为 异常
    public Integer state; //200为正常   其余异常
    public Object tables;//返回数据集合

}
