package com.mgj.util.upyun;

import java.io.File;
import java.io.IOException;

/**
 * Created by yanqu on 2016/7/13.
 */
public interface PicBucket {

    /** 根目录 */
    String DIR_ROOT = "/";
    public String writeFile(String filePath, String datas, boolean auto);
    public String writeFile(String filePath, File file, boolean auto) throws IOException;
    public String writeFile(String filePath, byte[] datas, boolean auto);

}
