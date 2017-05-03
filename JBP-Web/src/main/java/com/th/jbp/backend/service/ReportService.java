package com.th.jbp.backend.service;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("reportService")
@Scope(value = "singleton", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ReportService implements IReportService {

	@Autowired
	EntityManager em;

//	private static String SQL_ACCOUNT_BOOK_REPORT = " WITH N (TYPE, AMOUNT, OUTSTANDING, CREATE_DATE, E_WALLET_TRANS_ID) AS ( "
//			+ " SELECT TYPE, AMOUNT, OUTSTANDING, CREATE_DATE,E_WALLET_TRANS_ID FROM BMM_E_WALLET_TRANS WHERE  USER_ID = :userId "
//			+ " AND (MONTH(CREATE_DATE) = :month OR 0 = :month ) AND ( YEAR(CREATE_DATE) = :year OR 0 = :year) " + " UNION ALL "
//			+ " SELECT B.TYPE, B.FEE AS AMOUNT, B.OUTSTANDING, A.CREATE_DATE, A.E_WALLET_TRANS_ID FROM BMM_E_WALLET_TRANS A  "
//			+ " INNER JOIN BMM_E_WALLET_TRANS_FEE B ON A.E_WALLET_TRANS_ID = B.E_WALLET_TRANS_ID " + " WHERE  USER_ID = :userId "
//			+ " AND (MONTH(CREATE_DATE) = :month OR 0 = :month ) AND ( YEAR(CREATE_DATE) = :year OR 0 = :year) " + ") "
//			+ " SELECT DISTINCT * FROM N ORDER BY CREATE_DATE ASC, E_WALLET_TRANS_ID";
//
//	private static String SQL_WALLET_REPORT = " WITH N (TYPE, AMOUNT, OUTSTANDING, CREATE_DATE, E_WALLET_TRANS_ID) AS ( "
//			+ " SELECT TYPE, AMOUNT, OUTSTANDING, CREATE_DATE,E_WALLET_TRANS_ID FROM BMM_E_WALLET_TRANS WHERE  USER_ID = :userId "
//			+ " UNION ALL "
//			+ " SELECT B.TYPE, B.FEE AS AMOUNT, B.OUTSTANDING, A.CREATE_DATE, A.E_WALLET_TRANS_ID FROM BMM_E_WALLET_TRANS A  "
//			+ " INNER JOIN BMM_E_WALLET_TRANS_FEE B ON A.E_WALLET_TRANS_ID = B.E_WALLET_TRANS_ID " + " WHERE  USER_ID = :userId " + ") "
//			+ " SELECT DISTINCT * FROM N " + " WHERE ( TYPE = :type OR :type IS NULL ) ORDER BY CREATE_DATE ASC, E_WALLET_TRANS_ID";
//
//	private static String SQL_WALLET_REPORT_WITH_DATE = " WITH N (TYPE, AMOUNT, OUTSTANDING, CREATE_DATE, E_WALLET_TRANS_ID) AS ( "
//			+ " SELECT TYPE, AMOUNT, OUTSTANDING, CREATE_DATE,E_WALLET_TRANS_ID FROM BMM_E_WALLET_TRANS WHERE  USER_ID = :userId "
//			+ " UNION ALL "
//			+ " SELECT B.TYPE, B.FEE AS AMOUNT, B.OUTSTANDING, A.CREATE_DATE, A.E_WALLET_TRANS_ID FROM BMM_E_WALLET_TRANS A  "
//			+ " INNER JOIN BMM_E_WALLET_TRANS_FEE B ON A.E_WALLET_TRANS_ID = B.E_WALLET_TRANS_ID "
//			+ " WHERE  USER_ID = :userId "
//			+ ") "
//			+ " SELECT DISTINCT * FROM N "
//			+ " WHERE CAST(FLOOR(CAST(CREATE_DATE AS FLOAT)) AS DATETIME) BETWEEN :dateFrom AND :dateTo AND ( TYPE = :type OR :type IS NULL ) "
//			+ " ORDER BY CREATE_DATE ASC, E_WALLET_TRANS_ID";

	

}
