package com.lk.syxl.customview.http.utils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.ForegroundColorSpan;
import android.text.style.URLSpan;

import com.rkhd.service.sdk.constants.Constants;

import java.io.UnsupportedEncodingException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: jacky zhang
 * Date: 12-11-2
 * Time: 下午5:54
 */
public class StringUtil {
    public static boolean isEmpty(String s) {
        if (s == null || s.trim().equals("") || s.trim().equals("null")) {
            return true;
        }
        return false;
    }

    public static boolean isNotEmpty(String str) {
        return (str != null) && (str.length() > 0);
    }

    public static SpannableStringBuilder setSpannableString(String text, String target, int colorRes) {
        SpannableStringBuilder spannable = new SpannableStringBuilder(text);
        try {
            if (StringUtil.isNotEmpty(text) && StringUtil.isNotEmpty(target) && text.contains(target)) {
                CharacterStyle span = new ForegroundColorSpan(colorRes);
                int start = text.indexOf(target);
                int end = start + target.length();
                spannable.setSpan(span, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                return spannable;
            }
            return spannable;
        } catch (Exception e) {
            e.printStackTrace();
            return spannable;
        }
    }
    /**判断url*/
    public static boolean isTopURL(String str){
        //转换为小写
        str = str.toLowerCase();
        String domainRules = "com.cn|net.cn|org.cn|gov.cn|com.hk|公司|中国|网络|com|net|org|int|edu|gov|mil|arpa|Asia|biz|info|name|pro|coop|aero|museum|ac|ad|ae|af|ag|ai|al|am|an|ao|aq|ar|as|at|au|aw|az|ba|bb|bd|be|bf|bg|bh|bi|bj|bm|bn|bo|br|bs|bt|bv|bw|by|bz|ca|cc|cf|cg|ch|ci|ck|cl|cm|cn|co|cq|cr|cu|cv|cx|cy|cz|de|dj|dk|dm|do|dz|ec|ee|eg|eh|es|et|ev|fi|fj|fk|fm|fo|fr|ga|gb|gd|ge|gf|gh|gi|gl|gm|gn|gp|gr|gt|gu|gw|gy|hk|hm|hn|hr|ht|hu|id|ie|il|in|io|iq|ir|is|it|jm|jo|jp|ke|kg|kh|ki|km|kn|kp|kr|kw|ky|kz|la|lb|lc|li|lk|lr|ls|lt|lu|lv|ly|ma|mc|md|me|mg|mh|ml|mm|mn|mo|mp|mq|mr|ms|mt|mv|mw|mx|my|mz|na|nc|ne|nf|ng|ni|nl|no|np|nr|nt|nu|nz|om|pa|pe|pf|pg|ph|pk|pl|pm|pn|pr|pt|pw|py|qa|re|ro|ru|rw|sa|sb|sc|sd|se|sg|sh|si|sj|sk|sl|sm|sn|so|sr|st|su|sy|sz|tc|td|tf|tg|th|tj|tk|tm|tn|to|tp|tr|tt|tv|tw|tz|ua|ug|uk|us|uy|va|vc|ve|vg|vn|vu|wf|ws|ye|yu|za|zm|zr|zw";
        String regex = "^((https|http|ftp|rtsp|mms)?://)"
                + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" //ftp的user@
                + "(([0-9]{1,3}\\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
                + "|" // 允许IP和DOMAIN（域名）
                + "(([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]+\\.)?" // 域名- www.
                + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\\." // 二级域名
                + "("+domainRules+"))" // first level domain- .com or .museum
                + "(:[0-9]{1,4})?" // 端口- :80
                + "((/?)|" // a slash isn't required if there is no file name
                + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
        Pattern pattern = Pattern.compile(regex);
        Matcher isUrl = pattern.matcher(str);
        return isUrl.matches();
    }

    public static byte[] getBytesUTF8(String str) {
        try {
            return str.getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
    }

    /**
     * 设置超链接文字与颜色
     *
     * @param context
     * @param spannableContent
     * @param content
     * @return
     */
    public static SpannableString getSpannableContent(Context context, SpannableString spannableContent, String content, int color) {

        if (TextUtils.isEmpty(content)) {
            return new SpannableString("");
        }


        if (spannableContent == null) {
            spannableContent = new SpannableString(content);
        }
        //匹配链接
        Pattern pattern = Pattern.compile(Constants.PATTERN_URL, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(content);
        while (matcher.find()) {
            String s = matcher.group(0);
            String h = s;
            if (s.startsWith("www.")) {
                h = "https://" + s;
            }
            URLSpan spannable = new SpanWithLink(s, h, context, color, true);
            spannableContent.setSpan(spannable, matcher.start(0), matcher.end(0), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        //匹配邮件
        pattern = Pattern.compile("[A-Z0-9a-z\\._%+-]+@([A-Za-z0-9-]+\\.)+[A-Za-z]{2,4}", Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(content);
        while (matcher.find()) {
            String s = matcher.group(0);
//            String h = s;
//            if (s.startsWith("www.")) {
//                h = "https://" + s;
//            }
            SpanWithEmail spannable = new SpanWithEmail(s, s, context, color, true);
            spannableContent.setSpan(spannable, matcher.start(0), matcher.end(0), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }

        //匹配数字
        pattern = Pattern.compile(Constants.PHONE_NUMBER, Pattern.CASE_INSENSITIVE);
        matcher = pattern.matcher(content);
        while (matcher.find()) {
            String s = matcher.group(0);
//            String h = s;
//            if (s.startsWith("www.")) {
//                h = "https://" + s;
//            }
            SpanWithNumber spannable = new SpanWithNumber(s, s, context, color, true);
            spannableContent.setSpan(spannable, matcher.start(0), matcher.end(0), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return spannableContent;
    }

    public static String getString(int id) {
        String string = Utils.getContext().getString(id);
        return string;
    }
}
