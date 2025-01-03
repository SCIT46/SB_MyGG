package com.mygg.sb.match.entity;

import java.util.List;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MMetadataEntity
	{
		String  dataVersion;		// Match data version.
		String	matchId;			// Match id.
		List<String> participants; // A list of participant PUUIDs.
	}