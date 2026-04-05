package com.sentaroh.android.SMBSync2;

import java.io.PrintWriter;
import java.io.StringWriter;

public class MiscUtil {
    public static String getStackTraceString(Throwable e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    public static String convertRegExp(String wildcard) {
        if (wildcard == null) return null;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < wildcard.length(); i++) {
            char c = wildcard.charAt(i);
            switch (c) {
                case '*':
                    sb.append(".*");
                    break;
                case '?':
                    sb.append(".");
                    break;
                case '.':
                case '\\':
                case '[':
                case ']':
                case '^':
                case '$':
                case '+':
                case '(':
                case ')':
                case '{':
                case '}':
                case '|':
                    sb.append("\\").append(c);
                    break;
                default:
                    sb.append(c);
            }
        }
        return sb.toString();
    }
}
