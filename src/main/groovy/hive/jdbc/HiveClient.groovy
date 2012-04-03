import org.apache.hadoop.hive.conf.HiveConf
import org.apache.hadoop.hive.ql.Driver
import org.apache.hadoop.hive.ql.metadata.Hive

HiveConf conf = new HiveConf(Hive.class)
Driver driver = new Driver(conf)

driver.run("load data local inpath './data/emp.' into table emp")

String USER_QUERY = 'select empno, ename from emp'
	
int ret = driver.run(USER_QUERY)
Vector<String> res = new Vector<String>()

while (driver.getResults(res)) {
     println res.getString(1)
}