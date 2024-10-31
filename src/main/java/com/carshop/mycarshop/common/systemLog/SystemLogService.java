package com.carshop.mycarshop.common.systemLog;

public interface SystemLogService {

    //UserDTO findByUserName(String userName);

    void systemLog(String... logMessages );
}
