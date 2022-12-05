package com.kruger.dto;

public class FilterDTO {

	private Integer vaccinateState;

	private Integer vaccinateType;

	// @JsonSerialize(using = ToStringSerializer.class)
	// private LocalDate dateInit;
	private String dateInit;

	// @JsonSerialize(using = ToStringSerializer.class)
	// private LocalDate dateEnd;
	private String dateEnd;

	public Integer getVaccinateState() {
		return vaccinateState;
	}

	public void setVaccinateState(Integer vaccinateState) {
		this.vaccinateState = vaccinateState;
	}

	public Integer getVaccinateType() {
		return vaccinateType;
	}

	public void setVaccinateType(Integer vaccinateType) {
		this.vaccinateType = vaccinateType;
	}

	public String getDateInit() {
		return dateInit;
	}

	public void setDateInit(String dateInit) {
		this.dateInit = dateInit;
	}

	public String getDateEnd() {
		return dateEnd;
	}

	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}

}
