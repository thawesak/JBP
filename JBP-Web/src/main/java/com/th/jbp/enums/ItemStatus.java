package com.th.jbp.enums;

public enum ItemStatus {

	STOCK_NEW(11L), 
	USED(12L),
	STOCK_REUSED(60L),
	CARCASS(61L);
	
	Long id;

	private ItemStatus(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public static ItemStatus get(Long id) {
		for (ItemStatus status : ItemStatus.values()) {
			if (status.getId() == id)
				return status;
		}
		return null;
	}
}
