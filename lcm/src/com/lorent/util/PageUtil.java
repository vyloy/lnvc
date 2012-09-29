package com.lorent.util;

import java.util.ArrayList;
import java.util.List;

public class PageUtil {
	public final static int PAGEROW = 20;
	public final static String DESC = "desc";
	public final static String ASC = "asc";
	public final static String PAGEMAP_KEY_PAGEROW = "pageRow";
	public final static String PAGEMAP_KEY_PAGECONT = "pageCont";
	public final static String PAGEMAP_KEY_RESULTCONT = "resultCont";
	public final static String PAGEMAP_KEY_CURRENTPAGE = "currentPage";
	public final static String PAGEMAP_KEY_PAGERESULT = "pageResult";
	public final static String PAGEMAP_KEY_ORDERBY = "orderBy";
	public final static String PAGEMAP_KEY_ORDERDESC = "orderDesc";
	public final static String PAGEMAP_KEY_ENABLELIKE = "unableLike";
	public final static String PAGEMAP_KEY_PAGEURL = "pageUrl";
	public final static String PAGEMAP_KEY_TOPAGE = "topage";
	
	public static int pageRow;
	public static int currentPage;
	public static int resultCont = 0;
	public static int pageCont = 0;
	public static List pageResult = new ArrayList();
	public static boolean enableLike = false;
}
