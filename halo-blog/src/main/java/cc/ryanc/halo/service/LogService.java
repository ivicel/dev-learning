package cc.ryanc.halo.service;

import cc.ryanc.halo.model.domain.Logs;
import cc.ryanc.halo.service.base.CrudService;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

public interface LogService extends CrudService<Logs, Long> {

    void save(String logTitle, String logContent, HttpServletRequest request);

    List<Logs> findLogsLatest();
}
