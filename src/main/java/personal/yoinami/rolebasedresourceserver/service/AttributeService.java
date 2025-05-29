package personal.yoinami.rolebasedresourceserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import personal.yoinami.rolebasedresourceserver.model.Attribute;
import personal.yoinami.rolebasedresourceserver.model.ProductAttribute;
import personal.yoinami.rolebasedresourceserver.repository.AttributeRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AttributeService {

    @Autowired
    AttributeRepository attributeRepository;

    public Attribute saveOrElse(Attribute inputAttribute) throws Exception {
        if(inputAttribute.getName() == (null)) throw new Exception("bruh");
        Attribute attribute;

        try {
            attribute = attributeRepository.getByName(inputAttribute.getName())
                    .orElseThrow(() -> new Exception("Not Found"));
            return attribute;
        } catch (Exception e) {
            inputAttribute.setNullable(false);
            inputAttribute.setType("String");
            attribute = attributeRepository.save(inputAttribute);
            return attribute;
        }
    }
}
