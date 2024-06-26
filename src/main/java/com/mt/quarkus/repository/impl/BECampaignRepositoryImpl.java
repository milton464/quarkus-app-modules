package com.mt.quarkus.repository.impl;

import static com.mt.quarkus.model.tables.Campaigns.CAMPAIGNS;

import java.math.BigInteger;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import com.mt.quarkus.ConnectionUtil;
import com.mt.quarkus.dto.BECampaignDto;
import com.mt.quarkus.model.records.CampaignsRecord;
import com.mt.quarkus.model.tables.pojos.Campaigns;
import com.mt.quarkus.repository.BECampaignRepository;

import org.jooq.DSLContext;
import org.jooq.SQLDialect;
import org.jooq.impl.DSL;
import org.jooq.types.ULong;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Default;
import lombok.extern.slf4j.Slf4j;

/**
 * @author MILTON
 */
@Slf4j
@ApplicationScoped
@Default
public class BECampaignRepositoryImpl implements BECampaignRepository {
	
	public static final String ERROR = "ERROR: {}";
	
	
	@Override
	public Optional<BECampaignDto> findCampaignById(BigInteger id) {
		if (id == null) {
			return Optional.empty();
		}

		try (Connection connection = ConnectionUtil.getConnection()){
			DSLContext dslContext = DSL.using(connection, SQLDialect.MYSQL);
			
			BECampaignDto campaign = dslContext.select(CAMPAIGNS.ID, 
					CAMPAIGNS.TITLE, 
					CAMPAIGNS.STATUS, 
					CAMPAIGNS.SETTING_INFOS,
					CAMPAIGNS.COMMON_EMAIL_SUBJECT,
					CAMPAIGNS.EMAIL_THREADS)
					.from(CAMPAIGNS)
					.where(CAMPAIGNS.ID.eq(ULong.valueOf(id))).fetchOneInto(BECampaignDto.class);
			
			return Optional.ofNullable(campaign);
			
		} catch (Exception e) {
			log.error(ERROR, e);
		}
		
		return Optional.empty();
	}
	
	@Override
	public List<Campaigns> getCampaign(BigInteger agencyId) {
		if (agencyId == null) {
			return Collections.emptyList();
		}

		try (Connection connection = ConnectionUtil.getConnection()){
			DSLContext dslContext = DSL.using(connection, SQLDialect.MYSQL);
			
			return dslContext.select(CAMPAIGNS)
					.from(CAMPAIGNS)
					.where(CAMPAIGNS.AGENCY_ID.eq(ULong.valueOf(agencyId))).fetchInto(Campaigns.class);
			
		} catch (Exception e) {
			log.error(ERROR, e);
		}
		
		return Collections.emptyList();
	}
	
	
	
	@Override
	public boolean update(BigInteger id, BigInteger agencyId, Map<String, Object> fieldMap) {
		if (id == null || fieldMap == null || fieldMap.isEmpty()) {
			return false;
		}
		
		try (Connection connection = ConnectionUtil.getConnection()){
			DSLContext dslContext = DSL.using(connection, SQLDialect.MYSQL);
			
			fieldMap.put("updated_at", LocalDateTime.now());
			
			return dslContext.update(CAMPAIGNS).set(fieldMap).where(CAMPAIGNS.ID.equal(ULong.valueOf(id))).execute() > 0;
			
		} catch (Exception e) {
			log.error(ERROR, e);
		}
		
		return false;
	}


	
	@Override
	public Optional<BigInteger> addCampaign(Campaigns campaign) {
		UUID uid = UUID.randomUUID();

		try (Connection connection = ConnectionUtil.getConnection()) {
			DSLContext dslContext = DSL.using(connection, SQLDialect.MYSQL);
			CampaignsRecord connectedEmailsRecord = dslContext
					.insertInto(CAMPAIGNS,
							CAMPAIGNS.UID,
							CAMPAIGNS.USER_ID,
							CAMPAIGNS.TEAM_ID,
							CAMPAIGNS.AGENCY_ID, 
							CAMPAIGNS.TITLE,
							CAMPAIGNS.TIMEZONE,
							CAMPAIGNS.STATUS, 
							CAMPAIGNS.IS_FAVOURITE, 
							CAMPAIGNS.EMAIL_THREADS, 
							CAMPAIGNS.COMMON_EMAIL_SUBJECT, 
							CAMPAIGNS.API_KEY, 
							CAMPAIGNS.START_TIME, 
							CAMPAIGNS.PAUSE_TIME, 
							CAMPAIGNS.SETTING_INFOS, 
							CAMPAIGNS.CREATED_AT,
							CAMPAIGNS.UPDATED_AT)
					.values(uid, 
							campaign.getUserId(), 
							campaign.getTeamId(),
							campaign.getAgencyId(),
							campaign.getTitle(),
							campaign.getTimezone(),
							campaign.getStatus(), 
							campaign.getIsFavourite(),
							campaign.getEmailThreads(),
							campaign.getCommonEmailSubject(),
							campaign.getApiKey(),
							campaign.getStartTime(),
							campaign.getPauseTime(),
							campaign.getSettingInfos(),
							LocalDateTime.now(), 
							LocalDateTime.now())
					.returning(CAMPAIGNS.ID).fetchOne();

			return Optional.ofNullable(connectedEmailsRecord.getValue(CAMPAIGNS.ID).toBigInteger());

		} catch (Exception e) {
			log.error(ERROR, e);
		}

		return Optional.empty();
	}


}
