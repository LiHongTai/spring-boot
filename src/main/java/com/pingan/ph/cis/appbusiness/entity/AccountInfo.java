package com.pingan.ph.cis.appbusiness.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "account_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountInfo {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Column(name = "account_no")
	private String accountNo;
	
	@Column(name = "account_name")
	private String accountName;
	
	@Column(name = "account_status")
	private String accountStatus;
	
	@Column(name = "cust_no")
	private String custNo;
	
	@Transient
	private List<BankCardInfo> bankCardInfos;
}
