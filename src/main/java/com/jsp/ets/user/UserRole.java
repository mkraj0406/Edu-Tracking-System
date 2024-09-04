package com.jsp.ets.user;


public enum UserRole {

	SUPER_ADMIN(java.util.List.of(
			Privilege.WRITE_ADMIN,
			Privilege.WRITE_HR,
			Privilege.WRITE_TRAINER,
			Privilege.READ_USER,
			Privilege.READ_BATCH,
			Privilege.READ_MOCK,
			Privilege.READ_RATING,
			Privilege.READ_REQURIENTMENT,
			Privilege.READ_SCHEDULE,
			Privilege.READ_TECH_REPORT
			)),
	ADMIN(java.util.List.of(
			Privilege.WRITE_HR,
			Privilege.WRITE_TRAINER,
			Privilege.WRITE_STUDENT,
			Privilege.READ_USER,
			Privilege.READ_BATCH,
			Privilege.READ_MOCK,
			Privilege.READ_RATING,
			Privilege.READ_REQURIENTMENT,
			Privilege.READ_SCHEDULE,
			Privilege.READ_TECH_REPORT
			
			)), 
	HR(java.util.List.of(
					Privilege.WRITE_HR,
					Privilege.WRITE_STUDENT,
					Privilege.WRITE_BATCH,
					Privilege.WRITE_REQURIENTMENT,
					Privilege.WRITE_SCHEDULE,
					Privilege.READ_USER,
					Privilege.READ_BATCH,
					Privilege.READ_MOCK,
					Privilege.READ_RATING,
					Privilege.READ_REQURIENTMENT,
					Privilege.READ_SCHEDULE,
					Privilege.READ_TECH_REPORT
					
					)),
	TRAINER(java.util.List.of(
			Privilege.WRITE_STUDENT,
			Privilege.WRITE_MOCK,
			Privilege.WRITE_RATING,
			Privilege.WRITE_TECH_REPORT,
			Privilege.READ_USER,
			Privilege.READ_BATCH,
			Privilege.READ_MOCK,
			Privilege.READ_RATING,
			Privilege.READ_REQURIENTMENT,
			Privilege.READ_SCHEDULE,
			Privilege.READ_TECH_REPORT
			
			)), STUDENT(java.util.List.of(
					Privilege.WRITE_STUDENT,
					Privilege.READ_BATCH,
					Privilege.READ_MOCK,
					Privilege.READ_RATING,
					Privilege.READ_REQURIENTMENT,
					Privilege.READ_SCHEDULE,
					Privilege.READ_TECH_REPORT
					
					));

	private java.util.List<Privilege> privileges;

	private UserRole(java.util.List<Privilege> privileges) {
		this.privileges = privileges;
	}

	

	public java.util.List<Privilege> getUserRole() {
		return privileges;
	}

}
