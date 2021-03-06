package com.ruoyi.common.constant;

/**
 * 通用常量信息
 * 
 * @author ruoyi
 */
public class Constants
{
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 自动去除表前缀
     */
    public static String AUTO_REOMVE_PRE = "true";

    /**
     * 当前记录起始索引
     */
    public static String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static String IS_ASC = "isAsc";


    public static final String STATUS_ACTIVE = "0";
    public static final String STATUS_REMOVED = "1";

    public static final String PERSON_ACTIVE = "0";
    public static final String PERSON_LOCK = "1";
    public static final String PERSON_PREP = "2";
    public static final String PERSON_CLOSE = "3";

    public static final String VISIBLE_FALSE = "0";
    public static final String VISIBLE_TRUE = "1";

    public static final String ACCOUNT_ACTIVE_1 = "1";  //系统收入账户
    public static final long ACCOUNT_ACTIVE_1_ID = 1l;  //系统收入账户
    public static final String ACCOUNT_ACTIVE_2 = "2";  //系统支出账户
    public static final long ACCOUNT_ACTIVE_2_ID = 2l;  //系统支出账户
    public static final String ACCOUNT_ACTIVE_3 = "3";  // 普通商户

    public static final String ORDER_NORMAL = "0"; //正常
    public static final String ORDER_FINISH = "1"; //完成
    public static final String ORDER_CANCEL = "2"; //取消
    
    public static final String IDENTITY_TYPE= "system"; //身份类型：系统
    
    public static final String BANK_UNCHECK = "0";
    public static final String BANK_CHECKED = "1";
    public static final String BANK_CHECK_fAIL = "2";
    public static final String BANK_CHECK_DETAIL = "3";

    public static final String TX_CODE_CASH_RECHARGE = "1000";//现金充值
    public static final String TX_CODE_IMPORT_RECHARGE = "1001";//导入充值
    public static final String TX_CODE_BANK_BATCH_RECHARGE = "1002";//银行批量
    public static final String TX_CODE_BANK_RECHARGE = "1003";//银行单笔
    public static final String TX_CODE_DEPOSIT_INCOME = "1004";//开户押金
        public static final String TX_CODE_SINGLE_COST = "1005";//现金扣款

    public static final String TX_CODE_BUY_COST = "2000";//购买商品
    public static final String TX_CODE_IMPORT_COST = "2001";//导入消费

    public static final String TX_CODE_GOOD_REFUND = "3000";//商品退款
    public static final String TX_CODE_CLOSE_ACCOUNT = "3001";//销户退款

    public static final String TX_CODE_MERCHANT_SETTLE = "4000";//商户结算


    public static final String BANK_OPEN_CODE = "CMLT40";//商户结算
    public static final String BANK_QUERY_CODE = "CQRD01";//商户结算


}
