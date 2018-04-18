package mercari.com.example.lily.common.helper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * CheckHelper
 * <p>
 * history:create by tll
 **/
public class CheckHelper {

    private static Pattern patternNum = Pattern.compile("([0-9]+)");

    /**
     * 数字チェック
     *
     * @param input
     * @return
     */
    public static boolean isNumber(String input) {
        Matcher m = patternNum.matcher(input);
        m.reset();
        return m.find();
    }

    /**
     * 文字列空白チェク
     *
     * @param obj
     * @return
     */
    public static boolean isStringEmpty(Object obj) {
        if (obj == null) {
            return true;
        }

        if (obj.toString().trim().length() == 0) {
            return true;
        }

        return false;
    }

    /**
     * 文字列比較
     *
     * @param base ,compare
     * @return
     */
    public static boolean isStringEquals(String base, String compare) {
        //文字列空白、false
        if (isStringEmpty(base) || isStringEmpty(compare)) {
            return false;
        }
        if (base.equals(compare)) {
            return true;
        }
        return false;
    }
}
