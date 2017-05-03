package com.th.jbp.enums;

public enum Role {

	MD(1L), 
	SENIOR_MANAGER(2L), 
	MANAGER(3L), 
	SENIOR_OFFICER(4L), 
	OFFICER_HEAD_OFFICE(5L), 
	OFFICER_BANGSAI(6L),
	TECHNICIAN(7L), 
	DRIVER(8L);
	
	Long id;

	private Role(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public static Role get(Long id) {
		for (Role status : Role.values()) {
			if (status.getId() == id)
				return status;
		}
		return null;
	}
}
