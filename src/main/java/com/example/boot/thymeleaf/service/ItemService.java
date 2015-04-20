package com.example.boot.thymeleaf.service;

import com.example.boot.thymeleaf.dao.QueryParams;
import com.example.boot.thymeleaf.domain.Item;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class ItemService extends AbstractCrudService<Item> {

    public List<Item> findByName(final Optional<String> name) {
        if (name.isPresent()) {
            return dao.findWithNamedQuery(entityClass, "Item.findByName", QueryParams.with("name", name.get()));
        } else {
            return new ArrayList<>(0);
        }
    }
}
