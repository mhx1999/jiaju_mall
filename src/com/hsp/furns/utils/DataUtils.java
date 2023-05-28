package com.hsp.furns.utils;

import org.apache.commons.beanutils.BeanUtils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author 马海鑫
 * @version 1.0
 */
public class DataUtils {

    public static <T> T copyParamToBean(Map value, T bean) throws Exception {

  BeanUtils.populate(bean, value);

        return bean;
    }
}
