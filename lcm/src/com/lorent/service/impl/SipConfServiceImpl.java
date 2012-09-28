package com.lorent.service.impl;

import java.io.File;
import java.io.FilenameFilter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import com.lorent.util.PropertiesUtil;
import com.lorent.dao.SipConfDao;
import com.lorent.dto.RecordBean;
import com.lorent.exception.CustomSqlException;
import com.lorent.model.SipConfBean;
import com.lorent.service.SipConfService;

public class SipConfServiceImpl extends  GenericServiceImpl<SipConfDao, SipConfBean, Integer> implements SipConfService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	public List<SipConfBean> getAllUser() {
		List<SipConfBean> sipConfBeanList=null;
		try {
			sipConfBeanList= daoFacade.getSipConfDao().getAll();
		} catch (CustomSqlException e) {
			e.printStackTrace();
		}
		return sipConfBeanList;
	}

	@Override
	public List<RecordBean> getRecordByCondition(String fromlcc, String tolcc,
			Date date) throws Exception {

		List<RecordBean> records = new ArrayList<RecordBean>();
		File dir = new File(PropertiesUtil.getConstant("RECORD_FILE_PATH"));
		boolean exists = dir.exists();
		if (!exists) {
			return records;
		}
		String pfromlcc = ".*";
		String ptolcc = ".*";
		String pdate = ".*";
		if (fromlcc != null && fromlcc.length() > 0) {
			pfromlcc = fromlcc;
		}
		if (tolcc != null && tolcc.length() > 0) {
			ptolcc = tolcc;
		}
		if (date != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			pdate = sdf.format(date);
		}
		final String formatStr = "^" + pfromlcc + "-" + ptolcc + "_" + pdate
				+ "-\\d\\d-\\d\\d-\\d\\d[/.]wav$";
		String[] filenames = dir.list(new FilenameFilter() {

			public boolean accept(File dir, String name) {
				if (name.matches(formatStr)) {
					return true;
				} else {
					return false;
				}
			}
		});
		Arrays.sort(filenames, new CompratorByLastModified());
		for (String filename: filenames) {
			records.add(changeFilenameToRecordBean(filename));
		}
		return records;
	}
	private Date getDateFromName(String filename)throws Exception{
		String temp = filename.split("\\.")[0];
		String[] temp2 = temp.split("_");
		String date = temp2[1];
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");
		return sdf.parse(date);
	}
	private RecordBean changeFilenameToRecordBean(String filename) {
		String temp = filename.split("\\.")[0];
		String[] temp2 = temp.split("_");
		String date = temp2[1];
		String temp3 = temp2[0];
		String[] temp4 = temp3.split("-");
		String fromlcc = temp4[0];
		String tolcc = temp4[1];
		RecordBean record = new RecordBean();
		record.setFilename(filename);
		record.setDate(date);
		record.setFromlcc(fromlcc);
		record.setTolcc(tolcc);
		return record;
	}
	
	class CompratorByLastModified implements Comparator<String> {
		public int compare(String f1, String f2) {
			try{
				Date d1 = getDateFromName(f1);
				Date d2 = getDateFromName(f2);
				return d2.compareTo(d1);
			}catch(Exception e){
				e.printStackTrace();
				return -1;
			}
		}

	}

}
