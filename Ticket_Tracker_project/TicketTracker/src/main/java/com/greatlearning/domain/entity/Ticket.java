/**
 * 
 */
package com.greatlearning.domain.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Ticket entity class which is a virtual representation of the Ticket Tracker
 * Application Database's "ticket" table in ticket_tracker schema
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "ticket", schema = "ticket_tracker")
public class Ticket {

	@Id
	@Column(name = "ticket_id", nullable = false)
	private int id;

	@Column(name = "title", nullable = false)
	private String ticketTitle;

	@Column(name = "descr", nullable = false)
	private String ticketShortDescription;

	@Column(name = "created_on", nullable = false)
	private Date createdOn;

}
