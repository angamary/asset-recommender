package finance.simply.asset.recommender.service;

import finance.simply.asset.recommender.model.Asset;
import finance.simply.asset.recommender.model.Customer;
import finance.simply.asset.recommender.model.Deal;
import finance.simply.asset.recommender.repository.CustomerRepository;
import finance.simply.asset.recommender.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;


@Service
public class RecommendationService {

  private final DealRepository dealRepository;

  private final CustomerRepository customerRepository;

  private final AssetService assetService;

  @Autowired
  public RecommendationService(DealRepository dealRepository, CustomerRepository customerRepository,
                               AssetService assetService) {
    this.dealRepository = dealRepository;
    this.customerRepository = customerRepository;
    this.assetService = assetService;
  }

  public Map<Customer, List<Asset>> getRecommendations(LocalDate date) {
    List<Deal> deals = dealRepository.findAll();
    List<Customer> customers = customerRepository.findAll();

    Map<Customer, List<Asset>> recommendations = new HashMap<>();

    for (Customer customer : customers) {
      double currentPayment = 0.0;
      List<Asset.Type> customerTypes = new ArrayList<>();

      for (Deal deal : deals) {
        if (deal.getCustomers().contains(customer)) {
          if (!date.isBefore(deal.getStartDate()) && !date.isAfter(deal.getEndDate())) {
            int numCustomers = deal.getCustomers().size();
            double totalAssetCost =
                    deal.getAssets().stream().reduce(0.0, (subTotal, asset) -> subTotal + asset.getCost(), Double::sum);
            currentPayment += totalAssetCost / numCustomers;
          }

          customerTypes.addAll(deal.getAssets().stream().map(asset -> asset.getType()).collect(Collectors.toList()));
        }
      }

      List<Asset> unsoldAssets = assetService.getUnsoldAssets();
      for (Asset asset : unsoldAssets) {
        if (asset.getCost() <= customer.getAffordability() - currentPayment &&
            customerTypes.contains(asset.getType())) {
          recommendations.computeIfAbsent(customer, cust -> new ArrayList<>()).add(asset);
        }
      }
    }

    return recommendations;
  }

}