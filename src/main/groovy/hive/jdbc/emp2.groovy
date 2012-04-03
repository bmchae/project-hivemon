import groovy.sql.Sql

sql = Sql.newInstance('jdbc:hive://localhost:10000/default', '', '', 'org.apache.hadoop.hive.jdbc.HiveDriver')
//sql = Sql.newInstance('jdbc:hive://', '', '', 'org.apache.hadoop.hive.jdbc.HiveDriver')

java.sql.Statement stmt = sql.connection.createStatement()
java.sql.ResultSet rs = stmt.executeQuery('select empno, ename from emp limit 10')

while (rs.next()) {
    println rs.getInt(1) + ' | ' + rs.getString(2)
}


//sql.eachRow('select empno, ename from emp') { emp ->
//    printf '%5d %s\n', emp.empno, emp.ename
//}

//sql.query('select empno, ename from emp') { rs ->
//    printf '%5d %s\n', rs.getInt(1), rs.getString(2)
//}
