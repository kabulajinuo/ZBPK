package dckj.arrange.common.base;

/**
 * djl
 */
public enum ErrorCode {
	ERROR_SYSTEM(-1,"系统错误"),
	ERROR_SERVICE_FAIL(-2,"服务失败"),
	ERROR_MISS_PARAM(-3,"缺失参数"),
	
	ERROR_ACCOUNT_STOP(10000,"账号被禁用"),
	ERROR_ACCOUNT_IS_EXIST(10001,"用户名已经存在"),
	ERROR_PHONE_IS_EXIST(10002,"手机号已经存在"),
	ERROR_PWD_IS_NULL(10003,"密码不能为空"),
	ERROR_ACCOUNT_OR_PWD(10005,"用户名或者密码错误"),
	ERROR_SMS_CODE(10006,"短信验证码不正确或者失效"),
	ERROR_OPENID_IS_IS_EXIST(10007,"openId已经存在"),
	ERROR_USER_ROLE(10008,"权限不足"),
	ERROR_NO_REGISTER_PHONE(10009,"该手机号未注册"),
	ERROR_PHONE_FORMAT(10100,"手机号码格式错误"),
	ERROR_NOT_REGISTER_USER(10101,"用户未注册"),
	ERROR_NOT_PERMISSION(10102,"未授权的用户"),
	ERROR_NO_SCHOOL(10103,"学校名称不正确"),
	ERROR_NO_GRADE(10104,"年级名称不正确"),
	ERROR_NO_CLASS(10105,"班级名称不正确"),
	ERROR_NO_EXCEL(10106,"读取Excel出现错误"),
	ERROR_UNKNOWN_EXCEL(10107,"未知版本的Excel"),
	ERROR_NO_EXAMINEE(10108,"导入考生出现错误"),
	ERROR_STUDENT_EXIST(10109,"存在重复的学生相关信息"),
	ERROR_STUDENT_NOT_EXIST(10110,"不存在的学生信息"),

	ERROR_TARGET_USER_NO_EXIST(10200,"目标用户不存在"),
	ERROR_NULL_DATA(102001,"未知数据"),

	ERROR_DATA_EXIST(10300,"存在相同的数据"),

	ERROR_FUNC_EXPIRE(110001,"未缴费，或者缴费到期")

	;

	private Integer value;
	private String name;

	private ErrorCode(Integer value, String name) {
		this.name = name;
		this.value = value;
	}

	public Integer getValue() {
		return this.value;
	}

	public String getName() {
		return this.name;
	}
}