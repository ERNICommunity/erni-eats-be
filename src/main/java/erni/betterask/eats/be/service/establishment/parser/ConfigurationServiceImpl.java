package erni.betterask.eats.be.service.establishment.parser;

import erni.betterask.eats.be.model.Establishment;
import erni.betterask.eats.be.service.establishment.ConfigurationService;
import io.vavr.collection.List;
import io.vavr.control.Option;
import org.springframework.stereotype.Service;

@Service("parsedService")
public class ConfigurationServiceImpl implements ConfigurationService {

    private static final EstablishmentParser establishmentParser = new EstablishmentParser();

    @Override
    public List<Establishment> findAll() {
        return establishments;
    }

    @Override
    public Option<Establishment> findById(String id) {
        return establishments.find(establishment -> establishment.id.equals(id));
    }

    private static final List<Establishment> establishments = List.of(
            establishmentParser.getEstablishmentParsed()
    );

}
