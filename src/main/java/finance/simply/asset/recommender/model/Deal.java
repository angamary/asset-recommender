package finance.simply.asset.recommender.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "deal")
public class Deal {

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Integer id;

  @Column(name = "start_date")
  private LocalDate startDate;

  @Column(name = "end_date")
  private LocalDate endDate;

  @ManyToMany(fetch = FetchType.LAZY, mappedBy = "deals")
  private List<Customer> customers;

  @OneToMany(mappedBy = "deal")
  private List<Asset> assets;

  public Deal() {
  }

  public Deal(Integer id, LocalDate startDate, LocalDate endDate, List<Customer> customers, List<Asset> assets) {
    this.id = id;
    this.startDate = startDate;
    this.endDate = endDate;
    this.customers = customers;
    this.assets = assets;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public void setStartDate(LocalDate startDate) {
    this.startDate = startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public void setEndDate(LocalDate endDate) {
    this.endDate = endDate;
  }

  public List<Customer> getCustomers() {
    return customers;
  }

  public void setCustomers(List<Customer> customers) {
    this.customers = customers;
  }

  public List<Asset> getAssets() {
    return assets;
  }

  public void setAssets(List<Asset> assets) {
    this.assets = assets;
  }

}
