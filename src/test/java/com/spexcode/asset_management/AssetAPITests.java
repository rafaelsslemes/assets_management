package com.spexcode.asset_management;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.spexcode.asset_management.api.AssetsService;
import com.spexcode.asset_management.model.Asset;
import com.spexcode.asset_management.model.dto.AssetDTO;

@SpringBootTest(classes = AssetManagementApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
class AssetAPITests {
	
@Autowired
protected TestRestTemplate rest;

	private ResponseEntity<AssetDTO> getItem(String url) {
		return rest.getForEntity(url, AssetDTO.class);
	}

	private ResponseEntity<List<AssetDTO>> getList(String url){
		return rest.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<AssetDTO>>(){});
	}

	@Autowired
	AssetsService service;

	@Test
	void listAssetsAPI() {
		for (int i = 0; i < 5; i++) {
			service.register(baseAsset());
		}

		ResponseEntity<List<AssetDTO>> response = getList("/assets/v1/items/dto");
		assertEquals(HttpStatus.OK, response.getStatusCode());

		List<AssetDTO> assets = response.getBody();
		assertNotNull(assets);
		assertEquals(5, assets.size());
	}

	@Test
	void getByTypeAPI() {
		
		for (int i = 0; i < 5; i++) {
			Asset base = baseAsset();
			base.setType("type1");
			service.register(base);
		}

		for (int i = 0; i < 3; i++) {
			Asset base = baseAsset();
			base.setType("type2");
			service.register(base);
		}

		ResponseEntity<List<AssetDTO>> responseType1 = getList("/assets/v1/items/type/type1");
		assertEquals(HttpStatus.OK, responseType1.getStatusCode());

		List<AssetDTO> assets = responseType1.getBody();
		assertNotNull(assets);
		assertEquals(5, assets.size());

		ResponseEntity<List<AssetDTO>> responseType2 = getList("/assets/v1/items/type/type2");
		assertEquals(HttpStatus.OK, responseType2.getStatusCode());

		assets = responseType2.getBody();
		assertNotNull(assets);
		assertEquals(3, assets.size());
	}

	@Test
	void getByTypeAPIEmpty() {

		ResponseEntity<List<AssetDTO>> response = getList("/assets/v1/items/type/typeX");
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

		List<AssetDTO> assets = response.getBody();
		assertNull(assets);
	}

	@Test
	void listAPINoneAsset() {

		ResponseEntity<List<AssetDTO>> response = getList("/assets/v1/items/dto");
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

		List<AssetDTO> assets = response.getBody();
		assertNull(assets);
	}

	@Test
	void getAssetAPI() {

		service.register(baseAsset());

		ResponseEntity<AssetDTO> response = getItem("/assets/v1/items/1");
		assertEquals(HttpStatus.OK, response.getStatusCode());

		AssetDTO asset = response.getBody();
		assertNotNull(asset);
		assertEquals(1, asset.getId());
	}

	@Test
	void getAssetAPINotFound() {

		ResponseEntity<AssetDTO> response = getItem("/assets/v1/items/1");
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());

		AssetDTO asset = response.getBody();
		assertNull(asset);
	}

	private Asset baseAsset() {
		Asset asset = new Asset();
		asset.setDescription("Trash Bucket");
		asset.setType("furniture");
		return asset;
	}


}
