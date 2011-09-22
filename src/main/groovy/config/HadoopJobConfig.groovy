package config

class HadoopJobConfig {
	static map = [:]
	
	static {
		map['mapred.job.name'] = ''
		map['hive.query.string'] = ''
		map['mapred.job.reuse.jvm.num.tasks'] = 1
		map['mapred.reduce.tasks.speculative.execution'] = true
		map['hive.mapjoin.maxsize'] = true
		map['hive.input.format'] = 'org.apache.hadoop.hive.ql.io.HiveInputFormat'
		map['io.sort.spill.percent'] = 0.80
		map['mapred.compress.map.output'] = false
		map['mapred.tasktracker.reduce.tasks.maximum'] = 2
		map['hive.optimize.skewjoin'] = false
		map['hive.mapred.reduce.tasks.speculative.execution'] = true
		map['hive.exec.parallel.thread.number'] = 8
		map['dfs.replication'] = 3
		map['hive.query.id'] = ''
		map['io.file.buffer.size'] = 4096
		map['mapred.map.max.attempts'] = 4
		map['hive.optimize.groupby'] = true
		map['hive.exec.dynamic.partition'] = false
		map['dfs.block.size'] = 67108864
		map['hive.exec.reducers.max'] = 999
		map['mapred.tasktracker.map.tasks.maximum'] = 2
		map['hive.optimize.pruner'] = true
		map['io.mapfile.bloom.size'] = 1048576
		map['mapred.child.java.opts'] = '-Xmx200m'
		map['mapred.input.format.class'] = 'org.apache.hadoop.hive.ql.io.HiveInputFormat'
		map['hive.optimize.ppd'] = true
		map['hive.exec.parallel'] = false
		map['dfs.namenode.handler.count'] = 2
		map['mapred.map.tasks'] = 2
		map['mapred.min.split.size'] = 0
		map['mapred.reduce.parallel.copies'] = 5
		map['io.sort.factor'] = 10
		map['mapred.task.timeout'] = 600000
		map['hive.optimize.cp'] = true
		map['tasktracker.http.threads'] = 40
		map['mapred.output.compress'] = false
		map['hive.exec.compress.output'] = false
		map['dfs.datanode.du.reserved'] = 0
		map['hive.exec.compress.intermediate'] = false
		map['dfs.datanode.handler.count'] = 3
	}
}