# Zog-zog Akita !

Ce petit jar exécutable démarre un service REST sur le port 8080

Le service expose une collection d'entités *Toto*. Chaque **Toto** dispose d'une liste de **Zogzog**s. Chaque *Zogzog* a une valeur textuelle.

Il y a 3 entités **Toto** identifiable par leur id : 1, 2 et 3.

Ce service expose 3 endpoints (toujours méthode GET pour simplifier l'usage avec un navigateur) :
* `GET toto` : récupère la liste des entités **Toto**
* `GET toto/add/{id}/{value}` : ajoute à l'entité **Toto** désignée par son {id} un **zogzog** de valeur {value}. Retourne l'entité avec son nouveau **zogzog**.
* `GET toto/cancel/{id}` : annule une entité **Toto** (c'est à dire supprime tous ses **zogzog**). Retourne l'entité telle qu'elle était avant annulation. Affiche également une trace dans la sortie standard du serveur.

## Observation

Le résultat de `GET toto/cancel/{id}` varie en fonction du délai de la méthode asynchrone `StdOutNotifService.notifyCanceled`.

* Délai de 1ms => l'entité **Toto** retournée a tous ses **Zogzog**s (état _avant_ suppression des **Zogzog**s)
* Délai de ~10ms => exception intermittente (v. Annexe)
* Délai de 1000ms => l'entité **Toto** retournée n'a plus aucun **Zogzog** (état _après_ suppression des **Zogzog**s)

# Annexe

```
org.hibernate.exception.GenericJDBCException: could not initialize a collection: [com.example.demo.Toto.zogzogs#1]
	at org.hibernate.exception.internal.StandardSQLExceptionConverter.convert(StandardSQLExceptionConverter.java:47) ~[hibernate-core-5.4.12.Final.jar:5.4.12.Final]
	at org.hibernate.engine.jdbc.spi.SqlExceptionHelper.convert(SqlExceptionHelper.java:113) ~[hibernate-core-5.4.12.Final.jar:5.4.12.Final]
	at org.hibernate.loader.collection.plan.AbstractLoadPlanBasedCollectionInitializer.initialize(AbstractLoadPlanBasedCollectionInitializer.java:97) ~[hibernate-core-5.4.12.Final.jar:5.4.12.Final]
	at org.hibernate.persister.collection.AbstractCollectionPersister.initialize(AbstractCollectionPersister.java:710) ~[hibernate-core-5.4.12.Final.jar:5.4.12.Final]
	at org.hibernate.event.internal.DefaultInitializeCollectionEventListener.onInitializeCollection(DefaultInitializeCollectionEventListener.java:76) ~[hibernate-core-5.4.12.Final.jar:5.4.12.Final]
	at org.hibernate.event.service.internal.EventListenerGroupImpl.fireEventOnEachListener(EventListenerGroupImpl.java:102) ~[hibernate-core-5.4.12.Final.jar:5.4.12.Final]
	at org.hibernate.internal.SessionImpl.initializeCollection(SessionImpl.java:2153) ~[hibernate-core-5.4.12.Final.jar:5.4.12.Final]
	at org.hibernate.collection.internal.AbstractPersistentCollection$4.doWork(AbstractPersistentCollection.java:589) ~[hibernate-core-5.4.12.Final.jar:5.4.12.Final]
	at org.hibernate.collection.internal.AbstractPersistentCollection.withTemporarySessionIfNeeded(AbstractPersistentCollection.java:264) ~[hibernate-core-5.4.12.Final.jar:5.4.12.Final]
	at org.hibernate.collection.internal.AbstractPersistentCollection.initialize(AbstractPersistentCollection.java:585) ~[hibernate-core-5.4.12.Final.jar:5.4.12.Final]
	at org.hibernate.collection.internal.AbstractPersistentCollection.read(AbstractPersistentCollection.java:149) ~[hibernate-core-5.4.12.Final.jar:5.4.12.Final]
	at org.hibernate.collection.internal.PersistentBag.toString(PersistentBag.java:621) ~[hibernate-core-5.4.12.Final.jar:5.4.12.Final]
	at java.base/java.util.Formatter$FormatSpecifier.printString(Formatter.java:3031) ~[na:na]
	at java.base/java.util.Formatter$FormatSpecifier.print(Formatter.java:2908) ~[na:na]
	at java.base/java.util.Formatter.format(Formatter.java:2673) ~[na:na]
	at java.base/java.util.Formatter.format(Formatter.java:2609) ~[na:na]
	at java.base/java.lang.String.format(String.java:2897) ~[na:na]
	at com.example.demo.StdOutNotifService.notifyCanceled(StdOutNotifService.java:21) ~[classes/:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke0(Native Method) ~[na:na]
	at java.base/jdk.internal.reflect.NativeMethodAccessorImpl.invoke(NativeMethodAccessorImpl.java:62) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:566) ~[na:na]
	at org.springframework.aop.support.AopUtils.invokeJoinpointUsingReflection(AopUtils.java:344) ~[spring-aop-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.invokeJoinpoint(ReflectiveMethodInvocation.java:198) ~[spring-aop-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.aop.framework.ReflectiveMethodInvocation.proceed(ReflectiveMethodInvocation.java:163) ~[spring-aop-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at org.springframework.aop.interceptor.AsyncExecutionInterceptor.lambda$invoke$0(AsyncExecutionInterceptor.java:115) ~[spring-aop-5.2.5.RELEASE.jar:5.2.5.RELEASE]
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1128) ~[na:na]
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:628) ~[na:na]
	at java.base/java.lang.Thread.run(Thread.java:834) ~[na:na]
Caused by: org.h2.jdbc.JdbcSQLNonTransientException: L'objet est déjà fermé
The object is already closed [90007-200]
	at org.h2.message.DbException.getJdbcSQLException(DbException.java:505) ~[h2-1.4.200.jar:1.4.200]
	at org.h2.message.DbException.getJdbcSQLException(DbException.java:429) ~[h2-1.4.200.jar:1.4.200]
	at org.h2.message.DbException.get(DbException.java:205) ~[h2-1.4.200.jar:1.4.200]
	at org.h2.message.DbException.get(DbException.java:181) ~[h2-1.4.200.jar:1.4.200]
	at org.h2.message.DbException.get(DbException.java:170) ~[h2-1.4.200.jar:1.4.200]
	at org.h2.jdbc.JdbcResultSet.checkClosed(JdbcResultSet.java:3223) ~[h2-1.4.200.jar:1.4.200]
	at org.h2.jdbc.JdbcResultSet.next(JdbcResultSet.java:134) ~[h2-1.4.200.jar:1.4.200]
	at com.zaxxer.hikari.pool.HikariProxyResultSet.next(HikariProxyResultSet.java) ~[HikariCP-3.4.2.jar:na]
	at org.hibernate.loader.plan.exec.process.internal.ResultSetProcessorImpl.extractResults(ResultSetProcessorImpl.java:119) ~[hibernate-core-5.4.12.Final.jar:5.4.12.Final]
	at org.hibernate.loader.plan.exec.internal.AbstractLoadPlanBasedLoader.executeLoad(AbstractLoadPlanBasedLoader.java:105) ~[hibernate-core-5.4.12.Final.jar:5.4.12.Final]
	at org.hibernate.loader.collection.plan.AbstractLoadPlanBasedCollectionInitializer.initialize(AbstractLoadPlanBasedCollectionInitializer.java:87) ~[hibernate-core-5.4.12.Final.jar:5.4.12.Final]
	... 27 common frames omitted
```
