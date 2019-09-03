package com.skindow.util;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2019/9/3.
 */
@Slf4j
public class FreemarkerStaticModels extends HashMap<String, Object> {
    public FreemarkerStaticModels(Map<String, String> classMap) {
        for (String key : classMap.keySet()) {
            put(key, getModel(classMap.get(key)));
        }
    }
    private TemplateHashModel getModel(String packageName) {
        BeansWrapper wrapper = new BeansWrapperBuilder(Configuration.VERSION_2_3_28).build();
        TemplateHashModel fileStatics=null;
        try {
            fileStatics = (TemplateHashModel)  wrapper.getStaticModels().get(packageName);
            return fileStatics;
        } catch (TemplateModelException e) {
            log.error(e.toString());
        }
        return fileStatics;
    }
}
