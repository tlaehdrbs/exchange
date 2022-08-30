package toy.project.exchange.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import toy.project.exchange.domain.Exchange;

public interface CurrencyExchangeRepository extends JpaRepository<Exchange, Integer> {
}
