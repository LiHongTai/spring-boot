package com.pingan.ph.cis.appbusiness.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.pingan.ph.cis.appbusiness.constant.NodeConstant;
import com.pingan.ph.cis.appbusiness.entity.AccountInfo;
import com.pingan.ph.cis.appbusiness.entity.BankCardInfo;
import com.pingan.ph.cis.appbusiness.repository.AccountInfoRepository;
import com.pingan.ph.cis.appbusiness.utils.ReflectUtil;

@Component
@SuppressWarnings("unchecked")
public class AccountInfoDao{

	private static final String ACCOUNT_NO = "accountNo";

	@Autowired
	private AccountInfoRepository repository;

	@Autowired
	private BankCardInfoDao bankCardInfoDao;

	public List<?> findByParam(Map<String, Object> param) {
		if (CollectionUtils.isEmpty(param))
			return new ArrayList<>();
		// TODO 只能知道具体的主键值，才可以根据特定的方法查询具体信息
		if (!param.containsKey(ACCOUNT_NO))
			return new ArrayList<>();
		String accountNo = (String) param.get(ACCOUNT_NO);

		List<AccountInfo> accountInfos = repository.findByAccountNo(accountNo);

		return handleAccountInfosByParam(accountInfos, param);
	}

	public AccountInfo findOneByAccountNo(String accountNo) {
		return repository.findOneByAccountNo(accountNo);
	}
	
	public List<AccountInfo> findByParam4CustInfo(String custNo, Map<String, Object> param) {
		List<AccountInfo> accountInfos = repository.findByCustNo(custNo);
		if (CollectionUtils.isEmpty(param))
			return accountInfos;

		return handleAccountInfosByParam(accountInfos, param);
	}

	private List<AccountInfo> handleAccountInfosByParam(List<AccountInfo> accountInfos, Map<String, Object> param) {
		List<AccountInfo> finalAccountInfos = new ArrayList<>();
		for (AccountInfo accountInfo : accountInfos) {
			if (ReflectUtil.filterEntityByParam(accountInfo, param))
				finalAccountInfos.add(accountInfo);
		}

		List<Map<String, Object>> childrenNodeList = (List<Map<String, Object>>) param.get(NodeConstant.CHILDREN_NODE);
		if (CollectionUtils.isEmpty(childrenNodeList))
			return finalAccountInfos;

		for (AccountInfo accountInfo : finalAccountInfos) {
			obtainAccountInfoChildrenNodeList(accountInfo, childrenNodeList);
		}
		return finalAccountInfos;
	}

	public List<AccountInfo> findByAccountNo(String accountNo, List<Map<String, Object>> childrenNodeList) {
		List<AccountInfo> accountInfos = repository.findByAccountNo(accountNo);

		for (AccountInfo accountInfo : accountInfos) {
			obtainAccountInfoChildrenNodeList(accountInfo, childrenNodeList);
		}

		return accountInfos;
	}

	public void obtainAccountInfoChildrenNodeList(AccountInfo accountInfo, List<Map<String, Object>> childrenNodeList) {
		if (CollectionUtils.isEmpty(childrenNodeList))
			return;

		for (Map<String, Object> childrenNode : childrenNodeList) {
			obtainAccountInfoChildrenNode(accountInfo, childrenNode);
		}
	}

	private void obtainAccountInfoChildrenNode(AccountInfo accountInfo, Map<String, Object> childrenNode) {
		if (CollectionUtils.isEmpty(childrenNode))
			return;

		for (Map.Entry<String, Object> node : childrenNode.entrySet()) {
			String nodeName = node.getKey();
			Map<String, Object> nodeParam = (Map<String, Object>) node.getValue();
			if (NodeConstant.BANK_CARD_INFO.equals(nodeName)) {
				obtainBankCardChildren(accountInfo, nodeParam, bankCardInfoDao);
			}
		}
	}

	private void obtainBankCardChildren(AccountInfo accountInfo, Map<String, Object> nodeParam,
			BankCardInfoDao bankCardInfoDao) {
		List<BankCardInfo> bankCardInfos = bankCardInfoDao.findByParam4AccountInfo(accountInfo.getAccountNo(),
				nodeParam);
		accountInfo.setBankCardInfos(bankCardInfos);
	}

}
