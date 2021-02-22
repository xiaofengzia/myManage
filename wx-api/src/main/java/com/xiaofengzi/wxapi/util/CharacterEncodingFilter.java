package com.xiaofengzi.wxapi.util;

import org.springframework.util.ClassUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

public class CharacterEncodingFilter extends OncePerRequestFilter {
	
	private static final boolean responseSetCharacterEncodingAvailable;
	
    private String encoding;
    
    private boolean forceEncoding;
    
    static Class _mthclass$(String x0) throws Throwable
    {
        try
        {
            return Class.forName(x0);
        }
        catch(ClassNotFoundException x1)
        {
            throw (new NoClassDefFoundError()).initCause(x1);
        }
    }

    static 
    {
        responseSetCharacterEncodingAvailable = ClassUtils.hasMethod(HttpServletResponse.class, "setCharacterEncoding", new Class[] {
            String.class
        });
    }

    public CharacterEncodingFilter()
    {
        forceEncoding = false;
    }

    public void setEncoding(String encoding)
    {
        this.encoding = encoding;
    }

    public void setForceEncoding(boolean forceEncoding)
    {
        this.forceEncoding = forceEncoding;
    }

    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
        throws ServletException, IOException
    {
    	
        if(encoding != null && (forceEncoding || request.getCharacterEncoding() == null))
        {
        	/* 澹版槑绯荤粺瀛楃闆嗭紝娌℃湁杩欒鐨勮瘽锛宻itemesh灏嗗嚭鐜颁贡鐮� */
        	System.setProperty("file.encoding","UTF-8");
            request.setCharacterEncoding(encoding);
            if(forceEncoding && responseSetCharacterEncodingAvailable){
                response.setCharacterEncoding(encoding);
            }
        }
        request.setAttribute("todayDate", new Date());
        filterChain.doFilter(request, response);
    }
}
