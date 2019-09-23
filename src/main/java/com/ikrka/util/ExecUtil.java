package com.ikrka.util;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ExecUtil {

    private ExecUtil() {
    }

    private final static class ExecUtilInstance {
        private final static ExecUtil execUtil = new ExecUtil();
    }

    public static ExecUtil getInstance() {
        return ExecUtilInstance.execUtil;
    }

    @SuppressWarnings("all")
    public static void exec(String command) throws Exception {
        BufferedInputStream bis = null;
        BufferedReader br = null;
        StringBuffer sb = null;
        try {
            Process process = Runtime.getRuntime().exec(command);
            bis = new BufferedInputStream(process.getInputStream());
            br = new BufferedReader(new InputStreamReader(bis));
            sb = new StringBuffer();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            process.waitFor();
            if (process.exitValue() != 0) {
                throw new RuntimeException(sb.toString());
            }
        } catch (IOException e) {
            throw new RuntimeException("IOException : { " + sb != null ? sb.toString() : e.getMessage() + " }", e);
        } catch (InterruptedException e) {
            throw new RuntimeException("InterruptedException : { " + sb != null ? sb.toString() : e.getMessage() + " }",
                    e);
        } finally {
            if (bis != null) {
                bis.close();
            }
            if (br != null) {
                br.close();
            }
        }
    }

}