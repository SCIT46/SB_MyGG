package com.mygg.sb.match;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MetadataDTO
	{
		String  dataVersion;		// Match data version.
		String	matchId;			// Match id.
		List<String> participants; // A list of participant PUUIDs.
	}