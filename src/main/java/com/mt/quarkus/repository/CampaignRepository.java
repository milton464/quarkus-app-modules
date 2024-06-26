package com.mt.quarkus.repository;

import java.util.List;

import com.mt.quarkus.model.Campaign;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public interface CampaignRepository {
	
	public List<Campaign> findAllCampaign();
}
