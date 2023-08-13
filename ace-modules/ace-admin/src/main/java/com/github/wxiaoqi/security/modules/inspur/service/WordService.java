package com.github.wxiaoqi.security.modules.inspur.service;

import com.github.wxiaoqi.security.common.exception.auth.UserInvalidException;
import com.github.wxiaoqi.security.common.msg.ObjectRestResponse;

import com.github.wxiaoqi.security.configuration.Config;
import com.github.wxiaoqi.security.modules.inspur.util.DateUtil;
import com.github.wxiaoqi.security.modules.inspur.vo.ArchivesVO;
import feign.template.Template;
import fr.opensagres.xdocreport.core.XDocReportException;
import fr.opensagres.xdocreport.document.IXDocReport;
import fr.opensagres.xdocreport.document.registry.XDocReportRegistry;
import fr.opensagres.xdocreport.template.IContext;
import fr.opensagres.xdocreport.template.TemplateEngineKind;
import fr.opensagres.xdocreport.template.formatter.FieldsMetadata;
import org.apache.poi.util.IOUtils;
import org.junit.Test;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@Service
public class WordService {

    public String word(ArchivesVO archivesVO, String docName) {
        OutputStream out = null;
        try {
            InputStream ins = this.getClass().getResourceAsStream("/模板.docx");
            IXDocReport report = XDocReportRegistry.getRegistry().loadReport(ins, TemplateEngineKind.Velocity);
            IContext context = report.createContext();
            FieldsMetadata fm = report.createFieldsMetadata();
            fm.addFieldAsImage("front");
            fm.addFieldAsImage("back");
            fm.addFieldAsImage("houseImage");
            fm.addFieldAsImage("houseImage02");
            fm.addFieldAsImage("houseImage03");
            fm.addFieldAsImage("house");
            fm.addFieldAsImage("report");
            fm.addFieldAsImage("member");
            fm.addFieldAsImage("poor");
            fm.addFieldAsImage("promise");
            fm.addFieldAsImage("promise02");
            fm.addFieldAsImage("cardBank");
            fm.addFieldAsImage("autellImage");
            fm.addFieldAsImage("record");
            fm.addFieldAsImage("scene");
            fm.addFieldAsImage("receipt");
            fm.addFieldAsImage("liability");
            fm.addFieldAsImage("liability02");
            fm.addFieldAsImage("wntellImage");
            fm.addFieldAsImage("fieldwn");
            fm.addFieldAsImage("fieldwn02");
            fm.addFieldAsImage("scenewn");
            fm.addFieldAsImage("receiptwn");
            fm.addFieldAsImage("hold");
            fm.addFieldAsImage("zjtellImage");
            fm.addFieldAsImage("protocol");
            fm.addFieldAsImage("protocol02");
            fm.addFieldAsImage("contract");
            fm.addFieldAsImage("contract02");
            fm.addFieldAsImage("inform");
            fm.addFieldAsImage("found");
            fm.addFieldAsImage("part");
            fm.addFieldAsImage("capped");
            fm.addFieldAsImage("checking");
            fm.addFieldAsImage("houseImage04");
            fm.addFieldAsImage("part02");
            fm.addFieldAsImage("acceptance");
            fm.addFieldAsImage("urge");
            fm.addFieldAsImage("filing");
            fm.addFieldAsImage("transfer");

            context.put("number", archivesVO.getUserId());
            context.put("town", archivesVO.getTown());
            context.put("town02", archivesVO.getTown());
            context.put("town03", archivesVO.getTown());
            context.put("town04", archivesVO.getTown());
            context.put("name", archivesVO.getName());
            context.put("address", archivesVO.getAddress());
            context.put("address02", archivesVO.getAddress());
            String getstaff = getstaff(archivesVO.getStaff());
            context.put("staff", getstaff);
            context.put("cardId", archivesVO.getCardId());
            context.put("front", getImg(archivesVO.getFront()));
            context.put("back", getImg(archivesVO.getBack()));
            context.put("housingSt", archivesVO.getHousingSt());
            String typr = gethouseTypr(archivesVO.getHouseType());
            context.put("houseType", typr);
            if (archivesVO.getHouseImage() != null) {
                String[] split1 = archivesVO.getHouseImage().split(",");
                if (split1.length == 3) {
                    context.put("houseImage", getImg(split1[0]));
                    context.put("houseImage02", getImg(split1[1]));
                    context.put("houseImage03", getImg(split1[2]));
                } else if (split1.length == 2) {
                    context.put("houseImage", getImg(split1[0]));
                    context.put("houseImage02", getImg(split1[1]));
                    context.put("houseImage03", getImg(split1[1]));
                } else {
                    context.put("houseImage", getImg(split1[0]));
                    context.put("houseImage02", getImg(split1[0]));
                    context.put("houseImage03", getImg(split1[0]));
                }
            }
            context.put("house", getImg(archivesVO.getHouse()));
            context.put("report", getImg(archivesVO.getReport()));
            context.put("member", getImg(archivesVO.getMember()));
            context.put("poor", getImg(archivesVO.getPoor()));
            if (archivesVO.getPromise() != null) {
                String[] split = archivesVO.getPromise().split(",");
                if (split.length == 2) {
                    context.put("promise", getImg(split[0]));
                    context.put("promise02", getImg(split[1]));
                } else {
                    context.put("promise", getImg(split[0]));
                    context.put("promise02", getImg(split[0]));
                }
            }
            context.put("cardBank", getImg(archivesVO.getCardBank()));
            context.put("creaTime", DateUtil.date(archivesVO.getCreaTime()));
            context.put("auTime", DateUtil.date(archivesVO.getAuTime()));
            context.put("autellTime", DateUtil.date(archivesVO.getAutellTime()));
            context.put("autellImage", getImg(archivesVO.getAutellImage()));
            context.put("record", getImg(archivesVO.getRecord()));
            context.put("scene", getImg(archivesVO.getScene()));
            context.put("receipt", getImg(archivesVO.getReceipt()));
            if (archivesVO.getLiability() != null) {
                String[] split = archivesVO.getLiability().split(",");
                if (split.length == 2) {
                    context.put("liability", getImg(split[0]));
                    context.put("liability02", getImg(split[1]));
                } else {
                    context.put("liability", getImg(split[0]));
                    context.put("liability02", getImg(split[0]));
                }
            }
            context.put("wntellImage", getImg(archivesVO.getWntellImage()));
            if (archivesVO.getFieldwn() != null) {
                String[] split = archivesVO.getFieldwn().split(",");
                if (split.length == 2) {
                    context.put("fieldwn", getImg(split[0]));
                    context.put("fieldwn02", getImg(split[1]));
                } else {
                    context.put("fieldwn", getImg(split[0]));
                    context.put("fieldwn02", getImg(split[0]));
                }
            }
            context.put("wnTime", DateUtil.date(archivesVO.getWnTime()));
            context.put("wntellTime", DateUtil.date(archivesVO.getWntellTime()));
            context.put("scenewn", getImg(archivesVO.getScenewn()));
            context.put("receiptwn", getImg(archivesVO.getReceiptwn()));
            context.put("hold", getImg(archivesVO.getHold()));
            context.put("tyTime", DateUtil.date(archivesVO.getTyTime()));
            context.put("mzTime", DateUtil.date(archivesVO.getMzTime()));
            context.put("zjTime", DateUtil.date(archivesVO.getZjTime()));
            context.put("zjtellTime", DateUtil.date(archivesVO.getZjtellTime()));
            context.put("zjtellImage", getImg(archivesVO.getZjtellImage()));
            if (archivesVO.getProtocol() != null) {
                String[] split = archivesVO.getProtocol().split(",");
                if (split.length == 2) {
                    context.put("protocol", getImg(split[0]));
                    context.put("protocol02", getImg(split[1]));
                } else {
                    context.put("protocol", getImg(split[0]));
                    context.put("protocol02", getImg(split[0]));
                }
            }
            if (archivesVO.getContract() != null) {
                String[] split = archivesVO.getContract().split(",");
                if (split.length == 2) {
                    context.put("contract", getImg(split[0]));
                    context.put("contract02", getImg(split[1]));
                } else {
                    context.put("contract", getImg(split[0]));
                    context.put("contract02", getImg(split[0]));
                }
            }
            context.put("inform", getImg(archivesVO.getInform()));
            context.put("found", getImg(archivesVO.getFound()));
            context.put("part", getImg(archivesVO.getPart()));
            context.put("capped", getImg(archivesVO.getCapped()));
            context.put("checking", getImg(archivesVO.getChecking()));
            if (archivesVO.getHouseImage() != null) {
                String[] split1 = archivesVO.getHouseImage().split(",");
                context.put("houseImage04", getImg(split1[0]));
            }
            context.put("part02", getImg(archivesVO.getPart()));
            context.put("acceptance", getImg(archivesVO.getAcceptance()));
            context.put("urge", getImg(archivesVO.getUrge()));
            context.put("filing", getImg(archivesVO.getFiling()));
            context.put("transfer", getImg(archivesVO.getTransfer()));
            String filename = encodingFilename(docName);
            out = new FileOutputStream(getAbsoluteFile(filename));
            report.process(context, out);
            return filename;
        } catch (Exception e) {
            throw new UserInvalidException("导出失败，资料不全请提交资料！");
        } finally {
            IOUtils.closeQuietly(out);
        }
    }

    //读取图片
    public InputStream getImg(String urls) throws IOException {
        URL url = new URL(urls);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setConnectTimeout(5 * 1000);
        InputStream inStream = conn.getInputStream();
        return inStream;
    }

    // 生成压缩包
    public String writeZip(List<String> files, String zipname) throws IOException {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = servletRequestAttributes.getResponse();
        String fileName = zipname + ".zip";
        OutputStream os = response.getOutputStream();
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-type", "text/html;charset=UTF-8");
        response.setHeader("content-disposition", "attachment;filename=" + new String(fileName.getBytes("gb2312"), "ISO8859-1"));
        ZipOutputStream zos = new ZipOutputStream(os);
        byte[] buf = new byte[8192];
        int len;
        for (int i = 0; i < files.size(); i++) {
            String downloadPath = Config.getDownloadPath() + files.get(i);
            File file = new File(downloadPath);
            if (!file.isFile()) continue;
            ZipEntry ze = new ZipEntry(file.getName());
            zos.putNextEntry(ze);
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
            while ((len = bis.read(buf)) > 0) {
                zos.write(buf, 0, len);
            }
            bis.close();
            zos.closeEntry();
        }
        zos.closeEntry();
        zos.close();
        // 删除下载的文件
        for (int i = 0; i < files.size(); i++) {
            File file = new File(files.get(i));
            if (!file.isFile()) continue;
            file.delete();
        }
        return fileName;
    }


    /**
     * 编码文件名
     */
    public String encodingFilename(String filename) {
        filename = UUID.randomUUID().toString() + "_" + filename + ".doc";
        return filename;
    }


    /**
     * 获取下载路径
     *
     * @param filename 文件名称
     */
    public String getAbsoluteFile(String filename) {
        String downloadPath = Config.getDownloadPath() + filename;
        File desc = new File(downloadPath);
        if (!desc.getParentFile().exists()) {
            desc.getParentFile().mkdirs();
        }
        return downloadPath;
    }


    public String getstaff(int staff) {
        if (staff == 1) {
            return "脱贫享受政策户";
        } else if (staff == 2) {
            return "脱贫不稳定户";
        } else if (staff == 3) {
            return "边缘易致贫户";
        } else if (staff == 4) {
            return "严重困难户";
        } else if (staff == 5) {
            return "低保户";
        } else if (staff == 6) {
            return "分散供养特困人员";
        } else if (staff == 7) {
            return "农村低保边缘家庭";
        } else if (staff == 8) {
            return "其他脱贫户";
        }
        return "显示失败";
    }

    public String gethouseTypr(String type) {
        if (type.equals("0")) {
            return "修缮";
        } else if (type.equals("1")) {
            return "重建";
        }
        return "显示失败";
    }
}

