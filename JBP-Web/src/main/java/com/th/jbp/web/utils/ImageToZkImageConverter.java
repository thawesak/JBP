package com.th.jbp.web.utils;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.zkoss.bind.BindContext;
import org.zkoss.bind.Converter;
import org.zkoss.image.AImage;
import org.zkoss.zul.Image;

public class ImageToZkImageConverter implements Converter<AImage, byte[], Image> {

	private static final Logger LOGGER = Logger.getLogger(ImageToZkImageConverter.class);

    @Override
    public byte[] coerceToBean(AImage compAttr, Image component, BindContext ctx) {
    	LOGGER.debug("Converting the image");
        return compAttr.getByteData();
    }

    @Override
    public AImage coerceToUi(byte[] beanProp, Image component, BindContext ctx) {
        try {
            if (beanProp != null && beanProp.length > 0) {
                AImage im = new AImage("", beanProp);
                component.setContent(im);
                return im;
            }else{
            	LOGGER.debug("Return null => image is empty");
            	AImage im = new AImage("C:\\tmp.png");
                component.setContent(im);
                return im;
            }
        } catch (IOException e) {
        	LOGGER.error("Error occured, returning null", e);
            return null;
        }
    }
}