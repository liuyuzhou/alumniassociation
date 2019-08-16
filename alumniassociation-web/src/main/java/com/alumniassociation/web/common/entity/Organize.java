package com.alumniassociation.web.common.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


/**
 * 记录组织机构管理信息
 * 
 * @author chenyi
 * @email qq228112142@qq.com
 * @date 2017-11-06 17:39:31
 */
public class Organize implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//部门标识
	private String orgId;
	//部门名称
	private String orgName;
	//上一级部门标识
	private String parentOrgId;

	private String[] parentOrgIds;
	//排序
	private BigDecimal sortNo;
	//是否显示  0：否 1：是
	private String state;
	//添加时间
	@JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
	private Date inDate;
	private boolean open;

	private String type;
	
	private String bapid;
	public boolean isOpen() {
		return open;
	}

	public void setOpen(boolean open) {
		this.open = open;
	}
	//部门类型
    private String orgType;
	
	//父部门名称
	private String parentOrgName;
	
	/**简称**/
	private String shortname;
	/**联系人**/
	private String contact;
	/**联系电话**/
	private String contactNumber;
	/**运营商地址**/
	private String address;
	/**企业编号**/
	private String operatorNumber;
	/**营业执照编号**/
	private String buslicNumber;
	/**省**/
	private String provinceCode;
	private String province;
	/**市**/
	private String city;
	private String cityCode;
	/**区**/
	private List<String> areaCodes;
	
	private String areaCode;
	
	/**工厂联系地址**/
	private String ftyAddress;
	/**工厂联系人**/
	private String ftyContact;
	/**工厂联系电话**/
	private String ftyContactTel;
	/**工厂联系人移动电话**/
	private String ftyContactMobile;
	/**工厂法人姓名**/
	private String legalPerson;
	/**工厂法人身份证号码**/
	private String legalPersonid;
	/**开户行名称**/
	private String bankName;
	/**开户行帐号**/
	private String bankAccNo;

	/**仓库联系电话**/
	private String telNo;
	/**负责人**/
	private String principal;
	/**负责人号码**/
	private String principalPhone;
	private String wmsAddress;
	private String wmsCity;

	public String getWmsCity() {
		return wmsCity;
	}

	public void setWmsCity(String wmsCity) {
		this.wmsCity = wmsCity;
	}

	public String getWmsAddress() {
		return wmsAddress;
	}

	public void setWmsAddress(String wmsAddress) {
		this.wmsAddress = wmsAddress;
	}

	public String getTelNo() {
		return telNo;
	}

	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getPrincipalPhone() {
		return principalPhone;
	}

	public void setPrincipalPhone(String principalPhone) {
		this.principalPhone = principalPhone;
	}

	public String getOrgType() {
		return orgType;
	}

	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	public String getParentOrgName() {
		return parentOrgName;
	}

	public void setParentOrgName(String parentOrgName) {
		this.parentOrgName = parentOrgName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getParentOrgId() {
		return parentOrgId;
	}

	public void setParentOrgId(String parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	public BigDecimal getSortNo() {
		return sortNo;
	}

	public void setSortNo(BigDecimal sortNo) {
		this.sortNo = sortNo;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Date getInDate() {
		return inDate;
	}

	public void setInDate(Date inDate) {
		this.inDate = inDate;
	}

	public String[] getParentOrgIds() {
		return parentOrgIds;
	}

	public void setParentOrgIds(String[] parentOrgIds) {
		this.parentOrgIds = parentOrgIds;
	}

	public String getShortname() {
		return shortname;
	}

	public void setShortname(String shortname) {
		this.shortname = shortname;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOperatorNumber() {
		return operatorNumber;
	}

	public void setOperatorNumber(String operatorNumber) {
		this.operatorNumber = operatorNumber;
	}

	public String getBuslicNumber() {
		return buslicNumber;
	}

	public void setBuslicNumber(String buslicNumber) {
		this.buslicNumber = buslicNumber;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getFtyAddress() {
		return ftyAddress;
	}

	public void setFtyAddress(String ftyAddress) {
		this.ftyAddress = ftyAddress;
	}

	public String getFtyContact() {
		return ftyContact;
	}

	public void setFtyContact(String ftyContact) {
		this.ftyContact = ftyContact;
	}

	public String getFtyContactTel() {
		return ftyContactTel;
	}

	public void setFtyContactTel(String ftyContactTel) {
		this.ftyContactTel = ftyContactTel;
	}

	public String getFtyContactMobile() {
		return ftyContactMobile;
	}

	public void setFtyContactMobile(String ftyContactMobile) {
		this.ftyContactMobile = ftyContactMobile;
	}

	public String getLegalPerson() {
		return legalPerson;
	}

	public void setLegalPerson(String legalPerson) {
		this.legalPerson = legalPerson;
	}

	public String getLegalPersonid() {
		return legalPersonid;
	}

	public void setLegalPersonid(String legalPersonid) {
		this.legalPersonid = legalPersonid;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankAccNo() {
		return bankAccNo;
	}

	public void setBankAccNo(String bankAccNo) {
		this.bankAccNo = bankAccNo;
	}

	public String getProvinceCode() {
		return provinceCode;
	}

	public void setProvinceCode(String provinceCode) {
		this.provinceCode = provinceCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public List<String> getAreaCodes() {
		return areaCodes;
	}

	public void setAreaCodes(List<String> areaCodes) {
		this.areaCodes = areaCodes;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBapid() {
		return bapid;
	}

	public void setBapid(String bapid) {
		this.bapid = bapid;
	}

}
