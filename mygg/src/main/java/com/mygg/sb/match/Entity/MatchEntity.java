package com.mygg.sb.match.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@Builder

@Entity
@Table(name = "Matches")
public class MatchEntity
	{
		@ManyToOne
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
		private long match_id;
		
		@Column(nullable = false, unique = true)
		private String match_name;
		
		String jsonData;
	}