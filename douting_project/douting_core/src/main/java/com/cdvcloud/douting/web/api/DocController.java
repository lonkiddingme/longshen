package com.cdvcloud.douting.web.api;

import com.cdvcloud.douting.common.CommonParameters;
import com.cdvcloud.douting.common.Configuration;
import com.cdvcloud.douting.common.Constants;
import com.cdvcloud.douting.common.HttpCommonUtil;
import com.cdvcloud.rms.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

/**
 * 分享页接口
 *
 * @author SongYuanKun
 * @date 2017/11/17
 */

@Controller
@RequestMapping(value = "/docWeb/")
public class DocController {
    @Autowired
    private HttpCommonUtil httpCommonUtil;

    @SuppressWarnings("unchecked")
	@RequestMapping(value = "{docId}")
    public ModelAndView shareWeb(@PathVariable("docId") String docId) {
        ModelAndView mav = new ModelAndView("hello");
        Map<String, Object> jsonMap = new HashMap<String, Object>(16);
        jsonMap.put(CommonParameters.ACCESSTOKEN, "accessToken");
        jsonMap.put(CommonParameters.TIMESTAMP, System.currentTimeMillis());
        jsonMap.put("docid", docId);
        String httpUrl = Configuration.getConfigValue("shareContentUrl");
        String resp = httpCommonUtil.doPost(httpUrl, jsonMap);
        Map<String, Object> respMap;
        Map<String, Object> data = new HashMap<String, Object>(16);
        try {
            respMap = JSONUtils.json2map(resp);
            String code = String.valueOf(respMap.get("code"));
            if (Constants.SZERO.equals(code)) {
                data = (Map<String, Object>) respMap.get("data");
                data.get("title");
                mav.addObject("content", data.get("srcontent"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        mav.setViewName("share/doc_web");
        return mav;
    }
}
