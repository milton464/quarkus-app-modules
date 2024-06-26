package com.mt.quarkus.dto;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.UUID;

import com.mt.quarkus.model.enums.CampaignsEmailThreads;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class BECampaignDto {

	private BigInteger id;

	private String title;

	private String timeZone;

	private String settingInfos;
	
	private String status;
	
    private UUID uid;
    
    private BigInteger userId;
    
    private BigInteger teamId;
    
    private BigInteger agencyId;
    
    private String apiKey;
    
    private String commonEmailSubject;
    
    private CampaignsEmailThreads emailThreads;
    
    private LocalDateTime startTime;
    
    private LocalDateTime pauseTime;
    
    private LocalDateTime createdAt;
    
    private LocalDateTime updatedAt;

}
