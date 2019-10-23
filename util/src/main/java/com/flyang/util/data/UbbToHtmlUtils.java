package com.flyang.util.data;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author caoyangfei
 * @ClassName UbbToHtmlUtils
 * @date 2019/9/28
 * ------------- Description -------------
 * UbbToHtml
 */
public class UbbToHtmlUtils {
    // 将ubb标签语言转成html
    public static String convertUbbToHtml(String str) {
        str = convertAngleTag(str);
        str = convertCommentTag(str);
        str = convertColorTag(str);
        str = convertImgTag(str);
        str = convertFieldsTag(str);
        str = convertKeywordsTag(str);
        str = convertJavaTag(str);
        str = convertOtherTag(str);
        return str;
    }

    // 将[code=img]和[/code]标签转成html
    public static String convertImgTag(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        String regexp = "(\\[code=img\\])([^\\[]+)(\\[/code\\])";
        Pattern pattern = Pattern.compile(regexp);
        Matcher match = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result = match.find();
        while (result) {
            match.appendReplacement(sb, "<img src=\"file:///android_asset/"
                    + match.group(2) + "\" width='230dp'>");
            result = match.find();
        }
        match.appendTail(sb);
        return sb.toString();
    }

    // 将[code=java]和[/code]标签转成html
    public static String convertJavaTag(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        String regexp = "(\\[code=java\\])\n?([\\s\\S]+?)(\\[/code\\])";
        Pattern pattern = Pattern.compile(regexp);
        Matcher match = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result = match.find();
        while (result) {
            match.appendReplacement(
                    sb,
                    "<div style='border:solid 1px #bbb;background:#eee;padding:8; margin:10 0 10 0;'>"
                            + match.group(2) + "</div>");
            result = match.find();
        }
        match.appendTail(sb);
        return sb.toString();
    }

    // 将[color=blue]标签转成html
    public static String convertColorTag(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        String regexp = "(\\[color=blue\\])([^\\s]+)([\\s]+?)";
        Pattern pattern = Pattern.compile(regexp);
        Matcher match = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result = match.find();
        while (result) {
            match.appendReplacement(sb,
                    "<div style='color:blue;margin:10 0 10 0;font-weight:bolder;'>"
                            + match.group(2) + "</div>");
            result = match.find();
        }
        match.appendTail(sb);
        return sb.toString();
    }

    // 将#[Fields]标签转成html
    public static String convertFieldsTag(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        String regexp = "(#\\[Fields\\])([^#]+?)#";
        Pattern pattern = Pattern.compile(regexp);
        Matcher match = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result = match.find();
        while (result) {
            match.appendReplacement(sb,
                    "<span style='color:red;'>" + match.group(2) + "</span>");
            result = match.find();
        }
        match.appendTail(sb);
        return sb.toString();
    }

    // 将#[Keywords]标签转成html
    public static String convertKeywordsTag(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        String regexp = "(#\\[Keywords\\])(\\w+[\\s]?)";
        Pattern pattern = Pattern.compile(regexp);
        Matcher match = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result = match.find();
        while (result) {
            match.appendReplacement(sb,
                    "<span style='color:#900;'>" + match.group(2) + "</span>");
            result = match.find();
        }
        match.appendTail(sb);
        return sb.toString();
    }

    // 将#[Comments]标签转成html
    public static String convertCommentTag(String str) {
        if (str == null || str.length() == 0) {
            return str;
        }
        String regexp = "(#\\[Comments\\]//)([^#]+)";
        Pattern pattern = Pattern.compile(regexp);
        Matcher match = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        boolean result = match.find();
        while (result) {
            match.appendReplacement(sb, "<div>//" + match.group(2) + "</div>");
            result = match.find();
        }
        match.appendTail(sb);
        return sb.toString();
    }

    // 将\n换行标签及其他标签转成html
    public static String convertOtherTag(String str) {
        str = str.replaceAll("#\\}", "<br/>}");
        str = str.replaceAll("#", "");
        str = str.replaceAll("\n", "<br/>");
        return str;
    }

    // 将左尖括号和右尖括号转成实体符号&lt;和&gt;。在html标签语言中，“<”和“>”会被认为是html标记。因此要先将其替换。
    public static String convertAngleTag(String str) {
        str = str.replaceAll("<", "&lt;");
        str = str.replaceAll(">", "&gt;");
        return str;
    }

}
