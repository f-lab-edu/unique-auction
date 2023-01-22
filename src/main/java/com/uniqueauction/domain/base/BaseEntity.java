package com.uniqueauction.domain.base;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
	@CreatedDate
	@Column(updatable = false)
	private LocalDateTime createdDate;

	@LastModifiedDate
	private LocalDateTime modifiedDate;

	@PrePersist
	public void onPrePersist() {
		this.createdDate = LocalDateTime.now();
		this.modifiedDate = this.createdDate;
	}

	@PreUpdate
	public void onPreUpdate() {
		this.modifiedDate = LocalDateTime.now();
	}
}
