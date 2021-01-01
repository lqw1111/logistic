package com.logistic.project.service.Impl;

import com.logistic.project.entity.Payment;
import com.logistic.project.entity.UserInfo;
import com.logistic.project.enumeration.PaymentType;
import com.logistic.project.service.MailTemplateService;
import org.springframework.stereotype.Service;

@Service
public class MailTemplateServiceImpl implements MailTemplateService {

    @Override
    public String contructActiveEmail(UserInfo userInfo, String activeUrl) {
        String activeHtml = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/0.5999/xhtml\">\n" +
                "<head>\n" +
                "\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=0.5.0\"/>\n" +
                "\t<title>Customer Register email</title>\n" +
                "  <style type=\"text/css\">\n" +
                "    body{width:100% !important; -webkit-text-size-adjust:100%; -ms-text-size-adjust:100%; margin:0; padding:0;}\n" +
                "    img {outline:none; text-decoration:none; -ms-interpolation-mode: bicubic;}\n" +
                "\t\ta img {border:none;}\n" +
                "\t\t.image_fix {display:block;}\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body style=\"margin: 0; padding: 0;\">\n" +
                "  <table\n" +
                "    align=\"center\"\n" +
                "    cellpadding=\"0\"\n" +
                "    cellspacing=\"0\"\n" +
                "    width=\"100%\"\n" +
                "    height=\"400\"\n" +
                "  >\n" +
                "    <thead\n" +
                "    style=\"background-color:rgba(198,198,198,0.3)\">\n" +
                "      <tr height=\"100\">\n" +
                "        <th\n" +
                "        height=\"50\"\n" +
                "        align=\"center\"\n" +
                "        >\n" +
                "          <p style=\"\n" +
                "            font-size: 35px; font-family: Arial, Helvetica, sans-serif;\n" +
                "            color:#424141;\n" +
                "            margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0; margin-right: 0;\">\n" +
                "            一闪速递\n" +
                "          </p>\n" +
                "        </th>\n" +
                "      </tr>\n" +
                "    </thead>\n" +
                "    <tbody\n" +
                "    style=\"background-color:rgba(198,198,198,0.3);\"\n" +
                "    >\n" +
                "      <tr>\n" +
                "        <td height=\"300\">\n" +
                "          <table\n" +
                "            cellpadding=\"0\"\n" +
                "            cellspacing=\"0\"\n" +
                "            width=\"600\"\n" +
                "            style=\"border-collapse: collapse;\"\n" +
                "            align=\"center\"\n" +
                "          >\n" +
                "          <tbody\n" +
                "          style=\"background-color: rgb(255,255,255) !important;\">\n" +
                "            <tr>\n" +
                "              <td width=\"500\" height=\"50\"  style = \"padding-left:20px; font-size: 20px; font-family: Arial, Helvetica, sans-serif;\" >\n" +
                "                 <p style=\"color:#424141;\">亲爱的 " + userInfo.getUsername() + "：</p>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "\n" +
                "              <tr>\n" +
                "                <td width=\"500\" style = \"padding-left:20px;padding-right:20px;\" >\n" +
                "                  <p\n" +
                "                  style=\"\n" +
                "                  font-size: 15px; font-family: Arial, Helvetica, sans-serif;\n" +
                "                  color:#424141;\n" +
                "                  margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0; margin-right: 0;\">\n" +
                "                    欢迎您注册使用一闪转运服务\n" +
                "                  </p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"500\" style = \"padding-left:20px;padding-right:20px;\">\n" +
                "                  <p\n" +
                "                  style=\"\n" +
                "                  font-size: 15px; font-family: Arial, Helvetica, sans-serif;\n" +
                "                  color:#424141;\n" +
                "                  margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0; margin-right: 0;\">\n" +
                "                    请点击下面按钮激活您的账户\n" +
                "                  </p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"500\" style = \"padding-left:20px;padding-right:20px;\">\n" +
                "                  <button style=\"\n" +
                "                  font-size: 15px; font-family: Arial, Helvetica, sans-serif;\n" +
                "                  color:#424141;\n" +
                "                  margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0; margin-right: 0;\"><a href=\"" + activeUrl + "\">激活</a></button>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"500\" style = \"padding-left:20px;padding-right:20px;\">\n" +
                "                   <a href=\"" + activeUrl + "\">" + activeUrl + "</a>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"500\" style = \"padding-left:20px;padding-right:20px;\" align=\"end\">\n" +
                "                  <p\n" +
                "                  style=\"\n" +
                "                  font-size: 15px; font-family: Arial, Helvetica, sans-serif;\n" +
                "                  color:#424141;\n" +
                "                  margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0; margin-right: 0;\">\n" +
                "                    谢谢您的支持\n" +
                "                  </p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"500\" style = \"padding-left:20px;padding-right:20px;\" align=\"end\">\n" +
                "                  <p  style=\"\n" +
                "                  font-size: 18px; font-family: Arial, Helvetica, sans-serif;\n" +
                "                  color:rgb(255, 145, 0);\n" +
                "                  margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0; margin-right: 0;\">\n" +
                "                    ⚡ 一闪团队\n" +
                "                  </p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </table>\n" +
                "          </tbody>\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </tbody>\n" +
                "\t</table>\n" +
                "\t<!-- End of wrapper table -->\n" +
                "</body>\n" +
                "</html>";
        return activeHtml;
    }

    @Override
    public String constructContent(UserInfo userInfo, String newPassword) {
        String resetPasswordHtml = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/0.5999/xhtml\">\n" +
                "<head>\n" +
                "\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=0.5.0\"/>\n" +
                "\t<title>Customer Register email</title>\n" +
                "  <style type=\"text/css\">\n" +
                "    body{width:100% !important; -webkit-text-size-adjust:100%; -ms-text-size-adjust:100%; margin:0; padding:0;}\n" +
                "    img {outline:none; text-decoration:none; -ms-interpolation-mode: bicubic;}\n" +
                "\t\ta img {border:none;}\n" +
                "\t\t.image_fix {display:block;}\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body style=\"margin: 0; padding: 0;\">\n" +
                "  <table \n" +
                "    align=\"center\" \n" +
                "    cellpadding=\"0\" \n" +
                "    cellspacing=\"0\" \n" +
                "    width=\"100%\"\n" +
                "    height=\"450\"\n" +
                "  >\n" +
                "    <thead \n" +
                "    style=\"background-color:rgba(198,198,198,0.3)\">\n" +
                "      <tr height=\"100\">\n" +
                "        <th  \n" +
                "        height=\"50\"\n" +
                "        align=\"center\"\n" +
                "        >\n" +
                "          <p style=\"\n" +
                "            font-size: 35px; font-family: Arial, Helvetica, sans-serif;\n" +
                "            color:#424141;\n" +
                "            margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0; margin-right: 0;\">\n" +
                "            一闪速递\n" +
                "          </p>\n" +
                "        </th>\n" +
                "      </tr>\n" +
                "    </thead>\n" +
                "    <tbody \n" +
                "    style=\"background-color:rgba(198,198,198,0.3);\"\n" +
                "    >\n" +
                "      <tr>\n" +
                "        <td>\n" +
                "          <table \n" +
                "            cellpadding=\"0\" \n" +
                "            cellspacing=\"0\" \n" +
                "            width=\"600\" \n" +
                "            style=\"border-collapse: collapse;\"\n" +
                "            align=\"center\" \n" +
                "          >\n" +
                "          <tbody style=\"background-color: rgb(255,255,255) !important;\">\n" +
                "            <tr>\n" +
                "              <td width=\"500\" height=\"50\"  style = \"padding-left:20px; font-size: 20px; font-family: Arial, Helvetica, sans-serif;\" >\n" +
                "                 <p style=\"color:#424141;\">亲爱的 " + userInfo.getUsername() + "：</p>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "              <tr>\n" +
                "                <td width=\"500\" style = \"padding-left:20px;padding-right:20px;\" >\n" +
                "                  <p \n" +
                "                  style=\"\n" +
                "                  font-size: 15px; font-family: Arial, Helvetica, sans-serif;\n" +
                "                  color:#424141;\n" +
                "                  margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0; margin-right: 0;\">\n" +
                "                    您忘记了密码并申请了密码找回\n" +
                "                  </p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"500\" style = \"padding-left:20px;padding-right:20px;\">\n" +
                "                  <p \n" +
                "                  style=\"\n" +
                "                  font-size: 15px; font-family: Arial, Helvetica, sans-serif;\n" +
                "                  color:#424141;\n" +
                "                  margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0; margin-right: 0;\">\n" +
                "                    以下是您的重置密码：\n" +
                "                  </p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"500\" style = \"padding-left:20px;padding-right:20px;\">\n" +
                "                  <p \n" +
                "                  style=\"\n" +
                "                  text-align:center;\n" +
                "                  font-size: 22px; font-family: Arial, Helvetica, sans-serif;\n" +
                "                  color:#424141;\n" +
                "                  margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0; margin-right: 0;\">\n" +
                "                    " + newPassword + "\n" +
                "                  </p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "              <td width=\"500\" style = \"padding-left:20px;padding-right:20px;\">\n" +
                "               \n" +
                "              </td>\n" +
                "            </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"500\" style = \"padding-left:20px;padding-right:20px;\">\n" +
                "                  <p \n" +
                "                  style=\"\n" +
                "                  font-size: 15px; font-family: Arial, Helvetica, sans-serif;\n" +
                "                  color:#424141;\n" +
                "                  margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0; margin-right: 0;\">\n" +
                "                  您可以重新登陆后在主页的“个人信息”中重置您的密码\n" +
                "                  </p>\n" +
                "              </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"500\" style = \"padding-left:20px;padding-right:20px;\">\n" +
                "                 \n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"500\" style = \"padding-left:20px;padding-right:20px;\" align=\"end\">\n" +
                "                  <p  \n" +
                "                  style=\"\n" +
                "                  font-size: 15px; font-family: Arial, Helvetica, sans-serif;\n" +
                "                  color:#424141;\n" +
                "                  margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0; margin-right: 0;\">\n" +
                "                    谢谢您的支持\n" +
                "                  </p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"500\" style = \"padding-left:20px;padding-right:20px;\" align=\"end\">\n" +
                "                  <p  style=\"\n" +
                "                  font-size: 18px; font-family: Arial, Helvetica, sans-serif;\n" +
                "                  color:rgb(255, 145, 0);\n" +
                "                  margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0; margin-right: 0;\">\n" +
                "                    ⚡ 一闪团队\n" +
                "                  </p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </table>\n" +
                "          </tbody>\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </tbody>\n" +
                "\t</table>\n" +
                "\t<!-- End of wrapper table -->\n" +
                "</body>\n" +
                "</html>";
        return resetPasswordHtml;
    }

    @Override
    public String paymentSuccessEmail(UserInfo userInfo, Payment payment) {
        String promotion = payment.getPromotionCode() == null ? "N/A" : payment.getPromotionCode();
        String paymentType = getPaymentType(payment.getPaymentTypeId());
        String paymentHTML = "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/0.5999/xhtml\">\n" +
                "<head>\n" +
                "\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\n" +
                "\t<meta name=\"viewport\" content=\"width=device-width, initial-scale=0.5.0\"/>\n" +
                "\t<title>Customer Register email</title>\n" +
                "  <style type=\"text/css\">\n" +
                "    body{width:100% !important; -webkit-text-size-adjust:100%; -ms-text-size-adjust:100%; margin:0; padding:0;}\n" +
                "    img {outline:none; text-decoration:none; -ms-interpolation-mode: bicubic;}\n" +
                "\t\ta img {border:none;}\n" +
                "\t\t.image_fix {display:block;}\n" +
                "  </style>\n" +
                "</head>\n" +
                "<body style=\"margin: 0; padding: 0;\">\n" +
                "  <table \n" +
                "    align=\"center\" \n" +
                "    cellpadding=\"0\" \n" +
                "    cellspacing=\"0\" \n" +
                "    width=\"100%\"\n" +
                "    height=\"550\"\n" +
                "  >\n" +
                "    <thead \n" +
                "    style=\"background-color:rgba(198,198,198,0.3)\">\n" +
                "      <tr height=\"100\">\n" +
                "        <th  \n" +
                "        height=\"50\"\n" +
                "        align=\"center\"\n" +
                "        >\n" +
                "          <p style=\"\n" +
                "            font-size: 35px; font-family: Arial, Helvetica, sans-serif;\n" +
                "            color:#424141;\n" +
                "            margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0; margin-right: 0;\">\n" +
                "            一闪速递\n" +
                "          </p>\n" +
                "        </th>\n" +
                "      </tr>\n" +
                "    </thead>\n" +
                "    <tbody \n" +
                "    style=\"background-color:rgba(198,198,198,0.3);\"\n" +
                "    >\n" +
                "      <tr>\n" +
                "        <td height=\"450\">\n" +
                "          <table \n" +
                "            cellpadding=\"0\" \n" +
                "            cellspacing=\"0\" \n" +
                "            width=\"600\" \n" +
                "            style=\"border-collapse: collapse;\"\n" +
                "            align=\"center\" \n" +
                "          >\n" +
                "          <tbody\n" +
                "          style=\"background-color: rgb(255,255,255) !important;\">\n" +
                "            <tr>\n" +
                "              <td width=\"500\" height=\"50\"  style = \"padding-left:20px; font-size: 20px; font-family: Arial, Helvetica, sans-serif;\" >\n" +
                "                 <p style=\"color:#424141;\">亲爱的 " + userInfo.getUsername() + "：</p>\n" +
                "              </td>\n" +
                "            </tr>\n" +
                "          \n" +
                "              <tr>\n" +
                "                <td width=\"500\" style = \"padding-left:20px;padding-right:20px;\" >\n" +
                "                  <p \n" +
                "                  style=\"\n" +
                "                  font-size: 15px; font-family: Arial, Helvetica, sans-serif;\n" +
                "                  color:#424141;\n" +
                "                  margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0; margin-right: 0;\">\n" +
                "                    您的订单（" + payment.getOrderId() + "）已经支付\n" +
                "                  </p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"500\" style = \"padding-left:20px;padding-right:20px;\">\n" +
                "                  <p \n" +
                "                  style=\"\n" +
                "                  font-size: 15px; font-family: Arial, Helvetica, sans-serif;\n" +
                "                  color:#424141;\n" +
                "                  margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0; margin-right: 0;\">\n" +
                "                    订单的价格为 ：¥ " + payment.getPrice() + "\n" +
                "                  </p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"500\" style = \"padding-left:20px;padding-right:20px;\">\n" +
                "                  <p \n" +
                "                  style=\"\n" +
                "                  font-size: 15px; font-family: Arial, Helvetica, sans-serif;\n" +
                "                  color:#424141;\n" +
                "                  margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0; margin-right: 0;\">\n" +
                "                    优惠券：" + promotion + "\n" +
                "                  </p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"500\" style = \"padding-left:20px;padding-right:20px;\">\n" +
                "                  <p \n" +
                "                  style=\"\n" +
                "                  font-size: 15px; font-family: Arial, Helvetica, sans-serif;\n" +
                "                  color:#424141;\n" +
                "                  margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0; margin-right: 0;\">\n" +
                "                  价格为 ：¥ " + payment.getPaid() + "\n" +
                "                  </p>\n" +
                "              </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"500\" style = \"padding-left:20px;padding-right:20px;\">\n" +
                "                  <p\n" +
                "                  style=\"\n" +
                "                  font-size: 15px; font-family: Arial, Helvetica, sans-serif;\n" +
                "                  color:#424141;\n" +
                "                  margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0; margin-right: 0;\">\n" +
                "                    您预报支付的金额为 ：¥ " + payment.getActualPaid() + "\n" +
                "                  </p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"500\" style = \"padding-left:20px;padding-right:20px;\">\n" +
                "                  <p\n" +
                "                  style=\"\n" +
                "                  font-size: 15px; font-family: Arial, Helvetica, sans-serif;\n" +
                "                  color:#424141;\n" +
                "                  margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0; margin-right: 0;\">\n" +
                "                    支付方式为 ：" + paymentType + "\n" +
                "                  </p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"500\" style = \"padding-left:20px;padding-right:20px;\">\n" +
                "                  <p\n" +
                "                  style=\"\n" +
                "                  font-size: 15px; font-family: Arial, Helvetica, sans-serif;\n" +
                "                  color:#424141;\n" +
                "                  margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0; margin-right: 0;\">\n" +
                "                  已经确认您的支付，您的包裹即将出发，如果有任何问题，请及时和客服联系\n" +
                "                  </p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"500\" style = \"padding-left:20px;padding-right:20px;\" align=\"end\">\n" +
                "                  <p  \n" +
                "                  style=\"\n" +
                "                  font-size: 15px; font-family: Arial, Helvetica, sans-serif;\n" +
                "                  color:#424141;\n" +
                "                  margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0; margin-right: 0;\">\n" +
                "                    谢谢您的支持\n" +
                "                  </p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "              <tr>\n" +
                "                <td width=\"500\" style = \"padding-left:20px;padding-right:20px;\" align=\"end\">\n" +
                "                  <p  style=\"\n" +
                "                  font-size: 18px; font-family: Arial, Helvetica, sans-serif;\n" +
                "                  color:rgb(255, 145, 0);\n" +
                "                  margin-top: 0.5em; margin-bottom: 0.5em; margin-left: 0; margin-right: 0;\">\n" +
                "                    ⚡ 一闪团队\n" +
                "                  </p>\n" +
                "                </td>\n" +
                "              </tr>\n" +
                "            </table>\n" +
                "          </tbody>\n" +
                "        </td>\n" +
                "      </tr>\n" +
                "    </tbody>\n" +
                "\t</table>\n" +
                "\t<!-- End of wrapper table -->\n" +
                "</body>\n" +
                "</html>\n" +
                "\n";
        return paymentHTML;
    }

    private String getPaymentType(Integer paymentTypeId) {
        if (paymentTypeId == null) return "N/A";
        if (PaymentType.WECHAT.getId().equals(paymentTypeId)) {
            return PaymentType.WECHAT.getName();
        } else if (PaymentType.ALIPAY.getId().equals(paymentTypeId)) {
            return PaymentType.ALIPAY.getName();
        } else if (PaymentType.INTERACT_ETRANSFER.getId().equals(paymentTypeId)) {
            return PaymentType.INTERACT_ETRANSFER.getName();
        } else {
            return "N/A";
        }
    }


}
