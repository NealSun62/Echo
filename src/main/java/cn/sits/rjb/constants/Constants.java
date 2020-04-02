package cn.sits.rjb.constants;

public final class Constants {
/**接口响应状态*/
    /** 成功 */
    public static final String STATUSCODE_SUCCEED = "32";
    /** 超时 */
    public static final String STATUSCODE_SESSIONTIMEOUT = "64";
    /** 失败 */
    public static final String STATUSCODE_FAILED = "128";
    /** 无操作 */
    public static final String STATUSCODE_NOTFOUND = "256";

    /** 删除标识*/
    /** 未删除 */
    public static final String DELETED_NO = "0";
    /** 已删除 */
    public static final String DELETED_YES = "1";

    /**锁定标识**/
    /**未锁定**/
    public static final String LOCKED_NO = "0";
    /**管理员锁定**/
    public static final String LOCKED_MANAGER = "1";
    /**密码输入错误次数过多**/
    public static final String LOCKED_ERROMAX = "2";

    /**新建用户时初始的密码和盐**/
    /**密码**/
    public static final String INITIAL_PASSWORD = "111111";
    public static final String INITIAL_SALT = "56f94a2284293111";

    /** 分页相关 */
    /** 默认页数 */
    public static final int DEFAULT_PAGE_INDEX = 0;
    /** 默认每页显示条数 */
    public static final int DEFAULT_PAGE_SIZE = 20;
    /** 默认每页显示条数 */
    public static final int DEFAULT_EXPORT_SIZE = 1000;


    /**返回消息*/
    public static final String MESSAGE_SUCCEED = "成功";
    public static final String MESSAGE_PARAMETER_ILLEGALITY = "参数不合法";
    public static final String MESSAGE_FAILED = "失败";
    public static final String TOKEN_NAME = "token";

    public static final String OPERATION_TYPE = "opt";

    /** redis中存的用户数据的前缀 使用字符串+userId拼接作为KEY值  */
    public static final String AUTHDATARESPONSEDTO = "AUTHDATARESPONSEDTO";
    public static final String ROLE = "ROLE";
    public static final String AREA = "AREA";
    public static final String ORGUNIT = "ORGUNIT";
    public static final String AUTH = "AUTH";
    public static final String RQIANALYSISREDISDTO = "RQIANALYSISREDISDTO";
    public static final String TYPE = "TYPE";
    public static final String SIZE = "SIZE";
    public static final String NUM = "NUM";
    /** redis 中userId的缓存数据的有效时间 30分钟*/
    public static final long SAVE_REDIS_DATA_TIMEOUT = 30*60;

    /** redis 中userId的缓存数据的有效时间 30分钟*/
    public static final int SAVE_DATA_DAYS= 1;

    public static final int TIME_LENGTH = 19;
    //状态
    public static final int BREAK = 0;//断开
    public static final int CONNENT = 1;//连接



}
