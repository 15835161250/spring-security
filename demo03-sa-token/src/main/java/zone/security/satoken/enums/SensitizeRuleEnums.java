package zone.security.satoken.enums;

import cn.hutool.core.util.DesensitizedUtil;

import java.util.function.Function;

/**
 * @author hzd
 * @description 数据脱敏枚举
 */
public enum SensitizeRuleEnums {
    /**
     * 用户id脱敏
     */
    USER_ID(s -> String.valueOf(DesensitizedUtil.userId())),

    /**
     * 中文姓名脱敏
     */
    CHINESE_NAME(DesensitizedUtil::chineseName),

    /**
     * 身份证脱敏
     */
    ID_CARD(s -> DesensitizedUtil.idCardNum(s, 3, 4)),

    /**
     * 固定电话
     */
    FIXED_PHONE(DesensitizedUtil::fixedPhone),

    /**
     * 手机号脱敏
     */
    MOBILE_PHONE(DesensitizedUtil::mobilePhone),

    /**
     * 地址脱敏
     */
    ADDRESS(s -> DesensitizedUtil.address(s, 8)),

    /**
     * 电子邮箱脱敏
     */
    EMAIL(DesensitizedUtil::email),

    /**
     * 密码脱敏
     */
    PASSWORD(DesensitizedUtil::password),

    /**
     * 中国车牌脱敏
     */
    CAR_LICENSE(DesensitizedUtil::carLicense),

    /**
     * 银行卡脱敏
     */
    BANK_CARD(DesensitizedUtil::bankCard);

    /**
     * 可自行添加其他脱敏策略.....
     */
    private final Function<String, String> sensitize;

    public Function<String, String> sensitize() {
        return sensitize;
    }

    SensitizeRuleEnums(Function<String, String> sensitize) {
        this.sensitize = sensitize;
    }
}
