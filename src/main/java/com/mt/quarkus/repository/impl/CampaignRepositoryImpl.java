package com.mt.quarkus.repository.impl;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.mt.quarkus.ConnectionUtil;
import com.mt.quarkus.model.Campaign;
import com.mt.quarkus.repository.CampaignRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import lombok.extern.slf4j.Slf4j;

@ApplicationScoped
@Default
@Slf4j
public class CampaignRepositoryImpl implements CampaignRepository {

	
	
	@Override
	public List<Campaign> findAllCampaign() {

		List<Campaign> campaignList = new ArrayList<>();
		try (Connection connection = ConnectionUtil.getConnection();) {
			System.out.println("connection: "+connection);
			String sql = new StringBuilder().append("select * from campaigns limit 5 ").toString();
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet result = preparedStatement.executeQuery();
			System.out.println("result: "+result);
			System.out.println("result: "+result.next());
			while (result.next()) {
				Campaign campaign = new Campaign();
				campaign.setId(new BigInteger(result.getString("id")));
				campaign.setUserId(new BigInteger(result.getString("user_id")));
				campaign.setTitle(result.getString("title"));
				campaignList.add(campaign);
			}

			return campaignList;
		} catch (Exception e) {
			log.error("ERROR:{}", e);
		}
		return Collections.emptyList();
	}
}
