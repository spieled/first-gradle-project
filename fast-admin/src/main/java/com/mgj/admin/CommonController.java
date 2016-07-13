package com.mgj.admin;

import com.mgj.admin.base.BaseController;
import com.mgj.base.Constants;
import com.mgj.base.Result;
import com.mgj.base.socialinsurance.InsurePolicy;
import com.mgj.base.socialinsurance.OrderItem;
import com.mgj.util.Util;
import com.mgj.util.upyun.PicBucket;
import net.bull.javamelody.MonitoredWithSpring;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by yanqu on 2016/6/29.
 */
@RestController
@RequestMapping("common")
@MonitoredWithSpring
public class CommonController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
    @Autowired
    private PicBucket picBucket;


    @RequestMapping("calc")
    public Result calc(HttpServletRequest request) {
        try {
            BigDecimal insureBase = Util.parseBigDecimal(request, "insureBase", BigDecimal.ZERO);
            boolean sick = Util.parseBoolean(request, "sick", false);
            String type = Util.parseString(request, "type", Constants.EMPTY);
            InsurePolicy policy = InsurePolicy.getPolicy2016(sick, "外地农村".equals(type));
            OrderItem item = OrderItem.calc(insureBase, policy);
            return Result.ok(Constants.EMPTY, item);
        } catch (Exception e) {
            logger.error("计算社保出差", e);
            return Result.fail("计算社保出差", Constants.EMPTY);
        }
    }

    // @CrossOrigin(origins = "*")
    @RequestMapping("upload")
    public Result upload(HttpServletRequest request) throws IOException {
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            String base64 = request.getParameter("file");
            String originalFilename = request.getParameter("name");
            byte[] bytes = decoder.decodeBuffer(base64);
            int index = originalFilename.lastIndexOf(".");
            String prefix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            String filename = prefix + originalFilename.substring(index, originalFilename.length());
            String realPath = request.getServletContext().getRealPath("/");
            String username = request.getRemoteUser();
            String fullPath = picBucket.writeFile(username + PicBucket.DIR_ROOT + filename, bytes, true);
            /*File f = new File(realPath + File.separator + username + File.separator + filename);
            org.apache.commons.io.FileUtils.writeByteArrayToFile(f, bytes);
            logger.info("保存图片成功" + f.getPath());
            return Result.ok(Constants.EMPTY, "/" + username + "/" + filename);*/
            logger.info("保存图片成功" + fullPath);
            return Result.ok(Constants.EMPTY, fullPath);
        } catch (Exception e) {
            logger.error("保存上次图片失败", e);
        }
        return Result.fail("保存上次图片失败", Constants.EMPTY);
    }

}
