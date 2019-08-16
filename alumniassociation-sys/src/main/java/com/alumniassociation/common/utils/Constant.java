package com.alumniassociation.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 常量
 * 
 * @author chenyi
 * @email 228112142@qq.com
 * @date 2016年11月15日 下午1:23:52
 */
public class Constant {
    /**包路径**/
    public final static String PACKAGE_NAME = "com.alumniassociation.common.enumresource";
	/** 超级管理员ID */
	public static final int SUPER_ADMIN = 1;

	/**门店端安装订单状态流程**/
    public final static List<Integer> INSTALL_STATUS_TREE
            = new ArrayList<Integer>(Arrays.asList(InstallStatus.WAIT_COMFIRM.getValue(),InstallStatus.WAIT_PAY.getValue(),InstallStatus.WAIT_ALLOT.getValue(),InstallStatus.WAIT_ACCEPT.getValue(),
            InstallStatus.WAIT_RESERVE.getValue(),InstallStatus.WAIT_INSTALL.getValue(),InstallStatus.ALEADY_INSTALL.getValue()));

    /**销售订单订单状态流程**/
    public final static List<Integer> SALES_ORDER_STATUS
            = new ArrayList<>(Arrays.asList(SalesOrderStatus.WAIT_MEASURING.getValue(),SalesOrderStatus.WAIT_QUOTE.getValue(),
            SalesOrderStatus.WAIT_RECEIPT.getValue(),SalesOrderStatus.WAIT_CHECK.getValue(),SalesOrderStatus.WAIT_RESERVE_DESIGN.getValue(),
            SalesOrderStatus.WAIT_ALLOT_FACTORY.getValue(),
            SalesOrderStatus.WAIT_PRODUCE.getValue(),OrderStatus.WAIT_STORAGE.getValue(),OrderStatus.WAIT_RETRIEVAL.getValue(),
            SalesOrderStatus.WAIT_RECEIPT_GOODS.getValue(),SalesOrderStatus.WAIT_ALLOT_INSTALL.getValue(),
            SalesOrderStatus.WAIT_RESERVE_INSTALL.getValue(),
            SalesOrderStatus.WAIT_HOME_INSTALL.getValue(),SalesOrderStatus.FINISHED.getValue()
            ));

    /**运营商派发安装单状态流程**/
    public final static List<Integer> OPERATOR_INSTALL_ORDER_STATUS
            = new ArrayList<>(Arrays.asList(SalesOrderStatus.WAIT_ALLOT_INSTALL.getValue(),
            SalesOrderStatus.WAIT_RESERVE_INSTALL.getValue(), SalesOrderStatus.WAIT_HOME_INSTALL.getValue(),SalesOrderStatus.FINISHED.getValue()
    ));


	/**
	 * 菜单类型
	 * 
	 * @author chenyi
	 * @email 228112142@qq.com
	 * @date 2016年11月15日 下午1:24:29
	 */
    public enum MenuType {
        /**
         * 目录
         */
    	CATALOG(0),
        /**
         * 菜单
         */
        MENU(1),
        /**
         * 按钮
         */
        BUTTON(2);

        private int value;

        private MenuType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    /**
     * 优惠类型
     * @author lewp
     */
    public enum  Offer_Type{
        /**
         * 打折
         */
    	DISCOUNT(1),
        /**
         * 满减
         */
    	FULL_REDU(2);

        private int value;

        private Offer_Type(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }
    
    /**
     * 支付方式
     * @author lewp
     */
    public enum PaymentMethod{
        /**
         * 微信支付
         */
    	WECHAT("WeChat"),
    	/**
         * 余额支付
         */
    	BALANCE_PAY("balancePay"),
    	/**
         * 支付宝支付
         */
    	ALI_PAY("aliPay"),
        /**
         * POS机支付
         */
        POS_PAY("posPay");

        private String value;

        private PaymentMethod(String value) {
            this.value = value;
        }
        
        public String getValue() {
            return value;
        }
    }
    
    /**
     * 定时任务状态
     * 
     * @author chenyi
     * @email 228112142@qq.com
     * @date 2016年12月3日 上午12:07:22
     */
    public enum ScheduleStatus {
        /**
         * 正常
         */
    	NORMAL(0),
        /**
         * 暂停
         */
    	PAUSE(1);

        private int value;

        private ScheduleStatus(int value) {
            this.value = value;
        }
        
        public int getValue() {
            return value;
        }
    }

    /**
     * 云服务商
     */
    public enum CloudService {
        /**
         * 七牛云
         */
        QINIU(1),
        /**
         * 阿里云
         */
        ALIYUN(2),
        /**
         * 腾讯云
         */
        QCLOUD(3);

        private int value;

        private CloudService(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }
    
    /**
     * 订单列表
     */
    public enum OrderStatus {
    	/**
         * 待派发
         */
        WAIT_ALLOT(0),
        /**
         * 待接单
         */
        WAIT_ACCEPT(1),
        /**
         * 待量尺
         */
        WAIT_MEASURING(2),
        /**
         * 待报价
         */
        WAIT_QUOTE(4),
        /**
         * 待收款
         */
        WAIT_RECEIPT(5),
        /**
         * 收款后，待订合同
         */
        WAIT_CONTRACT(51),
        /**
         * 一键下单
         */
        QUICK_ORDER(52),
        /**
         * 待审核
         */
        WAIT_CHECK(53),
        /**
         * 待财务审核
         */
        WAIT_MANAGER_CHECK(54),
        /**
         * 待设计师设计图纸
         */
        WAIT_RESERVE_DESIGN(7),
        /**
         * 待蚁装管理人员审核及拆单给工厂
         */
        WAIT_ALLOT_FACTORY(8),

        /**
         * 待工厂生产
         */
        WAIT_PRODUCE(9),
        /**
         * 待运营商确认
         */
        WAIT_CONFRIM(71),

        /**
         * 待仓库入库
         */
        WAIT_STORAGE(301),

        /**
         * 待仓库出库
         */
        WAIT_RETRIEVAL(302),
        
        /**
         * 待运营商派发
         */
        WAIT_ALLOT_INSTALL(18),
        /**
         * 待安装师傅接单， 接单之后变成 10
         */
        WAIT_GRAB_INSTALL(19),
        /**
         * 安装师傅预约客户
         * 待预约安装
         */
        WAIT_RESERVE_INSTALL(10),
        /**
         * 待上门安装
         */
        WAIT_HOME_INSTALL(11),
        
        /**
         * 预约安装超时 （弃用）
         */
        RESERVE_INSTALL_TIMEOUT(12),
        
        /**
         * 已安装
         */
        FINISHED(99),
        /**
         * 待售后处理
         */
        WAIT_AFTER_SALE(88),
        
        //以下为虚拟状态
        /**
         * 用户端待下单
         */
        WAIT_ORDER(6),
        /**
         * 量尺超时
         */
        MEASURING_TIMEOUT(3),
        /**
         * 待收貨
         */
        WAIT_RECEIPT_GOODS(91),
        /**
         * 待安装 (销售人员确认收货之后查询待安装订单)
         */
        WAIT_INSTALL(69),
        
        /**
         * 待上门二次安装
         */
        WAIT_HOME_SECOND_INSTALL(13),
        
        /**
         * 安装超时
         */
        INSTALL_TIMEOUT(14),
        /**
         * 待跟进
         * 待报价
         */
        WAIT_FOLLOW(100),
        /**
         * 订单状态时间轴
         */
        ORDER_STATUS_TIMES(200),
        /**
         * 跟进状态
         */
        FOLLOW_TIMEOUT(101),
        
        /**
         *量尺评价
         */
        RULER_EVA(61),
        
        /**
         * 安装评价
         */
        INSTALL_EVA(62),
        /**
         * 销售端查看待量尺评价
         */
        SALE_EVA_SALER(63),

        /**
         * 查看订单
         */
        VIEW_ORDER(202),
        
        /**
         * 门店待确定
         */
    	WAIT_COMFRIM_ORDER(77),


        /**
         * 门店待支付
         */
        WAIT_PAY(78),
        /**
         * 门店待安装
         */
        WAIT_INSTALL_MD(79),
        /**
         * 门店待接单
         */
        WAIT_ACCEPTL_MD(80),
        /**
         * 门店待评价
         */
        WAIT_EVALUATE_MD(81),
        /**
         * 门店售后
         */
        AFTER_SALE_MD(82),
        /**
         * 门店历史订单
         */
        HISTORY_MD(83),
        /**
         * 门店安装时间审核
         */
        APY_INSTALL_TIME_MD(84),
    	 /**
         * 查看安装订单
         */
    	VIEW_INSTALL_ORDER(205);

        private int value;

        private OrderStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
        
        /**
         * 获取不同状态对应的消息通知模板
         * @return
         */
        public String getMsg(){
        	String msgTemplate = null;
        	switch (value) {
        	case 0:
				msgTemplate = "您的预约信息已被系统接收！预约编号为：%s";
				break;
			case 1:
				msgTemplate = "系统已派单！等待销售经理跟进！";
				break;
			case 2:
				msgTemplate = "销售经理(%s)成功接单， 请保持电话通畅， 稍后会与您电话沟通， 约定上门量尺时间,联系电话：%s";
				break;
			case 4:
				msgTemplate = "已完成量尺，您可对本次服务进行评价。 ";
				break;
			case 5:
				msgTemplate = "您的订单%s总报价金额为%s, 请您尽快完成付款！ ";
				break;
			case 51:
				msgTemplate = "恭喜！您的订单%s已支付成功，我们正在订立合同以确保您的权益不被损害，请您耐心等候！ ";
				break;
			case 7:
				msgTemplate = "恭喜！您的订单%s合同已签订成功，设计师正在为您设计产品方案。 ";
				break;
			case 9:
				msgTemplate = "产品方案已发送到工厂， 工厂正在紧急生产中。 ";
				break;
			case 18:
				msgTemplate = "货物已送到，系统正在为您分配安装师傅！ ";
				break;
			case 19:
				msgTemplate = "已分配安装师傅， 待安装师傅跟进！ ";
				break;
			case 10:
				msgTemplate = "安装师傅(%s)已接单， 请保持电话通畅， 稍后会与您电话沟通， 约定上门安装时间。联系电话：%s";
				break;
			case 11:
				msgTemplate = "上门安装时间 为%s， 请您合理按时好时间，以待%s师傅上门安装！";
				break;
			case 99:
				msgTemplate = "已完成安装，您可对本次安装进行评价。";
				break;
			case 88:
				msgTemplate = "已接受您的售后问题，稍后与您沟通核实问题，请保持电话通畅。";
				break;
			default:
				break;
			}
        	return msgTemplate;
        }
        
        /**
         * 获取不同状态对应的消息通知模板
         * @return
         */
        public String getMsg(int roleNum){
        	String msgTemplate = null;
        	switch (value) {
        	case 0:
    			msgTemplate = "您的预约信息已被系统接收！预约编号为：%s";
    			break;
    		case 1:
    			if(roleNum == 2){
    				msgTemplate = "系统给你分配了一个量尺订单，请及时跟进！";
    			}
    			break;
    		case 2:
    			msgTemplate = "销售经理(%s)成功接单， 请保存电话通畅， 稍后会与您电话沟通， 约定上门量尺时间。联系电话：%s";
    			break;
    		case 4:
    			msgTemplate = "已完成量尺，您可对本次服务进行评价。 ";
    			break;
    		case 5:
    			msgTemplate = "您的订单%s总报价金额为%s, 请您尽快完成付款！ ";
    			break;
    		case 51:
    			msgTemplate = "恭喜！您的订单%s已支付成功，我们正在订立合同以确保您的权益不被损害，请您耐心等候！ ";
    			break;
    		case 7:
    			msgTemplate = "恭喜！您的订单%s合同已签订成功，设计师正在为您设计产品方案。 ";
    			break;
    		case 9:
    			msgTemplate = "产品方案已发送到工厂， 工厂正在紧急生产中。 ";
    			break;
    		case 18:
    			msgTemplate = "货物已送到，系统正在为您分配安装师傅！ ";
    			break;
    		case 19:
    			msgTemplate = "已分配安装师傅， 待安装师傅跟进！ ";
    			break;
    		case 10:
    			msgTemplate = "安装师傅(%s)已接单， 请保存电话通畅， 稍后会与您电话沟通， 约定上门安装时间。联系电话：%s";
    			break;
    		case 11:
    			msgTemplate = "上门安装时间 为%s， 请您合理按时好时间，以待%s师傅上门安装！";
    			break;
    		case 99:
    			msgTemplate = "已完成安装，您可对本次安装进行评价。";
    			break;
    		case 88:
    			msgTemplate = "已接受您的售后问题，稍后与您沟通核实问题，请保存电话通畅。";
    			break;
    		default:
    			break;
    		}
        	return msgTemplate;
        }
    }

    /**
     * 安装状态
     */
    public enum allowOrder {
        /**
         * 允许接单
         */
        ALLOW(0),
        /**
         * 禁止接单
         */
        UNALLOW(1);
        private int value;
        private allowOrder(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 安装状态
     */
    public enum InstallStatus {
        /**
         * 待确定
         */
        WAIT_COMFIRM(12),
        /**
         * 待确定
         */
        WAIT_PAY(13),
        /**
         * 待分配
         */
        WAIT_ALLOT(0),
        /**
         * 待接单
         */
        WAIT_ACCEPT(1),
        /**
         * 待预约
         */
        WAIT_RESERVE(2),
        /**
         * 待安装
         */
        WAIT_INSTALL(3),
        /**
         * 已安装
         */
        ALEADY_INSTALL(4);

        private int value;
        private InstallStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
        }


    /**
     * 安装订单来源
     */
    public enum InstallOrginal {
        /**
         * 运营商
         */
        OPERATOR(0),
        /**
         * 销售
         */
        SALE(1),
        /**
         * 用户
         */
        USER(2),
        /**
         * 门店
         */
        STORE(3);
        private int value;
        private InstallOrginal(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 安装最后轮次
     */
    public enum installStep {
        /**
         * 不是最后提交轮次
         */
        FALSE(0),
        /**
         * 最后提交轮次
         */
        TRUE(1);

        private int value;
        private installStep(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * 安装状态
     */
    public enum SalesOrderStatus {
        /**
         * 待分配
         */
        WAIT_ALLOT(0),
        /**
         * 待接单
         */
        WAIT_ACCEPT(1),
        /**
         * 待量尺
         */
        WAIT_MEASURING(2),
        /**
         * 量尺超时
         */
        MEASURING_TIMEOUT(3),
        /**
         * 待报价
         */
        WAIT_QUOTE(4),
        /**
         * 待收款
         */
        WAIT_RECEIPT(5),
        /**
         * 用户端待下单
         */
        WAIT_ORDER(6),
        /**
         * 待设计
         */
        WAIT_RESERVE_DESIGN(7),
        /**
         * 待蚁装管理人员审核及拆单给工厂
         */
        WAIT_ALLOT_FACTORY(8),
        /**
         * 待工厂生产
         */
        WAIT_PRODUCE(9),
        /**
         * 安装师傅预约客户
         * 待预约安装
         */
        WAIT_RESERVE_INSTALL(10),
        /**
         * 待上门安装
         */
        WAIT_HOME_INSTALL(11),
        /**
         * 待上门二次安装
         */
        WAIT_HOME_SECOND_INSTALL(13),
        /**
         * 安装超时
         */
        INSTALL_TIMEOUT(14),
        /**
         * 待运营商派发
         */
        WAIT_ALLOT_INSTALL(18),
        /**
         * 待安装师傅接单， 接单之后变成 10
         */
        WAIT_GRAB_INSTALL(19),
        /**
         * 工厂生产状态：已送货到仓库
         */
        ARRIVED_DEPOT(49),
        /**
         * 工厂生产状态：待出货
         */
        WAIT_SEND(48),

        /**
         * 工厂生产状态：生产中
         */
        PRODUCING(47),

        /**
         * 工厂生产状态：已排产
         */
        ARRANGE_PRODUCTION(46),
        /**
         * 工厂生产状态：工厂接单
         */
        IN_ORDER(44),
        /**
         * 待审核
         */
        WAIT_CHECK(53),
        /**
         * 待财务审核
         */
        WAIT_MANAGER_CHECK(54),
        /**
         * 待安装 (销售人员确认收货之后查询待安装订单)
         */
        WAIT_INSTALL(69),
        /**
         * 待售后处理
         */
        WAIT_AFTER_SALE(88),
        /**
         * 待收貨
         */
        WAIT_RECEIPT_GOODS(91),
        /**
         * 跟进超时
         */
        FOLLOW_TIMEOUT(101),
        /**
         * 待跟进
         */
        WAIT_FOLLOW(100),
        /**
         * 已安装
         */
        FINISHED(99);
        private int value;
        private SalesOrderStatus(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }



}
