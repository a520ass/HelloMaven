package com.aliyun.webupload;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;
import java.util.SimpleTimeZone;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.common.utils.BinaryUtil;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.PolicyConditions;
import com.qikemi.packages.alibaba.aliyun.oss.properties.OSSClientProperties;

public class WebUploadUtils {
	static Logger logger=LoggerFactory.getLogger(WebUploadUtils.class);
	
	/**
	 * 获取PostPolicy
	 */
	public static Map<String, String> getPostPolicy(){
		com.aliyun.oss.OSSClient client = new com.aliyun.oss.OSSClient(OSSClientProperties.ossCliendEndPoint, OSSClientProperties.key, OSSClientProperties.secret);
		Map<String, String> respMap = new LinkedHashMap<String, String>();
		try {
            Date expiration = new Date(new Date().getTime() + 1800 * 1000);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1048576000);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, "user");
            //上句代码指定了只能在user目录下的权限？
            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);
            
            respMap.put("accessid", OSSClientProperties.key);
            respMap.put("host", OSSClientProperties.ossEndPoint);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("expire", formatISO8601Date(expiration));
            String callbackhost=OSSClientProperties.ossEndPoint.substring(7).split("/")[0];
            String callback="{\"callbackUrl\":\"http://oss-demo.aliyuncs.com:23450\",\"callbackHost\":"+"\""+callbackhost+"\""+",\"callbackBody\":\"filename=${object}&size=${size}&mimeType=${mimeType}&height=${imageInfo.height}&width=${imageInfo.width}\",\"callbackBodyType\":\"application/x-www-form-urlencoded\"}";
            byte[] callbackbinaryData = callback.getBytes("utf-8");
            respMap.put("callback", BinaryUtil.toBase64String(callbackbinaryData));
            respMap.put("dir", "user/");
            logger.info("PostPolicy:"+respMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
		return respMap;
	}
	
	private static String formatISO8601Date(Date date) {
        final String pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
        SimpleDateFormat dateFormat = new SimpleDateFormat(pattern, Locale.US);
        dateFormat.setTimeZone(new SimpleTimeZone(0, "GMT"));
        return dateFormat.format(date);
    }
}
