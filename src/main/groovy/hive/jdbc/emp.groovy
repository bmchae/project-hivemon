import groovy.sql.Sql

sql = Sql.newInstance('jdbc:hive://localhost:10000/default', '', '', 'org.apache.hadoop.hive.jdbc.HiveDriver')

sql.eachRow('select empno, ename from emp') { emp ->
   printf '%5d %s\n', emp.empno, emp.ename
}

sql.query('select empno, ename from emp') { rs ->
   printf '%5d %s\n', rs.getInt(1), rs.getString(2)
}
