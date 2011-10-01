package config

class HadoopJobConfig {
	static map = [:]
	
	static {
		map['dfs.block.size'] = 67108864
		map['dfs.datanode.du.reserved'] = 0
		map['dfs.datanode.handler.count'] = 3
		map['dfs.namenode.handler.count'] = 2
		map['dfs.replication'] = 3
		map['hive.auto.convert.join'] = true
		map['hive.exec.compress.intermediate'] = false
		map['hive.exec.compress.output'] = false
		map['hive.exec.dynamic.partition'] = false
		map['hive.exec.parallel'] = false
		map['hive.exec.parallel.thread.number'] = 8
		map['hive.exec.reducers.max'] = 999
		map['hive.input.format'] = 'org.apache.hadoop.hive.ql.io.HiveInputFormat'
		map['hive.mapjoin.maxsize'] = true
		map['hive.mapjoin.smalltable.filesize'] = 25000000
		map['hive.mapred.reduce.tasks.speculative.execution'] = true
		map['hive.optimize.cp'] = true
		map['hive.optimize.groupby'] = true
		map['hive.optimize.ppd'] = true
		map['hive.optimize.pruner'] = true
		map['hive.optimize.skewjoin'] = false
		map['hive.query.id'] = ''
		map['hive.query.string'] = ''
		map['io.file.buffer.size'] = 4096
		map['io.mapfile.bloom.size'] = 1048576
		map['io.sort.factor'] = 10
		map['io.sort.record.percent'] = 0
		map['io.sort.spill.percent'] = 0.80
		map['mapred.child.java.opts'] = '-Xmx200m'
		map['mapred.compress.map.output'] = false
		map['mapred.fairscheduler.assignmultiple'] = true
		map['mapred.input.format.class'] = 'org.apache.hadoop.hive.ql.io.HiveInputFormat'
		map['mapred.job.name'] = ''
		map['mapred.job.reuse.jvm.num.tasks'] = 1
		map['mapred.job.shuffle.input.buffer.percent'] = 0
		map['mapred.local.dir'] = ''
		map['mapred.map.max.attempts'] = 4
		map['mapred.map.output.compression.codec'] = ''
		map['mapred.map.tasks'] = 2
		map['mapred.reduce.tasks'] = 2
		map['mapred.min.split.size'] = 0
		map['mapred.output.compress'] = false
		map['mapred.reduce.parallel.copies'] = 5
		map['mapred.reduce.tasks.speculative.execution'] = true
		map['mapred.task.timeout'] = 600000
		map['mapred.tasktracker.map.tasks.maximum'] = 2
		map['mapred.tasktracker.reduce.tasks.maximum'] = 2
		map['tasktracker.http.threads'] = 40
	}
}