package com.th.jbp.web.job;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.apache.log4j.Logger;
import org.quartz.InterruptableJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.UnableToInterruptJobException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import com.th.jbp.web.StaticContextHolder;

public class CalculateBonusJob extends QuartzJobBean implements InterruptableJob {

	private static final Logger LOGGER = Logger.getLogger(CalculateBonusJob.class);

	private volatile Thread currentThread;

	@Override
	protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
		this.currentThread = Thread.currentThread();
//		IBatchService batchService = StaticContextHolder.getBean(BatchService.class);
//		Date now = new Date();
//		int random = Integer.parseInt(DateFormatUtils.format(Calendar.getInstance(), "yyMMddHHmm", Locale.US));
//		LOGGER.info("--- CalculateBonusJob Run : " + now + " [" + random + "] ---");
//		List<BmmEWalletTrans> trans = batchService.findTransForProcessMatchingBonus(EWalletTransStatus.SUCCESS,
//				ProcessStatus.NOT_PROCESSED);
//		for (BmmEWalletTrans t : trans) {
//			LOGGER.info("Calculate TransID : [" + t.geteWalletTransId() + "]");
//			boolean status = true;
//			try {
//				batchService.processMatchingBonus(t.geteWalletTransId());
//			} catch (Exception e) {
//				status = false;
//				LOGGER.error(e.getMessage(), e);
//			}
//			LOGGER.info("Calculate TransID : [" + t.geteWalletTransId() + "] : Status " + (status ? "Success" : "Fail")
//					+ ".");
//		}
//		LOGGER.info("--- CalculateBonusJob End : " + new Date() + " [" + random + "] ---");
	}

	@Override
	public void interrupt() throws UnableToInterruptJobException {
		LOGGER.info("CalculateBonusJob interrupt : " + new Date());
		if (currentThread != null) {
			currentThread.interrupt();
		}
	}

}
