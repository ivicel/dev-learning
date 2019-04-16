package cc.ryanc.halo.listener;

import cc.ryanc.halo.model.domain.Option;
import cc.ryanc.halo.model.dto.HaloConst;
import cc.ryanc.halo.service.OptionsService;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
public class StartedListener implements ApplicationListener<ApplicationStartedEvent> {
    private OptionsService optionsService;

    @Autowired
    public StartedListener(OptionsService optionsService) {
        this.optionsService = optionsService;
    }

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        loadOptions();
    }

    private void loadOptions() {
        List<Option> options = optionsService.listAll();
        options.forEach(o -> HaloConst.OPTIONS.put(o.getOptionName(), o.getOptionValue()));
    }
}
