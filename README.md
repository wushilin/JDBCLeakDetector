# JDBCLeakDetector
Detect your JDBC connection, statement, resultset leaks (where you forgot to close)


# Releases
- v1.0.2 Added creation age in debug log
- v1.0.0 Initial release


# Example use case
Download the jar at:

`https://s01.oss.sonatype.org/service/local/repo_groups/staging/content/net/wushilin/jdbcleakdetector/1.0.2/jdbcleakdetector-1.0.2.jar`

Put in your library classpath!

For tomcat, put in `$CATALINA_HOME`/lib/ folder

## Use as JNDI resource

Tomcat Server.xml
```xml
   <Resource name="datasource/test" auth="Container"
                    type="net.wushilin.jdbc.debug.DebuggingDataSource"
                    factory="org.apache.naming.factory.BeanFactory"
                            username="user"
                            password="pass1234"
                            url="jdbc:mysql://mysql.jungle:3306/testdb"
                            driverClass="com.mysql.jdbc.Driver"/>
```

Your webapp's context.xml
```xml
<ResourceLink name="datasource/test"
                global="datasource/test"
                auth="Container"
                type="javax.sql.DataSource" />
```

Use the datasource as if it is any other data source by your application


## Use as a wrapper of your data source

```java
DataSource myds = ...; // get your data source
DataSource debugger = new DebuggingDataSource(myds); // Create wrapper
Connection conn = debugger.getConnection(); // use debugger datasource as if it is your original datasource!
```

## Use in springboot

Old way
```java
@Bean(id='myds')
public DataSource getDataSource() {
   // create your ds here
}

```

New way
```java
public DataSource getDataSource() {
   // create your ds here
}

@Bean
public DataSource getDebuggerDataSource() {
   return new DebuggingDataSource(getDataSource());
} // use this DataSource instead!
```


# Triggering debug
Just create file /tmp/debug-data-source
Make sure:
- It is a regular file (empty or not, does not matter)
- It is readable and writable by the java process that runs DB connection
- Wait for up to 10 seconds
- Filanem

Creating the file:
```bash
$ touch /tmp/debug-data-source
```

Checking log:
```bash
$ ls -la /tmp/datasource-*.txt
```


Sample output:
```
Outstanding connections:
  Connection: com.mysql.cj.jdbc.ConnectionImpl@1b26ea1c
    at org.apache.jsp.jsp.test2_jsp._jspService(test2_jsp.java:133)
    at org.apache.jasper.runtime.HttpJspBase.service(HttpJspBase.java:70)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.jasper.servlet.JspServletWrapper.service(JspServletWrapper.java:466)
    at org.apache.jasper.servlet.JspServlet.serviceJspFile(JspServlet.java:379)
    at org.apache.jasper.servlet.JspServlet.service(JspServlet.java:327)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:209)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.filters.HttpHeaderSecurityFilter.doFilter(HttpHeaderSecurityFilter.java:126)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)
    at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
    at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:492)
    at org.apache.catalina.valves.RequestFilterValve.process(RequestFilterValve.java:355)
    at org.apache.catalina.valves.RemoteAddrValve.invoke(RemoteAddrValve.java:54)
    at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:130)
    at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
    at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:673)
    at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
    at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)
    at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:389)
    at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
    at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:926)
    at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1791)
    at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
    at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
    at java.base/java.lang.Thread.run(Thread.java:833)
  Connection: com.mysql.cj.jdbc.ConnectionImpl@2e8ba8c
    at org.apache.jsp.jsp.test2_jsp._jspService(test2_jsp.java:133)
    at org.apache.jasper.runtime.HttpJspBase.service(HttpJspBase.java:70)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.jasper.servlet.JspServletWrapper.service(JspServletWrapper.java:466)
    at org.apache.jasper.servlet.JspServlet.serviceJspFile(JspServlet.java:379)
    at org.apache.jasper.servlet.JspServlet.service(JspServlet.java:327)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:209)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.filters.HttpHeaderSecurityFilter.doFilter(HttpHeaderSecurityFilter.java:126)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)
    at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
    at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:492)
    at org.apache.catalina.valves.RequestFilterValve.process(RequestFilterValve.java:355)
    at org.apache.catalina.valves.RemoteAddrValve.invoke(RemoteAddrValve.java:54)
    at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:130)
    at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
    at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:673)
    at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
    at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)
    at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:389)
    at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
    at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:926)
    at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1791)
    at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
    at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
    at java.base/java.lang.Thread.run(Thread.java:833)
  Connection: com.mysql.cj.jdbc.ConnectionImpl@5c298a74
    at org.apache.jsp.jsp.test2_jsp._jspService(test2_jsp.java:133)
    at org.apache.jasper.runtime.HttpJspBase.service(HttpJspBase.java:70)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.jasper.servlet.JspServletWrapper.service(JspServletWrapper.java:466)
    at org.apache.jasper.servlet.JspServlet.serviceJspFile(JspServlet.java:379)
    at org.apache.jasper.servlet.JspServlet.service(JspServlet.java:327)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:209)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.filters.HttpHeaderSecurityFilter.doFilter(HttpHeaderSecurityFilter.java:126)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)
    at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
    at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:492)
    at org.apache.catalina.valves.RequestFilterValve.process(RequestFilterValve.java:355)
    at org.apache.catalina.valves.RemoteAddrValve.invoke(RemoteAddrValve.java:54)
    at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:130)
    at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
    at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:673)
    at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
    at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)
    at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:389)
    at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
    at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:926)
    at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1791)
    at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
    at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
    at java.base/java.lang.Thread.run(Thread.java:833)
Outstanding statements:
  Statement: com.mysql.cj.jdbc.StatementImpl@4cb40334
    at org.apache.jsp.jsp.test2_jsp._jspService(test2_jsp.java:134)
    at org.apache.jasper.runtime.HttpJspBase.service(HttpJspBase.java:70)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.jasper.servlet.JspServletWrapper.service(JspServletWrapper.java:466)
    at org.apache.jasper.servlet.JspServlet.serviceJspFile(JspServlet.java:379)
    at org.apache.jasper.servlet.JspServlet.service(JspServlet.java:327)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:209)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.filters.HttpHeaderSecurityFilter.doFilter(HttpHeaderSecurityFilter.java:126)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)
    at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
    at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:492)
    at org.apache.catalina.valves.RequestFilterValve.process(RequestFilterValve.java:355)
    at org.apache.catalina.valves.RemoteAddrValve.invoke(RemoteAddrValve.java:54)
    at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:130)
    at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
    at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:673)
    at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
    at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)
    at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:389)
    at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
    at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:926)
    at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1791)
    at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
    at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
    at java.base/java.lang.Thread.run(Thread.java:833)
  Statement: com.mysql.cj.jdbc.ClientPreparedStatement: insert into users(name, age) values('Name-84', 84)
    at org.apache.jsp.jsp.test2_jsp._jspService(test2_jsp.java:136)
    at org.apache.jasper.runtime.HttpJspBase.service(HttpJspBase.java:70)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.jasper.servlet.JspServletWrapper.service(JspServletWrapper.java:466)
    at org.apache.jasper.servlet.JspServlet.serviceJspFile(JspServlet.java:379)
    at org.apache.jasper.servlet.JspServlet.service(JspServlet.java:327)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:209)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.filters.HttpHeaderSecurityFilter.doFilter(HttpHeaderSecurityFilter.java:126)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)
    at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
    at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:492)
    at org.apache.catalina.valves.RequestFilterValve.process(RequestFilterValve.java:355)
    at org.apache.catalina.valves.RemoteAddrValve.invoke(RemoteAddrValve.java:54)
    at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:130)
    at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
    at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:673)
    at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
    at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)
    at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:389)
    at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
    at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:926)
    at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1791)
    at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
    at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
    at java.base/java.lang.Thread.run(Thread.java:833)
  Statement: com.mysql.cj.jdbc.ClientPreparedStatement: insert into users(name, age) values(** NOT SPECIFIED **, ** NOT SPECIFIED **)
    at org.apache.jsp.jsp.test2_jsp._jspService(test2_jsp.java:136)
    at org.apache.jasper.runtime.HttpJspBase.service(HttpJspBase.java:70)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.jasper.servlet.JspServletWrapper.service(JspServletWrapper.java:466)
    at org.apache.jasper.servlet.JspServlet.serviceJspFile(JspServlet.java:379)
    at org.apache.jasper.servlet.JspServlet.service(JspServlet.java:327)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:209)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.filters.HttpHeaderSecurityFilter.doFilter(HttpHeaderSecurityFilter.java:126)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)
    at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
    at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:492)
    at org.apache.catalina.valves.RequestFilterValve.process(RequestFilterValve.java:355)
    at org.apache.catalina.valves.RemoteAddrValve.invoke(RemoteAddrValve.java:54)
    at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:130)
    at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
    at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:673)
    at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
    at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)
    at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:389)
    at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
    at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:926)
    at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1791)
    at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
    at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
    at java.base/java.lang.Thread.run(Thread.java:833)
  Statement: com.mysql.cj.jdbc.StatementImpl@2b7a5bb
    at org.apache.jsp.jsp.test2_jsp._jspService(test2_jsp.java:134)
    at org.apache.jasper.runtime.HttpJspBase.service(HttpJspBase.java:70)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.jasper.servlet.JspServletWrapper.service(JspServletWrapper.java:466)
    at org.apache.jasper.servlet.JspServlet.serviceJspFile(JspServlet.java:379)
    at org.apache.jasper.servlet.JspServlet.service(JspServlet.java:327)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:209)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.filters.HttpHeaderSecurityFilter.doFilter(HttpHeaderSecurityFilter.java:126)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)
    at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
    at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:492)
    at org.apache.catalina.valves.RequestFilterValve.process(RequestFilterValve.java:355)
    at org.apache.catalina.valves.RemoteAddrValve.invoke(RemoteAddrValve.java:54)
    at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:130)
    at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
    at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:673)
    at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
    at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)
    at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:389)
    at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
    at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:926)
    at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1791)
    at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
    at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
    at java.base/java.lang.Thread.run(Thread.java:833)
  Statement: com.mysql.cj.jdbc.StatementImpl@7c394164
    at org.apache.jsp.jsp.test2_jsp._jspService(test2_jsp.java:134)
    at org.apache.jasper.runtime.HttpJspBase.service(HttpJspBase.java:70)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.jasper.servlet.JspServletWrapper.service(JspServletWrapper.java:466)
    at org.apache.jasper.servlet.JspServlet.serviceJspFile(JspServlet.java:379)
    at org.apache.jasper.servlet.JspServlet.service(JspServlet.java:327)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:209)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.filters.HttpHeaderSecurityFilter.doFilter(HttpHeaderSecurityFilter.java:126)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)
    at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
    at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:492)
    at org.apache.catalina.valves.RequestFilterValve.process(RequestFilterValve.java:355)
    at org.apache.catalina.valves.RemoteAddrValve.invoke(RemoteAddrValve.java:54)
    at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:130)
    at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
    at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:673)
    at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
    at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)
    at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:389)
    at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
    at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:926)
    at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1791)
    at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
    at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
    at java.base/java.lang.Thread.run(Thread.java:833)
  Statement: com.mysql.cj.jdbc.ClientPreparedStatement: insert into users(name, age) values('Name-490', 10)
    at org.apache.jsp.jsp.test2_jsp._jspService(test2_jsp.java:136)
    at org.apache.jasper.runtime.HttpJspBase.service(HttpJspBase.java:70)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.jasper.servlet.JspServletWrapper.service(JspServletWrapper.java:466)
    at org.apache.jasper.servlet.JspServlet.serviceJspFile(JspServlet.java:379)
    at org.apache.jasper.servlet.JspServlet.service(JspServlet.java:327)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:209)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.filters.HttpHeaderSecurityFilter.doFilter(HttpHeaderSecurityFilter.java:126)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)
    at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
    at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:492)
    at org.apache.catalina.valves.RequestFilterValve.process(RequestFilterValve.java:355)
    at org.apache.catalina.valves.RemoteAddrValve.invoke(RemoteAddrValve.java:54)
    at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:130)
    at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
    at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:673)
    at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
    at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)
    at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:389)
    at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
    at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:926)
    at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1791)
    at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
    at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
    at java.base/java.lang.Thread.run(Thread.java:833)
Outstanding resultsets:
  ResultSet: Result set representing update count of 655
    at org.apache.jsp.jsp.test2_jsp._jspService(test2_jsp.java:142)
    at org.apache.jasper.runtime.HttpJspBase.service(HttpJspBase.java:70)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.jasper.servlet.JspServletWrapper.service(JspServletWrapper.java:466)
    at org.apache.jasper.servlet.JspServlet.serviceJspFile(JspServlet.java:379)
    at org.apache.jasper.servlet.JspServlet.service(JspServlet.java:327)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:209)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.filters.HttpHeaderSecurityFilter.doFilter(HttpHeaderSecurityFilter.java:126)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)
    at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
    at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:492)
    at org.apache.catalina.valves.RequestFilterValve.process(RequestFilterValve.java:355)
    at org.apache.catalina.valves.RemoteAddrValve.invoke(RemoteAddrValve.java:54)
    at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:130)
    at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
    at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:673)
    at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
    at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)
    at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:389)
    at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
    at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:926)
    at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1791)
    at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
    at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
    at java.base/java.lang.Thread.run(Thread.java:833)
  ResultSet: Result set representing update count of 657
    at org.apache.jsp.jsp.test2_jsp._jspService(test2_jsp.java:142)
    at org.apache.jasper.runtime.HttpJspBase.service(HttpJspBase.java:70)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.jasper.servlet.JspServletWrapper.service(JspServletWrapper.java:466)
    at org.apache.jasper.servlet.JspServlet.serviceJspFile(JspServlet.java:379)
    at org.apache.jasper.servlet.JspServlet.service(JspServlet.java:327)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:209)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.filters.HttpHeaderSecurityFilter.doFilter(HttpHeaderSecurityFilter.java:126)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)
    at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
    at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:492)
    at org.apache.catalina.valves.RequestFilterValve.process(RequestFilterValve.java:355)
    at org.apache.catalina.valves.RemoteAddrValve.invoke(RemoteAddrValve.java:54)
    at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:130)
    at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
    at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:673)
    at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
    at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)
    at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:389)
    at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
    at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:926)
    at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1791)
    at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
    at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
    at java.base/java.lang.Thread.run(Thread.java:833)
  ResultSet: Result set representing update count of 661
    at org.apache.jsp.jsp.test2_jsp._jspService(test2_jsp.java:142)
    at org.apache.jasper.runtime.HttpJspBase.service(HttpJspBase.java:70)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.jasper.servlet.JspServletWrapper.service(JspServletWrapper.java:466)
    at org.apache.jasper.servlet.JspServlet.serviceJspFile(JspServlet.java:379)
    at org.apache.jasper.servlet.JspServlet.service(JspServlet.java:327)
    at javax.servlet.http.HttpServlet.service(HttpServlet.java:596)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:209)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.tomcat.websocket.server.WsFilter.doFilter(WsFilter.java:53)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.filters.HttpHeaderSecurityFilter.doFilter(HttpHeaderSecurityFilter.java:126)
    at org.apache.catalina.core.ApplicationFilterChain.internalDoFilter(ApplicationFilterChain.java:178)
    at org.apache.catalina.core.ApplicationFilterChain.doFilter(ApplicationFilterChain.java:153)
    at org.apache.catalina.core.StandardWrapperValve.invoke(StandardWrapperValve.java:167)
    at org.apache.catalina.core.StandardContextValve.invoke(StandardContextValve.java:90)
    at org.apache.catalina.authenticator.AuthenticatorBase.invoke(AuthenticatorBase.java:492)
    at org.apache.catalina.valves.RequestFilterValve.process(RequestFilterValve.java:355)
    at org.apache.catalina.valves.RemoteAddrValve.invoke(RemoteAddrValve.java:54)
    at org.apache.catalina.core.StandardHostValve.invoke(StandardHostValve.java:130)
    at org.apache.catalina.valves.ErrorReportValve.invoke(ErrorReportValve.java:93)
    at org.apache.catalina.valves.AbstractAccessLogValve.invoke(AbstractAccessLogValve.java:673)
    at org.apache.catalina.core.StandardEngineValve.invoke(StandardEngineValve.java:74)
    at org.apache.catalina.connector.CoyoteAdapter.service(CoyoteAdapter.java:343)
    at org.apache.coyote.http11.Http11Processor.service(Http11Processor.java:389)
    at org.apache.coyote.AbstractProcessorLight.process(AbstractProcessorLight.java:63)
    at org.apache.coyote.AbstractProtocol$ConnectionHandler.process(AbstractProtocol.java:926)
    at org.apache.tomcat.util.net.NioEndpoint$SocketProcessor.doRun(NioEndpoint.java:1791)
    at org.apache.tomcat.util.net.SocketProcessorBase.run(SocketProcessorBase.java:49)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1191)
    at org.apache.tomcat.util.threads.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:659)
    at org.apache.tomcat.util.threads.TaskThread$WrappingRunnable.run(TaskThread.java:61)
    at java.base/java.lang.Thread.run(Thread.java:833)
```


# Enjoy
