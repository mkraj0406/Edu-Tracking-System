package com.jsp.ets.btach;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.validation.annotation.Validated;

import com.jsp.ets.config.GenerateSequenceId;
import com.jsp.ets.user.Student;
import com.jsp.ets.user.Subject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "batchs")
@EntityListeners(AuditingEntityListener.class)
public class Batch {
	
	@Id
	@GenerateSequenceId
	@Column(name = "batchId")
	private String batchId;
	
	@Column(name = "title")
	private String title;
	
	@Column(name = "begin_At")
	private LocalTime beginsAt;
	
	@Column(name = "end_At")
	private LocalTime endsAt;
	
	@Column(name = "starting_date")
	@CreatedDate
	private LocalDateTime startingDate;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "batch_status")
	private BatchStatus batchStatus;
	
	
	private List<Subject> subjects;
	
	
	@ManyToMany
	private List<Student> students;
}
