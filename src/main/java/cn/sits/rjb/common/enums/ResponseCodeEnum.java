package cn.sits.rjb.common.enums;

public enum ResponseCodeEnum {
	SUCCESS("10000","success"),
	//Exception
	FAILURE("0","调用失败"),//无法确定错误级别

	//系统通用错误.主要是参数验证等。
	//SystemException
	COMMON_ERROR_100("100","内部错误，请稍后重试"),

	//以下为通用异常
	COMMON_ERROR_101("101","非法方法名，或该方法名不存在"),
	COMMON_ERROR_102("102","未知请求类型或不支持当前请求方法"),
	COMMON_ERROR_103("103","不支持当前媒体类型"),

	COMMON_ERROR_104("104","缺少参数，请求失败"),
	COMMON_ERROR_105("105","参数解析错误"),
	/** 参数验证错误  */
	COMMON_ERROR_106("106","参数验证错误"),

	COMMON_ERROR_107("107","登录Token验证失败"),
	COMMON_ERROR_108("108","Sign签名验证失败"),
	COMMON_ERROR_109("109","调用链接失效，超过规定时效"),
	COMMON_ERROR_110("110","调用链接失效，返回内容错误"),
	COMMON_ERROR_111("111","服务器异常"),
	COMMON_ERROR_112("112","没有操作权限或数据权限"),
	COMMON_ERROR_113("113","数据处于使用中，无法删除"),
	COMMON_ERROR_114("114","数据不存在，获取数据失败"),
	COMMON_ERROR_115("115","无法操作该员工！"),
	COMMON_ERROR_1("-1","登录信息不存在，请重新登录"),
	COMMON_ERROR_2("-2","没有操作权限")
	//业务错误,定义在各个子项目里的ResponseBizCodeEnum。
	//BizException
	//BIZ_ERROR_201(201,"业务错误"),



	;//定义结束符
	private String code;
	private String msg;

	private ResponseCodeEnum(String code, String msg){
		this.code=code;
		this.msg=msg;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}


	public String getMsg(String code) {
		ResponseCodeEnum resultEnum = null;
		ResponseCodeEnum[] enumArray = ResponseCodeEnum.values();
		for (int i = 0; i < enumArray.length; i++) {
			if (enumArray[i].getCode().equals(code)) {
				resultEnum = enumArray[i];
				break;
			}
		}
		return resultEnum.getMsg();
	}
}
