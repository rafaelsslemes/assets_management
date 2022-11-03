package com.spexcode.asset_management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spexcode.asset_management.api.AssetsService;
import com.spexcode.asset_management.model.Asset;
import com.spexcode.asset_management.model.dto.AssetDTO;

@SpringBootTest
class AssetManagementApplicationTests {
	
	@Autowired
	AssetsService service;

	@Test
	void registerAsset() {
		Asset registered = service.register(baseAsset());
		assertNotNull(registered.getId());
	}

	@Test
	void getAsset() {
		Asset registered = service.register(baseAsset());
		Optional<Asset> fromDB = service.getById(registered.getId());
		
		assertTrue(fromDB.isPresent());
		assertEquals(registered.getId(), fromDB.get().getId());
	}

	@Test
	void deleteAsset() {
		Asset registered = service.register(baseAsset());
		service.delete(registered.getId());
		Optional<Asset> fromDB = service.getById(registered.getId());
		
		assertFalse(fromDB.isPresent());
	}

	@Test
	void updateAsset() {
		Asset registered = service.register(baseAsset());
		String description = registered.getDescription();

		registered.setDescription("New Text");
		service.update(registered);

		Optional<Asset> fromDB = service.getById(registered.getId());
		
		assertEquals(registered.getId(), fromDB.get().getId());
		assertNotEquals(description, fromDB.get().getDescription());
	}

	@Test
	void listAssets() {
		for (int i = 0; i < 30; i++) {
			service.register(baseAsset());
		}

		List<AssetDTO> assets = service.getAllDTO();
		assertEquals(assets.size(), 30);
	}

	private Asset baseAsset() {
		Asset asset = new Asset();
		asset.setDescription("Trash Bucket");
		asset.setType("furniture");
		return asset;
	}


}
