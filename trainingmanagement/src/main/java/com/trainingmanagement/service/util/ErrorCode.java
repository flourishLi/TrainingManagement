package com.trainingmanagement.service.util;

public class ErrorCode {
	public static final int Success=1;
	public static final String SignInSuccess_Message="登陆成功";
	public static final String SignUpSuccess_Message="注册成功";
	public static final int SignInFail=101;
	public static final String SignInFail_Message="用户名或者密码错误，登陆失败";
	public static final int UserNotExist=102;
	public static final String UserNotExist_Message="用户不存在，请先注册";
	public static final int SignUpFail=103;
	public static final String SignUpFail_Message="注册失败";
	
	public static final String RequestMethodError_Message="请求格式必须是post";
	public static final String SignInEmpty_Message="用户名密码不能为空";
	public static final String SignUpEmpty_Message="用户名密码不能为空";
	public static final String SignInNull_Message="请输入用户名密码";
	public static final String SignUpNull_Message="请输入用户名密码";
	
	public static final String SponsorSuccess_Message="发起培训成功";
	public static final int SponsorFail=104;
	public static final String SponsorFail_Message="发起培训失败";
	
	public static final int DeleteTrainFail=105;
	public static final String DeleteTrainFail_Message="删除培训失败";
	
	public static final int UpdateTrainFail=106;
	public static final String UpdateTrainSuccess_Message="更新培训成功";
	public static final String UpdateTrainFail_Message="更新培训失败";
	
	public static final int SponsorUserIsNull=107;
	public static final String SponsorUserOrTrainIsEmpty_Message="培训发起者和培训编号不能为空";
	public static final String SponsorUserIsNull_Message="培训发起者不能为空";
	
	public static final int SearchTrainFailByUserIDAndTrainID=108;
	public static final String SearchTrainFailByUserIDAndTrainID_Message="根据创建者培训编号查找失败，没有匹配成功";
	
	public static final String RequestToJsonIsError_Message="请求内容转化成json对象的时候出错";
	
	public static final String InputStreamToUTFIsError_Message="输入流到UTF-8转化出错";
	public static final String InputStreamIsError_Message="读取输入流出错";
	
	public static final String RequestCheckPassed_Message="请求参数检查通过";
	
	public static final int DataBaseError=200;//数据库操作异常
	public static final String DataBaseError_Message="数据库操作异常";//数据库操作异常

}
