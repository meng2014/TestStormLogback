# TestStormLogback
测试storm中使用的日志框架logback。storm0.9.5。logback版本为1.0.13。同时开启多个线程压测logback。
* 开启线程。格式：test11为项目名，storm为servlet名在web.xml中配置，t指定开启的线程数，s指定线程休眠的时间（毫秒）
* http://localhost:8080/test11/storm?t=10&s=0
* 停止线程。格式stop为过滤字段在servlet中指定。
* http://localhost:8080/test11/storm?stop
