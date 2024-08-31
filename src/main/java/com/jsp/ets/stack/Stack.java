package com.jsp.ets.stack;

import java.util.List;

import com.jsp.ets.user.Subject;




public enum Stack {
	
	JAVA_FULL_STACK(List.of(
			Subject.CORE_JAVA,
			Subject.JDBC,
			Subject.SERVLET,
			Subject.HIBERNATE,
			Subject.SPRING,
			Subject.SPRING_BOOT,
			Subject.HTML,
			Subject.CSS,
			Subject.JAVASCRIPT
			)),
	MERN_STACK(List.of(
			Subject.HTML,
			Subject.CSS,
			Subject.JAVASCRIPT
			)),
	PYTHON_STACK(List.of(
			Subject.PYTHON,
			Subject.SALENIUM,
			Subject.HTML,
			Subject.CSS,
			Subject.JAVASCRIPT
			));
	
	private List<Subject> subjects;

	private Stack(List<Subject> subjects) {
		this.subjects = subjects;
	}

	public List<Subject> getSubjects() {
		return subjects;
	}

	
	
}
