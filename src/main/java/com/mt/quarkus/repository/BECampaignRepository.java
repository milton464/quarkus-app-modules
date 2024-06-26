package com.mt.quarkus.repository;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.mt.quarkus.dto.BECampaignDto;
import com.mt.quarkus.model.tables.pojos.Campaigns;

/**
 * @author MILTON
 */
public interface BECampaignRepository {

	Optional<BECampaignDto> findCampaignById(BigInteger id);
	
	List<Campaigns> getCampaign(BigInteger agencyId);
	
	boolean update(BigInteger id, BigInteger agencyId,  Map<String, Object> fieldMap);

	Optional<BigInteger> addCampaign(Campaigns campaign);
	
}
