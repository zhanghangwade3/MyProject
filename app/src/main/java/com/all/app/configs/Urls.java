package com.all.app.configs;

/**
 *
 * @ClassName: Urls
 * @Description: TODO(描述: 网络访问相关配置)
 * @author Lee
 * @date 2016年4月29日 下午1:51:36
 * @version V1.0
 */
public class Urls {
	//王
	 //public static final String PATH_URL = "http://192.168.0.28/";
	//测试服
	//public static final String PATH_URL = "http://testwqjj.chinacloudapp.cn/";
	//正式服
	public static final String PATH_URL = "http://www.sxwqaz.com/";

	public static final String _URL = PATH_URL + "app.php?sign=sxwqapp";
	//获得地址的接口
	public static final String address ="&do=district&act=list";
    //登录接口
	public static final String login ="&do=user&act=login";
	//注册接口
	public static final String register ="&do=user&act=register";
	//获取验证码接口
	public static final String sendregistermsg ="&do=message&act=sendregistermsg";
	//找回密码获验证码接口
	public static final String sendregister ="&do=message&act=sendretrievemsg";
	//找回密码接口
	public static final String retrieve ="&do=user&act=retrieve";
	//认证管理实名认证提交
	public static final String savername ="&do=userauth&act=savername";
	//服务类型原始数据
	public static final String ServiceType ="&do=uservice&act=ServiceType";
	//实名认证信息读取
	public static final String getrname ="&do=userauth&act=getrname";
	//服务类型保存接口
	public static final String saveservicetype ="&do=uservice&act=saveservicetype";
	//服务类型保存状态的读取
	public static final String getservicetype ="&do=uservice&act=getservicetype";
	//当前城市所有区域原始数据读取
	public static final String getarea ="&do=uservice&act=getarea";
	//已选服务区域保存
	public static final String savearea ="&do=uservice&act=savearea";
	//当前城市已选区域状态的读取
	public static final String readarea ="&do=uservice&act=readarea";
	//进入服务信息页面数据的读取
	public static final String getservice ="&do=uservice&act=getservice";
	//服务信息保存接口
	public static final String saveservice ="&do=uservice&act=saveservice";
	//订单--接单列表显示页
   public static final String ordertask ="&do=order&act=ordertask";
	//订单--首页数字的显示
	public static final String orderstatenum ="&do=order&act=orderstatenum";
	//订单--接单详情页
	public static final String orderinfo ="&do=order&act=orderinfo";
	//订单--接单--我要报价
	public static final String orderprice ="&do=order&act=orderprice";
    //订单--已中标
	public static final String getorderlist ="&do=order&act=getorderlist";
   //订单--待施工
   public static final String getorderlist2 ="&do=order&act=getorderlist";
	//订单--已中标--确认接单
	public static final String saveorderstate ="&do=order&act=saveorderstate";
	//订单--待提货
	public static final String getorderlist3 ="&do=order&act=getorderlist";
	//订单--订单详情页
	public static final String getorderinfo ="&do=order&act=getorderinfo";
	//订单--待验收
	public static final String getorderlist4 ="&do=order&act=getorderlist";
	//订单--待提货--完成配送
	public static final String distribution ="&do=order&act=distribution";
	//订单--待施工--完成施工
	public static final String construct ="&do=order&act=construct";
	//订单--设为问题订单
	public static final String saveproblemorder ="&do=order&act=saveproblemorder";
	//订单--待验收--确认中标价
	public static final String updateorderprice ="&do=order&act=updateorderprice";
	//订单--待验收--获取中标价
	public static final String getbidprice ="&do=order&act=getbidprice";
	//订单--问题单列表
	public static final String getproblemlist ="&do=order&act=getproblemlist";
	//订单--问题单详情页
	public static final String getprobleminfo ="&do=order&act=getprobleminfo";
	//订单--问题单--已解决
	public static final String processtask="&do=order&act=processtask";
	//订单--已完成
	public static final String completeorder="&do=order&act=completeorder";
	//已报价列表
	public static final String offerlist="&do=home&act=offerlist";
	//已报价--报价详情页
	public static final String offerinfo="&do=home&act=offerinfo";
	//已报价--修改报价
	public static final String updoffer="&do=home&act=updoffer";
	//版本更新接口
	public static final String getversion="&do=public&act=getversion";
	//用户信息--我首页
	public static final String my ="&do=user&act=my";
	//用户信息--修改
	public static final String saveuserinfo ="&do=user&act=saveuserinfo";
	//读取用户信息
	public static final String getuserinfo ="&do=user&act=getuserinfo";
	//用户信息--意见反馈
	public static final String saveproposal ="&do=public&act=saveproposal";
	//发现--列表
	public static final String article ="&do=public&act=article";
	//设置--修改密码
	public static final String updatepwd ="&do=user&act=updatepwd";
	//个人中心--我的钱包--钱包明细
	public static final String getuserdetailed ="&do=burse&act=getuserdetailed";
	//个人中心--我的钱包--钱包明细详情
	public static final String getdetailedinfo ="&do=burse&act=getdetailedinfo";
	//首页--消息--消息数量
	public static final String mymsgnum ="&do=user&act=mymsgnum";
	//首页--消息--消息列表
	public static final String mymsg ="&do=user&act=mymsg";
	//首页--消息--消息详情
	public static final String msginfo ="&do=user&act=msginfo";
	//个人中心--我的钱包--银行卡/支付宝(列表)
	public static final String bankcardlist ="&do=burse&act=bankcardlist";
	//个人中心--我的钱包--删除银行卡信息
	public static final String delbank ="&do=burse&act=delbank";
	//个人中心--我的钱包--添加银行卡
	public static final String savebankcard ="&do=burse&act=savebankcard";
	//个人中心--我的钱包--添加支付宝
	public static final String savealipayjs ="&do=burse&act=savealipayjs";
	//个人中心--我的钱包--获取银行卡类型
	public static final String getbanktype ="&do=burse&act=getbanktype";
	//个人中心--我的钱包--提现页-可提现金
	public static final String getuserbalance ="&do=burse&act=getuserbalance";
	//个人中心--我的钱包--验证支付密码
	public static final String checkpwd ="&do=burse&act=checkpwd";
	//个人中心--我的钱包--提现申请
	public static final String withdrawapply="&do=burse&act=withdrawapply";
   //个人中心--我的钱包--获取保证金
   public static final String getuserbond="&do=burse&act=getuserbond";
	//首页--其他信息（可报价数、消息数、任务倒计时）
	public static final String getotherinfo="&do=public&act=getotherinfo";
	//个人中心--我的钱包--我的钱包首页
	public static final String mypurse="&do=burse&act=mypurse";
	//个人中心--我的钱包--修改支付密码
	public static final String updpaypwd="&do=burse&act=updpaypwd";
	//个人中心--退保证金
	public static final String surrender="&do=burse&act=surrender";
	//首页--公告列表
	public static final String notice="&do=public&act=notice";
	//首页--banner图
	public static final String bannerlist="&do=public&act=bannerlist";
	//个人中心--我的钱包--余额方式缴纳保证金
	public static final String saveuserbond="&do=burse&act=saveuserbond";
    //支付宝接口
	public static final String creatordercharge="&do=burse&act=creatordercharge";
	public class ActionId {
		public static final int login  = 0x144;
		public static final int register = 0x145;
		public static final int sendregistermsg = 0x146;
		public static final int address = 0x147;
		public static final int sendregister = 0x148;
		public static final int retrieve= 0x149;
		public static final int savername= 0x150;
		public static final int ServiceType = 0x151;
		public static final int getrname = 0x152;
		public static final int saveservicetype = 0x153;
		public static final int ordertask = 0x154;
		public static final int orderstatenum = 0x155;
		public static final int orderinfo= 0x156;
		public static final int getservicetype= 0x157;
		public static final int getarea= 0x158;
		public static final int savearea= 0x159;
		public static final int readarea = 0x160;
		public static final int getservice = 0x161;
		public static final int saveservice = 0x162;
		public static final int orderprice = 0x163;
		public static final int getorderlist = 0x164;
		public static final int getorderlist2 = 0x165;
		public static final int saveorderstate = 0x166;
		public static final int getorderlist3 = 0x167;
		public static final int getorderinfo  = 0x168;
		public static final int getorderlist4  = 0x169;
		public static final int distribution  = 0x170;
		public static final int saveproblemorder  = 0x171;
		public static final int construct  = 0x172;
		public static final int updateorderprice  = 0x173;
		public static final int  getbidprice  = 0x174;
		public static final int  getproblemlist = 0x175;
		public static final int  getprobleminfo = 0x176;
		public static final int  processtask = 0x177;
		public static final int  completeorder = 0x178;
		public static final int  offerlist = 0x179;
		public static final int  offerinfo = 0x180;
		public static final int  updoffer = 0x181;
		public static final int  getversion = 0x182;
		public static final int  my = 0x183;
		public static final int saveuserinfo = 0x184;
		public static final int getuserinfo = 0x185;
		public static final int saveproposal = 0x186;
		public static final int article = 0x187;
		public static final int updatepwd  = 0x188;
		public static final int getuserdetailed  = 0x189;
		public static final int getdetailedinfo  = 0x190;
		public static final int mymsgnum  = 0x191;
		public static final int mymsg = 0x192;
		public static final int msginfo  = 0x193;
		public static final int bankcardlist  = 0x194;
		public static final int delbank  = 0x195;
		public static final int savebankcard = 0x196;
		public static final int savealipayjs = 0x197;
		public static final int getbanktype = 0x198;
		public static final int getuserbalance = 0x199;
		public static final int checkpwd = 0x200;
		public static final int withdrawapply = 0x201;
		public static final int getuserbond = 0x202;
		public static final int getotherinfo = 0x203;
		public static final int mypurse = 0x204;
		public static final int updpaypwd = 0x205;
		public static final int surrender = 0x206;
		public static final int notice = 0x207;
		public static final int bannerlist = 0x208;
		public static final int saveuserbond = 0x209;
		public static final int creatordercharge = 0x210;
	}
}
