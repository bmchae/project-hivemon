set hive.cli.print.header=true;
set hive.exec.reducers.bytes.per.reducer=100m;
set hive.input.format=org.apache.hadoop.hive.ql.io.HiveInputFormat;
add jar file:///home/nexr/nexr_platforms/flume/flume/lib/collector-event-1.0.0.1001-SNAPSHOT.jar;

SELECT dt, count(*) cnt
FROM wireless_voice_log
WHERE dt > date_sub(from_unixtime(unix_timestamp(), 'yyyy-MM-dd'), 8)
GROUP BY dt
ORDER BY dt;
--HAVING dt > date_sub(from_unixtime(unix_timestamp(), 'yyyy-MM-dd'), 8);
