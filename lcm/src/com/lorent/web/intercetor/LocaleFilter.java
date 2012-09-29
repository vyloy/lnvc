package com.lorent.web.intercetor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import com.lorent.util.ThreadLocaleUtil;

public class LocaleFilter implements Filter {

	public void destroy() {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
//		ActionContext.getContext().setLocale(ThreadLocaleUtil.getLocale());
		((HttpServletRequest)request).getSession().setAttribute("WW_TRANS_I18N_LOCALE",ThreadLocaleUtil.getLocale());
//		ActionContext.getContext().setLocale(ThreadLocaleUtil.getLocale());
		chain.doFilter(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
		
	}

}
