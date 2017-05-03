package com.th.jbp.backend.service;

import javax.persistence.EntityManager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.th.jbp.jpa.repositories.SystemConfigRepository;
import com.th.jbp.jpa.repositories.UserRepository;

@Service("batchService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class BatchService implements IBatchService {

//	@Autowired
//	SystemConfigRepository systemConfigRepository;
//
//	@Autowired
//	UserRepository userRepository;

	@Autowired
	EntityManager em;

	private static final Logger LOGGER = Logger.getLogger(BatchService.class);

	/*
	public static String SQL_UPLINE = "WITH n(USER_ID, PARENT_USER_ID, LEVEL, NODE_FLAG) AS " + "( "
			+ "		SELECT USER_ID, PARENT_USER_ID, LEVEL, NODE_FLAG " + "		FROM BMM_MLM "
			+ "		WHERE USER_ID = :userId " + "			UNION ALL "
			+ "		SELECT m.USER_ID, m.PARENT_USER_ID, m.LEVEL, m.NODE_FLAG " + "		FROM BMM_MLM as m, n "
			+ "		WHERE n.PARENT_USER_ID = m.USER_ID " + ") "
			+ "SELECT n.* FROM n LEFT JOIN BMM_USER_ROLE r ON n.USER_ID = r.USER_ID "
			+ "WHERE n.USER_ID <> :userId AND r.ROLE_ID = " + Role.USER.getId() + " ORDER BY n.USER_ID desc "
			+ "OFFSET 0 ROWS FETCH NEXT :level ROWS ONLY";

	@SuppressWarnings("unchecked")
	@Override
	public List<BmmEWalletTrans> processMatchingBonus(Long eWalletTransId) {
		List<BmmEWalletTrans> results = new ArrayList<BmmEWalletTrans>();
		long current = System.currentTimeMillis();

		BmmEWalletTrans trans = ewalletTransRepository.findOne(eWalletTransId);
		Long refUserId = trans.getUser().getUserId();
		String strLevel = systemConfigRepository.findByKey("MATCHING_LEVEL").getSystemValue();
		int level = Integer.parseInt(strLevel);
		String strMatchingAmt = systemConfigRepository.findByKey("MATCHING_BONUS").getSystemValue();
		BigDecimal matchingAmt = new BigDecimal(strMatchingAmt);

		List<Object[]> objs = em.createNativeQuery(SQL_UPLINE).setParameter("userId", refUserId)
				.setParameter("level", level).getResultList();

		// Create matching bonus
		for (Object[] obj : objs) {

			Long userId = ((BigDecimal) obj[0]).longValue();
			BmmUser user = userRepository.findOne(new Long(userId));
			BmmEWallet ewallet = ewalletRepository.findOne(userId);
			BigDecimal outstanding = BigDecimal.valueOf(ewallet.getAmount().doubleValue() + matchingAmt.doubleValue());

			BmmEWalletTrans matchingTrans = new BmmEWalletTrans();
			matchingTrans.setUser(user);
			matchingTrans.setType(BO_MATCH.name());
			matchingTrans.setAmount(matchingAmt);
			matchingTrans.setRefTransUserId(refUserId);
			matchingTrans.setCreateDate(trans.getCreateDate());
			matchingTrans.setOutstanding(outstanding);
			matchingTrans.setStatus(SUCCESS.getId());
			ewalletTransRepository.saveAndFlush(matchingTrans);

			LOGGER.info("[ lineupUserId ] : " + userId);
			LOGGER.info("UpdateEWallet Lineup : [" + matchingAmt + "]");
			LOGGER.info("	Lineup Point Old : [" + ewallet.getAmount().doubleValue() + "]");
			ewallet.setAmount(outstanding);
			ewallet.setUpdateBy(userId);
			ewallet.setUpdateDate(trans.getCreateDate());
			ewalletRepository.saveAndFlush(ewallet);
			LOGGER.info("	Lineup Point New : [" + ewallet.getAmount().doubleValue() + "]");

		}

		trans.setMatchingBonusStatus(PROCESS_COMPLETED.getId());
		trans.setUpdateDate(new Timestamp(current));
		trans.setUpdateBy(0L);
		ewalletTransRepository.saveAndFlush(trans);

		LOGGER.info("[" + eWalletTransId.toString() + "] processMatchingBonus " + objs.size() + " records completed in "
				+ (System.currentTimeMillis() - current) + " milliseconds.");

		return results;
	}

	@Override
	@Transactional(readOnly = true)
	public List<BmmEWalletTrans> findTransForProcessMatchingBonus(EWalletTransStatus eWalletTransStatus,
			ProcessStatus processStatus) {
		List<BmmEWalletTrans> lists = ewalletTransRepository.findTransForProcessMatchingBonus(
				EWalletTransStatus.SUCCESS.getId(), ProcessStatus.NOT_PROCESSED.getId());
		return lists == null ? new ArrayList<BmmEWalletTrans>() : lists;
		return null;
	}
	*/

}
