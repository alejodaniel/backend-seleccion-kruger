package com.kruger.enums;

public enum VaccinationStatusEnum {

	VACUNADO("VACUNADO"), NO_VACUNADO("NO_VACUNADO");

	private String label;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	private VaccinationStatusEnum(String label) {
		this.label = label;
	}
}