package cc.ryanc.halo.service.impl;

import cc.ryanc.halo.model.domain.Option;
import cc.ryanc.halo.repository.OptionRepository;
import cc.ryanc.halo.service.OptionsService;
import cc.ryanc.halo.service.base.AbstractCrudService;
import cn.hutool.core.util.StrUtil;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("optionsService")
public class OptionsServiceImpl extends AbstractCrudService<Option, String> implements OptionsService {
    private OptionRepository optionRepository;

    public OptionsServiceImpl(OptionRepository optionRepository) {
        super(optionRepository);
        this.optionRepository = optionRepository;
    }

    @Override
    public void saveOption(String key, String value) {
        if (StrUtil.equals(value, "")) {
            removeByID(key);
        } else {
            Option option = fetchById(key).map(o -> {
                o.setOptionValue(value);
                return o;
            }).orElseGet(() -> {
                Option o = new Option();
                o.setOptionName(key);
                o.setOptionValue(value);
                return o;
            });
        }
    }

    @Override
    public Map<String, String> findAllOptions() {
        List<Option> options = optionRepository.findAll();
        Map<String, String> map = new HashMap<>();
        options.forEach(o -> map.putIfAbsent(o.getOptionName(), o.getOptionValue()));

        return map;
    }
}
