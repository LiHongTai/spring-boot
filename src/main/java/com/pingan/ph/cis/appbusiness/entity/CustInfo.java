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
@Table(name = "cust_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustInfo {

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Column(name = "cust_no")
	private String custNo;
	
	@Column(name = "cust_name")
	private String custName;
	
	@Transient
	List<CustDetailInfo> custDetailInfos;
	
	@Transient
	List<AccountInfo> accountInfos;
}
