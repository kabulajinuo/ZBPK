package dckj.arrange.common.config;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import dckj.arrange.common.util.StringUtils;
import dckj.arrange.common.util.YamlUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 全局配置类
 * 
 * @author ruoyi
 */
public class Global
{
    private static final Logger log = LoggerFactory.getLogger(Global.class);

    private static String NAME = "application.yml";

    /**
     * 当前对象实例
     */
    private static Global global;

    /**
     * 保存全局属性值
     */
    private static Map<String, String> map = new HashMap<String, String>();

    private Global()
    {
    }

    /**
     * 静态工厂方法
     */
    public static synchronized Global getInstance()
    {
        if (global == null)
        {
            global = new Global();
        }
        return global;
    }

    /**
     * 获取配置
     */
    public static String getConfig(String key)
    {
        String value = map.get(key);
        if (value == null)
        {
            Map<?, ?> yamlMap = null;
            try
            {
                yamlMap = YamlUtil.loadYaml(NAME);
                value = String.valueOf(YamlUtil.getProperty(yamlMap, key));
                map.put(key, value != null ? value : StringUtils.EMPTY);
            }
            catch (FileNotFoundException e)
            {
                log.error("获取全局配置异常 {}", key);
            }
        }
        return value;
    }

    /**
     * 获取文件上传路径
     */
    public static String getProfile()
    {
        return getConfig("dckj.profile");
    }

    /**
     * 获取下载路径
     */
    public static String getDownloadPath()
    {
        return getProfile() + "/download";
    }

    /**
     * 获取上传路径
     */
    public static String getUploadPath()
    {
        return getProfile() + "/upload";
    }
}
