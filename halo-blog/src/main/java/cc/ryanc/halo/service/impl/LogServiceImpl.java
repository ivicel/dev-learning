package cc.ryanc.halo.service.impl;

import cc.ryanc.halo.model.domain.Logs;
import cc.ryanc.halo.repository.LogRepository;
import cc.ryanc.halo.service.LogService;
import cc.ryanc.halo.service.base.AbstractCrudService;
import cn.hutool.extra.servlet.ServletUtil;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

@Service("logService")
public class LogServiceImpl extends AbstractCrudService<Logs, Long> implements LogService {
    private LogRepository logRepository;

    public LogServiceImpl(LogRepository logRepository) {
        super(logRepository);
        this.logRepository = logRepository;
    }

    @Override
    public void save(String logTitle, String logContent, HttpServletRequest request) {
        Logs logs = new Logs();
        logs.setLogTitle(logTitle);
        logs.setLogContent(logContent);
        logs.setLogIp(ServletUtil.getClientIP(request));
        logRepository.save(logs);
    }

    @Override
    public List<Logs> findLogsLatest() {
        return logRepository.findAll(Sort.by(Direction.DESC, "logCreated"));
    }
}
