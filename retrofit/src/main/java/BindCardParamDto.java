import java.io.Serializable;

/**
 * create by 陶江航 at 2017/11/7 17:41
 *
 * @version 1.0
 * @email taojianghang@xinzhentech.com
 * @description 绑定银行卡客户端传递到服务端的参数dto
 */
public class BindCardParamDto implements Serializable{

    private String bankCard;

    private String phone;

    private String verifyCode;

    public BindCardParamDto() {
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Override
    public String toString() {
        return "BindCardParamDto{" +
                "bankCard='" + bankCard + '\'' +
                ", phone='" + phone + '\'' +
                ", verifyCode='" + verifyCode + '\'' +
                '}';
    }
}
