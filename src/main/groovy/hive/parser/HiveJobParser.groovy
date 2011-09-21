package hive.parser

import util.MD5

class HiveJobParser {
	def parse(f) {
		def queryMap = [:]
			
		def jobStartTime = 0L
		def taskStartTime = 0L
		f.eachLine { line ->
			switch(line) {
				/**
				 * job
				 */
				case ~/(?m)^QueryStart\s+.+/ :
					def m = line =~ /(?m)^QueryStart\s+QUERY_STRING="(.+?)"\s+QUERY_ID="(.+?)"\s+TIME="(.+?)"/
					jobStartTime = Long.parseLong(m[0][3])
				    break
				case ~/(?m)^QueryEnd\s+.+/ :
					def m = line =~ /(?m)^QueryEnd\s+.*QUERY_STRING="(.+?)"\s+QUERY_ID="(.+?)"\s+.+TIME="(.+?)"/
					//if (m.size() < 1 && m[0].size() < 4)
					//	break
					printf '         * %s | %10s | %s | %s\n', 
							new java.text.SimpleDateFormat('HH:mm:ss').format(jobStartTime),
	                        Long.parseLong(m[0][3]) - jobStartTime,
							MD5.md5(m[0][1]),
	                        m[0][1].trim().substring(0, Math.min(50, m[0][1].trim().size()))
				    break
				/**
				 * task
				 */
				case ~/(?m)^TaskStart\s+.+/ :
					def m = line =~ /(?m)^TaskStart\s+.*TASK_NAME="(.+?)"\s+.*TASK_ID="(.+?)"\s+.*TIME="(.+?)"/
					taskStartTime = Long.parseLong(m[0][3])
					break
				case ~/(?m)^TaskEnd\s+.+/ :
					def m = line =~ /(?m)^TaskEnd\s+.*TASK_NAME="(.+?)"\s+.*TASK_ID="(.+?)"\s+.*TIME="(.+?)"/
					printf '                      %10s | %s - %s\n', 
	                        Long.parseLong(m[0][3]) - taskStartTime,
	                        m[0][2],
							m[0][1]
				    break
				case ~/(?m)^.+=.+/ :
					def m = line =~ /(?m)^(.+?)=.+/
					//printf '                    . %s\n', m[0][1]
					break
				default:
				    break
			}
		}
	}
}