package com.mygg.sb.match;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MatchDTO {
	private MatchInfoDTO Info;
	private MetadataDTO Metadata;
}
