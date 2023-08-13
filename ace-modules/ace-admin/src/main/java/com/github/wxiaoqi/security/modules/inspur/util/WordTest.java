/*
package com.github.wxiaoqi.security.modules.inspur.controller;

import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import org.junit.Test;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@Controller
@RequestMapping("/word")
public class WordTest {

//    @Test
//    public void test() throws IOException, XDocReportException {
//        generateWord();
//    }

    public void generateWord() throws IOException, XDocReportException {
        InputStream ins = this.getClass().getResourceAsStream("/模板.docx");
        IXDocReport report = XDocReportRegistry.getRegistry().loadReport(ins, TemplateEngineKind.Velocity);
        IContext context = report.createContext();
        FieldsMetadata fm = report.createFieldsMetadata();
        fm.addFieldAsImage("report");
        fm.addFieldAsImage("record");
        fm.addFieldAsImage("autellImage");
        fm.addFieldAsImage("scene");
        fm.addFieldAsImage("receipt");
        fm.addFieldAsImage("house");
        fm.addFieldAsImage("front");
        fm.addFieldAsImage("back");

        fm.addFieldAsImage("member");
        fm.addFieldAsImage("poor");
        fm.addFieldAsImage("fieldwn");
        fm.addFieldAsImage("fieldwn02");
        fm.addFieldAsImage("wntellImage");
        fm.addFieldAsImage("scenewn");
        fm.addFieldAsImage("receiptwn");
        fm.addFieldAsImage("hold");
        fm.addFieldAsImage("protocol");
        fm.addFieldAsImage("contract");
        fm.addFieldAsImage("promise");
        fm.addFieldAsImage("liability");
        fm.addFieldAsImage("inform");
        fm.addFieldAsImage("checking");
        fm.addFieldAsImage("found");
        fm.addFieldAsImage("part");
        fm.addFieldAsImage("capped");
        fm.addFieldAsImage("acceptance");
        fm.addFieldAsImage("urge");
        fm.addFieldAsImage("cardBank");
        fm.addFieldAsImage("transfer");
        fm.addFieldAsImage("filing");

        context.put("number", "111");
        context.put("town", "111");
        context.put("name", "111");
        context.put("address", "111");
        context.put("staff", "111");
        context.put("cardId", "111");

        context.put("report", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));
        context.put("record", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));
        context.put("autellImage", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));
        context.put("scene", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));

        context.put("receipt", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));
        context.put("house", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));
        context.put("front", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));
        context.put("back", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));

        context.put("member", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));
        context.put("poor", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));
        context.put("fieldwn", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));
        context.put("fieldwn02", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));

        context.put("wntellImage", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));
        context.put("scenewn", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));
        context.put("receiptwn", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));
        context.put("hold", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));

        context.put("protocol", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));
        context.put("contract", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));
        context.put("promise", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));
        context.put("liability", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));

        context.put("inform", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));
        context.put("checking", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));
        context.put("found", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));
        context.put("part", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));

        context.put("capped", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));
        context.put("acceptance", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));
        context.put("urge", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));
        context.put("cardBank", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));
        context.put("filing", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));
        context.put("transfer", getImg("http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png"));

        FileOutputStream out = new FileOutputStream(new File("/Users/gongguoqing/Downloads/download/模板.docx"));
        report.process(context, out);
        out.close();
    }

    public InputStream getImg(String urls) throws IOException {
        URL url = new URL(urls);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5 * 1000);
        InputStream inStream = conn.getInputStream();
        return inStream;
    }

    public static void main(String[] args) {
        String p = "http://system-town.oss-cn-zhangjiakou.aliyuncs.com/images/2022/08/07/16598807881636351.png,";
        String[] split = p.split(",");
        System.out.println(split[0]);
        if (split.length == 2) {
            System.out.println(split[1]);
        }
    }
}
*/
