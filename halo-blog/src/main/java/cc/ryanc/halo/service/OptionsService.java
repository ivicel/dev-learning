package cc.ryanc.halo.service;

import cc.ryanc.halo.model.domain.Option;
import cc.ryanc.halo.service.base.CrudService;
import java.util.Map;

public interface OptionsService extends CrudService<Option, String> {

    void saveOption(String key, String value);

    Map<String, String> findAllOptions();
}
