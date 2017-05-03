package com.th.jbp.web.vm;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.util.resource.Labels;
import org.zkoss.zk.ui.annotation.Command;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.ClickEvent;

import com.th.jbp.web.view.ItemView;

public class BaseVM implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final int PAGE_SIZE = 20;
	public static final String MESSAGEBOX_EVENT_NAME_OK = "onOK";
	public static final String MESSAGE_CONFIRM = "message.cofirm";
	public static final String MESSAGE_CONFIRM_UPDATE = "message.cofirm.update";
	public static final String MESSAGE_CONFIRM_SAVE = "message.cofirm.save";
	public static final String MESSAGE_CONFIRM_DELETE = "message.cofirm.delete";
	public static final String MESSAGE_CONFIRM_ADD = "message.cofirm.add";
	public static final String MESSAGE_DATA_INTEGRITY_VIOLATION = "message.error.delete.relationship";
	public static final String MESSAGE_ERROR_DELETE_FAIL = "message.error.delete.fail";
	public static final String NEW_LINE = "\n";
	public static final String PAGE_LOGIN = "LOGIN";
	public static final String MANAGEMENT_GENDER_CODE = "REF_GENDER_CODE";
	public static final String MANAGEMENT_ERROR_CODE = "RUL_ERROR_CODE";
	public static final String ACTIVITY_LOGIN = "Login";
	public static final String ACTIVITY_DELETE = "Delete";
	public static final String ACTIVITY_NEW = "New";
	public static final String ACTIVITY_EDIT = "Edit";
	public static final String ACTIVITY_VERIFY = "Verify";
	public static final String USERNAME_FORMAT = "^[Kk][a-zA-Z0-9][0-9]{6}$";
	private String mode;
	public static final String ITEM_EMPTY = "";
	static Map<String, String> params = new HashMap<String, String>();
	private static final Logger LOGGER = Logger.getLogger(BaseVM.class);
	private ListModelList<Integer> years = null;
	private ListModelList<ItemView> months = null;
	private int currentMonth;
	private int currentYear;
	public static final String UPLOAD_PATH = MessageFormat.format("C:{0}beyond{0}upload{0}", File.separator);
	public static final String TEMP_PATH = MessageFormat.format("C:{0}beyond{0}temp{0}", File.separator);

	public BaseVM() {
		params.put("sclass", "myMessagebox");
		Calendar cal = Calendar.getInstance(Locale.US);
		currentMonth = cal.get(Calendar.MONTH) + 1;
		months = new ListModelList<>();
		months.add(new ItemView(0, "0", "All"));
		months.add(new ItemView(1, "1", "Jan"));
		months.add(new ItemView(2, "2", "Fab"));
		months.add(new ItemView(3, "3", "Mar"));
		months.add(new ItemView(4, "4", "Apr"));
		months.add(new ItemView(5, "5", "May"));
		months.add(new ItemView(6, "6", "Jun"));
		months.add(new ItemView(7, "7", "July"));
		months.add(new ItemView(8, "8", "Aug"));
		months.add(new ItemView(9, "9", "Sep"));
		months.add(new ItemView(10, "10", "Oct"));
		months.add(new ItemView(11, "11", "Nov"));
		months.add(new ItemView(12, "12", "Dec"));
		currentYear = cal.get(Calendar.YEAR);
		years = new ListModelList<>();
		for (int i = 3; i >= 0; i--) {
			years.add(currentYear - i);
		}
	}

	private int count = 0;
	private String timeInfo = "";

	@Command
	@NotifyChange({ "timeInfo" })
	public void timer() {
		// Date now = new Date();

		// if (this.endDate != null) {
		count++;
		int second = (15 * 60) - count;
		int h = second / (60 * 60);
		int m = second / 60;
		int s = second % 60;

		// // boolean isWarn = false;
		// this.classTime = "remind-time";
		// if (m < WRAN1_TIME_MIN) {
		// // isWarn = true;
		// this.classTime = "remind-time-1";
		// }
		// if (m < WRAN2_TIME_MIN) {
		// // isWarn = true;
		// this.classTime = "remind-time-2";
		// }

		String strH = StringUtils.leftPad(String.valueOf(h), 2, "0");
		String strM = StringUtils.leftPad(String.valueOf(m), 2, "0");
		String strS = StringUtils.leftPad(String.valueOf(s), 2, "0");

		this.timeInfo = strH + ":" + strM + ":" + strS;

		if (second == 0) {
			// Messagebox.show(getMessage("test.timeout"));
			// // Update history
			// updateHistory(TestStatus.TIMEOUT, 0, 0, count);
			// clear();
			// this.currentPage = "../zul/criteria_etest.zul";
		}

		// }
	}

	public enum MODE {
		ADD, EDIT, SEARCH, INFO, ERROR
	}

	public String getMessage(String key, Object... objects) {
		return Labels.getLabel(key, objects);
	}

	public static String textformat(String format, Object... object) {
		if (StringUtils.isNotEmpty(format) && object != null) {
			return MessageFormat.format(format, object);
		}
		return "";
	}

	public static void showValidate(String message) {
		showError("Validate", message);
	}

	public static void showError(String title, String message) {
		Messagebox.Button[] messageBoxButtons = new Messagebox.Button[] { Messagebox.Button.OK };
		Messagebox.show(message, title, messageBoxButtons, null, Messagebox.ERROR, null, null, params);
		Clients.evalJavaScript("moveMessageboxToCenter('" + title + "');");
	}

	public static void showInformation(String message) {
		String title = "Information";
		Messagebox.Button[] messageBoxButtons = new Messagebox.Button[] { Messagebox.Button.OK };
		Messagebox.show(message, title, messageBoxButtons, null, Messagebox.INFORMATION, null, null, params);
		Clients.evalJavaScript("moveMessageboxToCenter('" + title + "');");
	}

	public static void showInformation(String title, String message) {
		Messagebox.Button[] messageBoxButtons = new Messagebox.Button[] { Messagebox.Button.OK };
		Messagebox.show(message, title, messageBoxButtons, null, Messagebox.INFORMATION, null, null, params);
		Clients.evalJavaScript("moveMessageboxToCenter('" + title + "');");
	}

	public InputStream getInputStreamXML(String file) {
		InputStream in = this.getClass().getClassLoader().getResourceAsStream("/template/excel/" + file);
		return in;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public static boolean isEnglish(String str) {
		return test(str, "[a-zA-Z]+");
	}

	public static boolean isNumberOnly(String str) {
		return test(str, "[0-9]+");
	}

	public static boolean isAllowCharacter(String str) {
		return test(str, "[ก-๙a-zA-Z0-9.,/\\s]+");
	}

	public static boolean test(String str, String regx) {
		if (str == null) {
			return false;
		}
		return str.matches(regx);
	}

	final static ResourceBundle RB = ResourceBundle.getBundle("application");

	public static String getProperty(String key) {
		if (RB != null) {
			return RB.getString(key);
		} else {
			LOGGER.error("Not found application.properties");
		}
		return "";
	}

	public void execute(final MethodOperation method, Logger logger) {
		execute(method, logger, null);
	}

	public void execute(final MethodOperation method, final Logger logger, final String messageConfirm) {
		if (method.isConfirm()) {
			String title = "Confirm";// getMessage(MESSAGE_CONFIRM);
			Messagebox.show(messageConfirm, title, new Messagebox.Button[] { Messagebox.Button.OK, Messagebox.Button.CANCEL }, null,
					Messagebox.INFORMATION, null, new EventListener<Messagebox.ClickEvent>() {
						@Override
						public void onEvent(ClickEvent event) throws Exception {
							if (event.getName().equals(MESSAGEBOX_EVENT_NAME_OK)) {
								try {
									method.execute();
								} catch (Exception e) {
									logger.error(e.toString(), e);
									showError("System error", getMessage("message.system.error"));
									Clients.clearBusy();
								}
							}
						}
					}, params);
			Clients.evalJavaScript("moveMessageboxToCenter('" + title + "');");
			LOGGER.info("title---> " + title);
		} else {
			try {
				method.execute();
			} catch (Exception e) {
				logger.error(e.toString(), e);
				showError("System error", getMessage("message.system.error"));
				Clients.evalJavaScript("moveMessageboxToCenter('System error');");
				Clients.clearBusy();
			}
		}

	}

	public String getLoginName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		return StringUtils.upperCase(auth.getName());
	}

	public ListModelList<Integer> getYears() {
		return years;
	}

	public ListModelList<ItemView> getMonths() {
		return months;
	}

	public int getCurrentMonth() {
		return currentMonth;
	}

	public int getCurrentYear() {
		return currentYear;
	}

	public String getTimeInfo() {
		return timeInfo;
	}

	public void setTimeInfo(String timeInfo) {
		this.timeInfo = timeInfo;
	}

}
