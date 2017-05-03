package com.th.jbp.enums;

public enum ProcessStatus {

	NOT_PROCESSED(0), PROCESS_COMPLETED(1);
	int id;

	private ProcessStatus(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public static ProcessStatus get(int id) {
		for (ProcessStatus status : ProcessStatus.values()) {
			if (status.getId() == id)
				return status;
		}
		return null;
	}
}
