package com.th.jbp.enums;

public enum Status {

	ACTIVE(1L), INACTIVE(2L), DELETED(3L);
	Long id;

	private Status(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public static Status get(Long id) {
		for (Status status : Status.values()) {
			if (status.getId() == id)
				return status;
		}
		return null;
	}
}
