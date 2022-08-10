package finance.simply.asset.recommender.service;

import finance.simply.asset.recommender.model.Asset;
import finance.simply.asset.recommender.model.Deal;
import finance.simply.asset.recommender.repository.AssetRepository;
import finance.simply.asset.recommender.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AssetService {

  private final AssetRepository assetRepository;

  private final DealRepository dealRepository;

  @Autowired
  public AssetService(AssetRepository assetRepository, DealRepository dealRepository) {
    this.assetRepository = assetRepository;
    this.dealRepository = dealRepository;
  }


  public List<Asset> getUnsoldAssets() {
    List<Deal> deals = dealRepository.findAll();
    List<Asset> assets = assetRepository.findAll();
    List<Asset> unsoldAssets = new ArrayList<>(assets);

    for (Deal deal : deals) {
      for (Asset asset : deal.getAssets()) {
        unsoldAssets.remove(asset);
      }
    }

    return unsoldAssets;
  }

}
